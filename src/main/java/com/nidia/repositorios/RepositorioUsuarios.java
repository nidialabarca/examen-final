package com.nidia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nidia.modelos.Usuario;

@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario, Long> {
	
	Usuario findByCorreo(String correo); //Select * from usuarios where email = <email>

}