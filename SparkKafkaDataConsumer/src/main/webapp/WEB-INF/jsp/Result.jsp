<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Result</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
		document.write('<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css" />');
	</script>
</head>
<body>

	<div class="container">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Branch</th>
					<th>Location</th>
					<th>Branch count By Location</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${result}" var="element">
					<tr>
						<td>${element.branch}</td>
						<td>${element.location}</td>
						<td>${element.numberofbranch}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>