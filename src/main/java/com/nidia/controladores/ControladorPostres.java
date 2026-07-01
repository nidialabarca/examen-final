package com.nidia.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nidia.modelos.Postre;
import com.nidia.modelos.Usuario;
import com.nidia.servicios.ServicioPostres;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorPostres {

	@Autowired
	private ServicioPostres servicioPostres;

	@GetMapping("/postres")
	public String mostrarPostres(@RequestParam(value = "busqueda", required = false) String busqueda,
								 HttpSession session, 
								 Model model) {

		/*===== Revisar que el usuario haya iniciado sesión =====*/
		if(session.getAttribute("usuarioEnSesion") == null) {
			//No ha iniciado sesión
			return "redirect:/login"; //redirijo al inicio de sesión
		}
		/*===== =====*/

		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		List<Postre> postres;

		if(busqueda == null) {
			postres = servicioPostres.todosLosPostresOrdenados();
		} else {
			postres = servicioPostres.buscarPostresPorNombre(busqueda);
		}

		model.addAttribute("listaPostres", postres);
		model.addAttribute("usuarioEnSesion", usuarioEnSesion);
		model.addAttribute("busqueda", busqueda);

		return "postres.jsp";
	}
	@GetMapping("/postres/nuevo")
	public String nuevoPostre(
			@ModelAttribute("postre") Postre postre,
			HttpSession session) {

		/*===== Revisar que el usuario haya iniciado sesión =====*/
		if(session.getAttribute("usuarioEnSesion") == null) {
			//No ha iniciado sesión
			return "redirect:/login"; //redirijo al inicio de sesión
		}
		/*===== =====*/

		return "agregar.jsp";
	}
	@PostMapping("/postres/crear")
	public String guardarPostre(
			@Valid @ModelAttribute("postre") Postre postre,
			BindingResult result,
			HttpSession session) {

		if (session.getAttribute("usuarioEnSesion") == null) {
			return "redirect:/login";
		}

		if (result.hasErrors()) {
			return "agregar.jsp";
		}

		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		postre.setUsuario(usuarioEnSesion);

		servicioPostres.guardarPostre(postre);

		return "redirect:/postres";
	}
	@GetMapping("/postres/{id}")
	public String detallePostre(@PathVariable("id") Long id,
							    Model model,
							    HttpSession session) {

		/*===== Revisar que el usuario haya iniciado sesión =====*/
		if(session.getAttribute("usuarioEnSesion") == null) {
			//No ha iniciado sesión
			return "redirect:/login"; //redirijo al inicio de sesión
		}
		/*===== =====*/
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		Postre postre = servicioPostres.buscarPostre(id);

		model.addAttribute("postre", postre);
		model.addAttribute("usuarioEnSesion", usuarioEnSesion);

		return "detalle.jsp";
	}
	@PostMapping("/postres/{id}/eliminar")
	public String eliminarPostre(@PathVariable("id") Long id,
								 HttpSession session) {

		/*===== Revisar que el usuario haya iniciado sesión =====*/
		if(session.getAttribute("usuarioEnSesion") == null) {
			//No ha iniciado sesión
			return "redirect:/login"; //redirijo al inicio de sesión
		}
		/*===== =====*/

		servicioPostres.eliminarPostre(id);

		return "redirect:/postres";
	}
	@GetMapping("/postres/{id}/editar")
	public String editarPostre(@PathVariable("id") Long id,
							   Model model,
							   HttpSession session) {

		/*===== Revisar que el usuario haya iniciado sesión =====*/
		if(session.getAttribute("usuarioEnSesion") == null) {
			//No ha iniciado sesión
			return "redirect:/login"; //redirijo al inicio de sesión
		}
		/*===== =====*/

		Postre postre = servicioPostres.buscarPostre(id);

		model.addAttribute("postre", postre);

		return "editar.jsp";
	}
	@PutMapping("/postres/actualizar/{id}")
	public String actualizarPostre(
	        @PathVariable("id") Long id,
	        @Valid @ModelAttribute("postre") Postre postre,
	        BindingResult result,
	        HttpSession session,
	        Model model) {

		/*===== Revisar que el usuario haya iniciado sesión =====*/
		if(session.getAttribute("usuarioEnSesion") == null) {
			//No ha iniciado sesión
			return "redirect:/login"; //redirijo al inicio de sesión
		}
		/*===== =====*/

	    if(result.hasErrors()) {
	        return "editar.jsp";
	    }

	    Postre postreOriginal = servicioPostres.encontrarPostre(id);

	    postre.setId(id);
	    postre.setUsuario(postreOriginal.getUsuario());

	    servicioPostres.guardarPostre(postre);

	    return "redirect:/postres";
	}
	
	
	@GetMapping("/mis-postres")
	public String misPostres(HttpSession session,
							 Model model) {

		/*===== Revisar que el usuario haya iniciado sesión =====*/
		if(session.getAttribute("usuarioEnSesion") == null) {
			//No ha iniciado sesión
			return "redirect:/login"; //redirijo al inicio de sesión
		}
		/*===== =====*/

		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		model.addAttribute("listaPostres",servicioPostres.postresPorUsuario(usuarioEnSesion));

		return "misPostres.jsp";
	}
	
}