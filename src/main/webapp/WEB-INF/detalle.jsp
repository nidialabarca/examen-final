<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle postre</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
	<main class="container mt-4">
		<nav class="d-flex justify-content-between align-items-center mb-4">
			<h1 class="display-2 fw-bold">Postres</h1>
			<div>
			    <a href="/postres" class="me-4 text-dark text-decoration-none fw-semibold">Postres</a>
			    <a href="/mis-postres" class="me-4 text-dark text-decoration-none fw-semibold">Mis postres</a>
			    <a href="/postres/nuevo" class="me-4 text-dark text-decoration-none fw-semibold">Agregar postre</a>
			    <a href="/logout" class="text-dark text-decoration-none fw-semibold">Logout</a>
			</div>
		</nav>
		<h3>${postre.nombre}</h3>

		<div class="card mt-3" style="max-width: 800px;">
			<img src="${postre.imagen}" class="card-img-top" style="max-height:300px; object-fit:cover;">

			<div class="card-body">
				<p><strong>Autor:</strong> ${postre.usuario.nombre}</p>
				<p><strong>Ingredientes:</strong></p>
				<p>${postre.ingredientes}</p>
	
				<p><strong>Instrucciones:</strong></p>
				<p>${postre.instrucciones}</p>
	
				<p><strong>Tiempo de preparación:</strong> ${postre.tiempoPreparacion} minutos</p>
	
				<c:if test="${postre.usuario.id == usuarioEnSesion.id}">
					<form action="/postres/${postre.id}/eliminar" method="post">
						<button class="btn btn-danger">Eliminar</button>
					</form>
				</c:if>
			</div>
		</div>

	</main>

</body>
</html>