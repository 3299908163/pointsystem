loadData(null);
var arr=[];
function clickUserName(){
    arr[1]=$(".userName").val();
    loadData();
}

layui.use(['form'], function () {
    var form = layui.form;

    form.on('select(testId)', function(data){
        if(data.value>0){
            arr[0]=data.value;
            loadData();
        } if(data.value==0) {
            arr[0]=null;
            loadData();
        }
    });

    form.on('select(userId)', function(data){
        if(data.value>0){
            arr[2]=data.value;
            loadData();
        } if(data.value==0) {
            arr[2]=null;
            loadData();
        }
    });
});
function loadData(){
    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 'full'
            ,url: '/point/getIntegralRecordPage' //数据接口
            ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                ,limit:10 //一页显示多少条
                ,limits:[5,10,15]//每页条数的选择项
                ,groups: 2 //只显示 2 个连续页码
                ,first: "首页" //不显示首页
                ,last: "尾页" //不显示尾页

            }
            ,cols: [[ //表头
                {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'name', title: '用户名', width:120,templet: '<div>{{d.user.name}}</div>'}
                ,{field: 'gradeName', title: '班级名', width:120,templet: '<div>{{d.grade.name}}</div>'}
                ,{field: 'point', title: '加减的分数', width:80, sort: true}
                ,{field: 'isAdd', title: '是加是减(1:加 0:减)', width:80}
                ,{field: 'causeId', title: '加减记录的原因', width: 177}
                ,{field: 'recorderId', title: '记录者姓名', width: 180, sort: true}
                ,{field: 'creationTime', title: '时间', width: 180, sort: true}
            ]]
            ,where: {
                // //.渲染请求参数(查询条件等)
                gradeId: arr[0].value,
                userName: arr[1],
                userId:arr[2]
            }
        });
    });
}