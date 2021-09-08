<%-- 
    Document   : Footer
    Created on : May 18, 2021, 7:40:27 PM
    Author     : Admin

--%>

<%@page import="dao.viewDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/footer.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <% 
        viewDAO viewdao = new viewDAO();
           viewdao.updateView();
           int count = viewdao.getView();
           
           String format = String.format("%06d", count);
           ArrayList arr = new ArrayList();
           for(int j = 0; j < format.length();j++){
            arr.add(format.charAt(j));
           }
        
        session.setAttribute("dodai", arr);
        %>
        
        <div class="footer">
            <div style="display: flex">
                <div class="create" style="width: 50%">
                    <a href="">Create with simple site</a>
                </div>
                <div class="code" style="width: 50%;text-align: right;font-weight: bold">
                    <c:forEach items="${dodai}" var="i">
                       <button>${i}</button>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>

