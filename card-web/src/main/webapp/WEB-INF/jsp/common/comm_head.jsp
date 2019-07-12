<%@ page import="com.fh.util.StringUtil" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%--<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">--%>
<%
    String scheme = "";
    String port = "";
    String path = request.getContextPath();
    Integer serverPort = request.getServerPort();
    String URL = request.getRequestURL().toString();
    if(!URL.toLowerCase().startsWith("https:")) {
        scheme = request.getScheme() + ":";
    }
    if((null !=serverPort && 80 != serverPort)){
        port = ":"+String.valueOf(serverPort);
    }
    String basePath = scheme +"//"+request.getServerName()+port+path+"/";
%>
<script>
    var ctx = "<%=basePath%>";
</script>
