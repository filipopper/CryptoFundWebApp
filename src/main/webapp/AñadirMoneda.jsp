<%@page import="entidades.OperacionEnidad"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="modelo.*"%>
<%@page import="entidades.MonedaEntidad"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="es">
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="CSS/TablaRes/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="CSS/TablaRes/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="CSS/TablaRes/animate.css">
    <link rel="stylesheet" type="text/css" href="CSS/TablaRes/select2.min.css">
    <link rel="stylesheet" type="text/css" href="CSS/TablaRes/perfect-scrollbar.css">
    <link rel="stylesheet" type="text/css" href="CSS/TablaRes/util.css">
    <link rel="stylesheet" type="text/css" href="CSS/TablaRes/main.css">
    <link rel="stylesheet" href="CSS/style.css">
    <link rel="stylesheet" href="CSS/add.css">
    <link rel="stylesheet" href="CSS/addmone.css">
    <link rel="shortcut icon" href="CSS/bitlogo.ico" />
    <script src="js/main.js"></script>
    <title>CryptoManager</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
    <div class="baner">
        <div>
            <h1>CryptoManager</h1>
            <h2>A�adir Moneda</h2>
        </div>

    </div>
    <div id="formdiv">
    	<%
			OperacionEnidad oprando = (OperacionEnidad) request.getAttribute("Tranacion");
    		
		%>
        <form action="${pageContext.request.contextPath}/CrearMoneda" method="post">

            <h3>Nik:</h3>
            <input class="InputText" type="text" value=<%=oprando.getNik()%> name="nik" id="nik" autocomplete="off">
            <h3>Nombre:</h3>
            <input class="InputText" type="text" name="name" value=<%=oprando.getNombre()%> id="name" autocomplete="off">
            <h3>URL de Logo:</h3>
            <input class="InputText" type="text" name="logo" id="logo" autocomplete="off" onchange="isImage()">
            <h6 id="valid">Imagen no valida</h6>
            <input class="casper" type="text" name="cantidad" value=<%=oprando.getCantidad()%>>
            <input class="casper" type="text" name="precio" value=<%=oprando.getPrice()%>>
            <input class="casper" type="text" name="mode" value=<%=oprando.getMode()%>>
            <button type="submit">A�adir Moneda</button>
        </form>
    </div>
    <div class="Botones">
        <div class="butts">
            <a id="coin" href="${pageContext.request.contextPath}/ListarMonedas">Monedas</a>
            <a id="opp" href="${pageContext.request.contextPath}/ListarTransaciones">Operaci�nes</a>
        </div>
    </div>
</body>

</html>