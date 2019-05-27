<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<fmt:setBundle basename="messages" var="messageRb" scope="request"/>
<head>
    <title><fmt:message key="sign-up.title" bundle="${ rb }"/></title>
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
            <form method="POST" action="${pageContext.request.contextPath}/?command=signUp" class="w3-container">
                <p>
                    <label class="w3-text-teal" for="inputLogin"><b><fmt:message key="label.login"
                                                                                 bundle="${ rb }"/></b></label>
                    <input class="w3-input w3-border w3-sand" name="login" type="text" id="inputLogin" placeholder=
                    <fmt:message key="placeholder.login" bundle="${ rb }"/> required/></p>
                <c:choose>
                    <c:when test="${error=='invalidLogin'}">
                        <div class="w3-container w3-center">
                            <div class="w3-panel w3-pale-red w3-border" role="alert">
                                <p><fmt:message key="alert.invalid-login" bundle="${ messageRb }"/></p>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${error=='reservedLogin'}">
                        <div class="w3-container w3-center">
                            <div class="w3-panel w3-pale-red w3-border" role="alert">
                                <p><fmt:message key="alert.reserved-login" bundle="${ messageRb }"/></p>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
                <p>
                <p>
                    <label for="inputPassword" class="w3-text-teal"><b><fmt:message key="label.password"
                                                                                    bundle="${ rb }"/></b></label>
                    <fmt:message key="placeholder.password" bundle="${ rb }" var="passwordPlaceholder"/>
                    <input class="w3-input w3-border w3-sand" name="password" type="password" id="inputPassword"
                           placeholder=${passwordPlaceholder} required/></p>
                <c:if test="${error=='invalidPassword'}">
                    <div class="w3-container w3-center">
                        <div class="w3-panel w3-pale-red w3-border" role="alert">
                            <p><fmt:message key="alert.invalid-password" bundle="${ messageRb }"/></p>
                        </div>
                    </div>
                </c:if>
                <p>
                <p>
                <p>
                    <label class="w3-text-teal" for="inputName"><b><fmt:message key="label.name" bundle="${ rb }"/></b></label>
                    <input class="w3-input w3-border w3-sand" name="name" type="text" id="inputName"
                           placeholder=
                           <fmt:message key="placeholder.name" bundle="${ rb }"/> required/></p>
                <c:if test="${error=='invalidName'}">
                    <div class="w3-container w3-center">
                        <div class="w3-panel w3-pale-red w3-border" role="alert">
                            <p><fmt:message key="alert.invalid-name" bundle="${ messageRb }"/></p>
                        </div>
                    </div>
                </c:if>
                <p>
                    <label class="w3-text-teal" for="inputLastName"><b><fmt:message key="label.lastname"
                                                                                    bundle="${ rb }"/></b></label>
                    <input class="w3-input w3-border w3-sand" name="lastName" type="text" id="inputLastName"
                           placeholder=
                           <fmt:message key="placeholder.lastname" bundle="${ rb }"/> required/></p>
                <c:if test="${error=='invalidLastName'}">
                    <div class="w3-container w3-center">
                        <div class="w3-panel w3-pale-red w3-border" role="alert">
                            <p><fmt:message key="alert.invalid-surname" bundle="${ messageRb }"/></p>
                        </div>
                    </div>
                </c:if>
                <p>
                <p>
                    <label class="w3-text-teal" for="inputEmail"><b><fmt:message key="placeholder.email"
                                                                                 bundle="${ rb }"/></b></label>
                    <input class="w3-input w3-border w3-sand" name="email" type="email" id="inputEmail"
                           aria-describedby="emailHelp"
                           placeholder="example@mail.com" required/></p>
                <c:choose>
                    <c:when test="${error=='invalidEmail'}">
                        <div class="w3-container w3-center">
                            <div class="w3-panel w3-pale-red w3-border" role="alert">
                                <p><fmt:message key="alert.invalid-email" bundle="${ messageRb }"/></p>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${error=='reservedEmail'}">
                        <div class="w3-container w3-center">
                            <div class="w3-panel w3-pale-red w3-border" role="alert">
                                <p><fmt:message key="alert.reserved-email" bundle="${ messageRb }"/></p>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
                <p>
                <p>
                    <label class="w3-text-teal" for="inputAddress"><b><fmt:message key="label.address"
                                                                                   bundle="${ rb }"/></b></label>
                    <input class="w3-input w3-border w3-sand" name="address" type="text" id="inputAddress"
                           placeholder=
                           <fmt:message key="placeholder.address" bundle="${ rb }"/> required/></p>
                <c:if test="${error=='invalidAddress'}">
                    <div class="w3-container w3-center">
                        <div class="w3-panel w3-pale-red w3-border" role="alert">
                            <p><fmt:message key="alert.invalid-address" bundle="${ messageRb }"/></p>
                        </div>
                    </div>
                </c:if>
                <p>
                    <button class="w3-btn w3-teal" type="submit"><fmt:message key="button.sign-up"
                                                                              bundle="${ rb }"/></button>
                </p>
                <a style="color:#0000ff" href="${pageContext.request.contextPath}/?command=toAccount"><u><fmt:message
                        key="link.alrady-have" bundle="${rb}"/></u></a>
            </form>
        </div>
</div>

</div>


</div>
</p>
</form>
        </div>
</main>
</body>
</html>
