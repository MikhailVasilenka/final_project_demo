<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<fmt:setBundle basename="messages" var="messageRb" scope="request"/>
<head>
    <title><fmt:message key="label.shop" bundle="${ rb }"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../style/w3.css">
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">--%>
    <%--<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"--%>
          <%--integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">--%>
    <script src="${pageContext.request.contextPath}/js/project.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"--%>
            <%--integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"--%>
            <%--crossorigin="anonymous"></script>--%>
</head>
<body>
<c:import url="header.jsp"/>

<!-- Page Content -->
<div class="w3-container w3-animate-opacity" style="text-align: justify">
<h2 style="text-align: center"><fmt:message key="label.items" bundle="${ rb }"/></h2>

<main role="main" class="container">
        <p>
    <div class="w3-container w3-center">
            <input class="w3-input w3-border w3-animate-input" style="width:100%" id="search" onclick="getDrugsFromBase()" type="text" placeholder=
            <fmt:message key="placeholder.search" bundle="${rb}"/> aria-label="Search">
            <p>
    <button class="w3-button w3-pale-blue w3-border w3-border-blue w3-round-large" onclick="document.getElementById('myModal').style.display='block'">
      <fmt:message key="label.search" bundle="${ rb }"/></button></p>
    </div>
    <div class="w3-modal" id="myModal" role="dialog">
        <div class="modal-dialog modal-result-in-table">
            <div class="w3-modal-content">
              <p>
                <!-- Modal Header -->
                <header class="w3-container w3-teal" style="height: 44px">
                <span onclick="document.getElementById('myModal').style.display='none'"
                      class="w3-button w3-display-topright">&times;</span>
                </header>

                <!-- Modal body -->
                <div class="w3-container">
                    <table class="w3-table w3-striped">
                        <tr>
                            <th scope="col"><fmt:message key="label.item" bundle="${ rb }"/></th>
                            <th scope="col"><fmt:message key="label.release-form" bundle="${ rb }"/></th>
                            <th scope="col"><fmt:message key="label.manufacturer" bundle="${ rb }"/></th>
                            <th scope="col"><fmt:message key="label.prescription" bundle="${ rb }"/></th>
                            <th scope="col"><fmt:message key="label.available-amount" bundle="${ rb }"/></th>
                            <th scope="col" class="text-center"><fmt:message key="label.quantity" bundle="${ rb }"/></th>
                            <th scope="col" class="text-right"><fmt:message key="label.price" bundle="${ rb }"/></th>
                            <th></th>
                        </tr>
                        <tbody id="tbodyTagModal">
                        </tbody>
                    </table>

                </div>

                <!-- Modal footer -->
                <footer class="w3-container w3-teal" style="height: 44px">
                </footer>
              </p>
            </div>
        </div>
        </p>
    </div>

</main>
</div>
<script>
    $(document).ready(function () {
        itemAmountUpdate();
    });
</script>
</body>
</html>
