<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>部门表信息查询</title>
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
        <%--顶部搜索--%>
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="${pageContext.request.contextPath}/admin/dept/list">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">部门名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="deptname" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn" lay-submit lay-filter="data-search-btn"><i
                                    class="layui-icon"></i> 搜 索
                            </button>
                            <button type="reset" class="layui-btn layui-btn-warm"><i
                                    class="layui-icon layui-icon-refresh-1"></i> 重 置
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>
        <%--顶部添加--%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i
                        class="layui-icon layui-icon-addition"></i> 添加
                </button>
            </div>
        </script>
        <%--表格渲染容器--%>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
        <%--行操作事件--%>
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit"><i
                    class="layui-icon layui-icon-edit"></i> 编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i
                    class="layui-icon layui-icon-delete"></i> 删除</a>
        </script>
        <%-- 添加和修改窗口 --%>
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <div class="layui-form-item">
                    <%--部门id--%>
                    <input name="id" type="hidden">
                    <label class="layui-form-label">部门名称</label>
                    <div class="layui-input-block">
                        <input type="text" id="deptName" name="deptname" lay-verify="required" autocomplete="off"
                               placeholder="请输入部门名称" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">部门地址</label>
                    <div class="layui-input-block">
                        <input type="text" id="address" name="address" lay-verify="required" autocomplete="off" placeholder="请输入部门地址"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">部门备注</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" id="remark" name="remark" id="content"></textarea>
                    </div>
                </div>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block" style="text-align: center;">
                        <button type="button" class="layui-btn" lay-submit lay-filter="formDemo"><span
                                class="layui-icon layui-icon-add-1"></span>提交
                        </button>
                        <button type="reset" id="restBtn" class="layui-btn layui-btn-warm"><span
                                class="layui-icon layui-icon-refresh-1"></span>重置
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table','layer'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;
            layer = layui.layer;
        // 表格数据渲染
        table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/dept/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {align: "center", type: 'numbers', width: 120, title: '排序'},
                {align: "center",field: 'id', width: 120, title: '部门编号'},
                {align: "center", field: 'deptname', minWidth: 120, title: '部门名称'},
                {align: "center", field: 'address', minWidth: 120, title: '部门地址', sort: true},
                {align: "center", field: 'createDate', minWidth: 120, title: '创建时间'},
                {align: "center", field: 'remark', title: '备注', minWidth: 150},
                {align: "center", title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
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

        // 添加监听事件
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                openAddWindow();
            }
        });

        var url; // 提交路径
        var mainIndex; // 打开窗口的索引
        // 添加窗口打开事件
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,
                title:"添加部门信息",
                area: ['600px', '350px'],
                content:$("#addOrUpdateWindow"),
                success:function(){
                    $("#restBtn").click();
                    // 提交请求
                    url = "/admin/dept/addDept";
                }
            });
        }

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
        
        // 编辑事件
        function openUpdateDept(data) {
            mainIndex = layer.open({
                type: 1,
                title:"编辑部门信息",
                area: ['600px', '350px'],
                content:$("#addOrUpdateWindow"),
                success:function(){
                    // 数据回显[回显表单的值，回显的数据]
                    form.val("dataFrm",data);
                    // 提交请求
                    url ="/admin/dept/updateDept";
                }
            });
        }

        // 部门提交事件
        form.on('submit(formDemo)', function (data) {
            console.log(data);
            $.post(url,data.field,function(result){
                // 判断是否提交成功
                if(result.succser){
                    // 刷新表格
                    table.reload('currentTableId');
                    // 关闭窗口
                    layer.close(mainIndex);
                }
                // 提示成功信息
                layer.msg(result.messages);
            },"json")
            return false;
        });
        
        // 删除部门
        function deleteById(data) {
            // 判断当前的部门下是否有员工存在
            $.get("/admin/dept/checkDeptHasEmployee",{"id":data.id},function(result){
                if(result.exist){
                    // 提示用户无法删除
                    layer.msg(result.messages);
                }else {
                    // 提示用户是否删除该部门
                    layer.confirm('真的删除'+data.deptname+'部门吗？',{icon:3,title:"删除确认"}, function (index) {
                        // 发送请求删除数据
                        $.post("/admin/dept/deleteById",{"id":data.id},function(result2){
                            console.log(result2);
                            if(result2.succser){
                                // 刷新表格
                                table.reload('currentTableId');
                            }
                            layer.msg(result2.messages);
                        },"json");
                        layer.close(index);
                    });
                }
            },"json");
        }
    });
</script>

</body>
</html>
