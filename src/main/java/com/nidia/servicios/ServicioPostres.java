package com.nidia.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nidia.modelos.Postre;
import com.nidia.modelos.Usuario;
import com.nidia.repositorios.RepositorioPostres;

@Service
public class ServicioPostres {

	@Autowired
	private RepositorioPostres repositorioPostres;

	public List<Postre> todosLosPostres() {
		return repositorioPostres.findAll();
	}

	public Postre guardarPostre(Postre postre) {
		return repositorioPostres.save(postre);
	}

	public Postre buscarPostre(Long id) {
		return repositorioPostres.findById(id).orElse(null);
	}

	public List<Postre> postresPorUsuario(Usuario usuario) {
		return repositorioPostres.findByUsuario(usuario);
	}

	public void eliminarPostre(Long id) {
		repositorioPostres.deleteById(id);
	}
	public List<Postre> todosLosPostresOrdenados() {
		return repositorioPostres.findAllByOrderByNombreAsc();
	}

	public List<Postre> buscarPostresPorNombre(String texto) {
		return repositorioPostres.findByNombreContainingIgnoreCaseOrderByNombreAsc(texto);
	}
	public Postre encontrarPostre(Long id) {
	    return repositorioPostres.findById(id).orElse(null);
	}
}