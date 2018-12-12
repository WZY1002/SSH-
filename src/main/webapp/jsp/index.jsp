<%--
  Created by IntelliJ IDEA.
  User: 好吧勇哥
  Date: 2018/4/27
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师综合信息一体化平台-管理员端</title>
    <link href="../css/amazeui.datatables.min.css" rel="stylesheet"/>
    <link href="../css/amazeui.min.css" rel="stylesheet"/>
    <link href="../css/app.css" rel="stylesheet"/>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/echarts.min.js"></script>
    <script type="text/javascript">
        function logout() {
            if (confirm("您确定要退出本系统吗？")) {
                top.location = "../Login.html";
                return false;
            }
        }
    </script>
</head>
<body data-type="widgets">
<script src="../js/theme.js"></script>
<div class="am-g tpl-g">
    <header>
        <!-- logo -->
        <div class="am-fl tpl-header-logo">
            <a href="javascript:;"><img src="../images/logo1.png" alt=""></a>
        </div>
        <!-- 右侧内容 -->
        <div class="tpl-header-fluid">
            <!-- 侧边切换 -->
            <div class="am-fl tpl-header-switch-button am-icon-list">
                    <span>
                    </span>
            </div>
            <!-- 其它功能-->
            <div class="am-fr tpl-header-navbar">
                <ul>
                    <!-- 欢迎语 -->
                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a href="javascript:;">欢迎你, <span>${adminBO.name}</span> </a>
                    </li>

                    <!-- 退出 -->
                    <li class="am-text-sm">
                        <a href="javascript:;" onclick="logout()">
                            <span class="am-icon-sign-out"></span> 退出
                        </a>
                    </li>
                </ul>
            </div>
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
                <span class="skiner-color skiner-white" data-color="theme-white" id="white"></span>
                <span class="skiner-color skiner-black" data-color="theme-black" id="black"></span>
            </div>
        </div>
    </div>

    <!-- 侧边导航栏 -->
    <div class="left-sidebar">
        <!-- 用户信息 -->
        <div class="tpl-sidebar-user-panel">
            <div class="tpl-user-panel-slide-toggleable">
                <div class="tpl-user-panel-profile-picture">
                    <!--头像-->
                    <img src="../images/user04.png" alt="">
                </div>
                <span class="user-panel-logged-in-text">
                        <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
                       ${adminBO.name}
                    </span>
                <a href="javascript: void(0)" class="tpl-user-panel-action-link" target="rightiFrame"> <span
                        class="am-icon-pencil"></span> 账号设置</a>
            </div>
        </div>

        <!-- 菜单 -->
        <ul class="sidebar-nav">
            <li class="sidebar-nav-heading">系统菜单 <span class="sidebar-nav-heading-info"></span></li>
            <li class="sidebar-nav-link">
                <a href="javascript:;" class="sidebar-nav-sub-title">
                    <i class="am-icon-table sidebar-nav-link-logo"></i> 教师管理
                    <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico sidebar-nav-sub-ico-rotate"></span>
                </a>
                <ul class="sidebar-nav sidebar-nav-sub" style="display: block;">
                    <li class="sidebar-nav-link">
                        <a id="teacher" href="../index/teacherList.action" target="rightiFrame">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>教师管理页
                        </a>
                    </li>
                </ul>
            </li>
            <li class="sidebar-nav-link">
                <a href="javascript:;" class="sidebar-nav-sub-title">
                    <i class="am-icon-table sidebar-nav-link-logo"></i> 教师科研业绩管理模块
                    <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico sidebar-nav-sub-ico-rotate"></span>
                </a>
                <ul class="sidebar-nav sidebar-nav-sub" style="display: block;">
                    <li class="sidebar-nav-link">
                        <a href="../index/thesisList.action" target="rightiFrame">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>论文管理
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="../index/projectList.action" target="rightiFrame">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>项目管理
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="../index/patentList.action" target="rightiFrame">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>专利管理
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="../index/copyrightList.action" target="rightiFrame">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>著作权管理
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="../index/monographsList.action" target="rightiFrame">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>专著管理
                        </a>
                    </li>
                </ul>
            </li>
            <li class="sidebar-nav-link">
                <a href="javascript:;" class="sidebar-nav-sub-title">
                    <i class="am-icon-table sidebar-nav-link-logo"></i> 教师教学工作维护和统计管理
                    <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico sidebar-nav-sub-ico-rotate"></span>
                </a>
                <ul class="sidebar-nav sidebar-nav-sub" style="display: block;">
                    <li class="sidebar-nav-link">
                        <a href="../index/teachingworkList.action" target="rightiFrame">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>教师教学工作量管理
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="../index/teachingwork.action" target="rightiFrame">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>工作量统计管理
                        </a>
                    </li>
                </ul>
            </li>
            <li class="sidebar-nav-link">
                <a href="javascript:;" class="sidebar-nav-sub-title">
                    <i class="am-icon-table sidebar-nav-link-logo"></i> 日历集成
                    <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico sidebar-nav-sub-ico-rotate"></span>
                </a>
                <ul class="sidebar-nav sidebar-nav-sub" style="display: block;">
                    <li class="sidebar-nav-link">
                        <a href="../index/calendarManager.action" target="rightiFrame">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>日历管理
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>

    <!-- 内容区域 -->
    <div id="ifm" class="tpl-content-wrapper">
        <iframe src="" scrolling="No" id="rightiFrame" name="rightiFrame" class="row-content am-cf"
                width="100%"></iframe>
    </div>

</div>
<script src="../js/amazeui.datatables.min.js"></script>
<script src="../js/amazeui.min.js"></script>
<script src="../js/app.js"></script>
<script src="../js/dataTables.responsive.min.js"></script>
</body>
</html>
<script type="text/javascript">
    $(".skiner-white").click(function () {
        $("#rightiFrame").contents().find(".skiner-white").click()
    });
    $(".skiner-black").click(function () {
        $("#rightiFrame").contents().find(".skiner-black").click()
    });

    //加载设置子窗口的高
    $("#rightiFrame").load(function () {
        var h = $("#ifm").height();
        $("#rightiFrame").height(h);
    })

</script>