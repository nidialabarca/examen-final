<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Postres</title>
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

		<h2 class="fw-bold mb-3">
		    Bienvenido de vuelta ${usuarioEnSesion.nombre}
		</h2>
		<form action="/postres" method="get" class="d-flex mb-4">
			<input type="text" name="busqueda" value="${busqueda}" class="form-control me-2" placeholder="Buscar postre">
			<button class="btn btn-primary">Filtrar</button>
		</form>
		<div class="row">
	    	<div class="col-md-8">
		        <c:forEach var="postre" items="${listaPostres}">
		            <div class="card mb-3 p-3 bg-light">
		                <div class="row align-items-center">
		
		                    <div class="col-md-4">
		                        <img src="${postre.imagen}" 
		                             alt="${postre.nombre}" 
		                             class="img-fluid rounded">
		                    </div>
		
		                    <div class="col-md-8">
		                        <div class="card-body border rounded bg-white">
		                            <h5 class="card-title fw-bold">
		                                <a href="/postres/${postre.id}" class="text-dark text-decoration-none">
		                                    ${postre.nombre}
		                                </a>
		                            </h5>
		
		                            <p class="card-text">
		                                <strong>Autor</strong>
		                                <span class="ms-5">${postre.usuario.nombre}</span>
		                            </p>
		
		                            <p class="card-text">
		                                <strong>Tiempo de preparación</strong>
		                                <span class="ms-4">${postre.tiempoPreparacion} minutos</span>
		                            </p>
		
		                            <c:if test="${postre.usuario.id == usuarioEnSesion.id}">
		                                <a href="/postres/${postre.id}/editar" class="btn btn-warning btn-sm">
		                                    Editar
		                                </a>
		                            </c:if>
		                        </div>
		                    </div>
		
		                </div>
		            </div>
		        </c:forEach>
		    </div>
		</div>
	</main>
</body>
</html>