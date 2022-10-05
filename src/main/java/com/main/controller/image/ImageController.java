package com.main.controller.image;

import com.main.config.auth.PrincipalDetails;
import com.main.domain.image.Image;
import com.main.domain.member.Member;
import com.main.domain.member.MemberRepository;
import com.main.dto.CMRespDto;
import com.main.dto.MultiResponseDto;
import com.main.dto.image.ImageUploadDto;
import com.main.dto.member.MemberProfileDto;
import com.main.dto.member.MemberProfileDto2;
import com.main.mapper.MemberMapper;
import com.main.service.MemberService;
import com.main.service.comment.image.ImageService;
import com.main.service.comment.image.LikesService;
import com.main.service.comment.image.MemberGetService;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ImageController {
    private final MemberService memberService;

    private final MemberGetService memberGetService;

    private final MemberMapper mapper;


    private final ImageService imageService;

    private final MemberRepository memberRepository;

    private final LikesService likesService;
    // postImageUrl을 받는 것이 아닌 파일을 받아야한다.
    // 그러므로 요청을 위한 Dto가 필요
    // 이미지를 업로드 하기 위해서는 로그인이 되어있는 유저정보가 필요
    // Controller는 사용자의 데이터를 받고 서비스를 호출하기만 하면 된다.
    @PostMapping("api/posts")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        //서비스 호출
        imageService.사진업로드(imageUploadDto,principalDetails);

        return "사진 업로드 완료";

    }


    /**
     *   멤버아이디로 사진 업로드 GET 요청
     */
    @GetMapping("/api/posts/{pageUserId}")
    public MemberProfileDto profile(@PathVariable Long pageUserId,
        @AuthenticationPrincipal PrincipalDetails principalDetails) {
        MemberProfileDto dto = memberGetService.회원프로필(pageUserId,
            principalDetails.getMember().getMemberId());
        return dto;
    }

    /**
     *  PostID로 GET 조회
     */


    @GetMapping("/api/posts2/{postId}") //postId 단일 겟 요청
    public MemberProfileDto2 profile2(@PathVariable Long postId,
        @AuthenticationPrincipal PrincipalDetails principalDetails) {
        MemberProfileDto2 dto2 = memberGetService.회원프로필2(postId,
            principalDetails.getMember().getMemberId());
        return dto2;
    }

    /**
     *  전체유저의 게시물 보이기
     */


    @GetMapping("/api/posts")
    public ResponseEntity getMembers(@Positive @RequestParam(required = false, defaultValue = "1") Integer page,
        @Positive @RequestParam(required = false, defaultValue = "100") Integer size) {
        Page<Image> pageMembers = imageService.findImages(page - 1, size);
        List<Image> images = pageMembers.getContent();
        return new ResponseEntity<>(
            new MultiResponseDto<>(mapper.pageResponseDtos(images),
                pageMembers),
            HttpStatus.OK);
    }

    /**
     *
     *  게시물 삭제
     */

    @DeleteMapping("/api/posts/{postId}") //게시물 삭제
    public ResponseEntity deletePost(
        @PathVariable("postId") @Positive long postId) {
        System.out.println("# delete post");
        imageService.deletePost(postId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     *   사진 좋아요 API
     */

    @PostMapping("/{imageId}/likes")
    public ResponseEntity likes(@PathVariable("imageId") Long imageId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        likesService.likes(imageId,principalDetails.getMember().getMemberId());
        return new ResponseEntity<>(new CMRespDto<>("좋아요성공",null),HttpStatus.CREATED);
    }
}