<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>添加论文</title>
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
                <div class="widget-title am-fl">添加论文</div>
                <div class="widget-function am-fr">
                    <a href="javascript:;" class="am-icon-cog"></a>
                </div>
            </div>
            <div class="widget-body am-fr">
                <form id="form1" class="am-form tpl-form-line-form">
                    <input type="text" class="tpl-form-input" id="id" name="id" style="display: none" value="${ThesisEntity.id}">
                    <%--论文名称--%>
                    <div class="am-form-group">
                        <label for="title" class="am-u-sm-3 am-form-label">论文名称<span class="tpl-form-line-small-title">title</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="title" name="title" value="${ThesisEntity.title}" placeholder="请输入论文名称">
                            <small>请填写论文名称0-20字左右。</small>
                        </div>
                    </div>

                    <%--发布时间--%>
                    <div class="am-form-group">
                        <label for="published_time" class="am-u-sm-3 am-form-label">发布时间<span class="tpl-form-line-small-title">publishedTime</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="published_time" name="published_time" value="${ThesisEntity.published_time}" placeholder="请输入发布时间">
                            <small>请填写1999-00-00格式时间。</small>
                        </div>
                    </div>
                    <%--杂志名称--%>
                    <div class="am-form-group">
                        <label for="magazine_name" class="am-u-sm-3 am-form-label">杂志名称<span class="tpl-form-line-small-title">magazineName</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="magazine_name" name="magazine_name"  value="${ThesisEntity.magazine_name}" placeholder="请输入杂志名称">
                        </div>
                    </div>
                    <%--其他作者--%>
                    <div class="am-form-group">
                        <label for="authors" class="am-u-sm-3 am-form-label">其他作者<span class="tpl-form-line-small-title">authors</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="authors" name="authors" value="${teacherEntity.authors}" placeholder="请输入其他作者">
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
        if ( $("#title").val().length == 0) {
            alert("论文题目不能为空", "提示");
            return false;
        }
        if ( $("#published_time").val().length == 0) {
            alert("发布时间不能为空", "提示");
            return false;
        }
        if ( $("#magazine_name").val().length == 0) {
            alert("发布杂志不能为空", "提示");
            return false;
        }
        if ( $("#authors").val().length == 0) {
            alert("其他作者不能为空", "提示");
            return false;
        }
        var datecheck=/^([1-2]\d{3})[\/|\-](0?[1-9]|10|11|12)[\/|\-]([1-2]?[0-9]|0[1-9]|30|31)$/
        if (!datecheck.test($("#published_time").val())) {
            alert("发布时间格式不正确!")
            return false
        }
        //添加论文
        var formData = new FormData($("#form1")[0]);
        $.ajax({
            url: '../thesis/add.action',
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