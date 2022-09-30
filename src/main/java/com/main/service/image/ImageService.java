package com.main.service.image;

import com.main.config.auth.PrincipalDetails;
import com.main.domain.image.Image;
import com.main.domain.image.ImageRepository;
import com.main.dto.image.ImageUploadDto;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();   //유일한 파일명을 만들어 DB에 저장하기 위한 방법
        String imageFileName =uuid + "_" +imageUploadDto.getFile().getOriginalFilename();

        // 실제 어디에 저장이 될건지 경로를 지정해주는 코드
        // C드라이브의 upload폴더에 imageFilename 원본으로 파일받는 코드
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        try{
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
        }catch (Exception e){

        }

        Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getMember(),
            principalDetails.getEmail());
        Image imageEntity = imageRepository.save(image);

    }
}
