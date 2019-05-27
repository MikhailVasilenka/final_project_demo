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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/w3.css">
    <link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script src="${pageContext.request.contextPath}/js/project.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>

<c:import url="header.jsp"/>
<div class="w3-animate-opacity">

<main role="main" class="container">
  <p>
    <div class="w3-row">
        <table class="w3-table-all">
            <tr class="w3-teal">
                <th scope="col"><fmt:message key="label.item" bundle="${ rb }"/></th>
                <th scope="col"><fmt:message key="label.release-form" bundle="${ rb }"/></th>
                <th scope="col"><fmt:message key="label.manufacturer" bundle="${ rb }"/></th>
                <th scope="col" class="text-center"><fmt:message key="label.quantity" bundle="${ rb }"/></th>
                <th scope="col" class="text-right"><fmt:message key="label.price" bundle="${ rb }"/></th>
            </tr>
            <c:forEach var="elem" items="${order.itemList}" varStatus="status">
                <tr>
                    <td><c:out value="${elem.drug.name}"></c:out></td>
                    <td><c:out value="${elem.drug.releaseForm.description}"></c:out></td>
                    <td><c:out value="${elem.drug.manufacturer.name}"></c:out></td>
                    <td><c:out value="${elem.amount}"></c:out></td>
                    <td><c:out value="${elem.drug.price}"></c:out></td>
                </tr>
            </c:forEach>
            <fmt:message key="label.total" bundle="${ rb }"/> ${order.price}
        </table>
    </div>
    </p>
    <div class="w3-row">
        <div class="w3-col m6">
            <form class="w3-row form-check" action="${pageContext.request.contextPath}/">
                <input class="w3-input w3-border" type="hidden" name="command" value="pay">
                <div class="form-group w3-col m7">
                    <label class="w3-text" for="card-holder"><fmt:message key="pay.label.cart-holder"
                                                                          bundle="${ rb }"/></label>
                    <input id="card-holder" class="w3-input w3-border form-control" type="text"
                           placeholder="Card Holder" aria-label="Card Holder" aria-describedby="basic-addon1" required>
                </div>
                <div class="form-group w3-row m5">
                    <label class="w3-text"><fmt:message key="pay.label.expiration-date" bundle="${ rb }"/></label>
                    <div class="input-group expiration-date">
                        <input class="w3-input w3-border form-control" type="text" placeholder="MM" aria-label="MM"
                               aria-describedby="basic-addon1" required>
                        <span class="date-separator">/</span>
                        <input class="w3-input w3-border form-control" type="text" placeholder="YY" aria-label="YY"
                               aria-describedby="basic-addon1" required>
                    </div>
                </div>
                <div class="form-group w3-col m8">
                    <label class="w3-text" for="card-number"><fmt:message key="pay.label.cart-number"
                                                                          bundle="${ rb }"/></label>
                    <input id="card-number" class="w3-input w3-border form-control" type="text"
                           placeholder="Card Number" aria-label="Card Holder" aria-describedby="basic-addon1" required>
                </div>
                <div class="form-group w3-col m4">
                    <label class="w3-text" for="cvc">CVC</label>
                    <input id="cvc" class="w3-input w3-border form-control" type="text" placeholder="CVC"
                           aria-label="Card Holder" aria-describedby="basic-addon1" required>
                </div>
                    <p>
                        <button type="submit" class="w3-button w3-pale-green w3-border w3-border-green w3-round-large">
                            <fmt:message key="button.pay" bundle="${ rb }"/></button>
                    </p>
            </form>
        </div>
    </div>
    </div>


</main>
</div>
</body>
</html>
