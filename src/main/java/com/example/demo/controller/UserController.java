package com.example.demo.controller;


import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.ApiError;
import com.example.demo.error.CitaExistException;
import com.example.demo.error.CitaNotFoundException;
import com.example.demo.error.LineaCitaServicioNotExistException;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.model.Cita;
import com.example.demo.model.LineaCitaServicios;
import com.example.demo.model.Mensaje;
import com.example.demo.model.Servicio;
import com.example.demo.model.SmtpMailSender;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CitaRepo;
import com.example.demo.repository.ServicioRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.CitaService;
import com.example.demo.service.LineaCitaServicioService;
import com.example.demo.service.UserService;


@RestController
public class UserController {

	/**
	 * INYECCIÓN DE TODOS LOS REPOSITORIOS Y SERVICIOS QUE PODEMOS NECESITAR
	 */
    @Autowired private UserRepo userRepo;
    
    @Autowired private CitaRepo citaRepo;
    
    @Autowired private ServicioRepo servicioRepo;
    
    @Autowired private UserService userService;
    
    @Autowired private CitaService citaService;
        
    @Autowired private LineaCitaServicioService lineaCitaServicioService;
    
    @Autowired private SmtpMailSender smtpMailSender;

   
    
    /**
     * METODO GETMAPPING QUE MUESTRA TODOS LOS SERVICIOS QUE OFRECE LA APLICACIÓN
     * @return
     */
    @GetMapping("/servicios")
    public List<Servicio> getServicios() {
		return servicioRepo.findAll();
    }

   
    /**
     * ETMAPPING QUE MUESTRA LOS DATOS DEL USUARIO
     * @return
     * SI EL USUARIO NO EXISTE DEVUELVE UNA EXCEPCIÓN QUE LO NOTIFICA
     */
    @GetMapping("/user")
    public ResponseEntity<Usuario> getUser() {
    	
    	String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario result =  userRepo.findByEmail(email);
        
    	if(result==null) {
    		throw new UserNotFoundException(result.getId());
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.OK).body(result);
    	}
    	
    	
	}
    
    
    /**
     * METODO GETMAPPING QUE NOS MUESTRA TODAS LAS CITAS DEL USUARIO
     * @return
     */
    @GetMapping("/cita")
	public ResponseEntity<List<Cita>> findAllCitas() {
    	
    	String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario result =  userRepo.findByEmail(email);

        return ResponseEntity.ok(result.getListaCitas());
	}
   
    
    /**
     * METODO POSTMAPPING QUE AÑADE CITAS AL USUARIO
     * @param id
     * @return
     * SI EL USUARIO NO EXISTE NOS DA UNA EXCEPCIÓN ADVIRTIENDOLO
     */
    @PostMapping("/cita")
    public Cita addCita(@RequestBody Cita datosCita) {
    	
    	String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario result =  userRepo.findByEmail(email);
   
    	if(result==null) {
			throw new UserNotFoundException(result.getId());
		}
		else {
			return this.citaService.addCita(result, datosCita);
		}
	}
    
    /**
     * METODO PUTMAPPING QUE EDITA LAS CITAS, PUDIENDO ASI MODIFICAR LA FECHA Y HORA DE LA CITA
     * @param c
     * @param id
     * @return
     * SI LA CITA NO EXISTIERA NOS TIRARIA UNA EXCEPCIÓN 
     */
    @PutMapping("/cita/{idC}")
	public Cita edit(@RequestBody Cita c, @PathVariable Long idC) {
    	
    	
    	Cita result= citaService.editCitas(c, idC);
		
		if (result == null) {
			throw new CitaNotFoundException(idC);
		} else {
			return result;
		}
	}
    
   /**
    * METODO POSTMAPPING QUE AÑADE LAS LINEASCITASERVICIO EN LA CITA INDICADA CON SU ID
    * @param lineaCitaServicio
    * @param idC
    * @return
    * SI LA CITA NO EXISTIERA NOS TIRARIA UNA EXCEPCIÓN 
    */
    @PostMapping("/cita/{idC}/lineaCitaServicio")
	public Cita addLineaCitaServicio(@RequestBody LineaCitaServicios idS, @PathVariable Long idC) {

    	Cita result= lineaCitaServicioService.addServicio(idS, idC);
    	
		if (result == null) {
			throw new CitaNotFoundException(idC);
		} else {
			return result;
		}

	}

    /**
     * METODO PUTMAPPING QUE EDITA LAS LINEASCITASERVICIO, PUDIENDO ASI MODIFICAR EL SERVICIO DE LA
     * CITA INDICADA E INCLUIR DESCUENTOS EN EL CASO DE QUE FUERA NECESARIO
     * @param linea
     * @param idC
     * @param idLP
     * @return
     * EN EL CASO DE QUE LA CITA NO EXISTIERA NOS SALTARIA LA EXCEPCIÓN CORRESPONDIENTE NOTIFICANDO QUE
     * LA CITA CON ESA ID NO EXISTE
     * 
     * EN EL CASO DE QUE LA LINEACITASERVICIO NO EXISTIERA NOS SALTARIA LA EXCEPCIÓN CORRESPONDIENTE NOTIFICANDO QUE
     * LA LINEACITASERVICIO CON ESA ID NO EXISTE
     */
    @PutMapping("/cita/{idC}/lineaCitaServicio/{idLP}")
	public LineaCitaServicios editLineaCitaServicio(@RequestBody LineaCitaServicios linea, @PathVariable Long idC, @PathVariable Long idLP) {
    	LineaCitaServicios result= lineaCitaServicioService.editLineaCitaServicio(linea, idC,idLP);
		Cita cita = citaRepo.findById(idC).orElse(null);
    	if(cita==null) {
    		throw new CitaNotFoundException(idC);
    	}
    	else {
    		if (result == null) {
    			throw new LineaCitaServicioNotExistException(idLP);
    		} else {
    			return result;
    		}
    	}
	}
	    

    
	
	/**
	 * METODO DELETEMAPPING QUE ELIMINA LA CITA CON LA ID QUE INDIQUEMOS
	 * @param id
	 * @return
	 * SI LA CITA NO EXISTIERA NOS TIRARIA UNA EXCEPCIÓN 
	 */
	@DeleteMapping("/cita/{idC}/delete")
	public ResponseEntity<?> deleteCita( @PathVariable Long idC) {
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id =  userRepo.findByEmail(email).getId();
        
		Cita result =  citaService.delete(id, idC);
		
		if (result == null) {
			throw new CitaNotFoundException(idC);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
		
	/**
	 * METODO DELETEMAPPING QUE BORRA LAS LINEASCITAPEDIDO QUE LE INDIQUEMOS
	 * @param idC
	 * @param idLP
	 * @return
	 * EN EL CASO DE QUE LA CITA NO EXISTIERA NOS SALTARIA LA EXCEPCIÓN CORRESPONDIENTE NOTIFICANDO QUE
     * LA CITA CON ESA ID NO EXISTE
     * 
     * EN EL CASO DE QUE LA LINEACITASERVICIO NO EXISTIERA NOS SALTARIA LA EXCEPCIÓN CORRESPONDIENTE NOTIFICANDO QUE
     * LA LINEACITASERVICIO CON ESA ID NO EXISTE
	 */
	@DeleteMapping("/cita/{idC}/lineaCitaServicio/{idLP}/delete")
	public ResponseEntity<?> deleteLineaCitaServicio(@PathVariable Long idC, @PathVariable Long idLP) {
		
		Cita cita = citaRepo.findById(idC).orElse(null);
    	if(cita==null) {
    		throw new CitaNotFoundException(idC);
    	}
    	else {
    		Cita result = lineaCitaServicioService.delete(idC, idLP);
    		if (result == null) {
    			throw new LineaCitaServicioNotExistException(idLP);
    		} else {
    			return ResponseEntity.noContent().build();
    		}
    	}	
	}
	
    
    /**
     * ENVIO DE EMAIL DE CONTACTO A LA APLICACION, EN ESTE CASO HEMOS SELECCIONDO EL MIO PRONCIPAL
     * @return 
     * @throws MessagingException 
     */
    @PostMapping("/sendMail")
    public void sendEmail(@RequestBody Mensaje datos) throws MessagingException {
    	datos.setTo("aalira.96@gmail.com");
    	
		smtpMailSender.send(datos.getTo(), datos.getSubject(), datos.getText());
	}  
	
	/**
	 * MANEJO DE ERRORES. CONTROL DE LA EXCEPCIÓN EN EL CASO DE QUE EL USUARIO CON LA ID INTRODUCIDA
	 * NO EXISTA
	 * @param ex
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> idUserError(UserNotFoundException ex) throws Exception {
    	ApiError e = new ApiError();
    	e.setEstado(HttpStatus.NOT_FOUND);
    	e.setMensaje(ex.getMessage());
    	e.setFecha(LocalDateTime.now());
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	} 
	
	/**
	 * MANEJO DE ERRORES. CONTROL DE LA EXCEPCIÓN EN EL CASO DE QUE LA CITA CON LA ID INTRODUCIDA
	 * NO EXISTA
	 * @param ex
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(CitaNotFoundException.class)
    public ResponseEntity<ApiError> idCitaError(CitaNotFoundException ex) throws Exception {
    	ApiError e = new ApiError();
    	e.setEstado(HttpStatus.NOT_FOUND);
    	e.setMensaje(ex.getMessage());
    	e.setFecha(LocalDateTime.now());
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	} 	
	
	/**
	 * MANEJO DE ERRORES. CONTROL DE LA EXCEPCIÓN EN EL CASO DE QUE LA LINEACITASERVICIO CON LA ID 
	 * INTRODUCIDA NO EXISTA
	 * @param ex
	 * @return
	 * @throws Exception
	 */
    @ExceptionHandler(LineaCitaServicioNotExistException.class)
    public ResponseEntity<ApiError> idLineaCitaServicioError(LineaCitaServicioNotExistException ex) throws Exception {
    	ApiError e = new ApiError();
    	e.setEstado(HttpStatus.NOT_FOUND);
    	e.setMensaje(ex.getMessage());
    	e.setFecha(LocalDateTime.now());
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	} 
    
    /**
	 * MANEJO DE ERRORES. CONTROL DE LA EXCEPCIÓN EN EL CASO DE QUE LA LINEACITASERVICIO CON LA ID 
	 * INTRODUCIDA NO EXISTA
	 * @param ex
	 * @return
	 * @throws Exception
	 */
    @ExceptionHandler(CitaExistException.class)
    public ResponseEntity<ApiError> idLineaCitaServicioError(CitaExistException ex) throws Exception {
    	ApiError e = new ApiError();
    	e.setEstado(HttpStatus.NOT_FOUND);
    	e.setMensaje(ex.getMessage());
    	e.setFecha(LocalDateTime.now());
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	} 
    
   
    
}