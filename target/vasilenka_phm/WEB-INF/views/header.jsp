<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    body {
        background: url(../../images/backgr1.jpg);
    }
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/w3.css">
<link rel="image" href="${pageContext.request.contextPath}/images/phm.png">
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<title><fmt:message
        key="home.title" bundle="${rb}"/></title>
<%-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> --%>

<!-- Sidebar -->
<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:15%">
    <h3 class="w3-bar-item"><fmt:message key="label.menu" bundle="${ rb }"/></h3>
    <a class="w3-bar-item w3-button" href="${pageContext.request.contextPath}/?command=toStartPage"><fmt:message
            key="link.home" bundle="${rb}"/></a>
    <a class="w3-bar-item w3-button" href="${pageContext.request.contextPath}/?command=toAccount"><fmt:message
            key="link.account" bundle="${rb}"/></a>
    <a class="w3-bar-item w3-button" href="${pageContext.request.contextPath}/?command=toShop"><fmt:message
            key="link.shop" bundle="${rb}"/></a>
    <c:if test="${role == 'GUEST'}">
        <a class="w3-bar-item w3-button" href="${pageContext.request.contextPath}/?command=toSignUp"><fmt:message
                key="link.sign-up" bundle="${rb}"/></a>
    </c:if>
    <a class="w3-bar-item w3-btn w3-green w3-round-large" href="${pageContext.request.contextPath}/?command=toCart">
        <i class="fa fa-shopping-cart"></i> <fmt:message
            key="link.cart" bundle="${rb}"/> <span class="w3-badge w3-red" id="itemAmount"></span></a>
    <c:if test="${role != 'GUEST'}">
        <a class="w3-bar-item w3-button" href="${pageContext.request.contextPath}/?command=logout"><fmt:message
                key="link.logout" bundle="${rb}"/></a>
    </c:if>
</div>

<!-- Page Content -->
<div style="margin-left:15%">

    <div class="w3-container w3-teal w3-block">
        <h1>
            <img alt="" src="../../images/phm.png"/>
            <%--<img alt="" src="${pageContext.request.contextPath}/images/phm.png"/>--%>
            <fmt:message
                    key="label.main-title" bundle="${rb}"/>
        </h1>
        <div class="w3-dropdown-hover w3-right">
            <button class="w3-bar-item w3-button" style="right: 0"><i class="fa fa-globe"></i> <fmt:message
                    key="link.language" bundle="${rb}"/></button>
            <div class="w3-dropdown-content w3-bar-block w3-border">
                <a class="w3-bar-item w3-button"
                   href="${pageContext.request.contextPath}/?command=doInitialRedirectCommand&lang=en_US">English</a>
                <a class="w3-bar-item w3-button"
                   href="${pageContext.request.contextPath}/?command=doInitialRedirectCommand&lang=ru_RU"><fmt:message
                        key="link.russian" bundle="${rb}"/></a>
            </div>
        </div>
    </div>
</div>
<div style="margin-left:15%">
