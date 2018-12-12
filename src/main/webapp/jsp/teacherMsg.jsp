<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>修改个人信息</title>
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
                <div class="widget-title am-fl">个人信息</div>
                <div class="widget-function am-fr">
                    <a href="javascript:;" class="am-icon-cog"></a>
                </div>
            </div>
            <div class="widget-body am-fr">
                <form id="form1" class="am-form tpl-form-line-form">
                    <input type="text" class="tpl-form-input" id="id" name="id" style="display: none" value="${teacherEntity.id}">
                    <%--密码--%>
                    <div class="am-form-group">
                        <label for="password" class="am-u-sm-3 am-form-label">密码<span class="tpl-form-line-small-title">password</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="password" name="password" value="${teacherEntity.password}" placeholder="请输入要修改的密码">
                            <%--<small>请填写标题文字10-20字左右。</small>--%>
                        </div>
                    </div>

                    <%--教师姓名--%>
                    <div class="am-form-group">
                        <label for="name" class="am-u-sm-3 am-form-label">姓名<span class="tpl-form-line-small-title">name</span></label>
                        <div class="am-u-sm-9">
                            <input type="text" class="tpl-form-input" id="name" name="name" value="${teacherEntity.name}" placeholder="请输入教师姓名">
                            <%--<small>请填写标题文字10-20字左右。</small>--%>
                        </div>
                    </div>
                        <%--政治面貌--%>
                        <div class="am-form-group">
                            <label for="politics" class="am-u-sm-3 am-form-label">政治面貌<span class="tpl-form-line-small-title">politics</span></label>
                            <%--<div class="am-u-sm-9">--%>
                                <%--<input type="text" class="tpl-form-input" id="politics" name="politics"  value="${teacherEntity.politics}" placeholder="请输入政治面貌">--%>
                            <%--</div>--%>
                            <div class="am-u-sm-9">
                                <select id="politics" name="politics" class="am-btn am-btn-default am-btn-secondary" style="color:black;">
                                    <option value="中共党员">中共党员</option>
                                    <option value="共青团员">共青团员</option>
                                    <option value="群众">群众</option>
                                    <option value="其他">其他</option>
                                </select>
                            </div>
                        </div>
                        <%--居住地址--%>
                        <div class="am-form-group">
                            <label for="address" class="am-u-sm-3 am-form-label">居住地址<span class="tpl-form-line-small-title">address</span></label>
                            <div class="am-u-sm-9">
                                <input type="text" class="tpl-form-input" id="address" name="address" value="${teacherEntity.address}" placeholder="请输入居住地址">
                            </div>
                        </div>
                        <%--职称--%>
                        <div class="am-form-group">
                            <label for="identity" class="am-u-sm-3 am-form-label">职称<span class="tpl-form-line-small-title">identity</span></label>
                            <%--<div class="am-u-sm-9">--%>
                                <%--<input type="text" class="tpl-form-input" id="identity" name="identity" value="${teacherEntity.identity}" placeholder="请输入现在职称">--%>
                            <%--</div>--%>
                            <div class="am-u-sm-9">
                                <select id="identity" name="identity" class="am-btn am-btn-default am-btn-secondary" style="color:black;">
                                    <option value="助教">助教</option>
                                    <option value="讲师">讲师</option>
                                    <option value="副教授">副教授</option>
                                    <option value="教授">教授</option>
                                </select>
                            </div>
                        </div>
                        <%--学历--%>
                        <div class="am-form-group">
                            <label for="education" class="am-u-sm-3 am-form-label">学历<span class="tpl-form-line-small-title">education</span></label>
                            <%--<div class="am-u-sm-9">--%>
                                <%--<input type="text" class="tpl-form-input" id="education" name="education" value="${teacherEntity.education}" placeholder="请输入学历">--%>
                            <%--</div>--%>
                            <div class="am-u-sm-9">
                                <select id="education" name="education" class="am-btn am-btn-default am-btn-secondary" style="color:black;">
                                    <option value="博士">博士</option>
                                    <option value="硕士">硕士</option>
                                    <option value="本科">本科</option>
                                    <option value="专科">专科</option>
                                    <option value="高中">高中</option>
                                    <option value="初中">初中</option>
                                    <option value="小学">小学</option>
                                    <option value="其他">其他</option>
                                </select>
                            </div>
                        </div>
                        <%--毕业学校--%>
                        <div class="am-form-group">
                            <label for="school" class="am-u-sm-3 am-form-label">毕业学校<span class="tpl-form-line-small-title">school</span></label>
                            <div class="am-u-sm-9">
                                <input type="text" class="tpl-form-input" id="school" name="school" value="${teacherEntity.school}" placeholder="请输入毕业学校">
                            </div>
                        </div>
                        <%--院系--%>
                        <div class="am-form-group">
                            <label for="department" class="am-u-sm-3 am-form-label">院系<span class="tpl-form-line-small-title">department</span></label>
                            <div class="am-u-sm-9">
                                <input type="text" class="tpl-form-input" id="department" name="department" value="${teacherEntity.department}" placeholder="请输入院系">
                            </div>
                        </div>
                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button id="update" type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success " onclick="validate()">修改</button>
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
        //添加管理员
        var formData = new FormData($("#form1")[0]);
        $.ajax({
            url: '../teacher/update.action',
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
    $(function () {
        $("#education").val(${teacherEntity.education});
        $("#identity").val(${teacherEntity.identity});
        $("#politics").val(${teacherEntity.politics});
    });
</script>