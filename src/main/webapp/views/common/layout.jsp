<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${mainPageTitle}</title>
 <link rel="shortcut icon" type="image/x-icon" href="${cp}/resources/images/favicon.ico">
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/layout.css">
</head>
<body>
	<div id="wrap">
		<header class="header">
			<jsp:include page="/views/common/header.jsp"/>
		</header>
		<main class="main">
			<jsp:include page="${mainPage}"/>
		</main>
		<footer class="footer">
			<jsp:include page="/views/common/footer.jsp"/>
		</footer>
	</div>
</body>
</html>