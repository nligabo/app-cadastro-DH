package br.com.app_cadastro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(false)// que sempre especificará o formato padrão primeiro 
		.ignoreAcceptHeader(false)// não ignora o formato especificado no cabeçalho
		.defaultContentType(MediaType.APPLICATION_JSON) // formato padrão
		.mediaType("json", MediaType.APPLICATION_JSON) // formato suportado pela API
		.mediaType("xml", MediaType.APPLICATION_XML);
	}
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("GET", "PUT", "POST", "DELETE", "PATH", "OPTIONS", "TRACE", "HEAD", "CONNECT");
	}
	
}
