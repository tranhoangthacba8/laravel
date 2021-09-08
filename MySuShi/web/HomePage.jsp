<%-- 
    Document   : HomePage
    Created on : May 18, 2021, 7:38:14 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="left">
                    <div class="image_top">
                        <div class="image_child">
                        </div>
                    </div>
                    <c:forEach items="${listSushi}" var="i">
                        <div class="list_product">
                            <div class="product_title">
                                ${i.name}
                            </div>
                            <div class="product_image">
                                <a href="DetailControl?sushiId=${i.id}">
                                    <img src="images/${i.image}" alt=""/>
                                </a>
                            </div>
                            ${i.shortDes}
                        </div>
                    </c:forEach>
                    <div class="paging">
                        <c:forEach begin="1" end="${maxPage}" var="i">
                            <a href="HomeControl?pageIndex=${i}">${i}</a>
                           
                        </c:forEach>
                    </div>
                </div>
                <jsp:include page="Right.jsp"/>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
