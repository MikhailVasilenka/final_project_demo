<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${message=='successChange'}">
    <div class="w3-container w3-center">
        <div class="w3-panel w3-pale-green w3-border" role="alert">
            <p><fmt:message key="alert.successfully-changing" bundle="${ messageRb }"/></p>
        </div>
    </div>
</c:if>
<form method="POST" action="${pageContext.request.contextPath}/">
    <input type="hidden" name="command" value="changeUserPassword">
    <fmt:message key="placeholder.old-password" bundle="${ rb }" var="passwordPlaceholder"/>
    <div class="form-group col-md-6">
        <label class="w3-text-teal"><fmt:message key="label.old-pas" bundle="${ rb }"/></label>
        <input type="password" name="oldPassword" class="w3-input w3-border" style="width:200px"
               placeholder=${passwordPlaceholder} required/>
        <p></p>
    </div>
    <c:if test="${error=='incorrectPassword'}">
        <div class="w3-container w3-center">
            <div class="w3-panel w3-pale-red w3-border" role="alert">
                <p><fmt:message key="alert.incorrect-password" bundle="${ messageRb }"/></p>
            </div>
        </div>
    </c:if>
    <fmt:message key="placeholder.new-password" bundle="${ rb }" var="passwordPlaceholder"/>
    <div class="form-group col-md-6">
        <label class="w3-text-teal"><fmt:message key="label.new-pas" bundle="${ rb }"/></label>
        <input type="password" name="newPassword" class="w3-input w3-border" style="width:200px"
               placeholder=${passwordPlaceholder} required/>
        <p></p>
    </div>
    <c:if test="${error=='incorrectAuthentication'}">
        <div class="w3-container w3-center">
            <div class="w3-panel w3-pale-red w3-border" role="alert">
                <p><fmt:message key="alert.invalid-password" bundle="${ messageRb }"/></p>
            </div>
        </div>
    </c:if>
    <button type="submit" class="w3-button w3-light-teal w3-border w3-border-teal w3-round-large">
        <fmt:message key="button.change" bundle="${ rb }"/></button>
</form>
