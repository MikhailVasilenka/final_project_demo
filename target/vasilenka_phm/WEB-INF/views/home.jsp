<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<head>
    <title><fmt:message key="home.title" bundle="${ rb }"/></title>
    <!-- css for cart img -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="${pageContext.request.contextPath}/js/project.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>

</head>
<body>

<c:import url="header.jsp"/>

    <main role="main" class="container">
            <div class="w3-container w3-animate-opacity" style="text-align: justify">
                <h2 style="text-align: center"><fmt:message key="label.welcome" bundle="${ rb }"/></h2>
                <table class="w3-table w3-margin-top" id="myTable1">
                    <tr>
                        <td><h3><i class="fa fa-home"></i> <fmt:message key="label.about" bundle="${ rb }"/></h3>
                            <p><fmt:message key="link.about" bundle="${ rb }"/></p>
                        </td>
                        <td>
                            <h3><i class="fa fa-search"></i> <fmt:message key="label.find" bundle="${ rb }"/></h3>
                            <p><fmt:message key="link.find" bundle="${ rb }"/></p>
                        </td>
                        <td>
                            <h3><i class="fa fa-list"></i> <fmt:message key="label.prescriptions-inf" bundle="${ rb }"/>
                            </h3>
                            <p><fmt:message key="link.prescriptions-inf" bundle="${ rb }"/></p>
                        </td>
                    </tr>
                </table>
            </div>

    </main>

</body>
</html>
