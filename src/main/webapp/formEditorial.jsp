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
  <h1><c:choose>
    <c:when test="${editorial != null}">Editar Editorial</c:when>
    <c:otherwise>Afegir Editorial</c:otherwise>
  </c:choose></h1>
  <form action="${editorial != null ? 'UpdateEditorial' : 'InsertEditorial'}" method="post">
    <c:if test="${editorial != null}">
      <input type="hidden" name="idEditorial" value="${editorial.id}" />
    </c:if>
    <div class="mb-3">
      <label for="nomEditorial" class="form-label">Nom de l'Editorial</label>
      <input type="text" class="form-control" id="nomEditorial" name="nomEditorial"
             value="${editorial != null ? editorial.nom : ''}" required />
    </div>
    <button type="submit" class="btn btn-success">Guardar</button>
    <a href="ListEditorials" class="btn btn-secondary">Cancel·lar</a>
  </form>
</div>
</body>
</html>
