<%--
  Created by IntelliJ IDEA.
  User: lpc
  Date: 2020/7/30
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>类别添加</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script>
    $(function () {
        $("#btn_submit").click(function () {
            $.getJSON(
                "${pageContext.request.contextPath}/admin/category/doAdd",
                {name:$("#name").val()},
                function (json) {
                    if (json.statusCode===200) {
                        location.href="${pageContext.request.contextPath}/admin/category/manager";
                        //正常响应
                    }else {
                        alert(json.msg);
                    }
                },
                "json"
            );
        })
    });



</script>
</head>
<body>
<h3>类别添加</h3>
<form action="${pageContext.request.contextPath}/admin/category/doAdd" method="post">
    类别名：<label for="name"></label><input id="name" type="text" name="name"><br>
    <input id="btn_submit" type="button" value="添加">
</form>
</body>
</html>
