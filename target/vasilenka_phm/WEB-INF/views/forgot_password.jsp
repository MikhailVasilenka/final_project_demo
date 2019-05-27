<%@ page  contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle  basename="pagecontent" var="rb" scope="request" />
<fmt:setBundle  basename="messages" var="messageRb" scope="request" />
<head>
    <title><fmt:message key="forgot-password.title" bundle="${ rb }" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/w3.css">
    <script src="${pageContext.request.contextPath}/js/project.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>

<!-- Page Content -->

<main role="main" class="container">
  <div class="w3-card-4 w3-animate-opacity">
    <form method="POST" action="${pageContext.request.contextPath}/?command=changePassword" class="w3-container">
          <p></p><label class="w3-text-teal" for="inputLogin"><fmt:message key="label.login" bundle="${ rb }"/></label>
            <input type="text" name="login" class="w3-input w3-border" style="width:200px" id="inputLogin" placeholder="login" required/><p></p>
            <label class="w3-text-teal" for="inputEmail"><fmt:message key="label.email" bundle="${ rb }" /></label>
            <input type="email" name="email" class="w3-input w3-border" style="width:200px" id="inputEmail"
              aria-describedby="emailHelp" placeholder="email" required/><p></p>
      <button type="submit" class="w3-button w3-light-teal w3-border w3-border-teal w3-round-large"><fmt:message
        key="button.send-new-password" bundle="${ rb }" /></button><p></p>
    </form>
    <c:if test="${error=='incorrectData'}">
      <div class="w3-container w3-center">
    <div class="w3-panel w3-pale-red w3-border" role="alert">
    <p>  <fmt:message key="alert.incorrect-login-or-email" bundle="${ messageRb }" /></p>
    </div>
  </div>
    </c:if>
  </div>
</main>
</body>
</html>
