<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet"
	href="https://apps.bdimg.com/libs/bootstrap/3.2.0/css/bootstrap.min.css" />
</head>
<body>
<h1>
	Here's your result: 
</h1>

		<table border="1" width="80%">
			<tr>
				<th>Movie ID</th>
				<th>Movie Title</th>
				<th>Genre</th>
			</tr>

			<c:forEach items="${movielist}" var="movie">
				<tr>
					<td>${movie.movieid}</td>
					<td>${movie.title}</td>
					<td>${movie.genre}</td>
				</tr>
			</c:forEach>
			

</body>
</html>
