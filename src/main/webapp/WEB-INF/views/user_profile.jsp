<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
     aria-labelledby="v-pills-home-tab">
     <table class="w3-table">
       <tr>
         <td><label><fmt:message key="label.login" bundle="${ rb }"/></label></td>
         <td><c:out value="${user.login}"></c:out></td>
       </tr>
       <tr>
         <td><label><fmt:message key="label.name" bundle="${ rb }"/></label></td>
         <td> <c:out value="${user.firstName}"></c:out></td>
       </tr>
       <tr>
         <td><label><fmt:message key="label.lastname" bundle="${ rb }"/></label></td>
         <td><c:out value="${user.lastName}"></c:out></td>
       </tr>
       <tr>
         <td><label><fmt:message key="label.email" bundle="${ rb }"/></label></td>
         <td><c:out value="${user.email}"></c:out> </td>
       </tr>
       <tr>
         <td> <label><fmt:message key="label.address" bundle="${ rb }"/></label></td>
         <td> <p><c:out value="${user.address}"></c:out></p></td>
       </tr>
     </table>
</div>
