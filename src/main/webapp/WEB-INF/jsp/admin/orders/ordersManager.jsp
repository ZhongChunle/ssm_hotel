<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.5.5/css/layui.css"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <%-- 搜索条件 --%>
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">预订人</label>
                            <div class="layui-input-inline">
                                <input type="text" name="reservationName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">身份证号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="idCard" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">手机号码</label>
                            <div class="layui-input-inline">
                                <input type="text" name="phone" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">房间类型</label>
                            <div class="layui-input-inline">
                                <select name="roomTypeId"  autocomplete="off" class="layui-input s_roomTypeId">
                                    <option value="">全部</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">预订状态</label>
                            <div class="layui-input-inline">
                                <select name="status" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                    <option value="1">待确认</option>
                                    <option value="2">已确认</option>
                                    <option value="3">已入住</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">开始日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="startDate" id="startTime" autocomplete="off" class="layui-input" placeholder="请选择开始日期(预订)" readonly>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">结束日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="endDate" id="endTime" autocomplete="off" class="layui-input" placeholder="请选择结束日期(预订)" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="text-align: center">
                            <button type="submit" class="layui-btn"  lay-submit lay-filter="data-search-btn"><i class="layui-icon layui-icon-search"></i>搜索</button>
                            <button type="reset" class="layui-btn layui-btn-warm"><i class="layui-icon layui-icon-refresh-1"></i>重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <%-- 表格工具栏 --%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="batchConfirm"><i
                        class="layui-icon layui-icon-edit"></i>批量确认
                </button>
            </div>
        </script>

        <%-- 数据表格 --%>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <%-- 行工具栏 --%>
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i
                    class="layui-icon layui-icon-ok-circle"></i>确认</a>
        </script>


    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table', 'laydate', 'jquery', 'layer'], function () {
        var $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            layer = layui.layer,
            table = layui.table;

        //渲染日期组件
        laydate.render({
            elem: '#startTime', //指定元素
            type: 'datetime'
        });
        laydate.render({
            elem: '#endTime', //指定元素
            type: 'datetime'
        });


        //渲染表格组件
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '/admin/orders/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 100, title: '预订编号', align: "center"},
                {field: 'typeName', width: 100, title: '预订房型', align: "center"},
                {field: 'roomNum', width: 80, title: '房间号', align: "center"},
                {field: 'reservationName', width: 100, title: '预订人', align: "center"},
                {field: 'idCard', minWidth: 250, title: '身份证号', align: "center"},
                {field: 'phone', minWidth: 250, title: '手机号', align: "center"},
                {field: 'status', width: 80, title: '状态', align: "center",templet:function (d) {
                        if(d.status==1){
                            return "待确认";
                        }else if(d.status==2){
                            return "已确认";
                        }else if(d.status==3){
                            return "已入住";
                        }else if(d.status==4){
                            return "已退房";
                        }
                    }},
                {field: 'reservePrice', width: 80, title: '预订价', align: "center"},
                {field: 'reserveDate', width: 160, title: '预订时间', align: "center"},
                {field: 'arriveDate', width: 110, title: '入住日期', align: "center"},
                {field: 'leaveDate', width: 110, title: '离店日期', align: "center"},
                {title: '操作', width: 120, toolbar: '#currentTableBar', align: "center",fixed:"right"}
            ]],
            page: true,
            done: function (res, curr, count) {
                //判断当前页码是否是大于1并且当前页的数据量为0
                if (curr > 1 && res.data.length == 0) {
                    var pageValue = curr - 1;
                    //刷新数据表格的数据
                    tableIns.reload({
                        page: {curr: pageValue}
                    });
                }
            }
        });

        // 查询房间类型下拉列表
        $.get("/admin/roomType/findAll",function(data) {
            var html = '';
            for (let i = 0; i < data.length; i++) {
                html += '<option value="'+data[i].id+'">'+data[i].typename+'</option>'
            }
            // 将数据渲染到下拉框
            $("[name='roomTypeId']").append(html);
            // 刷新layui下拉框
            form.render('select');
        },"json");

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            tableIns.reload({
                where: data.field,
                page: {
                    curr: 1
                }
            });
            return false;
        });

        // 行操作事件
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                confirmOrders(data);
                return false;
            }
        });

        // 批量监听事件
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'batchConfirm') {  // 监听添加操作
                barchConfim();
            }
        });

        var url; // 提交路径
        var mainIndex; // 打开窗口的索引

        // 预订确认事件
        function confirmOrders(data) {
            // 判断当前的状态
            if(data.status ==1){
                // 发送请求
                $.post("/admin/orders/confirmOrders",{"id":data.id},function (result) {
                    if(result.succser){
                        // 刷新表格数据
                        tableIns.reload();
                    }
                    layer.msg(result.messages);
                },"json");
            }else {
                layer.msg("该订单已确认，无需重复操作");
            }
        }

        // 批量监听事件
        function barchConfim() {
            // 获取选中行
            var checkStatus = table.checkStatus('currentTableId'); //idTest 即为基础参数 id 对应的值
            //获取选中行数量，可作为是否有选中行的条件
            var lengths = checkStatus.data.length;
            if( lengths > 0){
                // 判断包含或与入住的
                for (var i = 0; i < lengths; i++) {
                    if(checkStatus.data[i].status != 1){
                        layer.alert("只能批量待确认的订单");
                        return;
                    }
                }

                // 提示用户是否确认
                layer.confirm("确定要批量确认这些订单吗",{icon:3,title:"提示"},function (index) {
                    // 获取选中行的数据
                    var data = checkStatus.data;
                    // 声明数组存储批量确认的id
                    var idsArr = [];
                    // 循环获取到待确认的id
                    for (var i = 0; i < lengths; i++) {
                        // 将选中行的id放到数组中
                        idsArr.push(data[i].id);
                    }
                    // 将数组转换层字符串
                    var ids = idsArr.join(",");
                    // 发送请求
                    $.post("/admin/orders/barchConfim",{"ids":ids},function (result) {
                        if(result.succser){
                            // 刷新表格数据
                            tableIns.reload();
                        }
                        layer.msg(result.messages);
                    },"json");
                });
            }else {
                layer.msg("请选择需要确认的订单");
            }
        }

    });
</script>

</body>
</html>
