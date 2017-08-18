<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="entity.GUserEntity" %>
<%

    GUserEntity gUserEntity = (GUserEntity) session.getAttribute("user");
    // 获取session创建时间
    String name = gUserEntity.getUserName();
    System.out.print(name);
%>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
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
        function initList() {
            $.ajax({
                url: '/showArticle',
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
                    $.each(json, function (index, item) {
                        $('#a_content').append($("<tr class='gradeX'><td>" + data[index].articleContent + "</td><td> " +
                            "<div class='tpl-table-black-operation'> <a href='/showArticle?num=" + (index + 1) + "'> <i class='am-icon-pencil'></i> 查看 </a> <a href='/deleteArticle?articleId=" + data[index].articleId + "' class='tpl-table-black-operation-del'> <i class='am-icon-trash'></i> 删除 </a> </div> </td></tr>"))
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
        function initList1() {
            $.ajax({
                url: '/showAllUser',
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
                    $('#b_content').append("  <tr class='gradeX'> <td>用户名</td> <td>用户Id</td> <td>微信</td> <td>手机</td> <td>年级</td><td>班级</td> <td>学院</td> <td>用户角色</td><td>密码</td>   </tr> ")
                    $.each(json, function (index, item) {
                        $('#b_content').append($("<tr class='gradeX'><td>" + data[index].userName + "</td><td> " +
                           data[index].userId + "</td><td> " +
                            data[index].userWechat + "</td><td> " +
                             data[index].userTelephoneNumber + "</td><td> " +
                             data[index].userGrade + "</td><td> " +
                            data[index].userClass + "</td><td> " +
                             data[index].userInstitution + "</td><td> " +
                             data[index].userRole + "</td><td> " +
                             + data[index].userPassword + "</td><td> " +
                            "<div class='tpl-table-black-operation'> <a href='/showArticle?num=" + (index + 1) + "'> <i class='am-icon-pencil'></i> 查看 </a> <a href='/delete?userId=" + data[index].userId +"&password="+data[index].password+ "'class='tpl-table-black-operation-del'> <i class='am-icon-trash'></i> 删除 </a> </div> </td></tr>"))
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

<body data-type="index" onload="initList();initList1()">
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
            <li class="sidebar-nav-heading">Components <span class="sidebar-nav-heading-info"> 附加组件</span></li>
            <li class="sidebar-nav-link">
                <a href="index.jsp" class="active">
                    <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
                </a>
            </li>


        </ul>
    </div>


    <!-- 内容区域 -->
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
                                    <th>文章标题</th>

                                </tr>
                                </thead>
                                <tbody id="a_content">


                                <!-- more data -->
                                </tbody>
                            </table>
                            <div class="am-scrollable-horizontal ">
                                <table width="100%" class="am-table am-table-compact am-text-nowrap tpl-table-black "
                                       id="example-r">

                                    <tbody id="b_content">


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
</div>
<script src="assets/js/amazeui.min.js"></script>

<script src="assets/js/app.js"></script>

</body>

</html>