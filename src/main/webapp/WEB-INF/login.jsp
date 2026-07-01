<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
	<main class="container mt-4">
	    <nav class="d-flex justify-content-between align-items-center mb-5">
	        <h1 class="display-3 fw-bold">Postres</h1>
	
	        <div>
	            <a href="/login" class="btn btn-link text-dark text-decoration-none">Login</a>
	            <a href="/registro" class="btn btn-link text-dark text-decoration-none">Registro</a>
	        </div>
	    </nav>

	    <div class="row">
	        <div class="col-md-5">
	
	            <h2 class="mb-4">Login</h2>
	
	            <form:form action="/procesa/login" method="post" modelAttribute="loginUsuario">
	
	                <div class="mb-3">
	                    <form:label path="correoLogin">Correo</form:label>
	                    <form:input path="correoLogin" cssClass="form-control"/>
	                    <form:errors path="correoLogin" cssClass="text-danger"/>
	                </div>
	
	                <div class="mb-3">
	                    <form:label path="passwordLogin">Contraseña</form:label>
	                    <form:password path="passwordLogin" cssClass="form-control"/>
	                    <form:errors path="passwordLogin" cssClass="text-danger"/>
	                </div>
	
	                <button class="btn btn-primary w-100">
	                    Login
	                </button>
	
	            </form:form>
	
	        </div>
	    </div>

	</main>
</body>
</html>