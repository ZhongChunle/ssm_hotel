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

        <!-- 搜索条件 -->
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">员工姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="loginName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">真实姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="name" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">员工性别</label>
                            <div class="layui-input-inline">
                                <select name="sex" autocomplete="off" class="layui-input">
                                    <option value="">请选择性别</option>
                                    <option value="1">男</option>
                                    <option value="2">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">所属部门</label>
                            <div class="layui-input-inline">
                                <select name="deptId" autocomplete="off" id="s_deptId" class="layui-input">
                                    <option value="">请选择部门</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">开始日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="startDate" id="startData" autocomplete="off"
                                       class="layui-input" readonly>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">结束日期</label>
                            <div class="layui-input-inline">
                                <input type="text" name="endDate" id="endData" autocomplete="off" class="layui-input"
                                       readonly>
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

        <!-- 表格工具栏 -->
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i
                        class="layui-icon layui-icon-add-1"></i>添加
                </button>
            </div>
        </script>

        <!-- 数据表格 -->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <!-- 行工具栏 -->
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i
                    class="layui-icon layui-icon-edit"></i>编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i
                    class="layui-icon layui-icon-close"></i>删除</a>
            <button class="layui-btn layui-btn-xs layui-btn-warm" lay-event="resetPwd"><i
                    class="layui-icon layui-icon-refresh"></i>重置密码
            </button>
            <button class="layui-btn layui-btn-xs" lay-event="grantRole"><i class="layui-icon layui-icon-edit"></i>分配角色
            </button>
        </script>

        <!-- 添加和修改窗口 -->
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <!-- 隐藏域，保存员工id -->
                <input type="hidden" name="id" id="id">

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">登陆名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="loginName" id="loginname" lay-verify="required" autocomplete="off"
                                   placeholder="请输入登陆名称" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">员工姓名</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" id="name" lay-verify="required" autocomplete="off"
                                   placeholder="请输入员工姓名" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">入职日期</label>
                        <div class="layui-input-block">
                            <input type="text" name="hireDate" id="hiredate" readonly lay-verify="required"
                                   autocomplete="off" placeholder="请选择入职日期" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">所属部门</label>
                        <div class="layui-input-block">
                            <select name="deptId" id="deptid" class="layui-input">
                                <option value="">请选择部门</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">员工性别</label>
                        <div class="layui-input-block">
                            <input type="radio" name="sex" value="1" title="男" checked>
                            <input type="radio" name="sex" value="2" title="女">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">员工备注</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" name="remark" id="remark"></textarea>
                    </div>
                </div>


                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block" style="text-align: center;">
                        <button type="button" class="layui-btn" lay-submit lay-filter="formDemo"><span
                                class="layui-icon layui-icon-add-1"></span>提交
                        </button>
                        <button type="reset" class="layui-btn layui-btn-warm" id="restBtn"><span
                                class="layui-icon layui-icon-refresh-1"></span>重置
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <!-- 员工分配角色弹出层 -->
        <div style="display: none;padding: 5px" id="selectUserRoleDiv">
            <table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table', 'laydate', 'jquery', 'layer'], function () {
        var $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table,
            layer = layui.layer;

        //渲染日期组件
        laydate.render({
            elem: '#startData', //指定元素
            type: 'datetime'
        });
        laydate.render({
            elem: '#endData', //指定元素
            type: 'datetime'
        });
        laydate.render({
            elem: '#hiredate' //指定元素
        });

        //渲染表格组件
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/employee/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 100, title: '员工编号', align: "center"},
                {field: 'loginName', width: 120, title: '登录名', align: "center"},
                {field: 'name', width: 120, title: '真实姓名', align: "center"},
                {
                    field: 'sex', width: 80, title: '性别', align: "center", templet: function (e) {
                        return e.sex == 1 ? "男" : "女";
                    }
                },
                {field: 'deptName', width: 120, title: '所属部门', align: "center"},
                {field: 'remark', minWidth: 200, title: '备注', align: "center"},
                {field: 'hireDate', width: 180, title: '入职日期', align: "center"},
                {field: 'createDate', width: 180, title: '创建时间', align: "center"},
                {title: '操作', minWidth: 380, toolbar: '#currentTableBar', align: "center"}
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

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            //执行搜索重载
            table.reload('currentTableId', {
                where: data.field,
                page: {
                    curr: 1
                }
            });
            return false;
        });

        // 请求部门信息动态添加下拉框
        $.get("/admin/dept/deptList", function (data) {
            var html = "";
            for (let i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].deptname + '</option>';
            }
            $("[name='deptId']").append(html);
            // 重新渲染下拉框
            form.render("select");
        }, "json");

        // 添加监听事件
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                openAddWindow();
            }
        });

        // 行操作事件
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                updateEmployeeWindow(data);
                return false;
            } else if (obj.event === 'delete') {
                deleteById(data);
            } else if (obj.event === 'resetPwd') {
                resetPwd(data);
            } else if (obj.event === 'grantRole') {
                grantRole(data);
            }
        });

        var url; // 提交路径
        var mainIndex; // 打开窗口的索引
        // 添加窗口打开事件
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,
                title: "添加员工信息",
                area: ['800px', '400px'],
                content: $("#addOrUpdateWindow"),
                success: function () {
                    $("#restBtn").click();
                    // 提交请求
                    url = "/admin/employee/addEmployeeData";
                }
            });
        }

        // 编辑窗口打开事件
        function updateEmployeeWindow(data) {
            mainIndex = layer.open({
                type: 1,
                title: "编辑员工信息",
                area: ['800px', '400px'],
                content: $("#addOrUpdateWindow"),
                success: function () {
                    $("#loginname").prop("disabled", true);
                    // 数据回显
                    form.val("dataFrm", data);
                    // 提交请求
                    url = "/admin/employee/updateEmployeeData";
                }
            });
        }

        // 删除员工
        function deleteById(data) {
            // 提示用户是否删除该部门
            layer.confirm('真的删除【' + data.name + '】员工吗？', {icon: 3, title: "删除确认"}, function (index) {
                // 发送请求删除数据
                $.post("/admin/employee/deleteEmployeeData", {"id": data.id}, function (result2) {
                    console.log(result2);
                    if (result2.succser) {
                        // 刷新表格
                        table.reload('currentTableId');
                    }
                    layer.msg(result2.messages);
                }, "json");
                layer.close(index);
            });
        }

        // 重置密码
        function resetPwd(data) {
            // 提示用户是否重置密码
            layer.confirm('真的重置【' + data.name + '】员工密码吗？', {icon: 3, title: "重置确认"}, function (index) {
                // 发送请求重置
                $.post("/admin/employee/resetPwd", {"id": data.id}, function (result2) {
                    console.log(result2);
                    if (result2.succser) {
                        // 刷新表格
                        table.reload('currentTableId');
                    }
                    layer.msg(result2.messages);
                }, "json");
                layer.close(index);
            });
        }

        // 分配角色
        function grantRole(data) {
            mainIndex = layer.open({
                type: 1,
                title: "分配员工角色",
                area: ['800px', '400px'],
                content: $("#selectUserRoleDiv")
                , btn: ['保存', '取消',]
                , yes: function (index, layero) {
                    // 获取选中行
                    var checkStatus = table.checkStatus('roleTable');
                    // 判断行是否有选中
                    if(checkStatus.data.length > 0){
                        // 定义数组，保存选中的角色id
                        var idArr = [];
                        // 获取选中的角色
                        for (var i = 0; i < checkStatus.data.length; i++) {
                            idArr.push(checkStatus.data[i].id)
                        }
                        // 将数组分割开来
                        var ids = idArr.join(",");
                        // 发生请求
                        $.post("/admin/role/saveEmployeeRole",{"roleids":ids,"empId":data.id},function (msg) {
                            layer.msg(msg.messages);
                        },"json");
                        // 关闭窗口
                        layer.close(mainIndex);
                    }else {
                        layer.msg("请选中需要分配的角色");
                    }
                }
                , btn2: function (index, layero) {
                    //按钮【按钮二】的回调

                },
                success: function () {
                    initTable(data)
                }
            });
        }

        // 分配角色表格查询
        function initTable(data) {
            //渲染表格组件
            table.render({
                elem: '#roleTable',
                url: '${pageContext.request.contextPath}/admin/role/initRoleListByEmpId?id='+data.id,
                cols: [[
                    {type: "checkbox", width: 50},
                    {field: 'id', minWidth: 150, title: '角色编号', align: "center"},
                    {field: 'rolename', minWidth: 150, title: '角色名称', align: "center"},
                    {field: 'roledesc', minWidth: 150, title: '角色描述', align: "center"}
                ]]
            });
        }

        // 窗口提交事件
        form.on('submit(formDemo)', function (data) {
            $.post(url, data.field, function (result) {
                // 判断是否提交成功
                if (result.succser) {
                    // 刷新表格
                    table.reload('currentTableId');
                    // 关闭窗口
                    layer.close(mainIndex);
                }
                // 提示成功信息
                layer.msg(result.messages);
            }, "json");
            return false;
        });

    });

</script>

</body>
</html>

