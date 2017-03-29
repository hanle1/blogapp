<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	response.setHeader("progma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
%>

<c:set var="ctx" value="<%=request.getContextPath()%>" />
<link href="${ctx }/css/login.css" rel="stylesheet" type="text/css">
<script src="${ctx }/js/jquery.min.js" type="text/javascript"></script>

