<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="tb.etu.ir331.entities.Contrat"%>
<%@page import="tb.etu.ir331.services.IContratBean"%>
<%@page import="tb.etu.ir331.entities.Service"%>
<%@page import="tb.etu.ir331.services.IServiceBean"%>
<%@page import="tb.etu.ir331.entities.Employe"%>
<%@page import="tb.etu.ir331.web.tools.Generator"%>
<%@page import="tb.etu.ir331.web.tools.ServicesLocator"%>
<%@page import="tb.etu.ir331.services.IEmployeBean"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String result = "";
    String a = request.getParameter("a");
%>
<div>
    <h2> Index </h2>
</div>
<div>
    <h3>Scénario</h3>
    <a class="btn btn-primary" href="?a=scenario1">Scénario 1</a>
    <a class="btn btn-primary" href="?a=scenario2">Scénario 2</a>
    <a class="btn btn-primary" href="?a=scenario3">Scénario 3</a>
</div>
<div>
    <h3>Random action</h3>
    <a class="btn btn-primary" href="?a=rcreate_employe">Create random employe</a>
    <%
        if (a != null && a.equals("rcreate_employe")) {
            IEmployeBean employeBean = (IEmployeBean) ServicesLocator.getInstance().getRemoteInterface("EmployeBean");
            try {
                String prenom = Generator.generatePrenom();
                String nom = Generator.generateNom();
                result += "$ employeBean.create('" + prenom + "', '" + nom + "')\n";
                Employe e = employeBean.create(prenom, nom);
                result += "-> " + e.toString() + "\n";

            } catch (Exception ex) {
                result += ">>> Exception : " + ex.getLocalizedMessage() + "\n";
            }
        }
    %>
    <a class="btn btn-primary" href="?a=rcreate_service">Create random service</a>
    <%
        if (a != null && a.equals("rcreate_service")) {
            IServiceBean serviceBean = (IServiceBean) ServicesLocator.getInstance().getRemoteInterface("ServiceBean");
            try {
                String nom = Generator.generateServiceName();
                result += "$ serviceBean.create('" + nom + "','open', '-1', '-1')\n";
                Service s = serviceBean.create(nom, "open", "-1", "-1"); //No Parent, no REsp
                result += "-> " + s.toString() + "\n";

            } catch (Exception ex) {
                result += ">>> Exception : " + ex.getLocalizedMessage() + "\n";
            }
        }
    %>
    <a class="btn btn-primary" href="?a=rcreate_contrat">Create random contrat</a>
    <%
        if (a != null && a.equals("rcreate_contrat")) {
            IContratBean contratBean = (IContratBean) ServicesLocator.getInstance().getRemoteInterface("ContratBean");
            IEmployeBean employeBean = (IEmployeBean) ServicesLocator.getInstance().getRemoteInterface("EmployeBean");
            List<Integer> ids = new ArrayList<Integer>();
            for (Employe emp : employeBean.list()) {
                ids.add((int) (long)emp.getId());
            }
 
            try {
                int empId = (Integer) Generator.chooseFrom(ids.toArray());
                String etat = "waitsign";//TODO = Generator.generateContratEtat();
                String type = Generator.generateContratType();
                String startDate = Generator.generateDate();
                String endDate = Generator.generateDate();

                result += "$ contratBean.create("+empId+", '" + type + "', '" + etat + "', '" + startDate + "', '" + endDate + "')\n";
                Contrat c = contratBean.create(empId, type, etat, startDate, endDate);
                result += "-> " + c.toString() + "\n";

            } catch (Exception ex) {
                result += ">>> Exception : " + ex.getLocalizedMessage() + "\n";
            }
        }
    %>
</div>
<div id="result">
    <h3>Result</h3>
    <code><%=result.replaceAll("(\r\n|\r|\n|\n\r)", "<br>")%></code>
</div>