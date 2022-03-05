package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.error.LineaCitaServicioNotExistException;
import com.example.demo.model.Cita;
import com.example.demo.model.LineaCitaServicios;
import com.example.demo.model.Servicio;
import com.example.demo.repository.CitaRepo;
import com.example.demo.repository.LineaCitaServicioRepo;
import com.example.demo.repository.ServicioRepo;

@Service
public class LineaCitaServicioService {
	
	/**
	 * INYECTAMOS LOS REPOSITORIOS DE CITA, SERVICIO Y LINEACITASERVICIO
	 */
	@Autowired
	private LineaCitaServicioRepo repositorio;
	
	@Autowired
	private  CitaRepo citaRepo;
	
	@Autowired
	private  ServicioRepo servicioRepo;

	/**
	 * 
	 * METODO PARA CREAR UNA LINEACITASERVICIO, ES DECIR, AÑADIR UN SERVICIO A LA CITA
	 * @param idS
	 * @param idC
	 * @return
	 */
	public Cita addServicio(LineaCitaServicios idS, Long idC) {
		
		if (citaRepo.existsById(idC)) {

			Cita c = citaRepo.findById(idC).orElse(null);
			Servicio s  = servicioRepo.findById(idS.getId()).orElse(null);
			
			LineaCitaServicios ln= new LineaCitaServicios();
			
			ln.setServicio(s);
			
			if(s.getPrecio()>200){
				ln.setDescuento(0.05);
				ln.getServicio().setPrecio(s.getPrecio()-(s.getPrecio()*ln.getDescuento())); //COMPROBAR SI LO CALCULA BIEN
				
			}
			
			c.getListaServicios().add(ln);
			
		
			repositorio.save(ln);
			
			return citaRepo.save(c);
		} else {
			return null;
		}
		
	}

	/**
	 * METODO PARA EDITAR LA LINEACITASERVICIO
	 * @param linea
	 * @param idC
	 * @param idLP
	 * @return
	 */
	public LineaCitaServicios editLineaCitaServicio(LineaCitaServicios linea, Long idC, Long idLP) {
		
		LineaCitaServicios ln = repositorio.getById(idLP);
		Servicio s  = servicioRepo.findById(linea.getServicio().getId()).orElse(null);

		if (repositorio.existsById(idLP)) {


			if(s.getPrecio()>200 && ln.getDescuento()==0){
				ln.setDescuento(0.05);
				ln.getServicio().setPrecio(s.getPrecio()-(s.getPrecio()*ln.getDescuento())); //COMPROBAR SI LO CALCULA BIEN
				
			}
			ln.setServicio(s);
		
			return repositorio.save(ln);
		} else {
			return null;
		}
	}

	/**
	 * METODO QUE ELIMINA LA LINEACITASERVICIO DE LA CITA
	 * @param idC
	 * @param idLP
	 * @return
	 */
	public Cita delete(Long idC, Long idLP) {
		if (repositorio.existsById(idLP)) {
			
			LineaCitaServicios ln = repositorio.findById(idLP).orElse(null);
			Cita c = citaRepo.getById(idC);
			
			c.getListaServicios().remove(ln);
			citaRepo.save(c);
			
			repositorio.delete(repositorio.getById(idLP));
			
			return c;
		} else {
			return null;
		}
	}


	

}
