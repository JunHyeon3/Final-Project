package himedia.campus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${uploadPath}")
	String uploadPath;
	
	/**
	 * 파일 업로드 위치의 최상위 경로가 C드라이브일 경우, 
	 * 로컬에 있는 파일은 브라우저 보안상 접근이 되지않는다.
	 * 따라서, 외부경로에 있는 파일에 접근할 수 있도록 
	 * WebMvcConfigurer 인터페이스의 addResourceHandlers() 메서드를 오버라이딩
	 * 
	 * "/campus/**" 패턴의 URL은 uploadPath 폴더(file:///C:/campus/)를 기준으로 탐색
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/campus/**")
				.addResourceLocations(uploadPath);
	}
	
}
