
 //回车提交表单
function pushEnter()
{
    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) { // enter 键
            validate();
        }
    };
}

//检验验证码
function checkCode(code) {
    $.ajax({
        url: '/Regisiter/CheckCode',
        type: 'post',
        async: false,
        data: { "code": code},
        dataType: 'json',
        success: function (m)
        {
            if (m.successed)
            {
                return true;
            }
            else
            {
                alert(m.msg);
                codeImg();
                return false;
            }        
        }
    })
}

//自定义分页刷新
function Getpage()
{
    var count = $("#Pagecount").val();//获取总页数
    $("#pages").val($("#serpagesize").val());//更新总页数
    //刷新分页器
    var nowpage = Number($("#nowpage").html());
    var pcount = Number($("#pagecount1").html());
    if (nowpage == 1) {
        $("#faahead").addClass("am-disabled");
    }
    else {
        $("#faahead").removeClass("am-disabled");
    }
    if (nowpage >= pcount) {
        $("#fanext").addClass("am-disabled");
    }
    else {
        $("#fanext").removeClass("am-disabled");
    }
}
//上一页
function doahead()
{
    $("#nowpage").html(Number($("#nowpage").html()) - 1);
    var nowpage = $("#nowpage").html();
    var pagesize = $("#pages").val();
    GetManager(pagesize, nowpage);
}
//下一页
function donext()
{
    $("#nowpage").html(Number($("#nowpage").html()) + 1);
    var nowpage = $("#nowpage").html();
    var pagesize = $("#pages").val();
    GetManager(pagesize, nowpage);
}

//按页面大小和页码请求数据
function GetData(url, pagesize, nowpage)
{
    location.href = "" + url + "?pagesize=" + pagesize + "&nowpage=" + nowpage;
}

//post上传数据(json)(自定义定义结构数据)
function AddData(url,array)
{
    $.ajax({
        url: url,
        data: { "array": JSON.stringify(array)},
        type: 'post',
        dataType: 'json',
        success: function (m) {
            if (m.successed) {
                alert(m.msg, "提示");
                window.location.reload();
            }
            else
                alert(m.msg, "提示");
        }
    });
}

//FormData上传数据(表单)(含文件)
function AddForm(url, formdata)
{
    $.ajax({
        url: url,
        type: 'post',
        data: formdata,
        cache: false,
        processData: false,
        contentType: false,
        success: function (m) {
            if (m.successed) {
                alert(m.msg)
                window.location.reload();
            }
            esle
            alert(m.msg);
        },
        error: function (returndata) {
            alert("网络堵塞!");
            window.location.reload();
        }
    })
}

//get上传数据(序列化)
function Senddata(url, getdata)
{
    var sendata = JSON.stringify(getdata);
    $.ajax({
        url: url,
        data: { "entity": sendata },
        type: 'get',
        dataType: 'json',
        success: function (m) {
            if (m.successed) {
                alert(m.msg, "提示");
                window.location.reload();
            }
            else
                alert(m.msg,"提示");
        }
    });
}

//上传键值对数据，不加工
function updateondate(url, id)
{
    $.ajax({
        url: url,
        data: { "id": id},
        type: 'get',
        dataType: 'json',
        success: function (m) {
            if (m.successed) {
                alert(m.msg, "提示");
                window.location.reload();
            }
            else
                alert(m.msg, "提示");
        }
    });
}


//根据id删除数据
function DeleteDate(url,id)
{
    $.ajax({
        url: url,
        data: { "id": encodeURI(encodeURI(id))},
        type: 'get',
        dataType: 'json',
        success: function (m) {
            if (m.jsonResult.state) {
                window.location.reload();
            }
            else
                alert(m.msg, "提示");
        }
    });
}

//获取(id、name)列表并放入selection控件中
function getroomtype(controls,url) {
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if (data.length > 0) {
                for (var i = 0; i < data[0].length; i++) {
                    $(controls).append("<option value='" + (data[0])[i] + "'>" + (data[1])[i] + "</option>");
                }
            }
        }
    })
}

//日期插件
function Getdate()
{
    var nowTemp = new Date();
    var nowDay = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0).valueOf();
    var nowMoth = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), 1, 0, 0, 0, 0).valueOf();
    var nowYear = new Date(nowTemp.getFullYear(), 0, 1, 0, 0, 0, 0).valueOf();
    var $myStart2 = $('#my-start-2');

    var checkin = $myStart2.datepicker({
        onRender: function (date, viewMode) {
            // 默认 days 视图，与当前日期比较
            var viewDate = nowDay;

            switch (viewMode) {
                // moths 视图，与当前月份比较
                case 1:
                    viewDate = nowMoth;
                    break;
                // years 视图，与当前年份比较
                case 2:
                    viewDate = nowYear;
                    break;
            }

            return date.valueOf() < viewDate ? 'am-disabled' : '';
        }
    }).on('changeDate.datepicker.amui', function (ev) {
        if (ev.date.valueOf() > checkout.date.valueOf()) {
            var newDate = new Date(ev.date)
            newDate.setDate(newDate.getDate() + 1);
            checkout.setValue(newDate);
        }
        checkin.close();
        $('#my-end-2')[0].focus();
    }).data('amui.datepicker');

    var checkout = $('#my-end-2').datepicker({
        onRender: function (date, viewMode) {
            var inTime = checkin.date;
            var inDay = inTime.valueOf();
            var inMoth = new Date(inTime.getFullYear(), inTime.getMonth(), 1, 0, 0, 0, 0).valueOf();
            var inYear = new Date(inTime.getFullYear(), 0, 1, 0, 0, 0, 0).valueOf();

            // 默认 days 视图，与当前日期比较
            var viewDate = inDay;

            switch (viewMode) {
                // moths 视图，与当前月份比较
                case 1:
                    viewDate = inMoth;
                    break;
                // years 视图，与当前年份比较
                case 2:
                    viewDate = inYear;
                    break;
            }

            return date.valueOf() <= viewDate ? 'am-disabled' : '';
        }
    }).on('changeDate.datepicker.amui', function (ev) {
        checkout.close();
    }).data('amui.datepicker');
}


//日期插件2
function Getdate2() {
    var nowTemp = new Date();
    var nowDay = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0).valueOf();
    var nowMoth = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), 1, 0, 0, 0, 0).valueOf();
    var nowYear = new Date(nowTemp.getFullYear(), 0, 1, 0, 0, 0, 0).valueOf();
    var $myStart2 = $('#my-start-3');

    var checkin = $myStart2.datepicker({
        onRender: function (date, viewMode) {
            // 默认 days 视图，与当前日期比较
            var viewDate = nowDay;

            switch (viewMode) {
                // moths 视图，与当前月份比较
                case 1:
                    viewDate = nowMoth;
                    break;
                // years 视图，与当前年份比较
                case 2:
                    viewDate = nowYear;
                    break;
            }

            return date.valueOf() < viewDate ? 'am-disabled' : '';
        }
    }).on('changeDate.datepicker.amui', function (ev) {
        if (ev.date.valueOf() > checkout.date.valueOf()) {
            var newDate = new Date(ev.date)
            newDate.setDate(newDate.getDate() + 1);
            checkout.setValue(newDate);
        }
        checkin.close();
        $('#my-end-3')[0].focus();
    }).data('amui.datepicker');

    var checkout = $('#my-end-3').datepicker({
        onRender: function (date, viewMode) {
            var inTime = checkin.date;
            var inDay = inTime.valueOf();
            var inMoth = new Date(inTime.getFullYear(), inTime.getMonth(), 1, 0, 0, 0, 0).valueOf();
            var inYear = new Date(inTime.getFullYear(), 0, 1, 0, 0, 0, 0).valueOf();

            // 默认 days 视图，与当前日期比较
            var viewDate = inDay;

            switch (viewMode) {
                // moths 视图，与当前月份比较
                case 1:
                    viewDate = inMoth;
                    break;
                // years 视图，与当前年份比较
                case 2:
                    viewDate = inYear;
                    break;
            }

            return date.valueOf() <= viewDate ? 'am-disabled' : '';
        }
    }).on('changeDate.datepicker.amui', function (ev) {
        checkout.close();
    }).data('amui.datepicker');
}

