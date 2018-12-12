<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>论文管理</title>
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
                <div class="widget-title  am-cf">论文发表历史</div>
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
                        <input id="seltitle" type="text" class="am-form-field " placeholder="论文题目">
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
                            <th>论文名称</th>
                            <th>发布时间</th>
                            <th>发布杂志</th>
                            <th>其他作者</th>
                            <th>状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="ThesisEntity" items="${thesisEntityList}">
                            <c:if test="${thesis.index % 2 == 0}">
                                <tr class="gradeX">
                                    <td>${ThesisEntity.index}</td>
                                    <td>${ThesisEntity.title}</td>
                                    <td>${ThesisEntity.published_time}</td>
                                    <td>${ThesisEntity.magazine_name}</td>
                                    <td>${ThesisEntity.authors}</td>
                                    <td>
                                        <div class="tpl-table-black-operation">
                                            <c:if test="${ThesisEntity.status== 0}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 待审核
                                                </a>
                                            </c:if>
                                            <c:if test="${ThesisEntity.status==1}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已审核
                                                </a>
                                            </c:if>
                                            <c:if test="${ThesisEntity.status==2}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已驳回
                                                </a>
                                            </c:if>
                                            <a href="javascript:void(0);" onclick="Edit('${ThesisEntity.id}','${ThesisEntity.title}','${ThesisEntity.published_time}','${ThesisEntity.magazine_name}','${ThesisEntity.authors}')" data-am-modal="{target: '#edit', closeViaDimmer: 0, width: 600, height: 600}">
                                                <i class="am-icon-pencil"></i> 编辑
                                            </a>
                                            <a href="javascript:void(0);" onclick="Delete('${ThesisEntity.id}')" class="tpl-table-black-operation-del">
                                                <i class="am-icon-trash"></i> 删除
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${thesis.index % 2 > 0}">
                                <tr class="even gradeC">
                                    <td>${ThesisEntity.index}</td>
                                    <td>${ThesisEntity.title}</td>
                                    <td>${ThesisEntity.published_time}</td>
                                    <td>${ThesisEntity.magazine_name}</td>
                                    <td>${ThesisEntity.authors}</td>
                                    <td>
                                        <div class="tpl-table-black-operation">
                                            <c:if test="${ThesisEntity.status== 0}">
                                                <a href="javascript:void(0);">
                                                    <i class="am-icon-pencil"></i> 待审核
                                                </a>
                                            </c:if>
                                            <c:if test="${ThesisEntity.status==1}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已审核
                                                </a>
                                            </c:if>
                                            <c:if test="${ThesisEntity.status==2}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已驳回
                                                </a>
                                            </c:if>
                                            <a href="javascript:void(0);" onclick="Edit('${ThesisEntity.id}','${ThesisEntity.title}','${ThesisEntity.published_time}','${ThesisEntity.magazine_name}','${ThesisEntity.authors}')" data-am-modal="{target: '#edit', closeViaDimmer: 0, width: 600, height: 600}">
                                                <i class="am-icon-pencil"></i> 编辑
                                            </a>
                                            <a href="javascript:void(0);" onclick="Delete('${ThesisEntity.id}')" class="tpl-table-black-operation-del">
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
                                <p style="color:black;">修改论文信息</p>
                                <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
                            </div>
                            <div class="am-modal-bd" style="color:black;">
                                <form class="am-form" id="editform" enctype="multipart/form-data">
                                    <div class="am-form-group">
                                        <input type="text" id="edtitle" name="title" placeholder="论文名称" required>
                                        <input type="text" id="id" name="id" style="display: none">
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edpublished_time" name="published_time" placeholder="发布时间" required>
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edmagazine_name" name="magazine_name" placeholder="杂志名称" required>
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
        location.href = "../teacherIndex/thesisList.action?pageSize=" + pagesize + "&pageNum=" + nowpage;
    }

    //选择页面大小
    $("#pages").change(function () {
        var nowpage = Number($("#nowpage").html());
        var pagesize = $("#pages").val();//获取页面大小
        location.href = "../teacherIndex/thesisList.action?pageSize=" + pagesize + "&pageNum=" + nowpage;
    });

    //筛选条件
    function addwhere()
    {
        var title = $("#seltitle").val();
        var nowpage = Number($("#nowpage").html());
        var pagesize = $("#pages").val();
        location.href = "../teacherIndex/thesisList.action?pageSize=" + pagesize + "&pageNum=" + nowpage  + "&title=" +  encodeURI(encodeURI(title)) ;
    }
    //获取编辑信息
    function Edit(id,title,published_time,magazine_name,authors)
    {
        $("#id").val(id);
        $("#edtitle").val(title);
        $("#edpublished_time").val(published_time);
        $("#edmagazine_name").val(magazine_name);
        $("#edauthors").val(authors);
    }

    //更新信息
    function Update()
    {
        var datecheck=/^([1-2]\d{3})[\/|\-](0?[1-9]|10|11|12)[\/|\-]([1-2]?[0-9]|0[1-9]|30|31)$/
        if (!datecheck.test($("#edpublished_time").val())) {
            alert("发布时间格式不正确!")
            return false
        }
        var formData = new FormData($("#editform")[0]);
        $.ajax({
            url: '../thesis/update.action',
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
            DeleteDate('../thesis/delete.action', id);
        }
    }


</script>