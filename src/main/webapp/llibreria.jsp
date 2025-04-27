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
    <h1>Benvingut a la Teva Llibreria</h1>
    <div class="list-group">
        <a href="ListAutors" class="list-group-item list-group-item-action">
            Gestionar Autors
        </a>
        <a href="ListGeneres" class="list-group-item list-group-item-action">
            Gestionar Generes
        </a>
        <a href="ListEditorials" class="list-group-item list-group-item-action">
            Gestionar Editorials
        </a>
        <a href="ListLlibres" class="list-group-item list-group-item-action">
            Gestionar Llibres
        </a>
    </div>
</div>

<!-- Scripts de Bootstrap (CDN) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
