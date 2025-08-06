package com._z.eum.artisan.service;


import com._z.eum.artisan.dto.request.ArtisanRequest;
import com._z.eum.artisan.dto.response.ArtisanResponse;
import com._z.eum.artisan.entity.Artisan;
import com._z.eum.artisan.repository.ArtisanRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ArtisanService {

    private final ArtisanRepository artisanRepository;

    public ArtisanService(ArtisanRepository artisanRepository) {
        this.artisanRepository = artisanRepository;
    }

    //등록
    @Transactional
    public void create(ArtisanRequest artisanRequest) {
        if (artisanRepository.findByEmail(artisanRequest.email()).isPresent()) {
            throw new IllegalArgumentException("이미 장인으로 등록된 이메일입니다.");
        }
        Artisan newArtisan = new Artisan(artisanRequest);

        artisanRepository.save(newArtisan);
    }

    // 전체 장인 조회
    public List<ArtisanResponse> getAllArtisan() {
        return artisanRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // id로 단일 장인 조회
    public ArtisanResponse getArtisanById(Integer id) {
        Artisan artisan = artisanRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("장인을 찾을 수 없습니다. id=" + id));
        return toResponse(artisan);
    }


    // id 기준 삭제
    @Transactional
    public void deleteArtisanById(Integer id){
        if (!artisanRepository.existsById(id)) {
            throw new NoSuchElementException("장인을 찾을 수 없습니다. id=" + id);
        }
        artisanRepository.deleteById(id);
    }

    // 정보 수정
    @Transactional
    public void update(Integer id, ArtisanRequest dto){
        Artisan artisan = artisanRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("장인을 찾을 수 없습니다. id=" + id));

        artisan.updateArtisanInfo(dto.photoUrl(), dto.mainWorks(), dto.biography());
    }

    private ArtisanResponse toResponse(Artisan a) {
        return new ArtisanResponse(
                a.getId(),
                a.getSkillId(),
                a.getName(),
                a.getPhotoUrl(),
                a.getMainWorks(),
                a.getBiography(),
                a.getEmail()
        );
    }


}
