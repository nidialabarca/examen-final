package com.nidia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nidia.modelos.LoginUsuario;
import com.nidia.modelos.Usuario;
import com.nidia.servicios.ServicioUsuarios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorUsuarios {

	@Autowired
	private ServicioUsuarios ServicioUsuario;

	@GetMapping("/")
	public String raiz() {
		return "redirect:/login";
	}

	@GetMapping("/registro")
	public String registro(@ModelAttribute("usuario") Usuario usuario) {
		return "registro.jsp";
	}

	@PostMapping("/procesa/registro")
	public String procesaRegistro(@Valid @ModelAttribute("usuario") Usuario usuario,
								  BindingResult result,
								  HttpSession session) {
		Usuario usuarioRegistrado = ServicioUsuario.registrarUsuario(usuario, result);

		if (result.hasErrors()) {
			return "registro.jsp";
		}

		session.setAttribute("usuarioEnSesion", usuarioRegistrado);
		return "redirect:/postres";
	}

	@GetMapping("/login")
	public String login(@ModelAttribute("loginUsuario") LoginUsuario loginUsuario) {
		return "login.jsp";
	}

	@PostMapping("/procesa/login")
	public String procesaLogin(
			@Valid @ModelAttribute("loginUsuario") LoginUsuario loginUsuario,
			BindingResult result,
			HttpSession session
	) {
		Usuario usuarioIntentandoLogin = ServicioUsuario.login(loginUsuario, result);

		if (result.hasErrors()) {
			return "login.jsp";
		}

		session.setAttribute("usuarioEnSesion", usuarioIntentandoLogin);
		return "redirect:/postres";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("usuarioEnSesion");
		session.invalidate();
		return "redirect:/login";
	}
}