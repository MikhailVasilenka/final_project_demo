<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
<div class="w3-modal w3-animate-opacity" id="prescriptionDetails" role="dialog">
    <div class="w3-modal-content w3-card-4">
        <form method="POST" action="controller">

            <!-- Modal Header -->
            <header class="w3-container w3-teal">
                <h4 class="modal-title"><fmt:message key="prescription.prescription-details" bundle="${ rb }"/></h4>
                <span onclick="document.getElementById('prescriptionDetails').style.display='none'"
                      class="w3-button w3-large w3-display-topright">&times;</span>
            </header>


            <!-- Modal body -->
            <div class="w3-container">
                <p>
                    <p><label class="w3-text" for="inputDescription"> <fmt:message key="prescription.description"
                                                                                   bundle="${ rb }"/></label></p>
                    <textarea id="inputDescription" cols="40" rows="5"></textarea>

                <p> <label class="w3-text" for="inputDate"> <fmt:message key="prescription.validity-date"
                                                                         bundle="${ rb }"/></label></p>
                    <input class="w3-input w3-border" type="date" id="inputDate">
                </p>
                    <div class="w3-container w3-center">
                <p>
                    <button type="submit"
                            class="w3-button w3-pale-green w3-border w3-border-green w3-round-large"
                            id="buttonGive"><fmt:message key="button.give" bundle="${ rb }"/></button>
                </p>
                    </div>
            </div>
        <!-- Modal footer -->
        <footer class="w3-container w3-teal" style="height: 44px">
        </footer>
        </form>
    </div>
</div>

<div class="w3-container">
<table id="prescriptionList" class="w3-table" style="width:100%">
    <thead>
    <tr class="w3-teal">
        <th></th>
        <th><fmt:message key="label.prescription-number" bundle="${ rb }"/></th>
        <th><fmt:message key="label.user" bundle="${ rb }"/></th>
        <th><fmt:message key="label.drug" bundle="${ rb }"/></th>
        <th><fmt:message key="label.status" bundle="${ rb }"/></th>
    </tr>
    </thead>
    <tbody id="body">
    <c:forEach var="elem" items="${prescriptionList}" varStatus="status">
        <tr>
            <c:choose>
                <c:when test="${elem.issueDate == null}">
                    <td>
                        <button class="w3-button w3-teal" onclick="showPrescriptionDetails(${elem.id},this)">
                            <fmt:message key="button.details" bundle="${ rb }"/></button>
                    </td>
                </c:when>
                <c:when test="${elem.issueDate != null}">
                    <td></td>
                </c:when>
            </c:choose>
            <td><c:out value="${elem.id}"></c:out></td>
            <td><c:out value="${elem.userId}"></c:out></td>
            <td><c:out value="${elem.drugId}"></c:out></td>
            <c:choose>
                <c:when test="${elem.issueDate == null}">
                    <td><fmt:message key="prescription.requested" bundle="${ rb }"/></td>
                </c:when>
                <c:when test="${elem.issueDate != null}">
                    <td><fmt:message key="prescription.given" bundle="${ rb }"/></td>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>

</table>
</div>
</body>
