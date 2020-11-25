
loadData(null);
var userName;
var testId;
var isAdmin;
var arr=[];
function clickUserName(){
    userName=$(".userName").val();
    loadData();
}
layui.use(['form'], function () {
    var form = layui.form;
    form.on('select(testId)', function(data){
        if(data.value>0){
            testId=data.value;
            loadData();
        } if(data.value==0) {
            testId=null;
            loadData();
        }
    });
});

layui.use('upload', function(){
    var $ = layui.jquery,upload = layui.upload;
    upload.render({
        elem: '#test3'
        ,url: '/sys/import'
        ,method: 'post'
        ,exts:'xls|xlsx'
        ,accept: 'file' //普通文件
        ,done: function(res){
            alert("添加成功！");
        }
        ,error: function(){
            alert(1)
        }
    })
})
function loadData(){
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 'full'
            ,url: '/sys/list' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'id', title: 'ID',  sort: true, fixed: 'left'}
                ,{field: 'name', title: '用户名'}
                ,{field: 'identity', title: '身份证',  sort: true}
                ,{field: 'address', title: '地址'}
                ,{field: 'point', title: '积分'}
                ,{field: 'gradeId', title: '年级编号',  sort: true}
            ]]
            ,where: {
                // //.渲染请求参数(查询条件等)
                gradeId: testId,
                name:userName,
                isAdmin:isAdmin
            }
        });
    });
}