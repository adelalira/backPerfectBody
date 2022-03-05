package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {

				/**
				 * CORS PARA PODER HACER LOGIN
				 */
				registry.addMapping("/auth/login").allowedOrigins("http://localhost:4200")
                .allowedHeaders("GET", "POST", "DELETE", "OPTIONS", "PUT", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                       "Authorization", "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
        
				/**
				 * CORS PARA PODER HACER UN REGISTRO DE UN USUARIO
				 */
				 registry.addMapping("/auth/register").allowedOrigins("http://localhost:4200")
	                .allowedHeaders("GET", "POST", "OPTIONS", "PUT", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				/**
				 * CORS PARA PODER COMPROBAR EL TOKEN
				 */
				 registry.addMapping("/checkToken").allowedOrigins("http://localhost:4200")
	                .allowedHeaders("GET", "POST", "DELETE", "OPTIONS", "PUT", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                       "Authorization", "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				 /**
				  * CORS PARA MOSTRAR TODOS LOS SERVICIOS DE LA APLICACIÓN
				  */
				 registry.addMapping("/servicios").allowedOrigins("https://adelalira.github.io")
	                .allowedHeaders("GET", "POST", "DELETE", "OPTIONS", "PUT", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                       "Authorization", "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				/**
				 * CORS PARA COMPROBAR SI EN LA BASE DE DATOS ESTA EL EMAIL QUE INDICAMOS
				 */
				 registry.addMapping("/auth/user/email/{email}").allowedOrigins("http://localhost:4200")
	                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				/**
				 * CORS PARA MOSTRAR LOS DATOS DEL USUARIO
				 */
				 registry.addMapping("/auth/user").allowedOrigins("http://localhost:4200")
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				/**
				 * CORS PARA MOSTRAR LOS DATOS DEL USUARIO
				 */
				 registry.addMapping("/user").allowedOrigins("http://localhost:4200")
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 				
				/**
				 * CORS PARA AÑADIR CITAS
				 */
				 registry.addMapping("/cita").allowedOrigins("http://localhost:4200")
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				/**
				 * CORS PARA MOSTRAR LA CITA INDICADA
				 */
				 registry.addMapping("/cita/{idC}").allowedOrigins("http://localhost:4200")
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				/**
				 * CORS PARA ELIMINAR LA CITA INDICADA
				 */
				 registry.addMapping("/cita/{idC}/delete").allowedOrigins("http://localhost:4200")
				 .allowedMethods("GET", "POST", "OPTIONS", "PUT","DELETE")
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				/**
				 * CORS PARA AÑADIR A UNA CITA UN SERVICIO
				 */
				 registry.addMapping("/cita/{idC}/lineaCitaServicio").allowedOrigins("http://localhost:4200")
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 				 
	
				/**
				 * CORS PARA QUE SE PUEDA ENVIAR UN EMAIL
				 */
				 registry.addMapping("/sendMail").allowedOrigins("http://localhost:4200")
				 .allowedMethods("GET", "POST", "OPTIONS", "PUT","DELETE")
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				

			}
		};
	}


}
