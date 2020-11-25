layui.use(['form', 'layedit', 'laydate','table'], function(){
    var form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate
        ,table=layui.table
        ,$ = layui.jquery;


    form.on('select(test)', function(data){
        var gradeId=data.value;
        $.getJSON("/sys/selectUsersByGradeId","gradeId="+gradeId,function (data) {
            if(data!=null){
                $("#studentId").empty();
                for(var i=0;i<data.length;i++){
                    $("#studentId").append(" <option value='"+data[i].id+"'>"+data[i].name+"</option>")
                }
                form.render('select');
            }
        })
    });

    //学生积分加减按钮
    $("#submit").click(function () {
        var causeid=$("#cause_id").val();
        var point=$("#point").val();
        var is_add=$("#is_add").val();
        var studentId=$("#studentId").val();
        var date=$("#date").val();
        $.getJSON("/sys/addUserIngegral","studentId="+studentId+"&causeid="+causeid+"&point="+point+"&isadd="+is_add+"&date="+date,function (data) {
            if(data.toString()=="true"){   //提交成功
                layer.msg("提交成功！！");
                $("#content").load("/jump/user", function () {       //加载到班级信息页面
                    layui.use('element', function () {
                        var element = layui.element;
                    });
                });
            }else{
                layer.msg("提交失败！！")
            }
        })
    });

    //自定义原因
    $("#addCause").click(function () {
        $("#content").load("/jump/addCause", function () {
            layui.use('element', function () {
                var element = layui.element;
            });
        });
    });

    //日期
    laydate.render({
        elem: '#date'
    });


    // $("#addGrade").click(function () {
    //     layer.open({
    //         type: 2,
    //         content: 'http://sentsin.com' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
    //     });
    // });
});
