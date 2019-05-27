<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<body>

    <%--  --%>

    <div class="w3-modal w3-animate-opacity" id="changeDrugMenu" role="dialog">
            <div class="w3-modal-content w3-card-4">

                <!-- Modal Header -->
                <header class="w3-container w3-teal" style="height: 44px">
                  <div class="w3-container">
                  <span onclick="document.getElementById('changeDrugMenu').style.display='none'"
          class="w3-button w3-large w3-display-topright">&times;</span>
        </div>
                </header>

                <!-- Modal body -->
                <div class="w3-container">
                    <table class="w3-table ">
                      <div class="w3-container">
                        <tr>
                            <th scope="col"><fmt:message key="label.name" bundle="${ rb }"/></th>
                            <th scope="col"><fmt:message key="label.release-form" bundle="${ rb }"/></th>
                            <th scope="col"><fmt:message key="label.manufacturer" bundle="${ rb }"/></th>
                            <th scope="col"><fmt:message key="label.prescription" bundle="${ rb }"/></th>
                            <th scope="col"><fmt:message key="label.available-amount" bundle="${ rb }"/></th>
                            <th scope="col"><fmt:message key="label.price" bundle="${ rb }"/></th>
                        </tr>

                        <tr>
                            <td><input class="w3-input w3-border" id="inputName" required></td>
                            <td>
                                <select id="releaseForm">
                                </select>
                            </td>
                            <td>
                                <select id="manufacturer">
                                </select>
                            </td>
                            <td>
                                <select id="inputPrescription">
                                    <option><fmt:message key="option.required" bundle="${ rb }"/></option>
                                    <option><fmt:message key="option.not-required" bundle="${ rb }"/></option>
                                </select>
                            </td>
                            <td><input class="w3-input w3-border" id="inputAvailableAmount" type="number" min="0" required></td>
                            <td><input class="w3-input w3-border" id="inputPrice" type="number" min="0" required></td>
                        </tr>

                      </div>
                    </table>

                </div>
                <div class="w3-container w3-center">
                  <p>
                <button type="button" class="w3-button w3-pale-green w3-border w3-border-green w3-round-large"
                   id="changeDrugButton" onclick="changeDrug(${elem.id})"><fmt:message key="button.apply" bundle="${ rb }"/></button>
                  <p>
                  </div>

                <!-- Modal footer -->
                <footer class="w3-container w3-teal" style="height: 44px">
                </footer>
            </div>
        </div>

    <%--  --%>
<table id="example1" class="w3-table" style="width:100%">
    <thead>
    <tr>
        <th></th>
        <th></th>
        <th>Id</th>
        <th><fmt:message key="label.name-drug" bundle="${ rb }"/></th>
        <th><fmt:message key="label.prescription" bundle="${ rb }"/></th>
        <th><fmt:message key="label.available-amount" bundle="${ rb }"/></th>
        <th><fmt:message key="label.price" bundle="${ rb }"/></th>
    </tr>
    </thead>
    <tbody id="body">
    <c:forEach var="elem" items="${drugList}" varStatus="status">
        <tr>
            <td>
                <button class="w3-button w3-teal" onclick="document.getElementById('changeDrugMenu').style.display='block'; changeDrugMenu(${elem.id})">
                  <fmt:message key="button.edit" bundle="${ rb }"/></button>
            </td>
            <td>
                <button class="w3-button w3-teal" onclick="deleteDrug(${elem.id},this)"><fmt:message key="button.delete"
                  bundle="${ rb }"/></button>
            </td>
            <td><c:out value="${elem.id}"></c:out></td>
            <td><c:out value="${elem.name}"></c:out></td>

            <td>
                <c:choose>
                    <c:when test="${!elem.isPrescriptionRequired}">
                        <fmt:message key="info.non-prescription" bundle="${ rb }"/>
                    </c:when>
                    <c:when test="${elem.isPrescriptionRequired}">
                        <fmt:message key="info.required" bundle="${ rb }"/>
                    </c:when>
                </c:choose>

            </td>

            <td><c:out value="${elem.availableAmount}"></c:out></td>
            <td><c:out value="${elem.price}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

  <script type="text/javascript">
      $(document).ready(function () {
          startTableWithLocale('#example1');
      });
  </script>
</body>
