<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<fmt:setBundle basename="messages" var="messageRb" scope="request"/>
<head>
    <title><fmt:message key="account.title" bundle="${ rb }"/></title>
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
<div class="w3-animate-opacity">
<main role="main" class="container">
    <div class="w3-card-4 w3-animate-opacity">
        <table class="w3-table">
            <c:if test="${message=='success'}">
                <div class="w3-panel w3-pale-green w3-border" role="alert">
                    <fmt:message key="alert.successfully-pay" bundle="${ messageRb }"/>
                </div>
            </c:if>
            <td>
                <ul class="w3-ul" style="width:130px">
                    <button class="w3-bar-item w3-button tablink" onclick="openLink(event, 'Profile')"
                            style="outline: none"><fmt:message key="user-account.profile" bundle="${ rb }"/></button>
                    <button class="w3-bar-item w3-button tablink" onclick="openLink(event, 'Orders')"
                            style="outline: none"><fmt:message key="user-account.orders" bundle="${ rb }"/></button>
                    <button class="w3-bar-item w3-button tablink" onclick="openLink(event, 'Settings')"
                            style="outline: none"><fmt:message key="user-account.settings" bundle="${ rb }"/></button>
                </ul>
            </td>
            <td>
                <div style="margin-left:130px">
                    <div id="Profile" class="w3-container list w3-animate-opacity" style="display:none">
                        <table class="w3-table">
                            <tr>
                                <td><label><fmt:message key="label.login" bundle="${ rb }"/></label></td>
                                <td><c:out value="${user.login}"></c:out></td>
                            </tr>
                            <tr>
                                <td><label><fmt:message key="label.name" bundle="${ rb }"/></label></td>
                                <td><c:out value="${user.firstName}"></c:out></td>
                            </tr>
                            <tr>
                                <td><label><fmt:message key="label.lastname" bundle="${ rb }"/></label></td>
                                <td><c:out value="${user.lastName}"></c:out></td>
                            </tr>
                            <tr>
                                <td><label><fmt:message key="label.email" bundle="${ rb }"/></label></td>
                                <td><c:out value="${user.email}"></c:out></td>
                            </tr>
                            <tr>
                                <td><label><fmt:message key="label.address" bundle="${ rb }"/></label></td>
                                <td><p><c:out value="${user.address}"></c:out></p></td>
                            </tr>
                        </table>
                    </div>

                    <div id="Orders" class="w3-container list w3-animate-opacity" style="display:none">
                        <table id="orderList" class="w3-table " style="width:100%">
                            <thead class="thead-dark">
                            <tr>
                                <th></th>
                                <th><fmt:message key="label.order-number" bundle="${ rb }"/></th>
                                <th><fmt:message key="label.price" bundle="${ rb }"/></th>
                                <th><fmt:message key="label.status" bundle="${ rb }"/></th>
                            </tr>
                            </thead>
                            <tbody id="orderBody">
                            <c:import url="order_element.jsp"/>
                            </tbody>
                        </table>
                    </div>

                    <div id="Settings" class="w3-container list w3-animate-opacity" style="display:none">
                        <c:import url="changing_password.jsp"/>
                    </div>

                </div>
            </td>
        </table>
      </div>
    </div>
        <script>
            function openLink(evt, animName) {
                var i, x, tablinks;
                x = document.getElementsByClassName("list");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablink");
                for (i = 0; i < x.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" w3-border w3-border-green w3-round-large", "");
                }
                document.getElementById(animName).style.display = "block";
                evt.currentTarget.className += " w3-border w3-border-green w3-round-large";
            }
        </script>

</body>
</html>
