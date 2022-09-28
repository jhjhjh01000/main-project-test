package com.main.exception;

import com.main.dto.CMRespDto;
import java.util.Map;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerException {


    @ExceptionHandler(CustomValidationException.class)   //RuntimeException이 발생하는 모든 것들을 받아옴
    public CMRespDto<?> validationException(CustomValidationException e){
        return new CMRespDto(e.getMessage(),e.getErrorMap());
    }   // <Map<String,String>  -> 이부분에 <?> 해도 됨


    @ExceptionHandler(CustomValidationApiException.class)   //RuntimeException이 발생하는 모든 것들을 받아옴
    public CMRespDto<?> validationApiException(CustomValidationApiException e){
        return new CMRespDto(e.getMessage(),e.getErrorMap());
    }   // <Map<String,String>  -> 이부분에 <?> 해도 됨

}


