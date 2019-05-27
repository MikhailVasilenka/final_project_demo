<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<fmt:setBundle basename="messages" var="messageRb" scope="request"/>
<head>
    <title><fmt:message key="welcome" bundle="${ rb }"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/w3.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">--%>
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
<div class="w3-animate-opacity">
    <!-- Page Content -->
    <main role="main" class="container">
        <div class="w3-card-4 w3-animate-opacity">
            <div class="w3-container">
                <p>
                <div class="w3-row">
                    <div class="w3-col m3">
                        <ul class="w3-ul" id="v-pills-tab" role="tablist" style="width:130px">
                            <button class="w3-bar-item w3-button tablink" id="v-pills-home-tab"
                                    onclick="openLink(event, 'profile')" role="tab"
                                    style="outline: none" aria-controls="v-pills-home" aria-selected="true"><fmt:message
                                    key="user-account.profile" bundle="${ rb }"/></button>
                            <button class="w3-bar-item w3-button tablink" onclick="openLink(event, 'prescription_list')"
                                    role="tab"
                                    aria-controls="v-pills-profile" aria-selected="false"><fmt:message
                                    key="doctor-account.prescriptions" bundle="${ rb }"/></button>
                            <button class="w3-bar-item w3-button tablink" onclick="openLink(event, 'settings')"
                                    role="tab"
                                    aria-controls="v-pills-messages" aria-selected="false"><fmt:message
                                    key="user-account.settings" bundle="${ rb }"/></button>
                        </ul>
                    </div>
                    <div class="w3-col m9">
                        <div id="profile" class="w3-container list w3-animate-opacity" style="display:none"
                             aria-labelledby="v-pills-home-tab">
                            <c:import url="user_profile.jsp"/>
                        </div>
                        <div id="prescription_list" class="w3-container list w3-animate-opacity" style="display:none"
                             aria-labelledby="v-pills-profile-tab">
                            <c:import url="prescription_list.jsp"/>
                        </div>
                        <div id="settings" class="w3-container list w3-animate-opacity" style="display:none"
                             aria-labelledby="v-pills-messages-tab">
                            <c:import url="changing_password.jsp"/>
                        </div>
                    </div>
                </div>
                </p>
            </div>
        </div>
    </main>
</div>

<!-- /.container -->
<script type="text/javascript">
    $(document).ready(function () {
        startTableWithLocale('#prescriptionList');
    });
</script>
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
