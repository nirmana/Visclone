<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="cyclone.initialize.Project" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="refresh" content="0;url=pages/index.html">
<title>SB Admin 2</title>
<script language="javascript">
console.log('ssss');
   // window.location.href = "pages/index.html"
</script>
</head>
<body>
<%
Project project = (Project)session.getAttribute("Project");
%>
Hello<%  String[]  folderlist = project.getProjectStructure();
System.out.println(folderlist);
%>
<script type="text/javascript">

</script>
Go to <a href="pages/index.html">/pages/index.html</a>
</body>
</html>