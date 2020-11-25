layui.use(['form','table','layer'],function () {
    var table = layui.table
        , form = layui.form
        , layer = layui.layer
        , $ = layui.jquery;
    form.on('select(grade)', function (data) {
        alert(111);
    });
    var name;
    table.render({
        elem: '#test'
        , height: 'full'
        , url: '/user/studentInfo' //数据接口
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            title: '提示'
            , layEvent: 'LAYTABLE_TIPS'
            , icon: 'layui-icon-tips'
        }]
        , title: '用户数据表'
        , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            , limit: 10 //一页显示多少条
            , limits: [5, 10, 15]//每页条数的选择项
            , groups: 2 //只显示 2 个连续页码
            , first: "首页" //不显示首页
            , last: "尾页" //不显示尾页

        }
        , cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', sort: true, fixed: 'left'}
            , {field: 'name', title: '姓名'}
            , {field: 'identity', title: '身份证号', sort: true}
            , {field: 'address', title: '地址', sort: true}
            , {field: 'point', title: '积分', sort: true}
            , {field: 'gradeId', title: '班级id', sort: true}
        ]]
        ,id:'typeReload'
        ,name:name
    });
    //  $.active = {
    //     reload: function () {
    //         var name = $('#name');
    //         //执行重载
    //         table.reload('typeReload', {
    //             page: {
    //                 curr: 1 //重新从第 1 页开始
    //             }
    //             , where: {
    //                 name: name.val()//逗号隔开
    //             }
    //         }, 'data');
    //     }
    // };

    $('#search').on('click', function () {
        name=$("#name").val();
        table.reload('typeReload', {
            where: { //设定异步数据接口的额外参数，任意设
                name: name
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });

//头工具栏事件
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'updateGrade':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
            case 'addGrade':
                var data = checkStatus.data;
                // layer.msg("");
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选' : '未全选');
                break;

            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        }
        ;
    });

    //监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        //console.log(obj)
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            layer.prompt({
                formType: 2
                , value: data.email
            }, function (value, index) {
                obj.update({
                    email: value
                });
                layer.close(index);
            });
        }
    });


    // $("#addStudent").click(function () {
    //     var open=layer.open({
    //         type: 1,
    //         // time: 20000,
    //         skin: 'layui-layer-rim', //加上边框
    //         area: ['430px', '360px'], //宽高
    //
    //         content: '<form class="layui-form form" style="margin-left: 100px" id="form">  <div class="layui-form-item">\n' +
    //             '    <div class="layui-input-inline" style="margin: 10px"><label class="layui-form-label">新增学生</label></div>\n' +
    //             '    <div class="layui-input-inline" style="margin: 10px">\n' +
    //             '      <input type="text" name="name" id="names"placeholder="请输入姓名" required lay-verify="required"  class="layui-input">\n' +
    //             '    </div>\n' +
    //             '    <div class="layui-input-inline" style="margin: 10px">\n' +
    //             '      <input type="number" name="identity" id="identity"  placeholder="请输入身份证号" required lay-verify="identity"  class="layui-input">\n' +
    //             '    </div>\n' +
    //             '    <div class="layui-input-inline" style="margin: 10px">\n' +
    //             '      <input type="text" name="address" id="address"  placeholder="请输入地址" required lay-verify="required"  class="layui-input">\n' +
    //             '    </div>\n' +
    //             '    <div class="layui-input-inline" style="margin: 10px">\n' +
    //             '      <input type="text" name="gradeId" id="gradeId"  placeholder="请输入班级号" required lay-verify="required"  class="layui-input">\n' +
    //             '    </div>\n' +
    //             ' <div class="layui-input-inline"><input type="button" value="新增" class="layui-btn layui-btn-primary" style="margin: 50px" id="submit"></div> </div>' +
    //             '</form>'
    //     });
    //     //新增点击事件
    //     $("#submit").click(function () {
    //         var name=$("#names").val();
    //         alert(name);
    //         var identity=$("#identity").val();
    //         var address=$("#address").val();
    //         var gradeId=$("#gradeId").val();
    //         if (name == "" || name == null) {
    //             layer.msg("姓名不能为空！");
    //             return false;
    //         }else if(identity==""||identity==null){
    //             layer.msg("身份证号不能为空！");
    //             return false;
    //         }else if(gradeId==""||gradeId==null){
    //             layer.msg("班级号不能为空！");
    //             return false;
    //         }else if(identity.length<18){
    //             layer.msg("身份证号格式不正确！");
    //             return false;
    //         }else{
    //             layer.close(open);
    //         }
    //
    //         $.getJSON("/user/addStudent", "name=" + name+"&identity="+identity+"&address="+address+"&gradeId="+gradeId, function (data) {
    //             if (data != null) {
    //                 if (data.toString() == "true") {
    //                     layer.alert("添加成功");
    //                 } else {
    //                     layer.alert("添加失败");
    //                 }
    //
    //             }
    //         })
    //     });
    // });

});