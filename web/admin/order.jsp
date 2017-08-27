<%@ page import="entity.GUserEntity" %><%--
  Created by IntelliJ IDEA.
  User: panyunyi
  Date: 2017/8/27
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    GUserEntity gUserEntity = (GUserEntity) session.getAttribute("user");
    // 获取session创建时间
    String name = gUserEntity.getUserName();
    String userId=gUserEntity.getUserId();
    System.out.print(name);
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>order</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <script src="assets/js/echarts.min.js"></script>
    <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css"/>
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="assets/js/jquery.min.js"></script>
    <script>
        function initOrderList() {
            $.ajax({
                url: '/showTeacherTime?teacherId='+<%=userId%>,
                type: 'GET', //GET
                async: false,    //或false,是否异步
                timeout: 5000,    //超时时间
                dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                /* beforeSend:function(xhr){
                 console.log(xhr)
                 console.log('发送前')
                 },*/

                success: function (data, textStatus, jqXHR) {
                    var json = eval(data);
                    $('#order_content').append("  <tr class='gradeX'> <td>编号</td> <td>时间</td> <td>状态</td></tr> ")
                    $.each(json, function (index, item) {
                        var status;
                        if (data[index].timeStatus==1){
                            status="提交待同意"
                        }
                        if(data[index].timeStatus==2){
                            status="同意"
                        }
                        if(data[index].timeStatus==-1){
                            return true;//跳出本次循环
                        }

                        $('#order_content').append($("<tr id='abc' class='gradeX' ><td><b>" +
                            data[index].id + "</b></td><td><b>" +
                            data[index].timeDetail+ "</b></td><td><b>" +
                            status + "</b></td><td >" +

                            "<div class='tpl-table-black-operation'> <a href='/updateOrder?id="+data[index].id +"&status=2'"+"> <i class='am-icon-pencil'></i> 同意 </a> <a href='/updateOrder?id="+data[index].id +"&status=-1'"+" class='tpl-table-black-operation-del'> <i class='am-icon-trash'></i> 拒绝 </a> </div> </td></tr>"))
                    });

                },
                error: function (xhr, textStatus) {
                    console.log('错误')
                    console.log(xhr)
                    console.log(textStatus)
                    //window.location.href="404.html"
                },
                complete: function () {

                }
            })
        }

    </script>

</head>
<body onload="initOrderList()">
<script src="assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
    <header>
        <!-- logo -->
        <div class="am-fl tpl-header-logo">
            <a href="javascript:;"><img src="assets/img/logo.png" alt=""></a>
        </div>
        <!-- 右侧内容 -->
        <div class="tpl-header-fluid">
            <!-- 侧边切换 -->
            <div class="am-fl tpl-header-switch-button am-icon-list">
                    <span>

                </span>
            </div>
            <!-- 搜索 -->


        </div>

    </header>
    <!-- 风格切换 -->
    <div class="tpl-skiner">
        <div class="tpl-skiner-toggle am-icon-cog">
        </div>
        <div class="tpl-skiner-content">
            <div class="tpl-skiner-content-title">
                选择主题
            </div>
            <div class="tpl-skiner-content-bar">
                <span class="skiner-color skiner-white" data-color="theme-white"></span>
                <span class="skiner-color skiner-black" data-color="theme-black"></span>
            </div>
        </div>
    </div>
    <!-- 侧边导航栏 -->
    <div class="left-sidebar">
        <!-- 用户信息 -->
        <div class="tpl-sidebar-user-panel">
            <div class="tpl-user-panel-slide-toggleable">
                <div class="tpl-user-panel-profile-picture">
                    <img src="assets/img/user04.png" alt="">
                </div>
                <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon" id="userName"></i>
              <%=name%>
          </span>

            </div>
        </div>

        <!-- 菜单 -->
        <ul class="sidebar-nav">

            <li class="sidebar-nav-link">
                <a href="index.jsp" class="active">
                    <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
                </a>
            </li>

            <li class="sidebar-nav-link">
                <a href="order.jsp" class="active">
                    <i class="am-icon-home sidebar-nav-link-logo"></i> 预定
                </a>
            </li>
        </ul>
    </div>
    <div class="tpl-content-wrapper">


        <div class="row am-cf">


            <div class="am-u-sm-12 am-u-md-12 am-u-lg-8 widget-margin-bottom-lg">

                <div class="widget am-cf widget-body-lg">

                    <div class="widget-body  am-fr">
                        <div class="am-scrollable-horizontal ">
                            <table width="100%" class="am-table am-table-compact am-text-nowrap tpl-table-black "
                            >
                                <thead>
                                <tr>
                                    <th>预定列表</th>

                                </tr>
                                </thead>
                                <tbody id="order_content">


                                <!-- more data -->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</div>
</div>
<script src="assets/js/amazeui.min.js"></script>

<script src="assets/js/app.js"></script>
</body>
</html>
