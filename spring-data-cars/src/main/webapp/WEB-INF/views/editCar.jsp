<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                $(document.getElementById(dropBox))
                    .append($('<option>', { value : "0" })
                    .text("not selected"));
                $.getJSON("jsonData-"+table+"" , function(selectValues) {
                    $.each(selectValues, function(key, value) {
                        $(document.getElementById(dropBox))//$('#brand')
                            .append($('<option>', { value : this["id"]/*.toString()*/ })
                                .text(this["name"].toString()));
                    });
                    $(document.getElementById(dropBox)).val(activePosition.toString());
                });
                //alert("table - " + table);
            }
            function validate() {
                var elements = document.getElementById("my-form").elements;
                var errorText = "";
                var result = true;
                for (var i = 0, element; element = elements[i++];) {
                    if (element.value === "" || element.value === "0") {
                        errorText = errorText + "\t" + element.title + "\n\r"
                    }
                }
                if (errorText!="") {
                    alert("Заполните:\n\r" + errorText);
                    result = false;
                }
                return result;
            }

            $(document).ready(function () {
                $('#uploadFile').on('submit', function(e) {
                    e.preventDefault();
                    $.ajax({
                        url : $(this).attr('action') || window.location.pathname,
                        type: "post",
                        data: new FormData($("#uploadFile")[0]),
                        processData: false,
                        contentType: false,
                        async: false,
                        success: function (data) {
                            //$("#form_output").html(data);
                            d = new Date();
                            $("#carPicture").attr("src", "picture-0?"+d.getTime());
                        },
                        error: function (jXHR, textStatus, errorThrown) {
                            alert(errorThrown);
                        }
                    });
                });
                $("#carPicture").update();
            });

    </script>
</head>
<body>
<div class="container-fluid" id="main">
<h1>Add/edit</h1>
    <form action="add" id="my-form" method="post" onsubmit="return validate()">

        <div class="form-group">
            <label for="brand">Brand:</label>
            <select id="brand" name="brand" title="car brand" onchange="fillOptions('model', 'modelsOfBrand-'+this.value, 0)">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("brand", "table-brands", ${ad.brandModel.brandId});</script>

        <div class="form-group">
            <label for="model">Model:</label>
            <select id="model" title="model of car" name="model">
                <option value="0">not selected</option>
            </select>
        </div>

        <script> fillOptions("model", "modelsOfBrand-"+${ad.brandModel.brandId}, ${ad.brandModel.modelId});</script>

        <div class="form-group">
            <label for="wheel">Wheel:</label>
            <select id="wheel" title="wheel place" name="wheel.id">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("wheel", "table-wheel", ${ad.wheel.id});</script>

        <div class="form-group">
            <label for="drive">Drive:</label>
            <select id="drive" title="drive type" name="drive.id">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("drive", "table-drive", ${ad.drive.id});</script>

        <div class="form-group">
            <label for="colour">Colour:</label>
            <select id="colour" title="colour of car" name="colour.id">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("colour", "table-colour", ${ad.colour.id});</script>

        <div class="form-group">
            <label for="body">Car body:</label>
            <select id="body" title="body" name="body.id">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("body", "table-body", ${ad.body.id});</script>

        <div class="form-group">
            <label for="engine">Engine type:</label>
            <select id="engine" title="engine type" name="engine.id">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("engine", "table-engine", ${ad.engine.id});</script>

        <div class="form-group">
            <label for="transmission">Transmission type:</label>
            <select id="transmission" title="transmission type" name="transmission.id">
                <option value="0">not selected</option>
            </select>
        </div>
        <script> fillOptions("transmission", "table-transmission", ${ad.transmission.id});</script>

        <div class="form-inline">
            <label for="owners">Number of owners:</label>
            <input type="text" class="form-control" id="owners" name="owners" title="owners" value="${ad.owners}">
        </div>
        <div class="form-inline">
            <label for="engineVolume">Engine volume:</label>
            <input type="text" class="form-control" id="engineVolume" title="engineVolume" name="engineVolume" value="${ad.engineVolume}">
        </div>

        <div class="form-inline">
            <label for="powerOfEngine">Power (horse):</label>
            <input type="text" class="form-control" id="powerOfEngine" name="powerOfEngine" title="year" value="${ad.powerOfEngine}">
        </div>

        <div class="form-inline">
            <label for="carMeleage">Meleage:</label>
            <input type="text" class="form-control" id="carMeleage" title="carMeleage" name="carMeleage" value="${ad.carMeleage}">
        </div>

        <div class="form-inline">
            <label for="year">Year:</label>
            <input type="text" name="year" class="form-control" id="year" name="year" title="year" value="${ad.year}">
        </div>

        <div class="form-inline">
            <label for="price">Price:</label>
            <input type="text" name="price" class="form-control" id="price" name="price" title="price" value="${ad.price}">
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" name="description" class="form-control" id="description" name="description" title="description" value="${ad.description}">
        </div>
        <div class="form-group" >
            <img id="carPicture" src="picture-0" width="800"/>
        </div>



        <div class="form-inline">
            <label>Published: ${ad.published}</label>
        </div>

        <div class="form-inline">
            <label>Saled: ${ad.sale}</label>
        </div>
        <input type="submit" value="Add ad"/>
        <a href="index"> <input type="button" value="Back"/> </a>
    </form>
    <br>
    <form id="uploadFile" method="POST" action="uploadFile"  enctype="multipart/form-data" >
        Picture to upload: <input type="file" name="file" id="file" accept="image/jpeg"><br />

        <input type="submit" value="Upload"> Press here to upload the file!
    </form>
</div>


<div id="form_output">


</div>
</body>
</html>
