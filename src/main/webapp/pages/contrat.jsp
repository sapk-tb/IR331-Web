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
    <% if (a == null ) { %> <a href="?p=contrat&a=add" class="btn btn-default pull-right">Ajouter +</a><% } %>
    <h2> Contrats </h2>
</div>
<div>
    <%
        IContratBean contratBean = (IContratBean) ServicesLocator.getInstance().getRemoteInterface("ContratBean");
    if (a != null && a.equals("add")) {
    %> <h3> Add contrat :</h3> <%
        IEmployeBean employeBean = (IEmployeBean) ServicesLocator.getInstance().getRemoteInterface("EmployeBean");
    %>
        <form class="form-horizontal" action="index.jsp?p=contrat&a=add_confirm" method="POST" >
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
            <div class="form-group">
                <label for="startdate" class="col-lg-2 control-label">startdate</label>
                <div class="col-lg-10">
                    <input name="startdate" class="form-control" id="startdate" placeholder="startdate" type="date">
                </div>
            </div>
            <div class="form-group">
                <label for="enddate" class="col-lg-2 control-label">enddate</label>
                <div class="col-lg-10">
                    <input name="enddate" class="form-control" id="enddate" placeholder="enddate" type="date">
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
            <p>State : <%=c.getEtat()%></p>
            <p>Type : <%=c.getType()%></p>
            <p>Start Date : <%=c.getStartDate()%></p>
            <p>End Date : <%=c.getEndDate()%></p>
            <% if (c.getEtat().equals("waitsign")) { %>
                <a class="btn btn-primary" href="?p=contrat&a=waitsign&id=<%=c.getId()%>"> Lien de signature </a>
            <% } %>
            <!-- //TODO For CDI/CDD <a class="btn btn-success" href="?p=contrat&a=closed&id=<%=c.getId()%>"> Close </a> -->
            <%
        }
    } else if (a != null && a.equals("waitsign")) {
        // Signature par l'employé
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
        } else if (!c.getEtat().equals("waitsign")) {
            %>
            <p>Le contrat n'est pas en attente de signature.</p>
            <%
        } else {
            %>
            <p>ID : <%=c.getId()%></p>
            <p>Employé : <%=c.getEmploye().getPrenom()%> <%=c.getEmploye().getNom()%></p>
            <p>State : <%=c.getEtat()%></p>
            <p>Type : <%=c.getType()%></p>
            <p>Start Date : <%=c.getStartDate()%></p>
            <p>End Date : <%=c.getEndDate()%></p>
            <a class="btn btn-success" href="?p=contrat&a=sign_confirm&id=<%=c.getId()%>"> Signer </a>
            <%
        }
    } else if (a != null && a.equals("sign_confirm")) {
        // Validation signature par l'employé

        try {
            contratBean.sign(new Integer(request.getParameter("id")));
            %> <div class="alert alert-success" role="alert"><strong>OK !</strong></div> <script> window.setTimeout('window.location = "?p=contrat"', 2000);</script> <%
        } catch (Exception ex) {
            %> 
            <br><div class="alert alert-danger" role="alert"><strong>NOK !</strong> -> Details : <%=ex.getMessage()%></div>
            <%
        }

    } else if (a != null && a.equals("add_confirm")) {
        //TODO checkup var 
        try {
                contratBean.create(new Integer(request.getParameter("employe")), request.getParameter("type"), "waitsign", request.getParameter("startdate"), request.getParameter("enddate")); //Create with employe default state waitsign
                %> <div class="alert alert-success" role="alert"><strong>OK !</strong></div> <script> window.setTimeout('window.location = "?p=contrat"', 2000);</script> <%
        } catch (Exception ex) { //Alert and block
            %><br><div class="alert alert-warning" role="alert"><strong>NOK !</strong> Details : <%=ex.getMessage()%></div><%
        }
    } else {
    %> <h3> Contrat list : </h3>
    
    
        <table class="table table-striped table-hover">
            <thead>
                <tr><th>ID</th><th>Employe</th><th>Type</th><th>Start Date</th><th>End Date</th><th>State</th></tr>
            </thead>
            <tbody>
            <% for (Contrat c : contratBean.list()) { %>
                <tr>
                    <td><a href="?p=contrat&a=view&id=<%=c.getId()%>"><%=c.getId()%></a></td>
                    <td><a href="?p=employe&a=view&id=<%=c.getEmploye().getId()%>"><%=c.getEmploye().getPrenom()%> <%=c.getEmploye().getNom()%></a></td>
                    <td><%=c.getType()%></td>
                    <td><%=c.getStartDate()%></td>
                    <td><%=c.getEndDate()%></td>
                    <td><%=c.getEtat()%></td>
                </tr>
                <%   } %> 
            </tbody>
        </table> 
    <% } %>
</div>