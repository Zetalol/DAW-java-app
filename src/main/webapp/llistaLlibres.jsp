<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Llista de Llibres</title>
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
  <h1>Llista de Llibres</h1>

  <table class="table table-striped table-hover">
    <thead>
    <tr>
      <th>ID</th>
      <th>Títol</th>
      <th>ISBN</th>
      <th>Any Publicació</th>
      <th>Editorial</th>
      <th>Autors</th>
      <th>Gèneres</th>
      <th>Accions</th>
    </tr>
    </thead>
    <tbody>
    <!-- Iterar sobre els llibres -->
    <c:forEach var="llibre" items="${llibres}">
      <tr>
        <td><c:out value="${llibre.id}"/></td>
        <td><c:out value="${llibre.titol}"/></td>
        <td><c:out value="${llibre.isbn}"/></td>
        <td><c:out value="${llibre.anyPublicacio}"/></td>
        <td><c:out value="${llibre.editorialNom}"/></td>
        <td><c:out value="${llibre.autorNom}"/></td>
        <td><c:out value="${llibre.genereNom}"/></td>
        <td>
          <!-- Botó per editar -->
          <a href="GetLlibre?id=${llibre.id}" class="btn btn-warning btn-sm">Editar</a>

          <!-- Formulari per eliminar -->
          <form action="DeleteLlibre" method="post" class="d-inline">
            <input type="hidden" name="idLlibre" value="${llibre.id}" />
            <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <!-- Botó per afegir un nou llibre -->
  <a href="GetLlibre" class="btn btn-primary">Afegir Llibre</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
