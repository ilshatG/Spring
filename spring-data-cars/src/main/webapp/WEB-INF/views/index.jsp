<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Simple Spring MVC + Hibernate</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>

        function updateTable() {
            $.getJSON("jsonData-shortAd-" , function(data) {
                var tbl_body = "";
                var odd_even = false;
                $.each(data, function() {
                    var tbl_row = "";

                    tbl_row += "<td>" + this["id"] + "</td>";
                    tbl_row += "<td> <a href='add"+this["id"]+"'>" +" <img src='picture-" + this["id"] + "' width='100'/></a></td>";
                    tbl_row += "<td>" + this["brand"] + "</td>";
                    tbl_row += "<td>" + this["model"] + "</td>";
                    tbl_row += "<td>" + this["year"] + "</td>";
                    tbl_row += "<td>" + this["price"] + "</td>";
                    tbl_row += "<td>" + this["car_meleage"] + "</td>";
                    tbl_row += "<td>" + this["engine_volume"] + "</td>";
                    tbl_row += "<td>" + this["transmission"] + "</td>";
                    tbl_row += "<td>" + this["power_of_engine"] + "</td>";
                    tbl_row += "<td>" + this["car_body"] + "</td>";
                    tbl_row += "<td>" + this["drive"] + "</td>";
                    tbl_row += "<td>" + this["fuel"] + "</td>";
                    tbl_row += "<td>" + this["userName"] + "</td>";


                    tbl_body += "<tr valign='baseline'>"+tbl_row+"</tr>";
                    odd_even = !odd_even;
                })
                $("#table tbody").html(tbl_body);
            });

        }

        function timeStampToString(millis) {
            var date = new Date(millis);
            return date.toString();
        }

    </script>
</head>

<body>
<div class="container-fluid">


    <c:set var = "log" value="login"></c:set>
    <c:if test="${not empty userName}">
        <c:set var="log" value="logout"></c:set>
        <p>Current user is <c:out value="${userName}" /></p>
    </c:if>
    <a href="<c:out value="${log}"/>" ><input type="button" value="<c:out value="${log}"/>"></a>

    <h1>Cars ads</h1>
    <p>Spring MVC + Data</p>

    <h3>Table of ads</h3>

    <table class="table" id="table">
        <thead>
        <tr>
            <th>id</th>
            <th>Image</th>
            <th>Brand</th>
            <th>Model</th>
            <th>Year</th>
            <th>Price</th>
            <th>Meleage</th>
            <th>Volume</th>
            <th>Transmission</th>
            <th>Power</th>
            <th>Body</th>
            <th>Drive</th>
            <th>Fuel</th>
            <th>User</th>
        </tr>
        </thead>

        <tbody>

        </tbody>
    </table>
    <script>updateTable();</script>

    <a href="add0"><input type="button" value="Add ad"></a>
</div>


</body>
</html>