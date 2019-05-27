<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<fmt:setBundle basename="messages" var="messageRb" scope="request"/>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../style/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
    <c:if test="${message=='success'}">
        <div class="w3-container w3-center">
            <div class="w3-panel w3-pale-green w3-border" role="alert">
                <p><fmt:message key="alert.successfully-sending" bundle="${ messageRb }"/></p>
            </div>
        </div>
    </c:if>
    <div class="w3-card-4 w3-animate-opacity">
        <form method="POST" action="${pageContext.request.contextPath}/" class="w3-container">
                <input type="hidden" name="initialCommand" value=${initialCommand}>
                <input type="hidden" name="command" value="login">
                <p>
                    <label class="w3-text-teal" for="inputLogin"><b><fmt:message key="label.login"
                                                                                 bundle="${ rb }"/></b></label>
                    <label class="w3-text-teal"><b><fmt:message key="placeholder.login" bundle="${ rb }"
                                                                var="loginPlaceholder"/></b></label>
                    <input class="w3-input w3-border w3-sand" name="login" type="text" id="inputLogin"
                           placeholder=${loginPlaceholder} required/></p>
                <c:choose>
                    <c:when test="${error=='invalidLogin'}">
                        <div class="w3-container w3-center">
                            <div class="w3-panel w3-pale-red w3-border" role="alert">
                                <p><fmt:message key="alert.invalid-login" bundle="${ messageRb }"/></p>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
                <p>
                <p>
                    <label for="inputPassword" class="w3-text-teal"><b><fmt:message key="label.password"
                                                                                    bundle="${ rb }"/></b></label>
                    <label class="w3-text-teal"><b><fmt:message key="placeholder.password" bundle="${ rb }"
                                                                var="passwordPlaceholder"/></b></label>
                    <input class="w3-input w3-border w3-sand" name="password" type="password" id="inputPassword"
                           placeholder=${passwordPlaceholder} required/></p>
                <p>
                    <c:if test="${error=='incorrectAuthentication'}">
                    <div class="w3-container w3-center">
                        <div class="w3-panel w3-pale-red w3-border" role="alert">
                <p><fmt:message key="alert.incorrect-authentication" bundle="${ messageRb }"/></p>


    </c:if>
    <button class="w3-btn w3-teal" type="submit"><fmt:message key="button.login"
                                                              bundle="${ rb }"/></button>
    <p style="color:#0000ff"><u><a class="underlineHover"
                                   href="${pageContext.request.contextPath}/?command=toForgotPassword"><fmt:message
            key="link.forgot-password" bundle="${ rb }"/></a>
    </u>
    </p>
    <p><a href="${pageContext.request.contextPath}/?command=toSignUp" style="color:#0000ff"><u><fmt:message
            key="link.sign-up" bundle="${rb}"/></u></a></p>
        </form>
        </div>
</main>
</body>
</html>
