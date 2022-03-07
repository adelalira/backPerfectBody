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

				String link = "https://adelalira.github.io/";
				
				/**
				 * CORS PARA PODER HACER LOGIN
				 */
				registry.addMapping("/auth/login").allowedOrigins(link)
                .allowedHeaders("GET", "POST", "DELETE", "OPTIONS", "PUT", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                       "Authorization", "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
        
				/**
				 * CORS PARA PODER HACER UN REGISTRO DE UN USUARIO
				 */
				 registry.addMapping("/auth/register").allowedOrigins(link)
	                .allowedHeaders("GET", "POST", "OPTIONS", "PUT", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
				/**
				 * CORS PARA PODER COMPROBAR EL TOKEN
				 */
				 registry.addMapping("/checkToken").allowedOrigins(link)
	                .allowedHeaders("GET", "POST", "DELETE", "OPTIONS", "PUT", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                       "Authorization", "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				 /**
				  * CORS PARA MOSTRAR TODOS LOS SERVICIOS DE LA APLICACIÓN
				  */
				 registry.addMapping("/servicios").allowedOrigins(link)
	                .allowedHeaders("GET", "POST", "DELETE", "OPTIONS", "PUT", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                       "Authorization", "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				/**
				 * CORS PARA COMPROBAR SI EN LA BASE DE DATOS ESTA EL EMAIL QUE INDICAMOS
				 */
				 registry.addMapping("/auth/user/email/{email}").allowedOrigins(link)
	                .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				/**
				 * CORS PARA MOSTRAR LOS DATOS DEL USUARIO
				 */
				 registry.addMapping("/auth/user").allowedOrigins(link)
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				/**
				 * CORS PARA MOSTRAR LOS DATOS DEL USUARIO
				 */
				 registry.addMapping("/user").allowedOrigins(link)
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 				
				/**
				 * CORS PARA AÑADIR CITAS
				 */
				 registry.addMapping("/cita").allowedOrigins(link)
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				/**
				 * CORS PARA MOSTRAR LA CITA INDICADA
				 */
				 registry.addMapping("/cita/{idC}").allowedOrigins(link)
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				/**
				 * CORS PARA ELIMINAR LA CITA INDICADA
				 */
				 registry.addMapping("/cita/{idC}/delete").allowedOrigins(link)
				 .allowedMethods("GET", "POST", "OPTIONS", "PUT","DELETE")
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 
				/**
				 * CORS PARA AÑADIR A UNA CITA UN SERVICIO
				 */
				 registry.addMapping("/cita/{idC}/lineaCitaServicio").allowedOrigins(link)
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				 				 
	
				/**
				 * CORS PARA QUE SE PUEDA ENVIAR UN EMAIL
				 */
				 registry.addMapping("/sendMail").allowedOrigins(link)
				 .allowedMethods("GET", "POST", "OPTIONS", "PUT","DELETE")
				 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
	                        "Access-Control-Request-Headers")
	                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				

				 /**
					 * CORS PARA RECIBIR LAS LINEAS DE LA CITA
					 */
					 registry.addMapping("/cita/{idC}").allowedOrigins(link)
					 .allowedMethods("GET", "POST", "OPTIONS", "PUT","DELETE")
					 .allowedHeaders("GET", "POST", "OPTIONS", "PUT","DELETE", "Content-Type", "Authorization", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
		                        "Access-Control-Request-Headers")
		                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
				
			}
		};
	}


}
