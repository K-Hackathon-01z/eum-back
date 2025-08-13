package com._z.eum.governmentSupport.service;


import com._z.eum.governmentSupport.api.YouthPolicyApiClient;
import com._z.eum.governmentSupport.dto.GovernmentSupportResponse;
import com._z.eum.governmentSupport.dto.YouthPolicyResponse;
import com._z.eum.governmentSupport.entity.GovernmentSupport;
import com._z.eum.governmentSupport.entity.UserSupportResult;
import com._z.eum.governmentSupport.repository.GovernmentSupportRepository;
import com._z.eum.governmentSupport.repository.UserSupportResultRepository;
import com._z.eum.user.entity.User;
import com._z.eum.user.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GovernmentSupportService {

    private final GovernmentSupportRepository govRepo;
    private final UserSupportResultRepository userSupportRepo;
    private final YouthPolicyApiClient apiClient;
    private final UserService userService;

    public GovernmentSupportService(GovernmentSupportRepository govRepo,
                                    UserSupportResultRepository userSupportRepo,
                                    YouthPolicyApiClient apiClient,
                                    UserService userService
                                    ) {
        this.govRepo = govRepo;
        this.userSupportRepo = userSupportRepo;
        this.apiClient = apiClient;
        this.userService = userService;
    }
    public List<GovernmentSupportResponse> recommendSupports(Integer userId) {
        System.out.println("[추천 시작] userId: " + userId);
        User user = userService.getUserById(userId);
        System.out.println("[유저 정보] 나이: " + user.getAge() + ", 주소: " + user.getAddress());
        int age = user.getAge();
        String location = user.getAddress();

        Optional<UserSupportResult> recent = userSupportRepo.findTopByUserIdOrderByRecommendedAtDesc(userId);

        System.out.println("[추천 이력] 최근 이력 존재 여부: " + recent.isPresent());
        boolean needUpdate = recent.isEmpty() ||
                recent.get().getRecommendedAt().isBefore(LocalDateTime.now().minusDays(7));

        if (needUpdate) {
            List<YouthPolicyResponse> fetched = apiClient.fetchYouthPolicies();
            System.out.println("[정책 수신] 수신된 정책 개수: " + fetched.size());
            saveOrUpdateGovernmentSupport(fetched);

            List<GovernmentSupport> filtered = govRepo.findAll().stream()
                    .filter(s -> ageMatches(age, s.getTargetAge()))
                    .toList();
            System.out.println("[정책 필터링] 추천 대상 정책 개수: " + filtered.size());
            for (GovernmentSupport support : filtered) {
                UserSupportResult result = new UserSupportResult(
                        userId,
                        support,
                        LocalDateTime.now(),
                        false
                );
                userSupportRepo.save(result);
            }

        }

        return userSupportRepo.findByUserId(userId).stream()
                .map(r -> new GovernmentSupportResponse(r.getSupport()))
                .toList();
    }

    private void saveOrUpdateGovernmentSupport(List<YouthPolicyResponse> dtos) {
        for (YouthPolicyResponse dto : dtos) {
            govRepo.findBySourceApi(dto.sourceApi()).ifPresentOrElse(
                    existing -> existing.update(
                            dto.title(),
                            dto.description(),
                            dto.category(),
                            dto.targetAge(),
                            dto.targetLocation(),
                            dto.url()
                    ),
                    () -> {
                        GovernmentSupport newSupport = new GovernmentSupport(
                                dto.title(),
                                dto.description(),
                                dto.category(),
                                dto.targetAge(),
                                dto.targetLocation(),
                                dto.url(),
                                dto.sourceApi()
                        );
                        govRepo.save(newSupport);
                    }
            );
        }
    }


    private boolean ageMatches(int userAge, String targetAge) {
        if (targetAge == null || targetAge.isBlank()) return true;
        try {
            if (targetAge.contains("~")) {
                String[] parts = targetAge.replaceAll("[^0-9~]", "").split("~");
                int min = Integer.parseInt(parts[0]);
                int max = Integer.parseInt(parts[1]);
                return userAge >= min && userAge <= max;
            } else if (targetAge.contains("이상")) {
                int min = Integer.parseInt(targetAge.replaceAll("[^0-9]", ""));
                return userAge >= min;
            }
        } catch (Exception ignored) {}
        return true;
    }
}
