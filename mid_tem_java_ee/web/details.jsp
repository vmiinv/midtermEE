<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.iitu.ead.entities.Coffee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp"%>
    <style>
        img{
            max-width: 100%;
        }
    </style>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container mt-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">All Coffees</li>
        </ol>
    </nav>
</div>
<div class="container mt-3">
    <div class="row">
        <div class="col-sm-12">
            <%
                Coffee coffee = (Coffee) request.getAttribute("coffee");
                if(coffee!=null){
            %>
            <div class="jumbotron">
                <h2><%=coffee.getName()%></h2>
                <h3>For <%=coffee.getPrice()%> KZT</h3>
                <hr class="my-4">
                <p>
                    <%=coffee.getDescription()%>
                </p>
                <hr class="my-4">
                <p class="font-weight-bold float-right">
                    Author is : <%=coffee.getAuthor().getFullName()%>
                    <img src="<%=coffee.getAuthor().getPicture()%>" class="rounded-circle" alt="Cinque Terre" width="30" height="30">
                </p>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
