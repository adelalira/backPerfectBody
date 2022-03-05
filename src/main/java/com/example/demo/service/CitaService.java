package com.example.demo.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.error.CitaExistException;
import com.example.demo.error.EmailPasswordException;
import com.example.demo.error.PasswordException;
import com.example.demo.model.Cita;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CitaRepo;
import com.example.demo.repository.UserRepo;

/**
 * SERVICIO DE CITAS
 * @author adela
 *
 */
@Service
public class CitaService {

	/**
	 * INYECTAMOS LOS REPOSITORIOS DE CITA Y USUARIO
	 */
	@Autowired CitaRepo repositorio;
	@Autowired UserRepo usuarioRepo;
	
	/**
	 * METODO PARA  CREAR UNA CITA
	 * @param result
	 * @param datosCita
	 * @return
	 * SI LA CITA EXISTIERA YA SALTARIA UNA EXCEPCIÓN
	 */
	public Cita addCita(Usuario result, Cita datosCita) {
		
		Usuario  user = usuarioRepo.findById(result.getId()).orElse(null);
		
		if(this.repositorio.getDia(datosCita.getDia()) != null) {
    		throw new CitaExistException(); 
    	}
    	else {
    		if(datosCita.getDia().getMinute()==0) {
    			Cita cita = new Cita();
        		
        		cita.setDia(datosCita.getDia());
        		cita.setUsuario(user);
        	    		
        		user.getListaCitas().add(cita);
        		repositorio.save(cita);
        		usuarioRepo.save(user);
        	
        		return cita;
    		}
    		else {
    			throw new CitaExistException(); 
    		}
    		
    		
    	}
	}

	
	
	/**
	 * EDITA LOS DATOS DE LA CITA SELECCIONADA
	 * @param c
	 * @param idC
	 * @return
	 */
	public Cita editCitas(Cita c, Long idC) {
		if (repositorio.existsById(idC)) {
			
			Cita cita = repositorio.findById(idC).orElse(null);
			
			cita.setDia(c.getDia());
		
			return repositorio.save(cita);
		} else {
			return null;
		}
	}

	
	/**
	 * DEVUELVE LA LISTA DE CITAS
	 * @return
	 */
	public List<Cita> findAll() {
		return repositorio.findAll();
	}


	/**
	 * METODO QUE ELIMINA LAS CITAS
	 * @param id
	 * @param idC
	 * @return
	 */
	public Cita delete(Long id, Long idC) {
		if (repositorio.existsById(idC)) {
		
			Cita c = repositorio.findById(idC).get();
			
			
			Usuario user = usuarioRepo.findById(id).orElse(null);
			
			user.getListaCitas().remove(c);
			
			usuarioRepo.save(user);
			repositorio.deleteById(idC);
			
			return c;
		} else {
			return null;
		}
	}
	
	
	

}
