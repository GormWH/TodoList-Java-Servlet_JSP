<%--
  Created by IntelliJ IDEA.
  User: suhong
  Date: 2022/04/06
  Time: 12:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../includes/header.jsp" %>
<div>
    <input type="text" name="tno" value="${dto.tno}" readonly>
</div>
<div>
    <input type="text" name="title" value="${dto.title}" readonly>
</div>
<div>
    <input type="date" name="dueDate" value="${dto.dueDate}" readonly>
</div>
<div>
    <input type="checkbox" name="finished" ${dto.finished ? "checked" : ""} readonly>
</div>
<div>
    <a href="/todo/modify?tno=${dto.tno}">Modify/Remove</a>
    <a href="/todo/list">List</a>
</div>
<%@ include file="../includes/footer.jsp" %>