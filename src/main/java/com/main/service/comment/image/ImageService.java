package com.main.service.comment.image;

import com.main.config.auth.PrincipalDetails;
import com.main.domain.image.Image;
import com.main.domain.image.ImageRepository;
import com.main.domain.image.Likes;
import com.main.domain.image.LikesRepository;
import com.main.domain.member.Member;
import com.main.dto.image.ImageUpdateDto;
import com.main.dto.image.ImageUploadDto;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Data
@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID();   //유일한 파일명을 만들어 DB에 저장하기 위한 방법
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();

        // 실제 어디에 저장이 될건지 경로를 지정해주는 코드
        // C드라이브의 upload폴더에 imageFilename 원본으로 파일받는 코드
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        Long SnsId = imageUploadDto.getSnsId();

        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (Exception e) {

        }

        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getMember(),
            principalDetails.getEmail(), SnsId);
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
     *  게시글 수정
     */


    @Transactional
    public ImageUpdateDto update(Long id,ImageUpdateDto imageUpdateDto){
        Image image = imageRepository.findById(id).orElseThrow( ()->{
            return new IllegalArgumentException("아이디를 찾을 수 없습니다.");
                });

        image.setCaption(imageUpdateDto.getCaption());
        return ImageUpdateDto.toDto(image);
    }


    /**
     * 개별 게시물 조회
     */
    @Transactional(readOnly = true)
    public Image getImage(Long id){
        Image image = imageRepository.findById(id).orElseThrow(
                ()-> { return new IllegalArgumentException("회원아이디를 찾을 수 없습니다.");
                });
        return image;
    }



}
