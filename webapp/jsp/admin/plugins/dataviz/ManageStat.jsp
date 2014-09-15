<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="managestat" scope="session" class="fr.paris.lutece.plugins.dataviz.web.ManageStatJspBean" />

<% managestat.init( request, managestat.RIGHT_MANAGESTAT ); %>
<%= managestat.getManageStatHome ( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
