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
  <h1>Llista d'Editorials</h1>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>ID</th>
      <th>Nom</th>
      <th>Accions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="editorial" items="${editorials}">
      <tr>
        <td>${editorial.id}</td>
        <td>${editorial.nom}</td>
        <td>
          <a href="GetEditorial?id=${editorial.id}" class="btn btn-warning btn-sm">Editar</a>
          <form action="DeleteEditorial" method="post" class="d-inline">
            <input type="hidden" name="idEditorial" value="${editorial.id}" />
            <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <a href="formEditorial.jsp" class="btn btn-primary">Afegir Editorial</a>
</div>
</body>
</html>
