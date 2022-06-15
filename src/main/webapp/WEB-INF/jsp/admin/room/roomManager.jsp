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
    <style>
        .thumbBox{ height:200px; overflow:hidden; border:1px solid #e6e6e6; border-radius:2px; cursor:pointer; position:relative; text-align:center; line-height:200px;width: 210px;}
        .thumbImg{ max-width:100%; max-height:100%; border:none;}
        .thumbBox:after{ position:absolute; width:100%; height:100%;line-height:200px; z-index:-1; text-align:center; font-size:20px; content:"缩略图"; left:0; top:0; color:#9F9F9F;}
    </style>
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
                            <label class="layui-form-label">房间编号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="roomnum" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">房间类型</label>
                            <div class="layui-input-inline">
                                <select name="roomtypeid" id="s_roomTypeId" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">所属楼层</label>
                            <div class="layui-input-inline">
                                <select name="floorid" id="s_floorId" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">房间状态</label>
                            <div class="layui-input-inline">
                                <select name="status" id="s_status" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                    <option value="1">已预定</option>
                                    <option value="2">已入住</option>
                                    <option value="3">可预定</option>
                                </select>
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
                <!-- 隐藏域，保存房型id -->
                <input type="hidden" name="id">
                <div class="layui-col-md12 layui-col-xs12">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-col-md9 layui-col-xs7">
                            <div class="layui-form-item magt3" style="margin-top: 8px;">
                                <label class="layui-form-label">房间编号</label>
                                <div class="layui-input-block">
                                    <input type="text" class="layui-input" name="roomnum" lay-verify="required"  placeholder="请输入房间编号">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">房间类型</label>
                                <div class="layui-input-block">
                                    <select name="roomtypeid" id="roomtypeid" lay-verify="required">
                                        <option value="">请选择房型</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">房间备注</label>
                                <div class="layui-input-block">
                                    <textarea class="layui-textarea" name="remark" id="remark"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md3 layui-col-xs5">
                            <div class="layui-upload-list thumbBox mag0 magt3">
                                <input type="hidden" name="photo" id="photo" value="/statics/images/defaultimg.jpg">
                                <img class="layui-upload-img thumbImg" src="/statics/images/defaultimg.jpg">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item magb0">
                        <div class="layui-inline">
                            <label class="layui-form-label">所属楼层</label>
                            <div class="layui-input-inline">
                                <select name="floorid" id="floorid" lay-verify="required">
                                    <option value="">请选择楼层</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">房间状态</label>
                            <div class="layui-input-inline">
                                <select name="status" id="status" lay-verify="required">
                                    <option value="">请选择房间状态</option>
                                    <option value="1">已预定</option>
                                    <option value="2">已入住</option>
                                    <option value="3">可预定</option>
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">房间要求</label>
                        <div class="layui-input-block" >
                            <textarea id="roomrequirement" name="roomrequirement" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">房间详情</label>
                        <div class="layui-input-block" >
                            <textarea id="roomdesc" name="roomdesc" style="display: none;"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="text-align: center;">
                            <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit" id="doSubmit"><span
                                    class="layui-icon layui-icon-add-1"></span>提交
                            </button>
                            <button id="restBtn" type="reset" class="layui-btn layui-btn-warm"><span
                                    class="layui-icon layui-icon-refresh-1"></span>重置
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table', 'laydate', 'jquery', 'layer','upload','layedit'], function () {
        var $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            upload = layui.upload,
            layedit = layui.layedit,
            layer = layui.layer,
            table = layui.table;

        //渲染表格组件
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/room/roomList',
            toolbar: '#toolbarDemo',
            cols: [[
                {field: 'id', width: 60, title: '编号', align: "center"},
                {field: 'roomnum', minWidth: 120, title: '房间编号', align: "center"},
                {field: 'typeName', minWidth: 100, title: '房间类型', align: "center"},
                {field: 'name', minWidth: 100, title: '所属楼层', align: "center"},
                {field: 'statusStr', minWidth: 100, title: '房间状态', align: "center"},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
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
            tableIns.reload({
                where: data.field,
                page: {
                    curr: 1
                }
            });
            return false;
        });

        // 查询房间类型下拉列表
        $.get("/admin/roomType/findAll",function(data) {
            var html = '';
            for (let i = 0; i < data.length; i++) {
                html += '<option value="'+data[i].id+'">'+data[i].typename+'</option>'
            }
            // 将数据渲染到下拉框
            $("[name='roomtypeid']").append(html);
            // 刷新layui下拉框
            form.render('select');
        },"json");

        // 查询楼层下拉列表
        $.get("/admin/floor/findAll",function(data) {
            var html = '';
            for (let i = 0; i < data.length; i++) {
                html += '<option value="'+data[i].id+'">'+data[i].name+'</option>'
            }
            // 将数据渲染到下拉框
            $("[name='floorid']").append(html);
            // 刷新layui下拉框
            form.render('select');
        },"json");

        // 添加监听事件
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                openAddWindow();
            }
        });

        var url; // 提交路径
        var mainIndex; // 打开窗口的索引
        var layeditIndex; // 富文本编辑器

        // 添加窗口打开事件
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,
                title:"添加房房间信息",
                area: ['800px', '800px'],
                maxmin: true,
                content:$("#addOrUpdateWindow"),
                success:function(){
                    $("#restBtn").click();
                    // 提交请求
                    url = "/admin/room/addRoom";
                    // 重置上传的图片
                    $(".thumbImg").attr('src',"/hotel/show/images/defaultimg.jpg");
                    // 重置隐藏域文件的值
                    $("#photo").val("images/defaultimg.jpg");
                }
            });
            // 窗口最大化
            layer.full(mainIndex);
            // 初始化富文本编辑器
            layeditIndex = layedit.build('roomdesc',{
                uploadImage:{
                    // 文件上传地址和提交方式
                    url:'/admin/file/uploadFile',
                    type:'post'
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

        // 添加文件上传事件
        upload.render({
            elem: '.thumbImg' //绑定元素
            ,url: '/admin/file/uploadFile/' //上传接口
            ,acceptMime:'image/*' // 规定打开文件选择框时，筛选出的文件类型
            ,field:'file' // 文件上传的字段，等同于input标签的name值，与控制器的接收值一致
            ,method:'post' // 提交方式
            ,done: function(res){
                // 上传成功回显图片
                $(".thumbImg").attr('src',res.data.src);
                $(".thumbBox").css("backgrount",'#fff')
                // 设置隐藏域的值
                $("#photo").val(res.imagePath);
            }
            ,error: function(){
                //请求异常回调
                layer.msg("图片上传异常");
            }
        });

        // 行编辑事件
        function openUpdateDept(data) {
            mainIndex = layer.open({
                type: 1,
                maxmin: true,
                title: "编辑房间信息",
                area: ['800px', '800px'],
                content: $("#addOrUpdateWindow"),
                success: function () {
                    // 数据回显
                    form.val("dataFrm", data);
                    // 提交请求
                    url = "/admin/room/updateRoom";
                    // 图片回显
                    $(".thumbImg").attr('src',"/hotel/show/"+data.photo);
                    // 设置隐藏域的值
                    $("#photo").val(data.photo);
                }
            });
            // 窗口最大化
            layer.full(mainIndex);
            // 初始化富文本编辑器
            layeditIndex = layedit.build('roomdesc',{
                uploadImage:{
                    // 文件上传地址和提交方式
                    url:'/admin/file/uploadFile',
                    type:'post'
                }
            });
        }

        // 行删除事件
        function deleteById(data) {
            // 判断当前的菜单是否有子菜单
            if(data.status == 3){
                // 提示用户是否删除该部门
                layer.confirm('真的删除' + data.roomnum + '房间吗？', {icon: 3, title: "删除确认"}, function (index) {
                    $.post("/admin/room/deltetRoom", {"roomId": data.id}, function (result2) {
                        if (result2.exist) {
                            // 刷新表格
                            table.reload('currentTableId');
                            // 关闭窗口
                            layer.close(mainIndex);
                        }
                        layer.msg(result2.messages);
                    }, "json");
                });
            }else {
                layer.msg("只有可预定状态的房间才可以删除");
            }
        }

        // 窗口提交事件
        form.on('submit(doSubmit)', function (data) {
            // 添加信息中有富文本的编辑器，所以这里需要使用序列化的方式获取到值一次性的进行提交
            // 将富文本编辑器的内容同步到文本域中，否则获取不到数据
            layedit.sync(layeditIndex);
            // console.log($("#dataFrm").serialize());
            $.post(url, $("#dataFrm").serialize(), function (result) {
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
