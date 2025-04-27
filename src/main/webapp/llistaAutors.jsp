<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>La Meva Llibreria</title>
  <!-- Enllaç a Bootstrap 5 (CDN) -->
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="llibreria.jsp">Llibreria</a>
  </div>
</nav>

<body>


<div class="container mt-5">
  <h1>Llista d'Autors</h1>
  <!-- Missatge d'error -->
  <c:if test="${not empty error}">
    <div class="alert alert-danger">
      <c:out value="${error}" />
    </div>
  </c:if>
  <table class="table table-striped table-hover">
    <thead>
    <tr>
      <th>ID</th>
      <th>Nom</th>
      <th>Accions</th>
    </tr>
    </thead>
    <tbody>
    <!-- Iteració sobre la llista d'autors -->
    <c:forEach var="autor" items="${autors}">
      <tr>
        <td><c:out value="${autor.id}" /></td>
        <td><c:out value="${autor.nom}" /></td>
        <td>
          <!-- Enllaç per editar l'autor -->
          <a href="GetAutor?id=${autor.id}" class="btn btn-warning btn-sm">Editar</a>
          <!-- Formulari per eliminar l'autor -->
          <form action="DeleteAutor" method="post" class="d-inline">
            <input type="hidden" name="idAutor" value="${autor.id}" />
            <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <!-- Botó per afegir un nou autor -->
  <a href="formAutor.jsp" class="btn btn-primary">Afegir Autor</a>
</div>

<!-- Scripts de Bootstrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
