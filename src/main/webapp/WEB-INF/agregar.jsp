<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar postre</title>
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

		<form:form action="/postres/crear" method="post" modelAttribute="postre">
	
			<div class="mb-3">
				<form:label path="nombre">Nombre</form:label>
				<form:input path="nombre" cssClass="form-control"/>
				<form:errors path="nombre" cssClass="text-danger"/>
			</div>
	
			<div class="mb-3">
				<form:label path="ingredientes">Ingredientes</form:label>
				<form:textarea path="ingredientes" cssClass="form-control"/>
				<form:errors path="ingredientes" cssClass="text-danger"/>
			</div>
	
			<div class="mb-3">
				<form:label path="instrucciones">Instrucciones</form:label>
				<form:textarea path="instrucciones" cssClass="form-control"/>
				<form:errors path="instrucciones" cssClass="text-danger"/>
			</div>
	
			<div class="mb-3">
				<form:label path="imagen">URL de imagen</form:label>
				<form:input path="imagen" cssClass="form-control"/>
				<form:errors path="imagen" cssClass="text-danger"/>
			</div>
	
			<div class="mb-3">
				<form:label path="tiempoPreparacion">Tiempo de preparación en minutos</form:label>
				<form:input path="tiempoPreparacion" type="number" cssClass="form-control"/>
				<form:errors path="tiempoPreparacion" cssClass="text-danger"/>
			</div>
	
			<button class="btn btn-success">
				Guardar postre
			</button>
	
		</form:form>

	</main>

</body>
</html>