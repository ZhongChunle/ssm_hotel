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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/layuimini.css?v=2.0.4.2"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/themes/default.css" media="all">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/layui/lib/font-awesome-4.7.0/css/font-awesome.min.css"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">
    <%--添加树节点--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui_ext/dtree/dtree.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui_ext/dtree/font/dtreefont.css"
          media="all">
    <style>
        .dtree {
            width: 200px !important;
        }
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-row">
            <%-- 左侧菜单树 --%>
            <div class="layui-col-md2">
                <!-- 树节点容器开始 -->
                <ul id="menuTree" class="dtree" data-id="0" style="width: 200px!important;"></ul>
                <!-- 树节点容器结束 -->
            </div>

            <%-- 右侧数据表格 --%>
            <div class="layui-col-md10">

                <%-- 表格工具栏 --%>
                <script type="text/html" id="toolbarDemo">
                    <div class="layui-btn-container">
                        <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i
                                class="layui-icon layui-icon-add-1"></i>添加
                        </button>
                    </div>
                </script>

                <%-- 数据表格 --%>
                <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

                <%-- 行工具栏 --%>
                <script type="text/html" id="currentTableBar">
                    <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i
                            class="layui-icon layui-icon-edit"></i>编辑</a>
                    <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i
                            class="layui-icon layui-icon-close"></i>删除</a>
                </script>

                <%-- 添加和修改窗口 --%>
                <div style="display: none;padding: 5px" id="addOrUpdateWindow">
                    <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                        <%-- 菜单编号 --%>
                        <input type="hidden" name="id">
                        <div class="layui-form-item">
                            <label class="layui-form-label">父级菜单</label>
                            <div class="layui-input-block">
                                <%--父级菜单隐藏域--%>
                                <input type="hidden" name="pid" id="pid">
                                <ul id="menuSelectTree" class="dtree" data-id="0"></ul>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">菜单名称</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" lay-verify="required" autocomplete="off"
                                       placeholder="请输入菜单名称" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">菜单地址</label>
                            <div class="layui-input-block">
                                <input type="text" name="href" autocomplete="off" placeholder="请输入菜单地址"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">菜单图标</label>
                            <div class="layui-input-block">
                                <input type="hidden" name="icon" id="icon">
                                <input type="text" name="iconFa" id="iconPicker" lay-filter="iconPicker"
                                       autocomplete="off" placeholder="请输入菜单图标" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">是否展开</label>
                                <div class="layui-input-block">
                                    <input type="radio" name="spread" value="1" title="是">
                                    <input type="radio" name="spread" value="0" title="否" checked>
                                </div>
                            </div>
                        </div>

                        <div class="layui-form-item layui-row layui-col-xs12">
                            <div class="layui-input-block" style="text-align: center;">
                                <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span
                                        class="layui-icon layui-icon-add-1"></span>提交
                                </button>
                                <button type="reset" class="layui-btn layui-btn-warm"><span
                                        class="layui-icon layui-icon-refresh-1"></span>清空
                                </button>
                                <button type="button" class="layui-btn layui-btn-danger" id="resetMenu"><span
                                        class="layui-icon layui-icon-return"></span>重置菜单
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<%-- 导入layui的js文件--%>
<script src="${pageContext.request.contextPath}/statics/layui/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.extend({
        dtree: '${pageContext.request.contextPath}/statics/layui_ext/dtree/dtree',
        iconPickerFa: '${pageContext.request.contextPath}/statics/layui/js/lay-module/iconPicker/iconPickerFa'
    }).use(['form', 'table', 'laydate', 'jquery', 'layer', 'dtree', 'iconPickerFa'], function () {
        var form = layui.form,
            laydate = layui.laydate,
            dtree = layui.dtree,
            layer = layui.layer,
            iconPickerFa = layui.iconPickerFa,
            table = layui.table;
        // 渲染左侧的树节点
        // 初始化树
        var menuTree = dtree.render({
            elem: "#menuTree",
            url: "/admin/menu/loadMenuTree", // 使用url加载（可与data加载同时存在）
            dataStyle: "layuiStyle", // 使用layui风格样式的数据格式
            dataFormat: "list", // 配置data的风格为list
            response: {message: "msg", statusCode: 0} // 修改response中返回数据的定义
        });

        //初始化图标选择器组件
        iconPickerFa.render({
            // 选择器，推荐使用input
            elem: '#iconPicker', // fa 图标接口
            url: "/statics/layui/lib/font-awesome-4.7.0/less/variables.less",
            // 是否开启搜索：true/false，默认true
            search: true,
            // 是否开启分页：true/false，默认true
            page: true,
            // 每页显示数量，默认12
            limit: 12,
            // 点击回调
            click: function (data) {
                //给图标隐藏域赋值
                $("#icon").val("fa " + data.icon);
            },
            // 渲染成功后的回调
            success: function (d) {
                // console.log(d);
            }
        });

        // 表格数据渲染
        table.render({
            elem: '#currentTableId',
            url: '/admin/menu/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {align: "center", type: 'numbers', width: 120, title: '排序'},
                {align: "center", field: 'id', width: 120, title: '菜单编号'},
                {align: "center", field: 'title', minWidth: 120, title: '菜单名称'},
                {align: "center", field: 'href', minWidth: 120, title: '菜单链接'},
                {
                    align: "center", field: 'spread', minWidth: 120, title: '是否展开', templet: function (d) {
                        return d.spread == 1 ? '<span style="color: #FFB800;">是</span>' : '<span style="color: #01AAED;">否</span>'
                    }
                },
                {
                    align: "center", field: 'icon', minWidth: 120, title: '菜单图标', templet: function (d) {
                        return '<i class="' + d.icon + '"></i>   '
                    }
                },
                {align: "center", title: '操作', minWidth: 150, toolbar: '#currentTableBar'}
            ]],
            page: true,
            done:function(res,curr,count) {
                // 判断当前页码是否大于1并且当前页码数量是0
                if(curr>1&& res.data.length==0){
                    var pageValue = curr - 1;
                    // 判断刷新表格的数据
                    table.reload({
                        page:{curr:pageValue}
                    });
                }
            }
        });

        // 监听左侧树节点点击监听事件
        dtree.on('node(menuTree)', function (boj) {
            table.reload('currentTableId', {
                where: {"id": boj.param.nodeId},
                page: {
                    curr: 1
                }
            });
        });

        // 添加监听事件
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                openAddWindow();
            }
        });

        // 窗口打开之气就要渲染下拉树选项
        var menuSelectTree = dtree.renderSelect({
            elem: "#menuSelectTree",
            url: "/admin/menu/loadMenuTree",
            dataStyle: "layuiStyle", // 使用layui风格样式的数据格式
            dataFormat: "list", // 配置data的风格为list
            response: {message: "msg", statusCode: 0} // 修改response中返回数据的定义
        });

        // 监听下拉树事件【给父级隐藏域添加选中id：重点】
        dtree.on('node(menuSelectTree)', function (boj) {
            $("#pid").val(boj.param.nodeId);
        });

        // 行操作事件
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                openUpdateDept(data);
                return false;
            } else if (obj.event === 'delete') {
                deleteById(data);
            }
        });

        // 重置表单
        $("#resetMenu").click(function(){
            $("#dataFrm")[0].reset();
        });
        var url; // 提交路径
        var mainIndex; // 打开窗口的索引

        // 添加窗口打开事件
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,
                title: "添加菜单信息",
                area: ['800px', '500px'],
                content: $("#addOrUpdateWindow"),
                success: function () {
                    $("#dataFrm")[0].reset();
                    // 提交请求
                    url = "/admin/menu/addMenu";
                    // 设置图标默认值
                    iconPickerFa.checkIcon("iconPicker", "fa fa-star");
                }
            });
        }

        // 行编辑事件
        function openUpdateDept(data) {
            mainIndex = layer.open({
                type: 1,
                title: "编辑菜单信息",
                area: ['800px', '500px'],
                content: $("#addOrUpdateWindow"),
                success: function () {
                    // 数据回显
                    form.val("dataFrm", data);
                    // 提交请求
                    url = "/admin/menu/updateMenu";
                    // 设置图标默认值
                    iconPickerFa.checkIcon("iconPicker", data.icon);
                    // 判断父节点是否等于0,没有父级菜单，应该显示请选择
                    if (data.pid == 0) {
                        // 刷新菜单树
                        menuSelectTree.reload();
                    }
                    // 父级菜单回显【下拉树id属性值，父级id】
                    dtree.dataInit("menuSelectTree", data.pid);
                    dtree.selectVal("menuSelectTree");
                }
            });
        }

        // 行删除事件
        function deleteById(data) {
            // 判断当前的菜单是否有子菜单
            $.get("/admin/menu/checkMenuHasChild",{"id":data.id},function(result){
                if(result.exist){
                    // 提示无法删除
                    layer.msg(result.messages);
                }else {
                    // 提示用户是否删除该部门
                    layer.confirm('真的删除' + data.title + '菜单吗？', {icon: 3, title: "删除确认"}, function (index) {
                        $.post("/admin/menu/deleteMenuById", {"id": data.id}, function (result2) {
                            console.log("删除数据响应："+result2);
                            if (result2.succser) {
                                // 刷新表格
                                table.reload('currentTableId');
                                // 刷新左侧树
                                menuTree.reload();
                                // 刷新下拉树
                                menuSelectTree.reload();
                                // 关闭窗口
                                layer.close(mainIndex);
                            }
                            layer.msg(result2.messages);
                        }, "json");
                    });
                }
            },"json");
        }

        // 操作窗口的表单信息
        $("#resetMenu").click(function () {
            menuSelectTree.selectResetVal();
        });

        // 窗口提交事件
        form.on('submit(doSubmit)', function (data) {
            console.log(data);
            $.post(url, data.field, function (result) {
                // 判断是否提交成功
                if (result.succser) {
                    // 刷新表格
                    table.reload('currentTableId');
                    // 刷新左侧树
                    menuTree.reload();
                    // 刷新下拉树
                    menuSelectTree.reload();
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
</html>
