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
    <% if (a == null || !a.startsWith("add")) { %> <a href="?p=service&a=add" class="btn btn-default pull-right">Ajouter +</a><% } %>
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
                    <label for="responsable" class="col-lg-2 control-label">Responsable</label>
                    <div class="col-lg-10">
                      <select class="form-control" id="responsable">
                        <option>None</option>
                        <option>TODO</option>
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
        } else if (a != null && a.equals("add_confirm")) {
            //TODO checkup var 
            serviceBean.create(request.getParameter("nom"), "open"); //No responsable for now 
            %> TODO <%
        } else {
            //List services
            %> <h3> Service list :</h3> <%
            //*
            %> 
            <table class="table table-striped table-hover">
                <thead><tr><th>ID</th><th>Nom</th><th>Etat</th><th>Responsable</th></tr></thead>
                <tbody>
            <%
                
            for (Service s : serviceBean.list()) {
                %><tr><td><%=s.toString()%></td></tr><%
            }
                
            %> </tbody></table> <%
            //*/
        }
    %>
</div>
