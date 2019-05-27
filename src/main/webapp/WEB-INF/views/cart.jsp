<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctl" uri="/WEB-INF/tld/custom.tld" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<fmt:setBundle basename="messages" var="messageRb" scope="request"/>
<head>
    <title><fmt:message key="home.title" bundle="${ rb }"/></title>
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

    <div class="container mb-4 ">
        <c:choose>
            <c:when test="${error =='notEnoughItems'}">
                <div class="w3-container w3-center">
                    <div class="w3-panel w3-pale-red w3-border" role="alert">
                        <fmt:message key="alert.notEnoughItems" bundle="${ messageRb }"/> ${wrongAmountItemString}
                    </div>
                </div>
            </c:when>
            <c:when test="${error =='notEnoughPrescriptions'}">
                <div class="w3-container w3-center">
                    <div class="w3-panel w3-pale-red w3-border" role="alert">
                        <fmt:message key="alert.notEnoughPrescriptions" bundle="${ messageRb }"/>
                    </div>
                </div>
            </c:when>

        </c:choose>
        <div class="w3-container w3-center">
            <div class="w3-panel w3-pale-blue w3-bottombar w3-border-blue w3-border" role="alert" id="emptyCartMessage" >
                <p><fmt:message key="alert.emptyCart" bundle="${ messageRb }"/></p>
            </div>
        </div>
        <div class="row" id="cartBlock">
            <div class="col-12">
                <div class="table-responsive">
                    <table class="w3-table">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="label.item" bundle="${ rb }"/></th>
                            <th scope="col"><fmt:message key="label.release-form" bundle="${ rb }"/></th>
                            <th scope="col"><fmt:message key="label.manufacturer" bundle="${ rb }"/></th>
                            <th scope="col"><fmt:message key="label.prescription" bundle="${ rb }"/></th>
                            <th scope="col" class="text-center"><fmt:message key="label.quantity"
                                                                             bundle="${ rb }"/></th>
                            <th scope="col" class="text-right"><fmt:message key="label.price" bundle="${ rb }"/></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="tbodyTagCart">
                        <c:import url="cart_element.jsp"/>
                        </tbody>
                    </table>
                </div>
                <div class="w3-row" id="totalBlock">
                    <div class="w3-col m4 ml-auto">
                        <div class="w3-row">
                            <div class="w3-col m6">
                                <fmt:message key="label.total" bundle="${ rb }"/>
                            </div>
                            <div class="w3-col m6" id="total">
                                ${total}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col mb-2">
                <div class="w3-row">
                    <div class="col-sm-12 col-md-6 text-right">
                    </div>
                    <div class="col-sm-12 col-md-6 text-right">
                        <button class="btn btn-lg btn-block btn-success text-uppercase" id="checkout"
                                onclick="checkout()">
                            <fmt:message key="button.checkout" bundle="${ rb }"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script type="text/javascript">
    $(document).ready(function () {
        itemAmountUpdate();
        var cookieCartString = $.cookie('cart');
        if (cookieCartString == null) {
            $('#cartBlock').hide();
            $('#emptyCartMessage').show();
        } else if (cookieCartString != null) {
            var cookieCartJson = JSON.parse(decodeURIComponent(cookieCartString));
            if (cookieCartJson.length > 0) {
                $('#cartBlock').show();
                $('#emptyCartMessage').hide();
            } else {
                $('#cartBlock').hide();
                $('#emptyCartMessage').show();
            }
        }
    });
</script>
</body>
</html>
