package com.nidia.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nidia.modelos.Postre;
import com.nidia.modelos.Usuario;

public interface RepositorioPostres extends JpaRepository<Postre, Long> {
	List<Postre> findAll();
	List<Postre> findByUsuario(Usuario usuario);
	
	List<Postre> findAllByOrderByNombreAsc();

	List<Postre> findByNombreContainingIgnoreCaseOrderByNombreAsc(String texto);
}