<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>上传照片</title>
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
                <div class="widget-title am-fl">上传照片</div>
                <div class="widget-function am-fr">
                    <a href="javascript:;" class="am-icon-cog"></a>
                </div>
            </div>
            <div class="widget-body am-fr">
                <form id="form1"  class="am-form tpl-form-line-form" method="post" enctype="multipart/form-data">
                    <input type="text" class="tpl-form-input" id="id" name="id" style="display: none" value="${teacherEntity.id}">
                    <%--照片--%>
                        <div class="am-form-group">
                        <label for="headImg" class="am-u-sm-3 am-form-label"><span class="tpl-form-line-small-title">教师照片</span></label>
                        <div class="am-u-sm-9">
                        <div class="am-form-group am-form-file">
                            <div>
                            <img id="xmTanImg" alt="图片预览" height="300">
                            </div>
                            <button type="button" class="am-btn am-btn-danger am-btn-sm">
                                <i class="am-icon-cloud-upload"></i> 添加照片
                             </button>
                            <input id="headImg" type="file" name="headImg" onchange="xmTanUploadImg(this)"/>
                        </div>
                        </div>
                        </div>
                    <input type="button" value="提交" class="am-btn am-btn-primary tpl-btn-bg-color-success " onclick="submits()" />
                    <%--<input type="submit" value="提交"class="am-btn am-btn-primary tpl-btn-bg-color-success " />--%>
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
function submits() {
        var formData = new FormData($("#form1")[0]);
        $.ajax({
            url:'../teacher/uploadImg.action',
            type:'post',
            data: formData,
            enctype: 'multipart/form-data',
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
        });
}

    //上传图片
    function xmTanUploadImg(obj) {
        var file = obj.files[0];
        var reader = new FileReader();
        //读取文件过程方法
        reader.onerror = function (e) {
            alert("读取异常....");
        }
        reader.onload = function (e) {
            //var e = file;
            console.log("成功读取....");
            var img = document.getElementById("xmTanImg");
            img.src = e.target.result;
//            var imgdata = document.getElementById("imgdata");
//            imgdata.src = e.target.result;
//            var width = imgdata.width;
//            var height = imgdata.height;
        }
       reader.readAsDataURL(file);
    }
</script>