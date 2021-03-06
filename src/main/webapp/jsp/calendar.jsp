<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>校历</title>
    <link rel="icon" type="images/png" href="../assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/i/app-icon72x72@2x.png">
    <link rel="stylesheet" href="../assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="../assets/css/fullcalendar.min.css" />
    <link rel="stylesheet" href="../assets/css/fullcalendar.print.css" media='print' />
    <link rel="stylesheet" href="../assets/css/app.css">
    <script src="../assets/js/jquery.min.js"></script>
</head>
<body>
<script src="../js/theme.js"></script>
<div class="row">
    <!--主题选择-->
    <div class="tpl-skiner" style="display:none">
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
        <!-- 内容区域 -->
        <%--<div class="tpl-content-wrapper">--%>
            <div class="row-content am-cf">
                <div class="tpl-calendar-box">
                    <div id="calendar"></div>
                </div>
            </div>
        <%--</div>--%>
</div>
<!-- 弹出层 -->
<div class="am-modal am-modal-no-btn" id="calendar-edit-box">
    <div class="am-modal-dialog tpl-model-dialog">
        <div class="am-modal-hd">
            <a href="javascript: void(0)" class="am-close edit-box-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd tpl-am-model-bd am-cf">

            <form class="am-form tpl-form-border-form">
                <div class="am-form-group am-u-sm-12">
                    <label for="user-name" class="am-u-sm-12 am-form-label am-text-left">标题 <span class="tpl-form-line-small-title">Title</span></label>
                    <div class="am-u-sm-12">
                        <input type="text" class="tpl-form-input am-margin-top-xs calendar-edit-box-title" id="user-name" placeholder="">
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>
<script src="../assets/js/moment.js"></script>
<script src="../assets/js/amazeui.min.js"></script>
<script src="../assets/js/fullcalendar.min.js"></script>
<script src="../assets/js/app.js"></script>
<script>
    //var argsData=$('#calendarEvent').val();
    var edstart=null;
    var edend=null;
    var edtitle=null;

    $(document).ready(function() {
        $.ajax({
            type: 'post',
            url: '../calendar/list.action',
            dataType: 'json',
            success: function (ret)
            {
                var argsData=ret.list;
                var editBox = $('#calendar-edit-box');
                $('.edit-box-close').on('click', function() {
                    $('#calendar').fullCalendar('unselect');
                })
                $('#calendar').fullCalendar({
                    monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                    monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                    dayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
                    dayNamesShort: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
                    today: ["今天"],
                    firstDay: 1,
                    lang: 'zh-cn',
                    selectable: true,
                    selectHelper: true,
                    editable: true,
                    eventClick: function(event, jsEvent, view) {
                        // 标题
                        $('.calendar-edit-box-title').val(event.title)
                        //  弹出框
                        editBox.modal();
                    },
                    events:argsData
                });
            }
        })

    });
</script>
</body>
</html>
<script src="../js/Common.js"></script>
