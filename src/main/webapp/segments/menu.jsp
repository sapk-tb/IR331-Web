<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<li class="dropdown <% if(request.getParameter("p") != null && request.getParameter("p").equals("service")) {%>active<%}%>">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="service">Services <span class="caret"></span></a>
    <ul class="dropdown-menu" aria-labelledby="service">
        <li><a href="?p=service">Liste des Services</a></li>
        <li><a href="?p=service&a=add">Nouveau Service</a></li>
    </ul>
</li>
<li class="dropdown <% if(request.getParameter("p") != null && request.getParameter("p").equals("employe")) {%>active<%}%>">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="employe">Employés <span class="caret"></span></a>
    <ul class="dropdown-menu" aria-labelledby="employe">
        <li><a href="?p=employe">Liste des Employés</a></li>
        <li><a href="?p=employe&a=add">Nouveau Employé</a></li>
    </ul>
</li>
<li class="dropdown <% if(request.getParameter("p") != null && request.getParameter("p").equals("contrat")) {%>active<%}%>">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="contrat">Contrats <span class="caret"></span></a>
    <ul class="dropdown-menu" aria-labelledby="contrat">
        <li><a href="?p=contrat">Liste des Contrats</a></li>
        <li><a href="?p=contrat&a=add">Nouveau Contrat</a></li>
    </ul>
</li>