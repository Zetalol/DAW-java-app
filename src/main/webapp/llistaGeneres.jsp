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


<div class="container mt-5">
  <h1>Llista de Gèneres</h1>
  <!-- Missatge d'error si és present -->
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
    <!-- Iteració sobre la llista de gèneres -->
    <c:forEach var="genere" items="${generes}">
      <tr>
        <td><c:out value="${genere.id}" /></td>
        <td><c:out value="${genere.nom}" /></td>
        <td>
          <!-- Enllaç per editar el gènere -->
          <a href="GetGenere?id=${genere.id}" class="btn btn-warning btn-sm">Editar</a>
          <!-- Formulari per eliminar el gènere -->
          <form action="DeleteGenere" method="post" class="d-inline">
            <input type="hidden" name="idGenere" value="${genere.id}" />
            <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <!-- Botó per afegir un nou gènere -->
  <a href="formGenere.jsp" class="btn btn-primary">Afegir Gènere</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
