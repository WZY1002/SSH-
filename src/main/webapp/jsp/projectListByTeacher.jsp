<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>项目管理</title>
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
<%--分页辅助--%>
<input type="text" id="serpagesize" style="display:none;" value="${pageSize}" />
<input id="Pagecount" type="text" style="display:none" value="${pageCount}" />
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

    <!--内容-->
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
        <div class="widget am-cf">
            <%--小标题--%>
            <div class="widget-head am-cf">
                <div class="widget-title  am-cf">查看项目</div>
            </div>
            <div class="widget-body  am-fr">
                <%--添加/页面大小--%>
                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                    <div class="am-form-group">
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">
                                <%--查看条数--%>
                                <select id="pages" class="am-btn am-btn-default am-btn-secondary">
                                    <option value="10">查看10条</option>
                                    <option value="15">查看15条</option>
                                    <option value="20">查看20条</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <%--筛选内容--%>
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                        <input id="seltitle" type="text" class="am-form-field " placeholder="项目标题">
                        <span class="am-input-group-btn">
                                <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="button" onclick="addwhere()"></button>
                            </span>
                    </div>
                </div>

                <%--数据表格--%>
                <div class="am-u-sm-12">
                    <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>项目名称</th>
                            <th>类别</th>
                            <th>年限</th>
                            <th>经费</th>
                            <th>级别</th>
                            <th>其他作者</th>
                            <th>状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="projectEntity" items="${projectEntityList}">
                            <c:if test="${projectEntity.index % 2 == 0}">
                                <tr class="gradeX">
                                    <td>${projectEntity.index}</td>
                                    <td>${projectEntity.projectname}</td>
                                    <td>${projectEntity.type}</td>
                                    <td>${projectEntity.agelimit}</td>
                                    <td>${projectEntity.money}</td>
                                    <td>${projectEntity.level}</td>
                                    <td>${projectEntity.authors}</td>
                                    <td>
                                        <div class="tpl-table-black-operation">
                                            <c:if test="${projectEntity.status== 0}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 待审核
                                                </a>
                                            </c:if>
                                            <c:if test="${projectEntity.status==1}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已审核
                                                </a>
                                            </c:if>
                                            <c:if test="${projectEntity.status==2}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已驳回
                                                </a>
                                            </c:if>
                                            <a href="javascript:void(0);" onclick="Edit('${projectEntity.id}','${projectEntity.type}','${projectEntity.projectname}','${projectEntity.agelimit}','${projectEntity.money}','${projectEntity.level}','${projectEntity.authors}')" data-am-modal="{target: '#edit', closeViaDimmer: 0, width: 600, height: 600}">
                                                <i class="am-icon-pencil"></i> 编辑
                                            </a>
                                            <a href="javascript:void(0);" onclick="Delete('${projectEntity.id}')" class="tpl-table-black-operation-del">
                                                <i class="am-icon-trash"></i> 删除
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${projectEntity.index % 2 > 0}">
                                <tr class="even gradeC">
                                    <td>${projectEntity.index}</td>
                                    <td>${projectEntity.projectname}</td>
                                    <td>${projectEntity.type}</td>
                                    <td>${projectEntity.agelimit}</td>
                                    <td>${projectEntity.money}</td>
                                    <td>${projectEntity.level}</td>
                                    <td>${projectEntity.authors}</td>
                                    <td>
                                        <div class="tpl-table-black-operation">
                                            <c:if test="${projectEntity.status== 0}">
                                                <a href="javascript:void(0);">
                                                    <i class="am-icon-pencil"></i> 待审核
                                                </a>
                                            </c:if>
                                            <c:if test="${projectEntity.status==1}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已审核
                                                </a>
                                            </c:if>
                                            <c:if test="${projectEntity.status==2}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已驳回
                                                </a>
                                            </c:if>
                                            <a href="javascript:void(0);" onclick="Edit('${projectEntity.id}','${projectEntity.type}','${projectEntity.projectname}','${projectEntity.agelimit}','${projectEntity.money}','${projectEntity.level}','${projectEntity.authors}')" data-am-modal="{target: '#edit', closeViaDimmer: 0, width: 600, height: 600}">
                                                <i class="am-icon-pencil"></i> 编辑
                                            </a>
                                            <a href="javascript:void(0);" onclick="Delete('${projectEntity.id}')" class="tpl-table-black-operation-del">
                                                <i class="am-icon-trash"></i> 删除
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                    <%--编辑-弹出框--%>
                    <div class="am-modal am-modal-no-btn" tabindex="-1" id="edit">
                        <div class="am-modal-dialog">
                            <div class="am-modal-hd">
                                <p style="color:black;">修改项目信息</p>
                                <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
                            </div>
                            <div class="am-modal-bd" style="color:black;">
                                <form class="am-form" id="editform" enctype="multipart/form-data">
                                    <div class="am-form-group">
                                        <input type="text" id="edtype" name="type" placeholder="类别" required>
                                        <input type="text" id="id" name="id" style="display: none">
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edprojectname" name="projectname" placeholder="项目名" required>
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edagelimit" name="agelimit" placeholder="年限" required>
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edmoney" name="money" placeholder="经费" required>
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edlevel" name="level" placeholder="级别" required>
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edauthors" name="authors" placeholder="其他作者" required>
                                    </div>
                                    <%--<div class="am-form-group">--%>
                                    <%--<select id="edaccess" name="access" class="am-btn am-btn-default am-btn-secondary" style="color:black;">--%>
                                    <%--<option value="原始取得">原始取得</option>--%>
                                    <%--<option value="继受取得">继受取得</option>--%>
                                    <%--</select>--%>
                                    <%--</div>--%>
                                    <div class="am-form-group">
                                        <button type="button" class="am-btn am-btn-primary" onclick="Update()">确定</button>
                                        <button type="button" class="am-btn am-btn-warning " data-am-modal-close>取消</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>


                <%--分页--%>
                <div class="am-u-lg-12 am-cf">
                    <div class="am-fr">
                        <ul class="am-pagination tpl-pagination">
                            <li id="faahead"><a href="javascript:void(0);" onclick="ahead()">«</a></li>
                            <li id="fanext"><a href="javascript:void(0);" onclick="next()">»</a></li>
                            <div id="tips">当前第<span id="nowpage">${pageNum}</span>页,共<span id="pagecount1">${pageCount}</span>页</div>
                        </ul>
                    </div>
                </div>
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
    $(function () {
        Getpage();
    });
    function ahead() {
        doahead();
    };
    function next()
    {
        donext();
    }

    //按页面大小和页码请求数据
    function GetManager(pagesize, nowpage) {
        location.href = "../teacherIndex/projectList.action?pageSize=" + pagesize + "&pageNum=" + nowpage;
    }

    //选择页面大小
    $("#pages").change(function () {
        var nowpage = Number($("#nowpage").html());
        var pagesize = $("#pages").val();//获取页面大小
        location.href = "../teacherIndex/projectList.action?pageSize=" + pagesize + "&pageNum=" + nowpage;
    });

    //筛选条件
    function addwhere()
    {
        var name = $("#seltitle").val();
        var nowpage = Number($("#nowpage").html());
        var pagesize = $("#pages").val();
        location.href = "../teacherIndex/projectList.action?pageSize=" + pagesize + "&pageNum=" + nowpage  + "&name=" +  encodeURI(encodeURI(name));
    }

    //获取编辑信息
    function Edit(id,type,projectname,agelimit,money,level,authors)
    {
        $("#id").val(id);
        $("#edtype").val(type);
        $("#edprojectname").val(projectname);
        $("#edagelimit").val(agelimit);
        $("#edmoney").val(money);
        $("#edlevel").val(level);
        $("#edauthors").val(authors);
    }

    //更新信息
    function Update()
    {
        //添加管理员
        var formData = new FormData($("#editform")[0]);
        $.ajax({
            url: '../project/update.action',
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

    //删除
    function Delete(id) {
        var a=confirm("你确定要删？");
        if (a == true) {
            DeleteDate('../project/delete.action', id);
        }
    }

</script>