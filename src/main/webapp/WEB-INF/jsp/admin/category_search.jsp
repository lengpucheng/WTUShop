<%--@elvariable id="category" type="javax.xml.stream.util.StreamReaderDelegate"--%>
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
    <meta charset="UTF-8">
    <title>客户列表</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            search();
        });


        function search() {
            $.getJSON(
                "${pageContext.request.contextPath}/admin/category/doSearch",
                {name: $("#nameKey").val()},
                function (json) {
                    if (json.statusCode !== 200) {
                        alert("获取数据失败！" + json.msg);
                        return false;
                    }
                    for(let i=0; i<json.data.list.length; i++){
                        let category=json.data.list[i];
                        $("#searchVar").append(`
                        <tr>
                        <td>${category.id}</td>
                        <td>${category.name}</td>
                        <td>${category.goodsNum}</td>
                        <td><a href="" >删除</a>&nbsp;<a href="" >编辑</a></td>
                        </tr>
                        `);
                    }


                },
                "json"
            );
        }


    </script>
</head>
<body>
<h1>客户列表</h1>
搜索：<label for="nameKey"></label><input type="text" id="nameKey">&nbsp;&nbsp;
<button id="search">搜索</button>&nbsp;
<button id="reset">恢复</button>
&nbsp;共找到<span id="sumRows" style="color: crimson"></span>条数据

<table id="listCustomer">
    <thead>
    <th>ID</th>
    <th>商品名称</th>
    <th>商品数量</th>
    <th>操作</th>
    </thead>
    <tbody id="searchVar">
    <%-- 预留数据 --%>

    </tbody>
    <tfoot>
    <%-- 预留尾部数据 --%>

    </tfoot>

</table>

</body>
</html>