package devdungeon.config;

import devdungeon.interceptor.ApiAuthInterceptor;
import devdungeon.interceptor.ApiLoginInterceptor;
import devdungeon.interceptor.AuthInterceptor;
import devdungeon.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;
    private final AuthInterceptor authInterceptor;

    private final ApiLoginInterceptor apiLoginInterceptor;
    private final ApiAuthInterceptor apiAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor);
        registry.addInterceptor(authInterceptor);

        registry.addInterceptor(apiLoginInterceptor);
        registry.addInterceptor(apiAuthInterceptor);
    }
}
