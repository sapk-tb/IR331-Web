<%@page import="tb.etu.ir331.entities.Contrat"%>
<%@page import="tb.etu.ir331.entities.Employe"%>
<%@page import="tb.etu.ir331.services.IEmployeBean"%>
<%@page import="tb.etu.ir331.web.tools.ServicesLocator"%>
<%@page import="tb.etu.ir331.services.IContratBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String a = request.getParameter("a");
%>
<div>
    <% if (a == null || !a.startsWith("add")) { %> <a href="?p=contrat&a=add" class="btn btn-default pull-right">Ajouter +</a><% } %>
    <h2> Contrats </h2>
</div>
<div>
    <%
        IContratBean contratBean = (IContratBean) ServicesLocator.getInstance().getRemoteInterface("ContratBean");
    if (a != null && a.equals("add")) {
    %> <h3> Add contrat :</h3> <%
        IEmployeBean employeBean = (IEmployeBean) ServicesLocator.getInstance().getRemoteInterface("EmployeBean");
    %>
        <form class="form-horizontal" action="index.jsp?p=employe&a=add_confirm" method="POST" >
        <fieldset>
            <div class="form-group">
                <label for="employe" class="col-lg-2 control-label">Employé</label>
                <div class="col-lg-10">
                    <select class="form-control" id="employe" name="employe">
                        <% for (Employe e : employeBean.list()) {%>
                        <option value="<%=e.getId()%>"><%=e.getPrenom()%> <%=e.getNom()%></option>
                        <% } %>
                    </select>
                    <br>
                </div>
            </div>
            <div class="form-group">
                <label for="type" class="col-lg-2 control-label">Type</label>
                <div class="col-lg-10">
                    <select class="form-control" id="type" name="type">
                        <option>CDD</option>
                        <option>CDI</option>
                    </select>
                    <br>
                </div>
            </div>
            <div class="form-group pull-right">
                <div class="col-lg-10 col-lg-offset-2">
                    <button type="reset" class="btn btn-default">Cancel</button>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </fieldset>
    </form>
    <%
    } else if (a != null && a.equals("view")) {
        Contrat c = null;
        try {
            c = contratBean.getContrat(new Integer(request.getParameter("id")));
        } catch (Exception ex) {
            %> 
            <br><div class="alert alert-danger" role="alert"><strong>NOK !</strong> -> Details : <%=ex.getMessage()%></div>
            <%
        }
        if (c == null) {
    %>
    <p>Contrat Not Found</p>
    <%
    } else {
    %>
    <p>Employé : <%=c.getEmploye().getPrenom()%> <%=c.getEmploye().getNom()%></p>
    <p>Type : <%=c.getType()%></p>
    <p>Date : <%=c.getDate()%></p>
    <%
        }

    } else if (a != null && a.equals("add_confirm")) {
        //TODO checkup var 
        //TODO add date debut fin
        Employe e = null;
        if (request.getParameter("employe") != null && !request.getParameter("employe").equals("-1")) {
            IEmployeBean employeBean = (IEmployeBean) ServicesLocator.getInstance().getRemoteInterface("EmployeBean");
            try {
                e = employeBean.getEmploye(new Integer(request.getParameter("employe")));
                contratBean.create(e, request.getParameter("type")); //Create with employe default state waitsign
                %> <div class="alert alert-success" role="alert"><strong>OK !</strong></div> <script> window.setTimeout('window.location = "?p=contrat"', 2000);</script> <%
            } catch (Exception ex) { //Alert and block
                %> 
                <br><div class="alert alert-warning" role="alert"><strong>NOK !</strong> -> Employe to attach (<%=request.getParameter("employe")%>) not found Details : <%=ex.getMessage()%></div>
                <%
            }
        } else {
                %> 
                <br><div class="alert alert-warning" role="alert"><strong>NOK !</strong> -> Employe not defined </div>
                <%
        }
    } else {
    %> <h3> Contrat list : </h3>
        <table class="table table-striped table-hover">
        <thead><tr><th>ID</th><th>Employe</th>><th>Date</th><th>State</th></tr></thead>
        <tbody>
            <% for (Contrat c : contratBean.list()) { %>
                <tr>
                    <td><a href="?p=contrat&a=view&id=<%=c.getId()%>"><%=c.getId()%></a></td>
                    <td><a href="?p=employe&a=view&id=<%=c.getEmploye().getId()%>"><%=c.getEmploye().getPrenom()%> <%=c.getEmploye().getNom()%></a></td>
                    <td><%=c.getDate()%></td>
                    <td><%=c.getEtat()%></td>
                </tr>
                <%   } %> 
        </tbody></table> 
    <% } %>
</div>