<jsp:useBean id="managestatStat" scope="session" class="fr.paris.lutece.plugins.dataviz.web.StatJspBean" />
<% String strContent = managestatStat.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
