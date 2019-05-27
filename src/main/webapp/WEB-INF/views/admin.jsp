<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<head>

    <title>Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
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
<div class="w3-container w3-animate-opacity">
  <!-- Page Content -->
<div class="w3-modal w3-animate-opacity" id="addDrugMenu" role="dialog">
        <div class="w3-modal-content w3-card-4">

            <!-- Modal Header -->
            <header class="w3-container w3-teal" style="height: 44px">
              <span onclick="document.getElementById('addDrugMenu').style.display='none'"
      class="w3-button w3-large w3-display-topright">&times;</span>
            </header>
            <form action="${pageContext.request.contextPath}/" method="post">
            <!-- Modal body -->
            <div class="w3-container">
                <table class="w3-table ">
                  <div class="w3-container">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="label.name" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="label.release-form" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="label.manufacturer" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="label.prescription" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="label.available-amount" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="label.price" bundle="${ rb }"/></th>
                    </tr>
                    </thead>

                    <tr>
                        <td><input class="w3-input w3-border" id="inputName"></td>
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
                        <td><input class="w3-input w3-border" id="inputAvailableAmount" type="number" min="0"></td>
                        <td><input class="w3-input w3-border" id="inputPrice" type="number" min="0"></td>
                    </tr>

                  </div>
                </table>
            </div>
            </form>
            <div class="w3-container w3-center">
              <p>
            <button type="button" class="w3-button w3-pale-green w3-border w3-border-green w3-round-large"
               id="addDrugButton" onclick="addDrugToBase()">
               <fmt:message key="button.add" bundle="${ rb }"/></button>
              <p>
              </div>

            <!-- Modal footer -->
            <footer class="w3-container w3-teal" style="height: 44px">
            </footer>
        </div>
    </div>

<div class="w3-container">
  <p>
    <div class="w3-row">
        <div class="w3-container">
          <ul class="w3-ul" id="v-pills-tab" role="tablist" style="width:130px">
                <button class="w3-bar-item w3-button tablink" id="v-pills-home-tab" onclick="openLink(event, 'users')" role="tab"
                  style="outline: none" aria-controls="v-pills-home" aria-selected="true"><fmt:message key="admin-account.users" bundle="${ rb }"/></button>
                <button class="w3-bar-item w3-button tablink" id="v-pills-profile-tab" onclick="openLink(event, 'drugs')" role="tab"
                  style="outline: none" aria-controls="v-pills-profile" aria-selected="false"><fmt:message key="admin-account.drugs" bundle="${ rb }"/></button>
          </ul>
        </div>
        <p>
        <div class="w3-row">
            <div class="tab-content" id="v-pills-tabContent">
                <div id="users" class="w3-container list w3-animate-opacity" style="display:none"
                     aria-labelledby="v-pills-home-tab">
                    <c:import url="user_list.jsp"/>
                </div>
              <div id="drugs" class="w3-container list w3-animate-opacity" style="display:none"
                 aria-labelledby="v-pills-profile-tab">
                    <div class="w3-row">
                      <p>
                        <button class="w3-button w3-pale-green w3-border w3-border-green"
                          onclick="addDrugMenu()
                                  // document.getElementById('addDrugMenu').style.display='block'
                                    "><fmt:message key="button.add" bundle="${ rb }"/></button>
                        </p>
                    </div>
                    <c:import url="drug_list.jsp"/>
                </div>
            </div>
        </div>
      </p>
    </div>
  </p>
</div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $(document).ready(function () {
            startTableWithLocale('#example');
        });
    });
</script>
<%--<script type="text/javascript">--%>
    <%--$(document).ready(function () {--%>
        <%--startTableWithLocale('#example1');--%>
    <%--});--%>
<%--</script>--%>
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
