<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mis postres</title>
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

		<h3>Estos son tus postres agregados</h3>

		<div class="row">
    		<div class="col-md-8">

		        <c:forEach var="postre" items="${listaPostres}">
		
		            <div class="card mb-4 p-3 bg-light">
		
		                <div class="row align-items-center">
		
		                    <div class="col-md-3">
		                        <img src="${postre.imagen}"
		                             alt="${postre.nombre}"
		                             class="img-fluid rounded">
		                    </div>
		
		                    <div class="col-md-9">
		
		                        <div class="card-body border rounded bg-white">
		
		                            <h5 class="fw-bold mb-3">
		                                ${postre.nombre}
		                            </h5>
		
		                            <p class="text-secondary mb-3">
		                                Tiempo de preparación
		                                <strong class="ms-3">
		                                    ${postre.tiempoPreparacion} minutos
		                                </strong>
		                            </p>
		
		                            <div class="d-flex justify-content-end align-items-center">
		
		                                <a href="/postres/${postre.id}"
		                                   class="btn btn-primary btn-sm me-3">
		                                    Detalle
		                                </a>
		
		                                <a href="/postres/${postre.id}/editar"
		                                   class="text-dark text-decoration-none">
		                                    <i class="bi bi-pencil"></i>
		                                </a>
		
		                            </div>
		
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