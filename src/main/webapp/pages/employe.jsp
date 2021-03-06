<%@page import="tb.etu.ir331.entities.Contrat"%>
<%@page import="tb.etu.ir331.entities.Service"%>
<%@page import="tb.etu.ir331.services.IServiceBean"%>
<%@page import="tb.etu.ir331.entities.Employe"%>
<%@page import="tb.etu.ir331.web.tools.ServicesLocator"%>
<%@page import="tb.etu.ir331.services.IEmployeBean"%>
<%@page import="tb.etu.ir331.services.IEmployeBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String a = request.getParameter("a");
%>
<div>
    <% if (a == null ) { %> <a href="?p=employe&a=add" class="btn btn-default pull-right">Ajouter +</a><% } %>
    <% if (a == null ) { %> <a href="?p=employe&a=addextend" class="btn btn-default pull-right">Ajouter (extended) +</a><% } %>
    <h2> Employés </h2>
</div>

<div>
    <%
        IEmployeBean employeBean = (IEmployeBean) ServicesLocator.getInstance().getRemoteInterface("EmployeBean");
        if (a != null && a.equals("add")) {
    %> <h3> Add employé :</h3> <%
        IServiceBean serviceBean = (IServiceBean) ServicesLocator.getInstance().getRemoteInterface("ServiceBean");
    %>
    <form class="form-horizontal" action="index.jsp?p=employe&a=add_confirm" method="POST" >
        <fieldset>
            <div class="form-group">
                <label for="prenom" class="col-lg-2 control-label">Prénom</label>
                <div class="col-lg-10">
                    <input name="prenom" class="form-control" id="prenom" placeholder="Prénom" type="text">
                </div>
            </div>
            <div class="form-group">
                <label for="nom" class="col-lg-2 control-label">Nom</label>
                <div class="col-lg-10">
                    <input name="nom" class="form-control" id="nom" placeholder="Nom" type="text">
                </div>
            </div>
            <div class="form-group">
                <label for="service" class="col-lg-2 control-label">Service</label>
                <div class="col-lg-10">
                    <select class="form-control" id="service" name="service">
                        <option value="-1">Aucun</option>
                        <% for (Service s : serviceBean.list()) {%>
                        <option value="<%=s.getId()%>"><%=s.getNom()%></option>
                        <% } %>
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

    } else if (a != null && a.equals("addextend")) {
    %> <h3> Add employé :</h3> <%
        IServiceBean serviceBean = (IServiceBean) ServicesLocator.getInstance().getRemoteInterface("ServiceBean");
    %>
    <form class="form-horizontal" action="index.jsp?p=employe&a=addextend_confirm" method="POST" >
        <fieldset>
            <div class="form-group">
                <label for="prenom" class="col-lg-2 control-label">Prénom</label>
                <div class="col-lg-10">
                    <input name="prenom" class="form-control" id="prenom" placeholder="Prénom" type="text">
                </div>
            </div>
            <div class="form-group">
                <label for="nom" class="col-lg-2 control-label">Nom</label>
                <div class="col-lg-10">
                    <input name="nom" class="form-control" id="nom" placeholder="Nom" type="text">
                </div>
            </div>
            <div class="form-group">
                <label for="service" class="col-lg-2 control-label">Service</label>
                <div class="col-lg-10">
                    <select class="form-control" id="service" name="service">
                        <option value="-1">Aucun</option>
                        <% for (Service s : serviceBean.list()) {%>
                        <option value="<%=s.getId()%>"><%=s.getNom()%></option>
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

    } else if (a != null && a.equals("attach")) {
        Employe e = null;
        try {
            e = employeBean.getEmploye(new Integer(request.getParameter("id")));
        } catch (Exception ex) {
            %> 
            <br><div class="alert alert-danger" role="alert"><strong>NOK !</strong> -> Details : <%=ex.getMessage()%></div>
            <%
        }
        if (e == null) {
            %>
            <p>Employe Not Found</p>
            <%
        } else {
            IServiceBean serviceBean = (IServiceBean) ServicesLocator.getInstance().getRemoteInterface("ServiceBean");
            %>
            <br>
            <form class="form-horizontal" action="index.jsp?p=employe&a=attach_confirm" method="POST" >
                <input name="id" id="id" value="<%=e.getId()%>" type="hidden">
                <fieldset>
                    <div class="form-group">
                        <label for="prenom" class="col-lg-2 control-label">Prénom</label>
                        <div class="col-lg-10">
                            <input name="prenom" class="form-control" id="prenom" type="text" disabled="disabled" value="<%=e.getPrenom()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nom" class="col-lg-2 control-label">Nom</label>
                        <div class="col-lg-10">
                            <input name="nom" class="form-control" id="nom" type="text" disabled="disabled" value="<%=e.getNom()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="service" class="col-lg-2 control-label">Service</label>
                        <div class="col-lg-10">
                            <select class="form-control" id="service" name="service">
                                <% for (Service s : serviceBean.list()) { //TODO add previous %>
                                <option value="<%=s.getId()%>"><%=s.getNom()%></option>
                                <% } %>
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
        }
    } else if (a != null && a.equals("view")) {
        //TODO checkup var 
        Employe e = null;
        try {
            e = employeBean.getEmploye(new Integer(request.getParameter("id")));
        } catch (Exception ex) {
            %> 
            <br><div class="alert alert-danger" role="alert"><strong>NOK !</strong> -> Details : <%=ex.getMessage()%></div>
            <%
        }
        if (e == null) {
            %> <p>Employe Not Found</p> <%
        } else {
            %>
            <p>Identité : <%=e.getPrenom()%> <%=e.getNom()%></p>
            <p>Service : <% if (e.getService() != null) {%><%=e.getService().getNom()%><%}%> <a class="btn  btn-default btn-sm" href="?p=employe&a=attach&id=<%=e.getId()%>"> Change </a></p>
            <p>Détails : <%=e.getDetails()%></p>
            <p>Contrats :</p>
            <table class="table table-striped table-hover">
              <thead><tr><th>ID</th><th>Type</th><th>Etat</th><th>StartDate</th><th>EndDate</th></tr></thead>
              <tbody>
                <% for (Contrat c : employeBean.getContratList(e)) { %>
                    <tr>
                        <td><a href="?p=contrat&a=view&id=<%=c.getId()%>"><%=c.getId()%></a></td>
                        <td><%=c.getType()%></td>
                        <td><%=c.getEtat()%></td>
                        <td><%=c.getStartDate()%></td>
                        <td><%=c.getEndDate()%></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
            <%
        }

    } else if (a != null && a.equals("attach_confirm")) {
        //TODO checkup var 
        if (request.getParameter("service") != null && !request.getParameter("service").equals("-1")) {
            try {
                employeBean.attach(new Integer(request.getParameter("id")),new Integer(request.getParameter("service")));
                %> <div class="alert alert-success" role="alert"><strong>OK !</strong></div> <% 
            } catch (Exception ex) { //Alert and block
                %> 
                <br><div class="alert alert-warning" role="alert"><strong>NOK !</strong> -> Details : <%=ex.getMessage()%></div>
                <%
            }
        } else {
                %> 
                <br><div class="alert alert-warning" role="alert"><strong>NOK !</strong> -> Service to attach not given !</div>
                <%        }
                %><script> window.setTimeout('window.location = "?p=employe"', 2000);</script> <% 

    } else if (a != null && a.equals("add_confirm")) {
        //TODO checkup var 
        Service s = null;
        if (request.getParameter("service") != null && !request.getParameter("service").equals("-1")) {
            IServiceBean serviceBean = (IServiceBean) ServicesLocator.getInstance().getRemoteInterface("ServiceBean");
            try {
                s = serviceBean.getService(new Integer(request.getParameter("service")));
                employeBean.create(request.getParameter("prenom"), request.getParameter("nom"), s); //Create with service
                %> <div class="alert alert-success" role="alert"><strong>OK !</strong></div> <script> window.setTimeout('window.location = "?p=employe"', 2000);</script> <%
            } catch (Exception ex) { //Alert and block
                %> 
                <br><div class="alert alert-warning" role="alert"><strong>NOK !</strong> -> Service to attach (<%=request.getParameter("service")%>) not found Details : <%=ex.getMessage()%></div>
                <%
            }
        } else {
            try {
                employeBean.create(request.getParameter("prenom"), request.getParameter("nom")); //Create without Service defined
                %> <div class="alert alert-success" role="alert"><strong>OK !</strong></div> <script> window.setTimeout('window.location = "?p=employe"', 2000);</script> <%
            } catch (Exception ex) { //Alert and block
                %> 
                <br><div class="alert alert-warning" role="alert"><strong>NOK !</strong> -> Details : <%=ex.getMessage()%></div>
                <%
            } 

        }
    } else if (a != null && a.equals("addextend_confirm")) {
        //TODO checkup var 
        Service s = null;
        if (request.getParameter("service") != null && !request.getParameter("service").equals("-1")) {
            IServiceBean serviceBean = (IServiceBean) ServicesLocator.getInstance().getRemoteInterface("ServiceBean");
            try {
                s = serviceBean.getService(new Integer(request.getParameter("service")));
                employeBean.create(request.getParameter("prenom"), request.getParameter("nom"), s, request.getParameter("type"),"waitsign", request.getParameter("startdate"), request.getParameter("enddate")); //Create with service
                %> <div class="alert alert-success" role="alert"><strong>OK !</strong></div> <script> window.setTimeout('window.location = "?p=employe"', 2000);</script> <%
            } catch (Exception ex) { //Alert and block
                %> 
                <br><div class="alert alert-warning" role="alert"><strong>NOK !</strong> -> Service to attach (<%=request.getParameter("service")%>) not found Details : <%=ex.getMessage()%></div>
                <%
            }
        } else {
            try {
                employeBean.create(request.getParameter("prenom"), request.getParameter("nom")); //Create without Service defined
                %> <div class="alert alert-success" role="alert"><strong>OK !</strong></div> <script> window.setTimeout('window.location = "?p=employe"', 2000);</script> <%
            } catch (Exception ex) { //Alert and block
                %> 
                <br><div class="alert alert-warning" role="alert"><strong>NOK !</strong> -> Details : <%=ex.getMessage()%></div>
                <%
            } 

        }
    } else {
        //List employes
        %> 
        <h3> Employe list :</h3> 
        <table class="table table-striped table-hover">
            <thead><tr><th>ID</th><th>Prénom</th><th>Nom</th><th>Service</th></tr></thead>
            <tbody>
            <% for (Employe e : employeBean.list()) { %>
                <tr>
                    <td><a href="?p=employe&a=view&id=<%=e.getId()%>"><%=e.getId()%></a></td>
                    <td><%=e.getPrenom()%></td><td><%=e.getNom()%></td>
                    <td><% if (e.getService() != null) {%><a href="?p=service&a=view&id=<%=e.getService().getId()%>"><%=e.getService().getNom()%></a><%}%></td>
                </tr>
            <% } %> 
            </tbody>
        </table> 
    <% } %>
</div>