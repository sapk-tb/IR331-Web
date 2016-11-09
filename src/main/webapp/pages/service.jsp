<%@page import="tb.etu.ir331.entities.Employe"%>
<%@page import="tb.etu.ir331.services.IEmployeBean"%>
<%@page import="tb.etu.ir331.web.tools.ServicesLocator"%>
<%@page import="tb.etu.ir331.services.IServiceBean"%>
<%@page import="tb.etu.ir331.entities.Service"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String a = request.getParameter("a");
%>
<div>
    <% if (a == null) { %> <a href="?p=service&a=add" class="btn btn-default pull-right">Ajouter +</a><% } %>
    <h2> Service </h2>
</div>

<div>
    <%
        IServiceBean serviceBean = (IServiceBean) ServicesLocator.getInstance().getRemoteInterface("ServiceBean");

        if (a != null && a.equals("add")) {
    %> <h3> Add service :</h3> <%
        IEmployeBean employeBean = (IEmployeBean) ServicesLocator.getInstance().getRemoteInterface("EmployeBean");
    %>
    <form class="form-horizontal" action="index.jsp?p=service&a=add_confirm" method="POST" >
        <fieldset>
            <div class="form-group">
                <label for="nom" class="col-lg-2 control-label">Nom</label>
                <div class="col-lg-10">
                    <input name="nom" class="form-control" id="nom" placeholder="Nom" type="text">
                    <input name="etat" id="etat" value="open" type="hidden">
                </div>
            </div>
            <div class="form-group">
                <label for="parent" class="col-lg-2 control-label">Parent</label>
                <div class="col-lg-10">
                    <select class="form-control" id="parent" name="parent">
                        <option value="-1">Aucun</option>
                        <% for (Service s : serviceBean.list()) {%>
                        <option value="<%=s.getId()%>"><%=s.getNom()%></option>
                        <% } %>
                    </select>
                    <br>
                </div>
            </div>
            <div class="form-group">
                <label for="responsable" class="col-lg-2 control-label">Responsable</label>
                <div class="col-lg-10">
                    <select class="form-control" id="responsable" name="responsable" disabled="disabled">
                        <option value="-1">Aucun</option>
                        <% for (Employe e : employeBean.list()) {%>
                        <option value="<%=e.getId()%>"><%=e.getPrenom()%> <%=e.getNom()%></option>
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
    } else if (a != null && a.equals("setParent")) {
        Service s = null;
        try {
                s = serviceBean.getService(new Integer(request.getParameter("id")));
        } catch (Exception ex) {
            %> 
                <br><div class="alert alert-danger" role="alert"><strong>NOK !</strong> -> Details : <%=ex.getMessage()%></div>
            <%
        }

        if (s == null) {
            %>
            <p>Service Not Found</p>
            <%
        } else {
            %>
            <br>
            <form class="form-horizontal" action="index.jsp?p=service&a=setParent_confirm" method="POST" >
                <input name="id" id="id" value="<%=s.getId()%>" type="hidden">
                <fieldset>
                    <div class="form-group">
                        <label for="nom" class="col-lg-2 control-label">Nom</label>
                        <div class="col-lg-10">
                            <input name="nom" class="form-control" id="nom"  disabled="disabled" value="<%=s.getNom()%>" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="etat" class="col-lg-2 control-label">Etat</label>
                        <div class="col-lg-10">
                            <input name="etat" class="form-control" id="etat"  disabled="disabled" value="<%=s.getEtat()%>" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="parent" class="col-lg-2 control-label">Parent</label>
                        <div class="col-lg-10">
                            <select class="form-control" id="parent" name="parent">
                                <option value="-1">Aucun</option>
                                <% for (Service s2 : serviceBean.list()) {%>
                                <option value="<%=s2.getId()%>"><%=s2.getNom()%></option>
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
    } else if (a != null && a.equals("setResp")) {
        Service s = null;
        try {
                s = serviceBean.getService(new Integer(request.getParameter("id")));
        } catch (Exception ex) {
            %> 
                <br><div class="alert alert-danger" role="alert"><strong>NOK !</strong> -> Details : <%=ex.getMessage()%></div>
            <%
        }

        if (s == null) {
            %>
            <p>Service Not Found</p>
            <%
        } else {
                IEmployeBean employeBean = (IEmployeBean) ServicesLocator.getInstance().getRemoteInterface("EmployeBean");
            %>
            <br>
            <form class="form-horizontal" action="index.jsp?p=service&a=setResp_confirm" method="POST" >
                <input name="id" id="id" value="<%=s.getId()%>" type="hidden">
                <fieldset>
                    <div class="form-group">
                        <label for="nom" class="col-lg-2 control-label">Nom</label>
                        <div class="col-lg-10">
                            <input name="nom" class="form-control" id="nom"  disabled="disabled" value="<%=s.getNom()%>" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="etat" class="col-lg-2 control-label">Etat</label>
                        <div class="col-lg-10">
                            <input name="etat" class="form-control" id="etat"  disabled="disabled" value="<%=s.getEtat()%>" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="responsable" class="col-lg-2 control-label">Responsable</label>
                        <div class="col-lg-10">
                            <select class="form-control" id="responsable" name="responsable">
                                <% for (Employe e : serviceBean.getEmpList(s)) {%>
                                <option value="<%=e.getId()%>"><%=e.getPrenom()%> <%=e.getNom()%></option>
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
    } else if (a != null && a.equals("setResp_confirm")) {        
        //TODO checkup var 
        if (request.getParameter("responsable") != null && !request.getParameter("responsable").equals("-1")) {
            try {
                serviceBean.setResp(new Integer(request.getParameter("id")),new Integer(request.getParameter("responsable")));
//.attach(new Integer(request.getParameter("id")),new Integer(request.getParameter("service")));
                %> <div class="alert alert-success" role="alert"><strong>OK !</strong></div> <% 
            } catch (Exception ex) { //Alert and block
                %> 
                <br><div class="alert alert-warning" role="alert"><strong>NOK !</strong> -> Details : <%=ex.getMessage()%></div>
                <%
            }
        } else {
                %> 
                <br><div class="alert alert-warning" role="alert"><strong>NOK !</strong> -> Employe to attach not given !</div>
                <%        }
                %><script> window.setTimeout('window.location = "?p=service"', 2000);</script> <% 
    }  else if (a != null && a.equals("setParent_confirm")) {        
            try {
                serviceBean.setParent(request.getParameter("id"),request.getParameter("parent"));
                %> <div class="alert alert-success" role="alert"><strong>OK !</strong></div> <% 
                %><script> window.setTimeout('window.location = "?p=service"', 5000);</script> <% 
            } catch (Exception ex) { //Alert and block
                %> 
                <br><div class="alert alert-warning" role="alert"><strong>NOK !</strong> -> Details : <%=ex.getMessage()%></div>
                <%
            }
    } else if (a != null && a.equals("view")) {
        //TODO checkup var 
        Service s = null;
        try {
                s = serviceBean.getService(new Integer(request.getParameter("id")));
        } catch (Exception ex) {
            %> 
                <br><div class="alert alert-danger" role="alert"><strong>NOK !</strong> -> Details : <%=ex.getMessage()%></div>
            <%
        }

        if (s == null) {
            %>
            <p>Service Not Found</p>
            <%
        } else {
            %>
            <p>Service : <%=s.getNom()%></p>
            <p>Parent : <% if (s.getParent() != null) {%><%=s.getParent().getNom()%><%}%> <a class="btn  btn-default btn-sm" href="?p=service&a=setParent&id=<%=s.getId()%>"> Change </a></p>
            <p>Responsable : <% if (s.getResponsable() != null) {%><%=s.getResponsable().getPrenom()%> <%=s.getResponsable().getNom()%><%}%> <a class="btn  btn-default btn-sm" href="?p=service&a=setResp&id=<%=s.getId()%>"> Change </a></p>
            <p>Employés : </p>
            <table class="table table-striped table-hover">
              <thead><tr><th>ID</th><th>Prénom</th><th>Nom</th></tr></thead>
              <tbody>
                <% for (Employe e : serviceBean.getEmpList(s)) {%>
                <tr><td><a href="?p=employe&a=view&id=<%=e.getId()%>"><%=e.getId()%></a></td><td><%=e.getPrenom()%></td><td><%=e.getNom()%><td></tr>
                <% } %>
                </tbody>
            </table>
            <%
        }
    } else if (a != null && a.equals("add_confirm")) {

        try {
            serviceBean.create(request.getParameter("nom"), "open", request.getParameter("parent"), request.getParameter("responsable"));
            %> 
                <div class="alert alert-success" role="alert"><strong>OK !</strong></div>
                <script> window.setTimeout('window.location = "?p=service"', 1000);</script> 
            <%
        } catch (Exception ex) {
            %> 
                <div class="alert alert-danger" role="alert"><strong>NOK !</strong> -> <%=ex.getLocalizedMessage()%></div>
                <script> window.setTimeout('window.location = "?p=service"', 5000);</script>
            <%
        }
    } else {
        //List services
    %> <h3> Service list :</h3> <%
        //*
    %> 
    <table class="table table-striped table-hover">
        <thead><tr><th>ID</th><th>Nom</th><th>Etat</th><th>Parent</th><th>Responsable</th><th>Nb Emp.</th></tr></thead>
        <tbody>
            <%                for (Service s : serviceBean.list()) {
            %><tr>
                <td><a href="?p=service&a=view&id=<%=s.getId()%>"><%=s.getId()%></a></td>
                <td><%=s.getNom()%></td>
                <td><%=s.getEtat()%></td>
                <td><% if (s.getParent() != null) {%><a href="?p=service&a=view&id=<%=s.getParent().getId()%>"><%=s.getParent().getNom()%></a><%}%></td>
                <td><% if (s.getResponsable() != null) {%><a href="?p=employe&a=view&id=<%=s.getResponsable().getId()%>"><%=s.getResponsable().getPrenom()%> <%=s.getResponsable().getNom()%></a><%}%></td>
                <td><%=serviceBean.getNbEmp(s)%></td>
            </tr><%
                }

                                    %> </tbody></table> <%                    //*/
                                        }
            %>
</div>
