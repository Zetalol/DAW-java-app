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
  <h1><c:if test="${not empty genere}">Editar</c:if> Gènere</h1>
  <form action="<c:if test='${not empty genere}'>UpdateGenere</c:if><c:if test='${empty genere}'>InsertGenere</c:if>" method="post">

    <!-- Si és edició, enviar l'id -->
    <c:if test="${not empty genere}">
      <input type="hidden" name="idGenere" value="${genere.id}" />
    </c:if>

    <div class="mb-3">
      <label for="nomGenere" class="form-label">Nom del Gènere</label>
      <input type="text" class="form-control" id="nomGenere" name="nomGenere"
             value="<c:out value='${genere.nom}' />" required />
    </div>

    <button type="submit" class="btn btn-success">
      <c:if test="${not empty genere}">Actualitzar</c:if><c:if test="${empty genere}">Crear</c:if>
    </button>
    <a href="ListGeneres" class="btn btn-secondary">Cancel·lar</a>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
6