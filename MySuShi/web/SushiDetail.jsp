<%-- 
    Document   : SushiDetail
    Created on : May 18, 2021, 7:44:26 PM
    Author     : Admin
--%>

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
                    <div class="sushi">
                        <div class="sushi_title">${sushi.name}</div>
                        <div class="sushi_detail">
                            <div class="sushi_image">
                                <img src="images/${sushi.image}" alt=""/>
                            </div>
                            ${sushi.detailDes}
                        </div>
                        <div class="sushi_price">
                            Price: ${sushi.price} $
                        </div>
                    </div>
                </div>
                <jsp:include page="Right.jsp"/>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>

