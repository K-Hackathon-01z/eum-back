
package com._z.eum.matching.service;

import com._z.eum.artisan.entity.Artisan;
import com._z.eum.artisan.repository.ArtisanRepository;
import com._z.eum.matching.dto.ArtisanMessageResponse;
import com._z.eum.matching.dto.MatchingCreateRequest;
import com._z.eum.matching.entity.MatchingRequest;
import com._z.eum.matching.entity.Notification;
import com._z.eum.matching.repository.MatchingRequestRepository;
import com._z.eum.matching.repository.NotificationRepository;
import com._z.eum.user.entity.User;
import com._z.eum.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchingRequestService {

    private final MatchingRequestRepository matchingRepo;
    private final NotificationRepository notifRepo;
    private final UserRepository userRepo;
    private final ArtisanRepository artisanRepo;


    public MatchingRequestService( MatchingRequestRepository matchingRepo,
                                   NotificationRepository notifRepo,
                                   UserRepository userRepo,
                                   ArtisanRepository artisanRepo) {
        this.artisanRepo = artisanRepo;
        this.matchingRepo = matchingRepo;
        this.notifRepo = notifRepo;
        this.userRepo = userRepo;
    }



    @Transactional
    public Integer create(MatchingCreateRequest dto) {

        // 존재 검증
        User user = userRepo.findById(dto.userId())
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 사용자 없음"));

        Artisan artisan = artisanRepo.findById(dto.artisanId())
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 장인 없음"));



       MatchingRequest mr =
                new com._z.eum.matching.entity.MatchingRequest(
                        user.getId(),
                        artisan.getId(),
                        dto.message(),
                        dto.isAnonymous()
                );

        matchingRepo.save(mr);


        String title = "새로운 매칭 요청";
        String body  = "새로운 사용자로부터 매칭 요청이 도착했습니다.";
        Notification noti = new Notification(
                artisan.getId(),
                mr.getId(),
                "NEW_MESSAGE",
                title,
                body
        );
        notifRepo.save(noti);

        //푸시 알림

        return mr.getId();
    }

    @Transactional(readOnly = true)
    public List<ArtisanMessageResponse> listForArtisan(Integer artisanId, boolean unreadOnly) {
        List<com._z.eum.matching.entity.MatchingRequest> list = unreadOnly
                ? matchingRepo.findByArtisanIdAndReadFalseOrderByRequestDateDesc(artisanId)
                : matchingRepo.findByArtisanIdOrderByRequestDateDesc(artisanId);

        return list.stream().map(mr -> {
            String name = null, email = null;
            Integer age = null;
            if (!mr.isAnonymous()) {
                User u = userRepo.findById(mr.getUserId()).orElse(null);
                if (u != null) {
                    name = u.getName();
                    email = u.getEmail();
                    age = u.getAge();
                }
            }
            return new ArtisanMessageResponse(
                    mr.getId(),
                    mr.isAnonymous(),
                    mr.getMessage(),
                    mr.getRequestDate(),
                    mr.isRead(),
                    mr.getReadAt(),
                    name, email, age
            );
        }).toList();
    }

    @Transactional(readOnly = true)
    public List<ArtisanMessageResponse> listForUser(Integer userId) {
        List<MatchingRequest> list = matchingRepo.findByUserIdOrderByRequestDateDesc(userId);

        return list.stream().map(mr -> new ArtisanMessageResponse(
                mr.getId(),
                mr.isAnonymous(),
                mr.getMessage(),
                mr.getRequestDate(),
                mr.isRead(),
                mr.getReadAt(),
                null, null, null // 사용자 본인이 보는 거라 sender 정보 필요 없음
        )).toList();
    }


    @Transactional
    public void markRead(Integer artisanId, Integer messageId) {
        com._z.eum.matching.entity.MatchingRequest mr = matchingRepo.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("쪽지 없음"));

        if (!mr.getArtisanId().equals(artisanId)) {
            throw new IllegalArgumentException("권한 없음");
        }


        mr.markRead();

        // 관련 알림 읽음 처리
        notifRepo.findByArtisanIdAndReadFalseOrderByCreatedAtDesc(artisanId).stream()
                .filter(n -> n.getMatchingRequestId().equals(messageId))
                .findFirst()
                .ifPresent(Notification::markRead);
    }
}
