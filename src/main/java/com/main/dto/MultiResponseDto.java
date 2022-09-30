//package com.main.dto;
//
//import java.util.List;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.data.domain.Page;
//
//@Getter
//public class MultiResponseDto<T> {
//    private List<T> data;
//    private PageInfo pageInfo;
//
//    public MultiResponseDto(List<T> data, Page page) {
//        this.data = data;
//        this.pageInfo = new PageInfo(page.getNumber() + 1,
//            page.getSize(), page.getTotalElements(), page.getTotalPages());
//    }
//}