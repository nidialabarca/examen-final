package com.nidia.servicios;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.nidia.modelos.LoginUsuario;
import com.nidia.modelos.Usuario;
import com.nidia.repositorios.RepositorioUsuarios;

@Service
public class ServicioUsuarios {

	@Autowired
	private RepositorioUsuarios repositorioUsuarios;
	
	//Método que me guarde el nuevo registro SIEMPRE Y CUANDO cumpla con todas las validaciones (incluyendo 2 validaciones manuales)
	public Usuario registrarUsuario(Usuario nuevoUsuario, BindingResult result) {
		//Comparar las contraseñas
		String password = nuevoUsuario.getPassword();
		String confirmarPassword = nuevoUsuario.getConfirmarPassword();
		if(!password.equals(confirmarPassword)) {
			//Si son diferentes password y confirmación
			result.rejectValue("confirmarPassword", "Matches", "Los passwords no coinciden"); //Crear un error
			//(atributo, clave, mensaje)
		}
		
		//Revisar que el email no esté registrando
		String correo = nuevoUsuario.getCorreo();
		//Objeto Usuario (registro de mi bd) o null
		Usuario existeUsuario = repositorioUsuarios.findByCorreo(correo);
		if(existeUsuario != null) {
			//Ese email ya existe en BD
			result.rejectValue("correo", "Unique", "El correo ya está registrado en el sistema, elija otro");
		}
		
		//Si no hay errores, entonces guardamos el usuario
		if(result.hasErrors()) {
			return null;
		} else {
			//No hay errores
			//Hasheo la contraseña
			String passwordHasheado = BCrypt.hashpw(password, BCrypt.gensalt());
			nuevoUsuario.setPassword(passwordHasheado); //Establezco el password hasheado como pw
			
			return repositorioUsuarios.save(nuevoUsuario);
		}
	}
	
	public Usuario buscarPorCorreo(String correo) {
		return repositorioUsuarios.findByCorreo(correo);
	}
	
	//Método que haga las validaciones del inicio de sesión
	public Usuario login(LoginUsuario usuarioIniciandoSesion, BindingResult result) {
		//Revisar que el email exista en mi BD
		String correo = usuarioIniciandoSesion.getCorreoLogin(); //Obtenemos el email con el que intenta iniciar sesión 
		Usuario existeUsuario = repositorioUsuarios.findByCorreo(correo); //Objeto usuario (registro bd) o null
		if(existeUsuario == null) {
			//No existe ningún usuario con ese correo
			result.rejectValue("correoLogin", "Unique", "Correo no registrado");
		} else if(! BCrypt.checkpw(usuarioIniciandoSesion.getPasswordLogin(), existeUsuario.getPassword())) {
			//No coincide el password ingresado con el de la BD
			result.rejectValue("passwordLogin", "Matches", "Password incorrecto");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			return existeUsuario;
		}
	}
	
}