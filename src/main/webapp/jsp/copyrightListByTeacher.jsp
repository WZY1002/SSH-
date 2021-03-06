.<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>个人著作权查看</title>
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
                <div class="widget-title  am-cf">著作权发表历史</div>
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
                        <input id="seltitle" type="text" class="am-form-field " placeholder="作品名称">
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
                            <th>作品名称</th>
                            <th>作品类型</th>
                            <th>作者</th>
                            <th>著作权人</th>
                            <th>完成时间</th>
                            <th>首次发表日期</th>
                            <th>作品登记时间</th>
                            <th>登记号</th>
                            <th>权利取得方式</th>
                            <th>状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="copyrightEntity" items="${copyrightEntityList}">
                            <c:if test="${copyrightEntity.index % 2 == 0}">
                                <tr class="gradeX">
                                    <td>${copyrightEntity.index}</td>
                                    <td>${copyrightEntity.workname}</td>
                                    <td>${copyrightEntity.worktype}</td>
                                    <td>${copyrightEntity.author}</td>
                                    <td>${copyrightEntity.copyrighter}</td>
                                    <td>${copyrightEntity.completetime}</td>
                                    <td>${copyrightEntity.publishtime}</td>
                                    <td>${copyrightEntity.registration}</td>
                                    <td>${copyrightEntity.registid}</td>
                                    <td>${copyrightEntity.access}</td>
                                    <td>
                                        <div class="tpl-table-black-operation">
                                            <c:if test="${copyrightEntity.status== 0}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 待审核
                                                </a>
                                            </c:if>
                                            <c:if test="${copyrightEntity.status==1}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已审核
                                                </a>
                                            </c:if>
                                            <c:if test="${copyrightEntity.status==2}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已驳回
                                                </a>
                                            </c:if>
                                            <a href="javascript:void(0);" onclick="EditTeacher('${copyrightEntity.id}','${copyrightEntity.workname}','${copyrightEntity.worktype}','${copyrightEntity.author}','${copyrightEntity.copyrighter}','${copyrightEntity.completetime}','${copyrightEntity.publishtime}','${copyrightEntity.registration}','${copyrightEntity.registid}','${copyrightEntity.access}')" data-am-modal="{target: '#edit', closeViaDimmer: 0, width: 600, height: 600}">
                                                <i class="am-icon-pencil"></i> 编辑
                                            </a>
                                            <a href="javascript:void(0);" onclick="DeleteTeacher('${copyrightEntity.id}')" class="tpl-table-black-operation-del">
                                                <i class="am-icon-trash"></i> 删除
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${copyrightEntity.index % 2 > 0}">
                                <tr class="even gradeC">
                                    <td>${copyrightEntity.index}</td>
                                    <td>${copyrightEntity.workname}</td>
                                    <td>${copyrightEntity.worktype}</td>
                                    <td>${copyrightEntity.author}</td>
                                    <td>${copyrightEntity.copyrighter}</td>
                                    <td>${copyrightEntity.completetime}</td>
                                    <td>${copyrightEntity.publishtime}</td>
                                    <td>${copyrightEntity.registration}</td>
                                    <td>${copyrightEntity.registid}</td>
                                    <td>${copyrightEntity.access}</td>
                                    <td>
                                        <div class="tpl-table-black-operation">
                                            <c:if test="${copyrightEntity.status== 0}">
                                                <a href="javascript:void(0);">
                                                    <i class="am-icon-pencil"></i> 待审核
                                                </a>
                                            </c:if>
                                            <c:if test="${copyrightEntity.status==1}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已审核
                                                </a>
                                            </c:if>
                                            <c:if test="${copyrightEntity.status==2}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已驳回
                                                </a>
                                            </c:if>
                                            <a href="javascript:void(0);" onclick="EditTeacher('${copyrightEntity.id}','${copyrightEntity.workname}','${copyrightEntity.worktype}','${copyrightEntity.author}','${copyrightEntity.copyrighter}','${copyrightEntity.completetime}','${copyrightEntity.publishtime}','${copyrightEntity.registration}','${copyrightEntity.registid}','${copyrightEntity.access}')" data-am-modal="{target: '#edit', closeViaDimmer: 0, width: 600, height: 600}">
                                                <i class="am-icon-pencil"></i> 编辑
                                            </a>
                                            <a href="javascript:void(0);" onclick="DeleteTeacher('${copyrightEntity.id}')" class="tpl-table-black-operation-del">
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
                                <p style="color:black;">修改著作权信息</p>
                                <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
                            </div>
                            <div class="am-modal-bd" style="color:black;">
                                <form class="am-form" id="editform" enctype="multipart/form-data">
                                    <div class="am-form-group">
                                        <input type="text" id="edworkname" name="workname" placeholder="作品名称" required>
                                        <input type="text" id="id" name="id" style="display: none">
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edworktype" name="worktype" placeholder="作品类型" required>
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edauthor" name="author" placeholder="作者" required>
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edcopyrighter" name="copyrighter" placeholder="著作权人" required>
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edcompletetime" name="completetime" placeholder="完成时间" required>
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edpublishtime" name="publishtime" placeholder="首次发表日期" required>
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edregistration" name="registration" placeholder="作品登记时间" required>
                                    </div>
                                    <div class="am-form-group">
                                        <input type="text" id="edregistid" name="registid" placeholder="登记号" required>
                                    </div>
                                    <%--<div class="am-form-group">--%>
                                        <%--<input type="text" id="edaccess" name="access" placeholder="权利取得方式" required>--%>
                                    <%--</div>--%>
                                    <div class="am-form-group">
                                    <select id="edaccess" name="access" class="am-btn am-btn-default am-btn-secondary" style="color:black;">
                                    <option value="原始取得">原始取得</option>
                                    <option value="继受取得">继受取得</option>
                                    </select>
                                    </div>
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
        location.href = "../teacherIndex/copyrightList.action?pageSize=" + pagesize + "&pageNum=" + nowpage;
    }

    //选择页面大小
    $("#pages").change(function () {
        var nowpage = Number($("#nowpage").html());
        var pagesize = $("#pages").val();//获取页面大小
        location.href = "../teacherIndex/copyrightList.action?pageSize=" + pagesize + "&pageNum=" + nowpage;
    });

    //筛选条件
    function addwhere()
    {
        var workname = $("#seltitle").val();
        var nowpage = Number($("#nowpage").html());
        var pagesize = $("#pages").val();
        location.href = "../teacherIndex/copyrightList.action?pageSize=" + pagesize + "&pageNum=" + nowpage  + "&workname=" +  encodeURI(encodeURI(workname)) ;
    }

    //获取编辑信息
    function EditTeacher(id,workname,worktype,author,copyrighter,completetime,publishtime,registration,registid,access)
    {
        $("#id").val(id);
        $("#edworkname").val(workname);
        $("#edworktype").val(worktype);
        $("#edauthor").val(author);
        $("#edcopyrighter").val(copyrighter);
        $("#edcompletetime").val(completetime);
        $("#edpublishtime").val(publishtime);
        $("#edregistration").val(registration);
        $("#edregistid").val(registid);
        $("#edaccess").val(access);
    }
    //更新信息
    function Update()
    {
        var datecheck=/^([1-2]\d{3})[\/|\-](0?[1-9]|10|11|12)[\/|\-]([1-2]?[0-9]|0[1-9]|30|31)$/
        if (!datecheck.test($("#edcompletetime").val())) {
            alert("完成时间格式不正确!")
            return false
        }
        if (!datecheck.test($("#edpublishtime").val())) {
            alert("出版时间格式不正确!")
            return false
        }
        var formData = new FormData($("#editform")[0]);
        $.ajax({
            url: '../copyright/update.action',
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
    function DeleteTeacher(id)
    {
        var a=confirm("你确定要删？");
        if (a == true) {
            DeleteDate('../copyright/delete.action', id);
        }
    }
</script>