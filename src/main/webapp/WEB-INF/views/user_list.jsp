<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../../css/w3.css">
<script src="${pageContext.request.contextPath}/js/project.js"></script>

<style>
    .userlist {
        overflow-y: auto;
        width: auto;
        min-width: 200px;
        max-width: 60%;
    }
</style>
<body>



<table id="example" class="w3-table" style="width:100%">
    <thead>
    <tr>
        <th></th>
        <th>Id</th>
        <th><fmt:message key="label.login" bundle="${ rb }"/></th>
    </tr>
    </thead>
    <tbody id="body">
    <c:forEach var="elem" items="${userList}" varStatus="status">
        <tr>
            <td>
                <button class="w3-button w3-teal"
                        onclick="document.getElementById('userDetailsModal').style.display='block';
                                showUserDetails(${elem.id},this)">
                    <fmt:message key="button.details" bundle="${ rb }"/></button>
            </td>
            <td><c:out value="${elem.id}"></c:out></td>
            <td><c:out value="${elem.login}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="w3-modal w3-animate-opacity" id="userDetailsModal" role="dialog">
    <div class="w3-modal-content w3-card-4">

        <!-- Modal Header -->
        <header class="w3-container w3-teal" style="height: 44px;">
            <h4 class="modal-title"><fmt:message key="user-details.title" bundle="${ rb }"/></h4>
            <span onclick="document.getElementById('userDetailsModal').style.display='none'"
                  class="w3-button w3-large w3-display-topright">&times;</span>
        </header>

        <!-- Modal body -->
        <div class="w3-container">
            <p>
            <div class="w3-row">
                <div class="w3-col m3 w3-center">
                    <label><fmt:message key="label.login" bundle="${ rb }"/></label>
                </div>
                <div class="w3-col m9 w3-center" id="inputLogin">
                    <c:out value="${elem.login}"></c:out>
                </div>
            </div>
            <div class="w3-row">
                <div class="w3-col m3 w3-center">
                    <label><fmt:message key="label.name" bundle="${ rb }"/></label>
                </div>
                <div class="w3-col m9 w3-center" id="inputUserName">
                </div>
            </div>
            <div class="w3-row">
                <div class="w3-col m3 w3-center">
                    <label><fmt:message key="label.lastname" bundle="${ rb }"/></label>
                </div>
                <div class="w3-col m9 w3-center" id="inputLastName">
                </div>
            </div>
            <div class="w3-row">
                <div class="w3-col m3 w3-center">
                    <label><fmt:message key="label.address" bundle="${ rb }"/></label>
                </div>
                <div id="inputAddress" class="w3-col m9 w3-center">
                </div>
            </div>
            <div class="w3-row">
                <div class="w3-col m3 w3-center">
                    <label><fmt:message key="label.email" bundle="${ rb }"/></label>
                </div>
                <div id="inputEmail" class="w3-col m9 w3-center">
                </div>
            </div>
            <div class="w3-row">
                <div class="w3-col m3 w3-center">
                    <label><fmt:message key="label.role" bundle="${ rb }"/></label>
                </div>
                <div id="inputRole" class="w3-col m4 w3-center">
                </div>
                <div class="w3-col m5 w3-center">
                    <select class="form-control" id="changeRole">
                        <option>CLIENT</option>
                        <option>DOCTOR</option>
                    </select>
                </div>
            </div>
            </p>
            <div class="w3-container w3-center">
                <p>
                    <button type="button" class="w3-button w3-pale-green w3-border w3-border-green w3-round-large"
                            onclick="changeUser()"><fmt:message key="button.apply" bundle="${ rb }"/></button>
                    <button type="button" class="w3-button w3-pale-red w3-border w3-border-red w3-round-large"
                            id="deleteButton" onclick="deleteUser()"><fmt:message key="button.delete" bundle="${ rb }"/></button>
                </p>
            </div>
        </div>
        <footer class="w3-container w3-teal" style="height: 44px">
        </footer>
    </div>

</div>
</body>
