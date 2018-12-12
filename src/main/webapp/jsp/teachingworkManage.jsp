<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>教师教学工作量管理</title>
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
                <div class="widget-title  am-cf">教师教学工作量管理</div>
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
                    <div class="widget-body  am-fr">
                        <div class="am-u-sm-12">
                            <div class="am-g">
                                <div class="am-u-sm-3">
                                    <input id="seltitle" type="text" class="am-form-field " placeholder="课程名称">
                                </div>
                                <div class="am-u-sm-3">
                                    <input id="starttime" type="text" class="am-form-field " placeholder="请输入xxxx-xx-xx开始时间">
                                </div>
                                <div class="am-u-sm-3">
                                    <input id="endtime" type="text" class="am-form-field " placeholder="请输入xxxx-xx-xx结束时间">
                                </div>
                                <div class="am-u-sm-3">
                                  <span class="am-input-group-btn">
                                <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="button" onclick="addwhere()"></button>
                                 </span>
                                </div>
                            </div>
                        </div>
                    </div>
                <%--数据表格--%>
                <div class="am-u-sm-12">
                    <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>教师姓名</th>
                            <th>课程名</th>
                            <th>班级</th>
                            <th>课数</th>
                            <th>课时</th>
                            <th>课程开始时间</th>
                            <th>课程结束时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="teachingworkEntity" items="${teachingworkEntityList}">
                            <c:if test="${teachingworkEntity.index % 2 == 0}">
                                <tr class="gradeX">
                                    <td>${teachingworkEntity.index}</td>
                                    <td>${teachingworkEntity.name}</td>
                                    <td>${teachingworkEntity.coursename}</td>
                                    <td>${teachingworkEntity.classname}</td>
                                    <td>${teachingworkEntity.classnumber}</td>
                                    <td>${teachingworkEntity.classhour}</td>
                                    <td>${teachingworkEntity.starttime}</td>
                                    <td>${teachingworkEntity.endtime}</td>
                                    <td>
                                        <div class="tpl-table-black-operation">
                                            <c:if test="${teachingworkEntity.status== 0}">
                                                <a href="javascript:void(0);" onclick="Edit('${teachingworkEntity.id}','${teachingworkEntity.status}')" data-am-modal="{target: '#edit', closeViaDimmer: 0, width: 600, height: 300}">
                                                    <i class="am-icon-pencil"></i> 审核
                                                </a>
                                            </c:if>
                                            <c:if test="${teachingworkEntity.status==1}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已审核
                                                </a>
                                            </c:if>
                                            <c:if test="${teachingworkEntity.status==2}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已驳回
                                                </a>
                                            </c:if>
                                            <a href="javascript:void(0);" onclick="Delete('${teachingworkEntity.id}')" class="tpl-table-black-operation-del">
                                                <i class="am-icon-trash"></i> 删除
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${teachingworkEntity.index % 2 > 0}">
                                <tr class="even gradeC">
                                    <td>${teachingworkEntity.index}</td>
                                    <td>${teachingworkEntity.name}</td>
                                    <td>${teachingworkEntity.coursename}</td>
                                    <td>${teachingworkEntity.classname}</td>
                                    <td>${teachingworkEntity.classnumber}</td>
                                    <td>${teachingworkEntity.classhour}</td>
                                    <td>${teachingworkEntity.starttime}</td>
                                    <td>${teachingworkEntity.endtime}</td>
                                    <td>
                                        <div class="tpl-table-black-operation">
                                            <c:if test="${teachingworkEntity.status== 0}">
                                                <a href="javascript:void(0);" onclick="Edit('${teachingworkEntity.id}','${teachingworkEntity.status}')" data-am-modal="{target: '#edit', closeViaDimmer: 0, width: 600, height: 300}">
                                                    <i class="am-icon-pencil"></i> 审核
                                                </a>
                                            </c:if>
                                            <c:if test="${teachingworkEntity.status==1}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已审核
                                                </a>
                                            </c:if>
                                            <c:if test="${teachingworkEntity.status==2}">
                                                <a href="javascript:void(0);" >
                                                    <i class="am-icon-pencil"></i> 已驳回
                                                </a>
                                            </c:if>
                                            <a href="javascript:void(0);" onclick="Delete('${teachingworkEntity.id}')" class="tpl-table-black-operation-del">
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
                            <p style="color:black;">教师工作量审核</p>
                            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
                        </div>
                        <div class="am-modal-bd" style="color:black;">
                            <form class="am-form" id="editform" enctype="multipart/form-data">
                                <input id="edid" style="display: none">
                                <div class="am-form-group">
                                    <select id="edstatus" class="am-btn am-btn-default am-btn-secondary" style="color:black;">
                                        <option value="1">通过</option>
                                        <option value="2">驳回</option>
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
        location.href = "../index/teachingworkList.action?pageSize=" + pagesize + "&pageNum=" + nowpage;
    }

    //选择页面大小
    $("#pages").change(function () {
        var nowpage = Number($("#nowpage").html());
        var pagesize = $("#pages").val();//获取页面大小
        location.href = "../index/teachingworkList.action?pageSize=" + pagesize + "&pageNum=" + nowpage;
    });

    //筛选条件
    function addwhere()
    {
        var coursename = $("#seltitle").val();
        var starttime = $("#starttime").val();
        var endtime = $("#endtime").val();
        var nowpage = Number($("#nowpage").html());
        var pagesize = $("#pages").val();
        var datecheck=/^([1-2]\d{3})[\/|\-](0?[1-9]|10|11|12)[\/|\-]([1-2]?[0-9]|0[1-9]|30|31)$/
        if(!starttime.length==0){
            if (!datecheck.test(starttime)) {
                alert("开始时间格式不正确!")
                return false
            }
        }
        if(!endtime.length==0){
            if (!datecheck.test(endtime)) {
                alert("结束时间格式不正确!")
                return false
            }
        }
        location.href = "../index/teachingworkList.action?pageSize=" + pagesize + "&pageNum=" + nowpage  + "&coursename=" +  encodeURI(encodeURI(coursename))   + "&starttime=" +  encodeURI(encodeURI(starttime))  + "&endtime=" +  encodeURI(encodeURI(endtime));
    }

    //获取编辑信息
    function Edit(id)
    {
        $("#edid").val(id);
    }
    //更新信息
    function Update()
    {
        var id=$("#edid").val();
        var status=$("#edstatus").val();
        $.ajax({
            type: 'get',
            url: '../teachingwork/update.action',
            data: { "id":encodeURI(id),"status":encodeURI(status)},
            dataType: 'json',
            success: function (data)
            {
                if(data.jsonResult.state){
                    location.href = "../index/teachingworkList.action";
                }
                else {
                    alert(data.jsonResult.message);
                }
            }
        })
    }
    //删除
    function Delete(id)
    {
        var a=confirm("你确定要删？");
        if (a == true) {
            DeleteDate('../teachingwork/delete.action', id);
        }
    }
</script>