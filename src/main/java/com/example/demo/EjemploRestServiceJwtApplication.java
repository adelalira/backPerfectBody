package com.example.demo;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.Cita;
import com.example.demo.model.Servicio;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CitaRepo;
import com.example.demo.repository.ServicioRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.CitaService;

@SpringBootApplication
public class EjemploRestServiceJwtApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(EjemploRestServiceJwtApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EjemploRestServiceJwtApplication.class, args);
	}
	
	/**
	 * INYECTAMOS PASSWORDENCODER PARA PODER CIFRAR LA CONTRASEÑA DEL USUARIO Y AL SERVICIO DE LA CITA
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired CitaService citaService;
	
	/**
	 * INTRODUCIMOS UN USUARIO Y UNA CITA POR DEFECTO A ESTE USUARIO
	 * @param repositorioUsers
	 * @param repositorioCitas
	 * @return
	 */
	@Bean
	CommandLineRunner initData (UserRepo repositorioUsers, CitaRepo repositorioCitas) {		
		Usuario usuario1 = new Usuario((long) 1,"Adela","Lira","48123651S","722524571","adela@gmail.com", 
				passwordEncoder.encode("12345"));
		
		return (args) -> {
			repositorioUsers.saveAll(Arrays.asList(usuario1));
			
			//citaService.addCita(usuario1, new Cita(1,LocalDateTime.now(), usuario1));

			
		};
	}
	
	/**
	 * INTRODUCIMOS ENLA BASE DE DATOS LOS SERVICIOS QUE OFRECEN LAS CITAS
	 * @param repositorioServicios
	 * @return
	 */
	@Bean
	CommandLineRunner initDataServicios (ServicioRepo repositorioServicios) {
		return (args) -> {
			repositorioServicios.saveAll(Arrays.asList(
					new Servicio(1, "Manicure", 15.99, "Nails"),
					new Servicio(2,"Pedicure", 21.99, "Nails"),
					new Servicio(3,"Hair removal with wax", 16.99, "Hair removal"),
					new Servicio(4,"Laser hair removal", 89.99, "Hair removal"),
					new Servicio(5,"Eyebrows hair removal with tweezers", 4.99, "Hair removal"),
					new Servicio(6,"Hair removal of Eyebrows with thread", 8.99, "Hair removal"),
					new Servicio(7, "Hair to hair lashes", 34.99, "Eyelash"),
					new Servicio(8, "Eyebrows microblading", 299.99, "Eyebrows"),
					new Servicio(9, "Eyelash microblading", 269.99, "Eyelash"),
					new Servicio(10, "Mustache hair removal with tweezers", 289.99, "Hair removal"),
					new Servicio(11, "Eyelash lifting",69.99, "Eyelash"),
					new Servicio(12,"Eyebrow tint",14.99, "Eyebrows"),
					new Servicio(13,"Curly eyelash",29.99, "Eyelash"),
					new Servicio(14, "Eyebrows design", 22.99, "Eyebrows"),
					new Servicio(15, "Eyelash tint", 19.99, "Eyelash")
					));
			
		};
	}

}
