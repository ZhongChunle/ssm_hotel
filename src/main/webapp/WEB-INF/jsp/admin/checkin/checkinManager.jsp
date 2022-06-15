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
                            <label class="layui-form-label">入住人</label>
                            <div class="layui-input-inline">
                                <input type="text" name="customername" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">身份证号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="idcard" autocomplete="off" class="layui-input">
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
                                <select name="roomtypeid" autocomplete="off" class="layui-input s_roomTypeId">
                                    <option value="">全部</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">入住状态</label>
                            <div class="layui-input-inline">
                                <select name="status" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                    <option value="1">入住中</option>
                                    <option value="2">已离店</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">入住日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="arrivedate" id="startTime" autocomplete="off"
                                       class="layui-input" placeholder="请选择入住日期" readonly>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">离店日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="leavedate" id="endTime" autocomplete="off" class="layui-input"
                                       placeholder="请选择离店日期" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="text-align: center">
                            <button type="submit" class="layui-btn" lay-submit lay-filter="data-search-btn"><i
                                    class="layui-icon layui-icon-search"></i>搜索
                            </button>
                            <button type="reset" class="layui-btn layui-btn-warm"><i
                                    class="layui-icon layui-icon-refresh-1"></i>重置
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <%-- 表格工具栏 --%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="checkIn"><i
                        class="layui-icon layui-icon-edit"></i>登记入住
                </button>
            </div>
        </script>

        <%-- 数据表格 --%>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <%-- 行工具栏 --%>
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="checkout"><i
                    class="layui-icon layui-icon-delete"></i>退房</a>
        </script>

        <%-- 添加和修改窗口 --%>
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">预订编号</label>
                        <div class="layui-input-inline">
                            <input type="text" name="ordersid" lay-verify="required" autocomplete="off" readonly
                                   placeholder="请选择预订订单" class="layui-input">
                        </div>
                        <div class="layui-form-mid" style="position:relative;bottom:5px;">
                            <button type="button" class="layui-btn layui-btn-sm" id="openOrdersWindow"><i
                                    class="layui-icon layui-icon-add-circle-fine"></i>选择订单
                            </button>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">所属房型</label>
                        <div class="layui-input-inline">
                            <input type="hidden" name="roomtypeid">
                            <input type="text" name="roomType" placeholder="请选择房型" lay-verify="required"
                                   autocomplete="off" readonly class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">房间编号</label>
                        <div class="layui-input-inline">
                            <input type="hidden" name="roomid">
                            <input type="text" name="room" placeholder="请输入房间号码" lay-verify="required"
                                   autocomplete="off" readonly class="layui-input">
                        </div>
                    </div>

                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">入住人</label>
                        <div class="layui-input-inline">
                            <input type="text" name="customername" placeholder="请输入入住人姓名" lay-verify="required"
                                   autocomplete="off" readonly class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">手机号码</label>
                        <div class="layui-input-inline">
                            <input type="text" name="phone" placeholder="请输入手机号码" lay-verify="required"
                                   autocomplete="off" readonly class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">入住价格</label>
                        <div class="layui-input-inline">
                            <input type="text" name="payprice" placeholder="请输入入住价格" lay-verify="required"
                                   autocomplete="off" readonly class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">入住状态</label>
                        <div class="layui-input-inline">
                            <input type="text" value="" autocomplete="off" readonly class="layui-input">
                        </div>
                    </div>

                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">入住日期</label>
                        <div class="layui-input-inline">
                            <input type="text" name="arrivedate" placeholder="请选择入住日期" readonly autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">离店日期</label>
                        <div class="layui-input-inline">
                            <input type="text" name="leavedate" placeholder="请选择离店日期" readonly autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">身份证号</label>
                    <div class="layui-input-block">
                        <input type="text" name="idcard" placeholder="请输入身份证号码" lay-verify="required" autocomplete="off"
                               readonly class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" name="remark"></textarea>
                    </div>
                </div>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block" style="text-align: center;">
                        <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span
                                class="layui-icon layui-icon-add-1"></span>提交
                        </button>
                        <button type="reset" class="layui-btn layui-btn-warm"><span
                                class="layui-icon layui-icon-refresh-1"></span>重置
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <!-- 选择预定订单窗口 -->
        <div style="display: none;" id="selectOrdersWindow">
            <%-- 数据表格 --%>
            <table class="layui-hide" id="ordersTableId" lay-filter="ordersTableFilter"></table>
        </div>

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

        // 查询房间类型下拉列表
        $.get("/admin/roomType/findAll", function (data) {
            var html = '';
            for (let i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].typename + '</option>'
            }
            // 将数据渲染到下拉框
            $("[name='roomtypeid']").append(html);
            // 刷新layui下拉框
            form.render('select');
        }, "json");

        //渲染表格组件
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/checkin/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {field: 'id', width: 100, title: '入住编号', align: "center"},
                {
                    field: 'roomType', width: 100, title: '入住房型', align: "center"
                },
                {
                    field: 'room', width: 80, title: '房间号', align: "center"
                },
                {field: 'customername', width: 100, title: '入住人', align: "center"},
                {field: 'idcard', minWidth: 120, title: '身份证号', align: "center"},
                {field: 'phone', width: 150, title: '手机号', align: "center"},
                {field: 'status', width: 100, title: '状态', align: "center", templet: function (d) {
                        if (d.status == 1) {
                            return "入住中";
                        } else if (d.status == 2) {
                            return "已退房";
                        }
                    }},
                {field: 'payprice', width: 120, title: '支付金额', align: "center"},
                {field: 'arrivedate', width: 170, title: '入住日期', align: "center"},
                {field: 'leavedate', width: 170, title: '离店日期', align: "center"},
                {title: '操作', width: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true
        });

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

        // 入住登记监听事件
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'checkIn') {  // 监听添加操作
                openCheckInWindow();
            }
        });

        // 行操作事件
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'checkout') {
                checkOut(data);
                return false;
            }
        });

        var url; // 提交路径
        var mainIndex; // 打开窗口的索引
        // 添加窗口打开事件
        function openCheckInWindow() {
            mainIndex = layer.open({
                type: 1,
                title: "入住登记",
                area: ['800px', '570px'],
                content: $("#addOrUpdateWindow"),
                success: function () {
                    $("#dataFrm")[0].reset();
                    // 提交请求
                    url = "/admin/checkin/addCheckin";
                }
            });
        }

        // 窗口中点击选择订单事件
        $("#openOrdersWindow").click(function () {
            var index = layer.open({
                type: 1,
                title: "选择入住订单",
                area: ['1300px', '500px'],
                maxmin: true,
                content: $("#selectOrdersWindow"),
                success: function () {
                    $("#dataFrm")[0].reset();
                    // 提交请求
                    url = "${pageContext.request.contextPath}/admin/checkin/addCheckin";
                    // 渲染表格
                    initOrdersTable();
                },
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    // 获取选中行
                    var checkStatus = table.checkStatus('ordersTableId'); //idTest 即为基础参数 id 对应的值
                    if (checkStatus.data.length > 0) {
                        // 获取当前行的数据
                        var data = checkStatus.data[0];
                        // 给登记入住信息回显选中的订单数据
                        form.val("dataFrm", {
                            //key是form表单的name属性值
                            "ordersid": data.id//预订id
                            , "roomtypeid": data.roomTypeId //房型ID
                            , "roomType": data.typeName //房型名称
                            , "room": data.roomNum   //房间号码
                            , "roomid": data.roomId          //房间ID
                            , "customername": data.reservationName   //预订人姓名
                            , "phone": data.phone    //预订人手机
                            , "payprice": data.reservePrice  //预订价格
                            , "arrivedate": data.arriveDate  //入住日期
                            , "leavedate": data.leaveDate    //离店日期
                            , "idcard": data.idCard  //身份证号码
                            , "remark": data.remark //备注
                        });
                        //关闭窗口
                        layer.close(index);
                    } else {
                        layer.msg("请选择一条订单信息再确认");
                    }
                },
                cancel: function () {

                }
            });
            // 开启最大化
            layer.full(index);
        });

        // 选择入住信息的表格渲染方法
        function initOrdersTable() {
            table.render({
                elem: '#ordersTableId',
                url: '${pageContext.request.contextPath}/admin/orders/list?status=2',
                cols: [[
                    {type: "radio", width: 50},
                    {field: 'id', width: 100, title: '入住编号', align: "center"},
                    {field: 'typeName', minWidth: 150, title: '入住房型', align: "center"},
                    {field: 'roomNum', minWidth: 140, title: '房间号', align: "center"},
                    {field: 'reservationName', width: 100, title: '入住人', align: "center"},
                    {field: 'idCard', minWidth: 200, title: '身份证号', align: "center"},
                    {field: 'phone', minWidth: 200, title: '手机号', align: "center"},
                    {
                        field: 'status', width: 100, title: '状态', align: "center", templet: function (d) {
                            if (d.status == 1) {
                                return "待确认";
                            } else if (d.status == 2) {
                                return "已确认";
                            } else if (d.status == 3) {
                                return "已入住";
                            }
                        }
                    },
                    {field: 'reservePrice', width: 120, title: '支付金额', align: "center"},
                    {field: 'reserveDate', minWidth: 200, title: '预订时间', align: "center"},
                    {field: 'arriveDate', width: 170, title: '入住日期', align: "center"},
                    {field: 'leaveDate', width: 170, title: '离店日期', align: "center"}
                ]],
                page: true
            });
        }
        
        // 退房事件
        function checkOut(data) {
            console.log(data);
            // 判断当前的入住状态
            if(data.status == 1){
                // 提示是否退房
                layer.confirm('您确定要办理退房吗？', {icon: 3, title:'退房提示'}, function(index){
                    //发送请求
                    $.post("/admin/checkout/addCheckout",{"checkInId":data.id,"consumePrice":data.payprice},function(result){
                        if(result.succser){
                            layer.alert(result.messages,{icon:1});
                            tableIns.reload();
                        }else {
                            layer.alert(result.messages,{icon:5});
                        }
                    },"json");
                    layer.close(index);
                });
            }else {
                layer.msg("该入住订单已完成，无需重复办理退房");
            }
        }

        // 提交入住
        form.on('submit(doSubmit)', function (data) {
            console.log(data.field);
            // JSON.stringify(data.field);

            $.post(url, data.field, function (result) {
                // 判断是否提交成功
                if (result.succser) {
                    // 刷新表格
                    tableIns.reload();
                    // 关闭窗口
                    layer.close(mainIndex);
                }
                // 提示成功信息
                layer.msg(result.messages);
            }, "json")
            return false;
        });
    });
</script>

</body>
</html>
