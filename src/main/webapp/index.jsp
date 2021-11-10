<%-- 
    Document   : index
    Created on : 9 nov. de 2021, 19:56:16
    Author     : miky_pro
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.DAO_Seminario"%>
<%
  
    List<DAO_Seminario> lista = (List<DAO_Seminario>)request.getAttribute("lista");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LSITADO DE SEMINARIOS </h1>
        <p><a href="MainController?op=nuevo">NUEVO </a> </p>
        <table border="1">
                <tr>
                    <th>ID</th>
                    <th>TITULO</th>
                    <th>EXPOSITOR</th>
                    <th>FECHA</th>
                    <th>HORAS</th>
                    <th>CUPOS</th>
                    
                    <th>EDITAR</th>
                    <th>ELIMINAR</th>
                </tr>
                <c:forEach var="item" items="${lista}">
                <tr>
                    <th> ${item.id}</th>
                    <th> ${item.titulo}</th>
                    <th>${item.expositor}</th>
                    <th>${item.fecha}</th>
                    <th>${item.horas}</th>
                    <th>${item.cupos}</th>
                    <th><a href="MainController?op=editar&id=${item.id}">EDITAR</a></th>
                    <th><a href="MainController?op=eliminar&id=${item.id}
                           " onclick="return(confirm('estas seguro de eliminar?'))">ELIMINAR</a></th>
                </tr>
                </c:forEach>
           
        </table>
    </body>
</html>