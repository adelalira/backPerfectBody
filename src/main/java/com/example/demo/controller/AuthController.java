package com.example.demo.controller;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.ApiError;
import com.example.demo.error.EmailNotExistException;
import com.example.demo.error.EmailPasswordException;
import com.example.demo.error.PasswordException;
import com.example.demo.error.UserExistException;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.model.Cita;
import com.example.demo.model.LoginCredentials;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UserRepo;
import com.example.demo.security.JWTUtil;
import com.example.demo.service.CitaService;
import com.example.demo.service.UserService;


@RestController
public class AuthController {

	/**
	 * INYECTAMOS EL REPOSITORIO DEL USUARIO, LA CLASE JWTUTIL PERTENECIENTE AL PAQUETE DE SEGURIDAD
	 * Y DOS CLASES PROPIAS DE JAVA
	 */
    @Autowired private UserRepo userRepo;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder passwordEncoder;
    /**
     * INYECYAMOS EL SERVICIO DEL USUARIO
     */
    @Autowired private UserService userService;

    /**
     * POSTMAPPING QUE NOS PERMITE REGISTRAR USUARIOS EN NUESTRA APLICACIÓN
     * @param user
     * @param body
     * @return
     * SI EL EMAIL QUE ES USADO PARA EL REGISTRO YA ESTA EN LA BASE DE DATOS NOS DARA UNA EXCEPCIÓN
     */
    @PostMapping("/auth/register")
    public Map<String, Object> registerHandler(@RequestBody Usuario user, Usuario body){
    	
    	if (userRepo.findByEmail(user.getEmail())==null) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        user = userRepo.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return Collections.singletonMap("access_token", token);
    	}
	    else {
			throw new UserExistException();
		}
    }

    /**
     * POSTMAPPING QUE RECIBE LOS DATOS INTRODUCIDOS POR EL CLIENTE.
     * @param body
     * @return
     * SI LOS DATOS INTRODUCIDOS POR EL CLIENTE EXISTEN GENERARA UN TOKEN Y LO MANDARA AL FRONT END.
     * 
     * SI LOS DATOS INTRODUCIDOS POR EL CLIENTE NO EXISTEN NOS DARA DOS TIPOS DE EXCEPCIONES, COMPRUEBA SI 
     * EL DATO INCORRECTO ES EL EMAIL, LA CONTRASEÑA U AMBOS.
     */
    @PostMapping("/auth/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getEmail());

            return Collections.singletonMap("access_token", token);
        }catch (AuthenticationException authExc){
        	if(this.userRepo.getEmail(body.getEmail()) != null) {
        		throw new PasswordException();
        	}
        	else {
        		throw new EmailPasswordException();
        	}
            	
        }
    }

    
    /**
     * GETMAPPING QUE COMPRUEBA SI EL TOKEN ES VALIDO
     * @return
     * @throws Exception
     */
    @GetMapping("/checkToken")
	public ResponseEntity<String> checkToken() throws Exception {
    	try {
    		return ResponseEntity.ok("");
		}
		catch(Exception e){
			throw new Exception();
		}
	}

    
    /**
     * GETMAPPING QUE COMPRUEBA SI EL EMAIL QUE INTRODUCIMOS EN EL REGISTRO ESTA EN LA BASE DE DATOS
     * @param email
     * @return
     * DEVUELVE EL USURIO SI ES ENCONTRADO.
     */
    @GetMapping("/auth/user/email/{email}")
    public Usuario compruebaEmail( @PathVariable String email) { 	
    	if(userService.findByEmail(email)!=null) {
    		return userService.findByEmail(email);   	
    	}
    	else {
    		throw new EmailNotExistException(email);
    	}
    	
    }
    
    
    /**
     * GETMAPPING QUE ENVIA AL FRONT END LA ID DEL USUARIO QUE SE LOGEA
     * @return
     * ID DEL USUARIO
     */
    @GetMapping("/auth/user")
    public Long getUserDetails(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByEmail(email).getId();
    }
    
    
 
    /**
     * EXCEPCIÓN QUE NOS ADVIERTE QUE ESE EMAIL NO EXISTE
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(EmailNotExistException.class)
    public ResponseEntity<ApiError> idUserError(EmailNotExistException ex) throws Exception {
    	ApiError e = new ApiError();
    	e.setEstado(HttpStatus.NOT_FOUND);
    	e.setMensaje(ex.getMessage());
    	e.setFecha(LocalDateTime.now());
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	} 
    
   /**
    * EXCEPCIÓN QUE NOS ADVIERTE QUE EL USUARIO INTRODUCIDO YA EXISTE
    * @param ex
    * @return
    * @throws Exception
    */
    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ApiError> idUserError(UserExistException ex) throws Exception {
    	ApiError e = new ApiError();
    	e.setEstado(HttpStatus.BAD_REQUEST);
    	e.setMensaje(ex.getMessage());
    	e.setFecha(LocalDateTime.now());
    	
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	} 
	
    
    /**
     * EXCEPCIÓN QUE CONTROLA SI LA CONTRASEÑA ES INCORRECTA
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<ApiError> passwordError(PasswordException ex) throws Exception {
    	ApiError e = new ApiError();
    	e.setEstado(HttpStatus.BAD_REQUEST);
    	e.setMensaje(ex.getMessage());
    	e.setFecha(LocalDateTime.now());
    	
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	} 
    
    
    /**
     * EXCEPCIÓN QUE CONTROLA SI LA CONTRASEÑA O EL EMAIL SON INCORRECTOS
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(EmailPasswordException.class)
    public ResponseEntity<ApiError> emailPasswordError(EmailPasswordException ex) throws Exception {
    	ApiError e = new ApiError();
    	e.setEstado(HttpStatus.BAD_REQUEST);
    	e.setMensaje(ex.getMessage());
    	e.setFecha(LocalDateTime.now());
    	
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	} 
    
    
}
