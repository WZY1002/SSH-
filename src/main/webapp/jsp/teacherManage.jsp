<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <title>教师管理</title>
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
                <div class="widget-title  am-cf">教师管理</div>
            </div>
            <div class="widget-body  am-fr">
                <%--添加/页面大小--%>
                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                    <div class="am-form-group">
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">
                                <button id="addroominfo" type="button" class="am-btn am-btn-primary" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 500, height: 800}"><span class="am-icon-plus"></span> 新增</button>

                                <%--弹出窗口--%>
                                <div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
                                    <div class="am-modal-dialog">
                                        <div class="am-modal-hd">
                                            <p style="color:black;">添加教师</p>
                                            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
                                        </div>
                                        <div class="am-modal-bd" style="color:black;">
                                            <form class="am-form" id="form1" enctype="multipart/form-data">
                                                <div class="am-form-group">
                                                    <input type="text" id="adname" name="adname" placeholder="教师姓名" required>
                                                </div>
                                                <div class="am-form-group">
                                                    <input type="text" id="adnation" name="adnation" placeholder="民族" required>
                                                </div>
                                                <div class="am-form-group">
                                                    <input type="text" id="admobile_phone" name="admobile_phone" placeholder="手机号码/登录账号" required>
                                                </div>
                                                <div class="am-form-group">
                                                    <input type="text" id="adpassword" name="adpassword" placeholder="密码" required>
                                                </div>
                                                <div class="am-form-group">
                                                    <input type="text" id="adhome_phone" name="adhome_phone" placeholder="家庭电话" required>
                                                </div>
                                                <div class="am-form-group">
                                                    <input type="text" id="adaddress" name="adaddress" placeholder="家庭住址" required>
                                                </div>
                                                <div class="am-form-group">
                                                    <input type="text" id="adplace" name="adplace" placeholder="籍贯" required>
                                                </div>
                                                <div class="am-form-group">
                                                    <input type="text" id="adidentity" name="adidentity" placeholder="职称" required>
                                                </div>
                                                <div class="am-form-group">
                                                    <input type="text" id="adeducation" name="adeducation" placeholder="学历" required>
                                                </div>
                                                <div class="am-form-group">
                                                    <input type="text" id="adschool" name="adschool" placeholder="就业学校" required>
                                                </div>
                                                <div class="am-form-group">
                                                    <input type="text" id="adpolitics" name="adpolitics" placeholder="政治面貌" required>
                                                </div>
                                                <div class="am-form-group">
                                                    <input type="text" id="adhealthy" name="adhealthy" placeholder="健康状况" required>
                                                </div>
                                                <%--<div class="am-form-group">--%>
                                                    <%--<select id="adroomtype" class="am-btn am-btn-default am-btn-secondary" style="color:black;">--%>

                                                    <%--</select>--%>
                                                <%--</div>--%>
                                                <%--<div class="am-form-group">--%>
                                                    <%--<select id="adstate" class="am-btn am-btn-default am-btn-secondary" style="color:black;">--%>
                                                        <%--<option value="1" style="color:black;">启用</option>--%>
                                                        <%--<option value="2" style="color:black;">维护中</option>--%>
                                                    <%--</select>--%>
                                                <%--</div>--%>
                                                <div class="am-form-group">
                                                    <button type="button" class="am-btn am-btn-primary" onclick="validate()">确定</button>
                                                    <button type="button" class="am-btn am-btn-warning " data-am-modal-close>取消</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>

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
                        <input id="name" type="text" class="am-form-field " placeholder="教师姓名">
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
                            <th>教师姓名</th>
                            <th>手机号码</th>
                            <th>职称</th>
                            <th>入职时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="teacherBO" items="${teacherBOList}">
                                       <c:if test="${teacherBO.index % 2 == 0}">
                                            <tr class="gradeX">
                                                <td>${teacherBO.index}</td>
                                                <td>${teacherBO.name}</td>
                                                <td>${teacherBO.mobile_phone}</td>
                                                <td>${teacherBO.identity}</td>
                                                <td>${teacherBO.employ_time}</td>
                                                <td>
                                                    <div class="tpl-table-black-operation">
                                                        <a href="javascript:void(0);" onclick="EditTeacher('${teacherBO.id}','${teacherBO.name}','${teacherBO.password}','${teacherBO.nation}','${teacherBO.birth}','${teacherBO.place}','${teacherBO.address}','${teacherBO.mobile_phone}','${teacherBO.home_phone}','${teacherBO.identity}','${teacherBO.education}','${teacherBO.school}','${teacherBO.politics}','${teacherBO.healthy}')" data-am-modal="{target: '#edit', closeViaDimmer: 0, width: 600, height: 800}">
                                                            <i class="am-icon-pencil"></i> 编辑
                                                        </a>
                                                        <a href="javascript:void(0);" onclick="DeleteTeacher('${teacherBO.id}')" class="tpl-table-black-operation-del">
                                                            <i class="am-icon-trash"></i> 删除
                                                        </a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${teacherBO.index % 2 > 0}">
                                            <tr class="even gradeC">
                                                <td>${teacherBO.index}</td>
                                                <td>${teacherBO.name}</td>
                                                <td>${teacherBO.mobile_phone}</td>
                                                <td>${teacherBO.identity}</td>
                                                <td>${teacherBO.employ_time}</td>
                                                <td>
                                                    <div class="tpl-table-black-operation">
                                                        <a href="javascript:void(0);" onclick="EditTeacher('${teacherBO.id}','${teacherBO.name}','${teacherBO.password}','${teacherBO.nation}','${teacherBO.birth}','${teacherBO.place}','${teacherBO.address}','${teacherBO.mobile_phone}','${teacherBO.home_phone}','${teacherBO.identity}','${teacherBO.education}','${teacherBO.school}','${teacherBO.politics}','${teacherBO.healthy}')" data-am-modal="{target: '#edit', closeViaDimmer: 0, width: 600, height: 800}">
                                                            <i class="am-icon-pencil"></i> 编辑
                                                        </a>
                                                        <a href="javascript:void(0);" onclick="DeleteTeacher('${teacherBO.id}')" class="tpl-table-black-operation-del">
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
                            <p style="color:black;">编辑教师信息</p>
                            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
                        </div>
                        <div class="am-modal-bd" style="color:black;">
                            <form class="am-form" id="editform" enctype="multipart/form-data">
                                <div class="am-form-group">
                                    <input type="text" id="edname" name="edname" placeholder="教师姓名" required>
                                    <input type="text" id="id" name="id" style="display: none">
                                </div>
                                <div class="am-form-group">
                                    <input type="text" id="nation" name="nation" placeholder="民族" required>
                                </div>
                                <div class="am-form-group">
                                    <input type="text" id="mobile_phone" name="mobile_phone" placeholder="手机号码" required>
                                </div>
                                <div class="am-form-group">
                                    <input type="text" id="password" name="password" placeholder="密码" required>
                                </div>
                                <div class="am-form-group">
                                    <input type="text" id="home_phone" name="home_phone" placeholder="家庭电话" required>
                                </div>
                                <div class="am-form-group">
                                    <input type="text" id="address" name="address" placeholder="家庭住址" required>
                                </div>
                                <div class="am-form-group">
                                    <input type="text" id="place" name="place" placeholder="籍贯" required>
                                </div>
                                <div class="am-form-group">
                                    <input type="text" id="identity" name="identity" placeholder="职称" required>
                                </div>
                                <div class="am-form-group">
                                    <input type="text" id="education" name="education" placeholder="学历" required>
                                </div>
                                <div class="am-form-group">
                                    <input type="text" id="school" name="school" placeholder="就业学校" required>
                                </div>
                                <div class="am-form-group">
                                    <input type="text" id="edpolitics" name="edpolitics" placeholder="政治面貌" required>
                                </div>
                                <div class="am-form-group">
                                    <input type="text" id="edhealthy" name="edhealthy" placeholder="健康状况" required>
                                </div>
                                <%--<div class="am-form-group">--%>
                                    <%--<select id="edroomtype" class="am-btn am-btn-default am-btn-secondary" style="color:black;"></select>--%>
                                <%--</div>--%>
                                <%--<div class="am-form-group">--%>
                                    <%--<select id="edroomstate" class="am-btn am-btn-default am-btn-secondary" style="color:black;">--%>
                                        <%--<option value="1">启用</option>--%>
                                        <%--<option value="2">维护中</option>--%>
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
        location.href = "../index/teacherList.action?pageSize=" + pagesize + "&pageNum=" + nowpage;
    }

    //选择页面大小
    $("#pages").change(function () {
        var nowpage = Number($("#nowpage").html());
        var pagesize = $("#pages").val();//获取页面大小
        location.href = "../index/teacherList.action?pageSize=" + pagesize + "&pageNum=" + nowpage;
    });

    //筛选条件
    function addwhere()
    {
        var name = $("#name").val();
        //var roomtype = $("#selroomtype").val();
        var nowpage = Number($("#nowpage").html());
        var pagesize = $("#pages").val();
        location.href = "../index/teacherList.action?pageSize=" + pagesize + "&pageNum=" + nowpage  + "&name=" +  encodeURI(encodeURI(name)) ;
    }

    //添加表单验证,添加
    function validate() {
        var name=$("#adname").val();
        var nation=$("#adnation").val();
        var brith=$("#adbirth").val();
        var mobile_phone=$("#admobile_phone").val();
        var home_phone=$("#adhome_phone").val();
        var birth=$("#adbirth").val();
        var address=$("#adaddress").val();
        var place=$("#adplace").val();
        var identity=$("#adidentity").val();
        var education=$("#adeducation").val();
        var school=$("#adschool").val();
        var password=$("#adpassword").val();
        var  politics=$("#adpolitics").val();
        var healthy=$("#adhealthy").val();
        if (name.length == 0) {
            alert("教师姓名不能为空", "提示");
            return false;
        }
        else if (password.length == 0) {
            alert("密码不能为空", "提示");
            return false;
        }
        else if (mobile_phone.length == 0) {
            alert("手机号码/账号不能为空", "提示");
            return false;
        }
        $.ajax({
            type: 'GET',
            url: '../teacher/add.action',
            data: { "id":encodeURI(id),"name":encodeURI(name),"password":encodeURI(password),"nation":encodeURI(nation),"birth":encodeURI(birth),"place":encodeURI(place),"address":encodeURI(address),"mobile_phone":encodeURI(mobile_phone),"home_phone":encodeURI(home_phone),"identity":encodeURI(identity),"education":encodeURI(education),"school":encodeURI(school),"politics":encodeURI(politics),"healthy":encodeURI(healthy)},
            dataType: 'json',
            success: function (data)
            {
                if(data.jsonResult.state){
                    window.location.reload();
                }
                else {
                    alert(data.jsonResult.message);
                }
            }
        })
     }

    //获取编辑信息
    function EditTeacher(id,name,password,nation,birth,place,address,mobile_phone,home_phone,identity,education,school,politics,healthy)
    {
                $("#id").val(id);
                $("#edname").val(name);
                $("#password").val(password);
                $("#nation").val(nation);
                $("#birth").val(birth);
                $("#mobile_phone").val(mobile_phone);
                $("#home_phone").val(home_phone);
                $("#birth").val(birth);
                $("#address").val(address);
                $("#place").val(place);
                $("#identity").val(identity);
                $("#education").val(education);
                $("#school").val(school);
                $("#edpolitics").val(politics);
                $("#edhealthy").val(healthy);
    }
    //更新信息
    function Update()
    {
        var id=$("#id").val();
        var name=$("#edname").val();
        var nation=$("#nation").val();
        var brith=$("#birth").val();
        var mobile_phone=$("#mobile_phone").val();
        var home_phone=$("#home_phone").val();
        var birth=$("#birth").val();
        var address=$("#address").val();
        var place=$("#place").val();
        var identity=$("#identity").val();
        var education=$("#education").val();
        var school=$("#school").val();
        var password=$("#password").val();
        var politics=$("#edpolitics").val();
        var healthy=$("#edhealthy").val();
        if (name.length == 0) {
            alert("教师姓名不能为空", "提示");
            return false;
        }
        else if (password.length == 0) {
            alert("密码不能为空", "提示");
            return false;
        }
        else if (mobile_phone.length == 0) {
            alert("手机号码不能为空", "提示");
            return false;
        }
        $.ajax({
            type: 'get',
            url: '../teacher/update.action',
            data: { "id":encodeURI(id),"name":encodeURI(name),"password":encodeURI(password),"nation":encodeURI(nation),"birth":encodeURI(birth),"place":encodeURI(place),"address":encodeURI(address),"mobile_phone":encodeURI(mobile_phone),"home_phone":encodeURI(home_phone),"identity":encodeURI(identity),"education":encodeURI(education),"school":encodeURI(school),"politics":encodeURI(politics),"healthy":encodeURI(healthy)},
            dataType: 'json',
            success: function (data)
            {
                if(data.jsonResult.state){
                    location.href = "../index/teacherList.action";
                }
                else {
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
            DeleteDate('../teacher/delete.action', id);
        }
    }

</script>