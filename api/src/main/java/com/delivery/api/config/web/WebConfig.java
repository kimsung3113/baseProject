package com.delivery.api.config.web;

import com.delivery.api.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 이 객체가 주입받을 수 있는 이유는 @component이기 때문이다.
    private final AuthorizationInterceptor authorizationInterceptor;

    private final List<String> OPEN_API = List.of(
      "/open-api/**"
    );

    private final List<String> DEFAULT_EXCLUDE = List.of(
        "/",
        "favicon.ico",
        "/error"
    );

    private final List<String> SWAGGER = List.of(
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/v3/api-docs/**"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)   // 인터셉터 등록
                .excludePathPatterns(OPEN_API)
                .excludePathPatterns(DEFAULT_EXCLUDE)
                .excludePathPatterns(SWAGGER);
    }
}
