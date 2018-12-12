<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>添加著作权</title>
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
                <div class="widget-title am-fl">添加著作权</div>
                <div class="widget-function am-fr">
                    <a href="javascript:;" class="am-icon-cog"></a>
                </div>
            </div>
            <div class="widget-body am-fr">
                <form id="form1" class="am-form tpl-form-line-form">
                    <%--作品名称--%>
                    <div class="am-form-group">
                        <label for="workname" class="am-u-sm-3 am-form-label">作品名称<span class="tpl-form-line-small-title">name</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="workname" name="workname"  placeholder="请输入作品名称">
                            <%--<small>请填写论文名称0-20字左右。</small>--%>
                        </div>
                    </div>

                    <%--作品类型--%>
                    <div class="am-form-group">
                        <label for="worktype" class="am-u-sm-3 am-form-label">作品类型<span class="tpl-form-line-small-title">type</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="worktype" name="worktype" placeholder="请输入作品类型">
                            <%--<small>请填写1999-00-00格式时间。</small>--%>
                        </div>
                    </div>
                    <%--作者--%>
                    <div class="am-form-group">
                        <label for="author" class="am-u-sm-3 am-form-label">作者<span class="tpl-form-line-small-title">author</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="author" name="author"  placeholder="请输入作者">
                        </div>
                    </div>
                    <%--著作权人--%>
                    <div class="am-form-group">
                        <label for="copyrighter" class="am-u-sm-3 am-form-label">著作权人<span class="tpl-form-line-small-title">copyrighter</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="copyrighter" name="copyrighter" placeholder="请输入著作权人">
                            <%--<small>请填写1999-00-00格式时间。</small>--%>
                        </div>
                    </div>
                    <%--完成时间--%>
                    <div class="am-form-group">
                        <label for="completetime" class="am-u-sm-3 am-form-label">完成时间<span class="tpl-form-line-small-title">completetime</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="completetime" name="completetime" placeholder="请输入完成时间">
                            <small>请填写1999-00-00格式时间。</small>
                        </div>
                    </div>
                    <%--首次发表日期--%>
                    <div class="am-form-group">
                        <label for="publishtime" class="am-u-sm-3 am-form-label">首次发表日期<span class="tpl-form-line-small-title">publishtime</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="publishtime" name="publishtime" placeholder="请输入首次发表日期">
                            <small>请填写1999-00-00格式时间。</small>
                        </div>
                    </div>
                        <%--作品登记时间--%>
                        <div class="am-form-group">
                            <label for="registration" class="am-u-sm-3 am-form-label">作品登记时间<span class="tpl-form-line-small-title">registration</span></label>
                            <div class="am-u-sm-9">
                                <input type="text" class="tpl-form-input" id="registration" name="registration" placeholder="请输入作品登记时间">
                                <small>请填写1999-00-00格式时间。</small>
                            </div>
                        </div>
                        <%--登记号--%>
                        <div class="am-form-group">
                            <label for="registid" class="am-u-sm-3 am-form-label">登记号<span class="tpl-form-line-small-title">registid</span></label>
                            <div class="am-u-sm-9">
                                <input type="text" class="tpl-form-input" id="registid" name="registid" placeholder="请输入首次登记号">
                                <%--<small>请填写1999-00-00格式时间。</small>--%>
                            </div>
                        </div>
                        <%--权利取得方式--%>
                        <div class="am-form-group">
                            <label for="access" class="am-u-sm-3 am-form-label">权利取得方式<span class="tpl-form-line-small-title">access</span></label>
                            <div class="am-u-sm-9">
                                <%--<input type="text" class="tpl-form-input" id="access" name="access" placeholder="请输入权利取得方式(原始取得,继受取得)">--%>
                                    <select id="access" name="access" class="am-btn am-btn-default am-btn-secondary" style="color:black;">
                                        <option value="原始取得">原始取得</option>
                                        <option value="继受取得">继受取得</option>
                                    </select>
                                <%--<small>请填写1999-00-00格式时间。</small>--%>
                            </div>
                        </div>
                        <%--<div class="am-form-group">--%>
                            <%--<select id="access" name="access" class="am-btn am-btn-default am-btn-secondary" style="color:black;">--%>
                                <%--<option value="原始取得">原始取得</option>--%>
                                <%--<option value="继受取得">继受取得</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
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
        if ( $("#workname").val().length == 0) {
            alert("作品名称不能为空", "提示");
            return false;
        }
        if ( $("#worktype").val().length == 0) {
            alert("作品类型不能为空", "提示");
            return false;
        }
        if ( $("#author").val().length == 0) {
            alert("作者不能为空", "提示");
            return false;
        }
        if ( $("#copyrighter").val().length == 0) {
            alert("著作权人不能为空", "提示");
            return false;
        }
        if ( $("#completetime").val().length == 0) {
            alert("完成时间不能为空", "提示");
            return false;
        }
        if ( $("#publishtime").val().length == 0) {
            alert("首次发表日期不能为空", "提示");
            return false;
        }
        if ( $("#registration").val().length == 0) {
            alert("作品登记时间不能为空", "提示");
            return false;
        }
        if ( $("#registid").val().length == 0) {
            alert("登记号不能为空", "提示");
            return false;
        }
        if ( $("#access").val().length == 0) {
            alert("权利取得方式不能为空", "提示");
            return false;
        }
        var datecheck=/^([1-2]\d{3})[\/|\-](0?[1-9]|10|11|12)[\/|\-]([1-2]?[0-9]|0[1-9]|30|31)$/
        if (!datecheck.test($("#publishtime").val())) {
            alert("首次发表日期时间格式不正确!")
            return false
        }
        if (!datecheck.test( $("#completetime").val())) {
            alert("完成时间格式不正确!")
            return false
        }
        if (!datecheck.test( $("#registration").val())) {
            alert("作品等级时间格式不正确!")
            return false
        }
        //添加论文
        var formData = new FormData($("#form1")[0]);
        $.ajax({
            url: '../copyright/add.action',
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