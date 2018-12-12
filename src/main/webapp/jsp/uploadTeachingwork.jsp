<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>工作维护</title>
    <link href="../css/amazeui.datatables.min.css" rel="stylesheet" />
    <link href="../css/amazeui.min.css" rel="stylesheet" />
    <link href="../css/app.css" rel="stylesheet" />
    <link href="../css/amazeui.page.css" rel="stylesheet" />
    <link href="../css/amazeui.cropper.css" rel="stylesheet" />
    <script src="../js/jquery.min.js"></script>
    <script src="../js/echarts.min.js"></script>
    <script src="../js/amazeui.page.js"></script>
    <script src="../js/amazeui.dialog.min.js"></script>
    <script src="../js/paginator.js"></script>
</head>
<body>
<script src="../js/theme.js"></script>

<div class="row">
    <%--@*主题选择*@--%>
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

    <%--@*内容表单*@--%>
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
        <div class="widget am-cf">
            <%--小标题--%>
            <div class="widget-head am-cf">
                <div class="widget-title am-fl">工作维护</div>
                <div class="widget-function am-fr">
                    <a href="javascript:;" class="am-icon-cog"></a>
                </div>
            </div>
            <div class="widget-body am-fr">
                <form id="form1" class="am-form tpl-form-line-form">
                    <%--课程名--%>
                    <div class="am-form-group">
                        <label for="coursename" class="am-u-sm-3 am-form-label">课程名<span class="tpl-form-line-small-title">coursename</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="coursename" name="coursename"  placeholder="请输入课程名">
                            <%--<small>请填写论文名称0-20字左右。</small>--%>
                        </div>
                    </div>

                    <%--班级名称--%>
                    <div class="am-form-group">
                        <label for="classname" class="am-u-sm-3 am-form-label">班级名称<span class="tpl-form-line-small-title">classname</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="classname" name="classname" placeholder="请输入班级名称">
                            <%--<small>请填写1999-00-00格式时间。</small>--%>
                        </div>
                    </div>
                    <%--课时--%>
                    <div class="am-form-group">
                        <label for="classhour" class="am-u-sm-3 am-form-label">课时<span class="tpl-form-line-small-title">classhour</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="classhour" name="classhour"  placeholder="请输入课时">
                        </div>
                    </div>
                    <%--课数--%>
                    <div class="am-form-group">
                        <label for="classnumber" class="am-u-sm-3 am-form-label">课数<span class="tpl-form-line-small-title">classnumber</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="classnumber" name="classnumber" placeholder="请输入课数">
                            <%--<small>请填写1999-00-00格式时间。</small>--%>
                        </div>
                    </div>
                    <%--课程开始时间--%>
                    <div class="am-form-group">
                        <label for="starttime" class="am-u-sm-3 am-form-label">课程开始时间<span class="tpl-form-line-small-title">starttime</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="starttime" name="starttime" placeholder="请输入课程开始时间">
                            <small>请填写1999-00-00格式时间。</small>
                        </div>
                    </div>
                    <%--课程结束时间--%>
                    <div class="am-form-group">
                        <label for="endtime" class="am-u-sm-3 am-form-label">课程结束时间<span class="tpl-form-line-small-title">endtime</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="endtime" name="endtime" placeholder="请输入课程结束时间">
                            <small>请填写1999-00-00格式时间。</small>
                        </div>
                    </div>
                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button id="update" type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success " onclick="validate()">添加</button>
                            <button type="reset" class="am-btn am-btn-warning " data-am-modal-close>取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="../js/amazeui.datatables.min.js"></script>
<script src="../js/amazeui.min.js"></script>
<script src="../js/app.js"></script>
<script src="../js/dataTables.responsive.min.js"></script>
</body>
</html>
<script src="../js/Common.js"></script>
<script type="text/javascript">
    function validate()
    {
        if ( $("#coursename").val().length == 0) {
            alert("课程名不能为空", "提示");
            return false;
        }
        if ( $("#classname").val().length == 0) {
            alert("班级名不能为空", "提示");
            return false;
        }
        if ( $("#classhour").val().length == 0) {
            alert("课时不能为空", "提示");
            return false;
        }
        if ( $("#classnumber").val().length == 0) {
            alert("课数不能为空", "提示");
            return false;
        }
        if ( $("#starttime").val().length == 0) {
            alert("课程开始时间不能为空", "提示");
            return false;
        }
        if ( $("#endtime").val().length == 0) {
            alert("课程结束时间不能为空", "提示");
            return false;
        }
        var datecheck=/^([1-2]\d{3})[\/|\-](0?[1-9]|10|11|12)[\/|\-]([1-2]?[0-9]|0[1-9]|30|31)$/
        if (!datecheck.test($("#starttime").val())) {
            alert("开始时间格式不正确!")
            return false
        }
        if (!datecheck.test($("#endtime").val())) {
            alert("结束时间格式不正确!")
            return false
        }
        //添加论文
        var formData = new FormData($("#form1")[0]);
        $.ajax({
            url: '../teachingwork/add.action',
            type: 'post',
            data: formData,
            cache: false,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.jsonResult.state) {
                    alert(data.jsonResult.message);
                    window.location.reload();
                }else {
                    alert(data.jsonResult.message);
                }
            }
        })
    }
</script>