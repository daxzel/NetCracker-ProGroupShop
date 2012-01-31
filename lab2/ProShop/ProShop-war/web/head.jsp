<%-- 
    Document   : head
    Created on : 29.01.2012, 8:35:50
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/orbit.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/main.css">
<link href="<%=request.getContextPath()%>/static/dropdown.css" media="all" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/static/dropdown.vertical.css" media="all" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/static/default.css" media="all" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/static/menu.css" media="all" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/static/jquery.fancybox.css" rel="stylesheet" type="text/css"  media="screen" />


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/static/jquery.fancybox.js" type="text/javascript" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery.orbit.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
            $('.fancybox').fancybox();
    });
    
   

</script>

<script type="text/javascript">
    
        
        $(window).load(function() {
                $('#featured').orbit();
        });

         
</script>

