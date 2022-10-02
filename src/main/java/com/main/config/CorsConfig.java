package com.main.config;

import javax.swing.text.html.Option;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //config.addAllowedOrigin("http://stbuddy-v2-http.s3-website.ap-northeast-2.amazonaws.com/"); //프론트 엔드포인트
        config.addAllowedOrigin("https://staybuddy.netlify.app/"); //프론트 엔드포인트
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization"); //프론트에서 로컬스토리지에 저장 가능하게 expose
        config.addExposedHeader("memberId");  //프론트에서 로컬스토리지에 저장 가능하게 expose
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}