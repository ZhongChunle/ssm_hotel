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
  <%--导入树形插件--%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui_ext/dtree/dtree.css">
  <%--树形图标--%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui_ext/dtree/font/dtreefont.css">
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
              <label class="layui-form-label">角色名称</label>
              <div class="layui-input-inline">
                <input type="text" name="roleName" autocomplete="off" class="layui-input">
              </div>
            </div>
            <div class="layui-inline">
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
        <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i class="layui-icon layui-icon-add-1"></i>添加 </button>
      </div>
    </script>

    <!-- 数据表格 -->
    <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

    <!-- 行工具栏 -->
    <script type="text/html" id="currentTableBar">
      <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
      <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i class="layui-icon layui-icon-close"></i>删除</a>
      <a class="layui-btn layui-btn-xs data-count-edit" lay-event="grantMenu"><i class="layui-icon layui-icon-edit"></i>分配菜单</a>
    </script>

    <!-- 添加和修改窗口 -->
    <div style="display: none;padding: 5px" id="addOrUpdateWindow">
      <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
        <div class="layui-form-item">
          <label class="layui-form-label">角色名称</label>
          <div class="layui-input-block">
            <!-- 角色编号 -->
            <input type="hidden" name="id">
            <input type="text" name="roleName" lay-verify="required" autocomplete="off"
                   placeholder="请输入角色名称" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">角色备注</label>
          <div class="layui-input-block">
            <textarea class="layui-textarea" name="roleDesc" id="roleDesc"></textarea>
          </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
          <div class="layui-input-block" style="text-align: center;">
            <button type="button" class="layui-btn" lay-submit lay-filter="formDemo"><span
                    class="layui-icon layui-icon-add-1"></span>提交
            </button>
            <button type="reset" class="layui-btn layui-btn-warm"><span
                    class="layui-icon layui-icon-refresh-1" id="restBtn"></span>重置
            </button>
          </div>
        </div>
      </form>
    </div>

    <!-- 分配菜单的弹出层 开始 -->
    <div style="display: none;" id="selectRoleMenuDiv">
      <ul id="roleTree" class="dtree" data-id="0"></ul>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
  layui.extend({
    dtree: '${pageContext.request.contextPath}/statics/layui_ext/dtree/dtree'
  }).use(['form', 'table', 'laydate', 'jquery','layer','dtree'], function () {
    var $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            dtree = layui.dtree,
            layer = layui.layer,
            table = layui.table;

    //渲染表格组件
    table.render({
      elem: '#currentTableId',
      url: '${pageContext.request.contextPath}/admin/role/list',
      toolbar: '#toolbarDemo',
      cols: [[
        {type: "checkbox", width: 50},
        {type: 'numbers', width: 100, title: '排序', align: "center"},
        {field: 'id', width: 100, title: '角色编号', align: "center"},
        {field: 'roleName', minWidth: 150, title: '角色名称', align: "center"},
        {field: 'roleDesc', minWidth: 200, title: '角色描述', align: "center"},
        {title: '操作', minWidth: 120, toolbar: '#currentTableBar', align: "center"}
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
        title:"添加角色信息",
        area: ['600px', '280px'],
        content:$("#addOrUpdateWindow"),
        success:function(){
          $("#restBtn").click();
          // 提交请求
          url = "/admin/role/addRole";
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
      }else if (obj.event === 'grantMenu') {
        openGrantMenuWindow(data);
      }
    });

    // 分配菜单弹出窗口
    function openGrantMenuWindow(data){
      mainIndex = layer.open({
        type: 1,
        title:"分配【"+data.roleName+"】菜单信息",
        area: ['300px', '400px'],
        content: $("#selectRoleMenuDiv")
        , btn: ['确认', '取消']
        , yes: function (index, layero) {
          // 获取复选框选中的值
          var params = dtree.getCheckbarNodesParam("roleTree");
          // 判断是否选中
          if(params.length > 0){
            // 定义数组保存选中的菜单值
            var idArr = [];
            // 循环获取选中的值
            for (let i = 0; i < params.length; i++) {
              // nodeId是选中的树节点的值，固定不能更改的
              idArr.push(params[i].nodeId);
            }
            // 将数组装转换成字符串[选中的菜单id]
            var ids = idArr.join(",");
            // 发起ajax请求，
            $.post("/admin/role/saveRoleMenu",{"ids":ids,"roleId":data.id},function (result) {
              layer.msg(result.message);
            },"json");
          }else {
            layer.msg("请选择要分配的菜单");
          }
        }
        , btn2: function (index, layero) {
          //按钮【按钮二】的回调
        },
        success:function(){
          // 初始化树
          dtree.render({
            elem: "#roleTree",
            url: "/admin/role/initMenuTree?roleId="+data.id, // 使用url加载（可与data加载同时存在）
            dataStyle:"layuiStyle", // 使用layui风格样式的数据格式
            dataFormat:"list", // 配置data的风格为list
            response:{message:"msg",statusCode:0}, // 修改response中返回数据的定义
            checkbar: true,
            checkbarType: "all" // 默认就是all，其他的值为： no-all  p-casc   self  only
          });
        }
      });
    }

    // 编辑事件
    function openUpdateDept(data) {
      mainIndex = layer.open({
        type: 1,
        title:"编辑角色信息",
        area: ['600px', '280px'],
        content:$("#addOrUpdateWindow"),
        success:function(){
          // 数据回显[回显表单的值，回显的数据]
          form.val("dataFrm",data);
          // 提交请求
          url ="/admin/role/updtaeRole";
        }
      });
    }

    // 角色提交事件
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
      $.get("/admin/role/checkRoleHasEmployee",{"id":data.id},function(result){
        if(result.exist){
          // 提示用户无法删除
          layer.msg(result.messages);
        }else {
          // 提示用户是否删除该部门
          layer.confirm('真的删除'+data.deptname+'角色吗？',{icon:3,title:"删除确认"}, function (index) {
            // 发送请求删除数据
            $.post("/admin/role/deleteById",{"id":data.id},function(result2){
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

