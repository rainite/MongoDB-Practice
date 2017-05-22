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
	Welcome to MovieLens Searching Page!  
</h1>



			<form method="post" action="<c:url value="/search"/>" target="_parent"
				class="form-inline">
				
				<select class="form-control" name="selection">
  				<option value = "movieid">MovieID</option>
  				<option value = "title">Title</option>
  				<option value = "genres">Genres</option>
				</select>
				<input id="input" type="text" name="desc" class="form-control"
					size="50" /> &nbsp;&nbsp; 
				<input type="submit" name="button"
					value="Search" id="Search" class="btn btn-primary" class="button" />				
			</form>

</body>
</html>
