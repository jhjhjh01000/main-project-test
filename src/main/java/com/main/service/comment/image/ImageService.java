package com.main.service.comment.image;

import com.main.config.auth.PrincipalDetails;
import com.main.domain.image.Image;
import com.main.domain.image.ImageRepository;
import com.main.domain.image.LikeImage;
import com.main.domain.image.LikeImageRepository;
import com.main.domain.member.Member;
import com.main.dto.image.ImageUploadDto;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    private final LikeImageRepository likeImageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();   //유일한 파일명을 만들어 DB에 저장하기 위한 방법
        String imageFileName =uuid + "_" +imageUploadDto.getFile().getOriginalFilename();

        // 실제 어디에 저장이 될건지 경로를 지정해주는 코드
        // C드라이브의 upload폴더에 imageFilename 원본으로 파일받는 코드
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        Long SnsId = imageUploadDto.getSnsId();

        try{
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
        }catch (Exception e){

        }

        Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getMember(),
            principalDetails.getEmail(),SnsId);
        Image imageEntity = imageRepository.save(image);

    }

    public Page<Image> findImages(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());
        return imageRepository.findAll(pageRequest);
    }
    @Transactional

    public void deletePost(Long postId) {
        imageRepository.deleteById(postId);

    }

    /**
     *  좋아요 기능
     */


    // imageId와 내 User정보가 매개변수로 들어감
    @Transactional
    public String likeImage(Long id, Member member){
        Image image = imageRepository.findById(id).orElseThrow( () -> {
            throw new IllegalStateException("해당 게시글을 찾을 수 없습니다.");
        });



        if (likeImageRepository.findByImageAndMember(image,member) == null){
            // 좋아요를 누른적이 없다면 LikeImage 생성 후 좋아요 처리
            image.setLiked(image.getLiked()+1);
            LikeImage likeImage = new LikeImage(image,member); // 생성자를 만들어 true 처리
            likeImageRepository.save(likeImage);
            return "좋아요 처리 완료";
        } else{
            // 좋아요를 누른적 있다면 취소 처리 후 테이블에서 삭제
            LikeImage likeImage = likeImageRepository.findByImageAndMember(image,member);
            likeImage.unLikeImage(image);
            likeImageRepository.delete(likeImage);
            return "좋아요 취소";
        }

    }
}
