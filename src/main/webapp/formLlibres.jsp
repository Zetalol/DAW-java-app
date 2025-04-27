<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.llibreria.models.Llibre" %>
<!DOCTYPE html>
<html>
<head>
  <title>Gestió de Llibres</title>
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
    Llibre llibre = (Llibre) request.getAttribute("llibre");
    boolean isEdit = (llibre != null && llibre.getId() != null);
  %>

  <h1><%= isEdit ? "Editar Llibre" : "Afegir Llibre" %></h1>

  <form action="<%= isEdit ? "UpdateLlibre" : "InsertLlibre" %>" method="post">

    <!-- ID en cas d'edició -->
    <c:if test="${llibre != null && llibre.id != null}">
      <input type="hidden" name="idLlibre" value="${llibre.id}" />
    </c:if>

    <!-- Títol -->
    <div class="mb-3">
      <label for="titol" class="form-label">Títol</label>
      <input type="text" class="form-control" id="titol" name="titol"
             value="<c:out value='${llibre.titol}'/>" required />
    </div>

    <!-- ISBN -->
    <div class="mb-3">
      <label for="isbn" class="form-label">ISBN</label>
      <input type="text" class="form-control" id="isbn" name="isbn"
             value="<c:out value='${llibre.isbn}'/>" required />
    </div>

    <!-- Any de publicació -->
    <div class="mb-3">
      <label for="any_publicacio" class="form-label">Any de Publicació</label>
      <input type="number" class="form-control" id="any_publicacio" name="any_publicacio"
             value="<c:out value='${llibre.anyPublicacio}'/>" required />
    </div>

    <!-- Editorial (select) -->
    <div class="mb-3">
      <label for="idEditorial" class="form-label">Editorial</label>
      <select id="idEditorial" name="idEditorial" class="form-select" required>
        <c:forEach var="editorial" items="${editorials}">
          <option value="${editorial.id}"
                  <c:if test="${llibre != null && llibre.idEditorial == editorial.id}">
                    selected
                  </c:if>>
              ${editorial.nom}
          </option>
        </c:forEach>
      </select>
    </div>

    <!-- Autors (checkboxes) -->
    <div class="mb-3">
      <label for="idAutor" class="form-label">Autor</label>
      <select id="idAutor" name="idAutor" class="form-select" required>
        <c:forEach var="autor" items="${autors}">
          <option value="${autor.id}"
                  <c:if test="${llibre != null && llibre.autorId == autor.id}">
                    selected
                  </c:if>>
              ${autor.nom}
          </option>
        </c:forEach>
      </select>
    </div>


    <!-- Gèneres (checkboxes) -->
    <div class="mb-3">
      <label for="idGenere" class="form-label">Gènere</label>
      <select id="idGenere" name="idGenere" class="form-select" required>
        <c:forEach var="genere" items="${generes}">
          <option value="${genere.id}"
                  <c:if test="${llibre != null && llibre.genereId == genere.id}">
                    selected
                  </c:if>>
              ${genere.nom}
          </option>
        </c:forEach>
      </select>
    </div>


    <!-- Botó submit -->
    <button type="submit" class="btn btn-success">
      <%= isEdit ? "Actualitzar" : "Crear" %>
    </button>
    <a href="ListLlibres" class="btn btn-secondary">Cancel·lar</a>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
