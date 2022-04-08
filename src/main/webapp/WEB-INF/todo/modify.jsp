<%--
  Created by IntelliJ IDEA.
  User: suhong
  Date: 2022/04/06
  Time: 12:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../includes/header.jsp" %>
<form id="form1" action="/todo/modify" method="post">
    <div>
        <input type="text" name="tno" value="${dto.tno}" readonly>
    </div>
    <div>
        <input type="text" name="title" value="${dto.title}" >
    </div>
    <div>
        <input type="date" name="dueDate" value="${dto.dueDate}" >
    </div>
    <div>
        <input type="checkbox" name="finished" ${dto.finished ? "checked":""} >
    </div>
    <div>
        <button type="submit">Modify</button>
    </div>
</form>
<form id="form2" action="/todo/remove" method="post">
    <input type="hidden" name="tno" value="${dto.tno}" readonly>
    <div>
        <button type="submit">Remove</button>
    </div>
</form>
<%@ include file="../includes/footer.jsp" %>
