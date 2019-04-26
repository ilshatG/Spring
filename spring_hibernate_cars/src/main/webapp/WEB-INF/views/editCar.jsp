<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add/edit ad</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script>

            function fillOptions(dropBox, table, activePosition) {
                $(document.getElementById(dropBox)).empty();
                $.getJSON("jsonData-"+table+".do" , function(selectValues) {
                    $.each(selectValues, function(key, value) {
                        $(document.getElementById(dropBox))//$('#brand')
                            .append($('<option>', { value : this["id"].toString() })
                                .text(this["name"].toString()));
                    });
                    $(document.getElementById(dropBox)).val(activePosition.toString());
                });
                //alert("table - " + table);
            }

    </script>
</head>
<body>
<div class="container-fluid">
<h1>Add/edit</h1>
    <form action="add.do" id="my-form" method="post">
        <c:if test="${ad.id != 0}">
            <p>ad.carBody.id ${ad.carBody.id}</p>
        </c:if>

        <div class="form-group">
            <label for="brand">Brand:</label>
            <select id="brand" name="brand" onchange="fillOptions('model', 'modelsOfBrand-'+this.value, 0)">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("brand", "table-brands", ${ad.brandModel.brandId});</script>

        <div class="form-group">
            <label for="model">Model:</label>
            <select id="model" name="model">
                <option value="0">not selected</option>
            </select>
        </div>

        <script> fillOptions("model", "modelsOfBrand-"+${ad.brandModel.brandId}, ${ad.brandModel.modelId});</script>

        <div class="form-group">
            <label for="wheel">Wheel:</label>
            <select id="wheel" name="wheel.id">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("wheel", "table-wheel", ${ad.wheel.id});</script>

        <div class="form-group">
            <label for="drive">Drive:</label>
            <select id="drive" name="drive.id">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("drive", "table-drive", ${ad.drive.id});</script>

        <div class="form-group">
            <label for="colour">Colour:</label>
            <select id="colour" name="colour">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("colour", "table-colour", ${ad.colour.id});</script>

        <div class="form-group">
            <label for="carBody">Car body:</label>
            <select id="carBody" name="carBody">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("carBody", "table-car_body", ${ad.carBody.id});</script>

        <div class="form-group">
            <label for="engineType">Engine type:</label>
            <select id="engineType" name="engineType">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("engineType", "table-engine_type", ${ad.engineType.id});</script>

        <div class="form-group">
            <label for="transmission">Transmission type:</label>
            <select id="transmission" name="transmission">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("transmission", "table-transmission", ${ad.transmission.id});</script>

        <div class="form-inline">
            <label for="owners">Number of owners:</label>
            <input type="text" class="form-control" id="owners" title="owners" value="${ad.owners}">
        </div>
        <div class="form-inline">
            <label for="engineVolume">Engine volume:</label>
            <input type="text" class="form-control" id="engineVolume" title="engineVolume" value="${ad.engineVolume}">
        </div>

        <div class="form-inline">
            <label for="powerOfEngine">Power (horse):</label>
            <input type="text" class="form-control" id="powerOfEngine" title="year" value="${ad.powerOfEngine}">
        </div>

        <div class="form-inline">
            <label for="carMeleage">Meleage:</label>
            <input type="text" class="form-control" id="carMeleage" title="carMeleage" value="${ad.carMeleage}">
        </div>

        <div class="form-inline">
            <label for="year">Year:</label>
            <input type="text" name="year" class="form-control" id="year" title="year" value="${ad.year}">
        </div>

        <div class="form-inline">
            <label for="price">Price:</label>
            <input type="text" name="price" class="form-control" id="price" title="price" value="${ad.price}">
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" name="description" class="form-control" id="description" title="description" value="${ad.description}">
        </div>
        <div class="form-group">
            <img src="picture-${ad.id}.do" width="800"/>
        </div>
        <div class="form-inline">
            <label>Published: ${ad.published}</label>
        </div>

        <div class="form-inline">
            <label>Saled: ${ad.sale}</label>
        </div>
        <input type="submit">
    </form>
</div>
</body>
</html>
