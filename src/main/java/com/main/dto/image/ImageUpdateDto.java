package com.main.dto.image;

import com.main.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageUpdateDto {

    private String caption;
    private String username;


    //엔티티 -> dto
    public static ImageUpdateDto toDto(Image image){
        return new ImageUpdateDto(
                image.getCaption(),
                image.getMember().getUsername());
    }
}
