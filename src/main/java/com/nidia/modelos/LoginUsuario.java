package com.nidia.modelos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//No es una entidad, Ni va a generar una tabla
//Objetivo: obtener la informacion y crear una instancia (para poder compararla con un registro real)
public class LoginUsuario {

	@NotBlank(message="Debe ingresar un correo")
	@Email(message="Correo inválido")
	private String correoLogin;
	
	@NotBlank(message = "Debe ingresar una contraseña")
    private String passwordLogin;
	
	public LoginUsuario() {}

	public String getCorreoLogin() {
		return correoLogin;
	}

	public void setCorreoLogin(String correoLogin) {
		this.correoLogin = correoLogin;
	}

	public String getPasswordLogin() {
		return passwordLogin;
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}
}
