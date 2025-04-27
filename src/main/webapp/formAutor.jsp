<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.llibreria.models.Autor" %>
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
  <%
    // Verificar si l'objecte 'autor' existeix
    Autor autor = (Autor) request.getAttribute("autor");
    boolean isEdit = autor != null && autor.getId() != null;
  %>

  <h1><%= isEdit ? "Editar Autor" : "Afegir Autor" %></h1>

  <!-- Formulari -->
  <form action="<%= isEdit ? "UpdateAutor" : "InsertAutor" %>" method="post">
    <!-- Camp ocult per a l'ID (només si estem editant) -->
    <c:if test="${autor != null && autor.id != null}">
      <input type="hidden" name="idAutor" value="${autor.id}" />
    </c:if>

    <div class="mb-3">
      <label for="nomAutor" class="form-label">Nom de l'Autor</label>
      <input type="text" class="form-control" id="nomAutor" name="nomAutor"
             value="${autor != null ? autor.nom : ''}" required />
    </div>

    <button type="submit" class="btn btn-success">
      <%= isEdit ? "Actualitzar" : "Crear" %>
    </button>
    <a href="ListAutors" class="btn btn-secondary">Cancel·lar</a>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
