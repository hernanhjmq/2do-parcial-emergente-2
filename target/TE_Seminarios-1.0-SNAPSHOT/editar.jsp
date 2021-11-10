
<%-- 
    Document   : editar
    Created on : 9 nov. de 2021, 20:48:34
    Author     : miky_pro
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.DAO_Seminario"%>
<%
  
    DAO_Seminario semi = (DAO_Seminario)request.getAttribute("semi");
%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><c:if test="${semi.id ==0}">
                Nuevo 
            </c:if>
            <c:if test="${semi.id !=0}">
                Editar 
            </c:if></h1>
        <form action="MainController" method="post">
            <input type="hidden" name="id" value="${semi.id}">
            
            <table border="1">
                <tr>
                    <th>TITULO</th>
                    <th><input type="text" name="titulo" value="${semi.titulo}"></th>
                </tr>
                  <tr>
                    <th>EXPOSITOR</th>
                    <th><input type="text" name="expositor" value="${semi.expositor}"></th>
                </tr>
                  <tr>
                    <th>FECHA</th>
                    <th><input type="date" name="fecha" value="${semi.fecha}"></th>
                </tr>
                  <tr>
                    <th>HORAS</th>
                    <th><input type="text" name="horas" value="${semi.horas}"></th>
                </tr>
                  <tr>
                    <th>CUPOS</th>
                    <th><input type="number" name="cupos" value="${semi.cupos}"></th>
                </tr>
                 <tr>
                    <th></th>
                    <th><input type="submit"  value="enviar"></th>
                </tr>
               
           
        </table> 
        </form>
    </body>
</html>
