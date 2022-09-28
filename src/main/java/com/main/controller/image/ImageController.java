package com.main.controller.image;

import com.main.config.auth.PrincipalDetails;
import com.main.dto.image.ImageUploadDto;
import com.main.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ImageController {


    private final ImageService imageService;

    // postImageUrl을 받는 것이 아닌 파일을 받아야한다.
    // 그러므로 요청을 위한 Dto가 필요
    // 이미지를 업로드 하기 위해서는 로그인이 되어있는 유저정보가 필요
    // Controller는 사용자의 데이터를 받고 서비스를 호출하기만 하면 된다.
    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        //서비스 호출
        imageService.사진업로드(imageUploadDto,principalDetails);

        return "사진 업로드 완료";

    }


}