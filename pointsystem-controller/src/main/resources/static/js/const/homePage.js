
layui.use(['form','table','layer'],function () {
     var  table = layui.table
        , form = layui.form
        ,layer=layui.layer
        , $ = layui.jquery;


    //点击加载班级奖罚页面
    $("#gradeReward").click(function () {
        $("#content").load("/sys/grade/gradeReward", function (data) {
            if(eval('(' + data + ')').Code.toString()=="600"){
                window.location = "/sys/toErrorLogin"
                return;
            }
            layui.use('element', function () {
                var element = layui.element;
            });
        });
    });
    //点击加载班级信息页面
    $("#gradePoint").click(function () {
        $("#content").load("/sys/grade/gradePoint", function (data) {
            if(eval('(' + data + ')').Code.toString()=="600"){
                window.location = "/sys/toErrorLogin"
                return;
            }
            layui.use('element', function () {
                var element = layui.element;
            });
        });
    });



    //点击加载学生奖罚页面
    $("#studentReward").click(function () {
        $("#content").load("/sys/studentReward", function (data) {
            if(eval('(' + data + ')').Code.toString()=="600"){
                window.location = "/sys/toErrorLogin";
                return;
            }
            layui.use('element', function () {
                var element = layui.element;
            });
        });
    });

    //点击退出
    $("#out").click(function () {
        $.getJSON("/sys/sessionCancellation",function (data) {
            if(data.toString()=="true"){       //退出
                window.location.href="loginTeacher";
            }
        })
    });

    //点击加载积分记录页面
    $("#integralRecord").click(function () {
        $("#content").load("/jump/integralRecord", function (data) {
            if(eval('(' + data + ')').Code.toString()=="600"){
                window.location = "/sys/toErrorLogin";
                return;
            }
            layui.use('element', function () {
                var element = layui.element;
            });
        });
    });


    //点击加载积分记录页面
    $("#pizeInfo").click(function () {
        $("#content").load("/jump/prizeInfo", function (data) {
            if(eval('(' + data + ')').Code.toString()=="600"){
                window.location = "/sys/toErrorLogin"
                return;
            }
            layui.use('element', function () {
                var element = layui.element;
            });
        });
    });

    //点击加载商品兑换记录
    $("#pizeExchang").click(function () {
        $("#content").load("/jump/prizeExchangRecord", function (data) {
            if(eval('(' + data + ')').Code.toString()=="600"){
                window.location = "/sys/toErrorLogin"
                return;
            }
            layui.use('element', function () {
                var element = layui.element;
            });
        });
    });

    //点击加载用户页面
    $("#userInfo").click(function () {
        $("#content").load("/jump/user", function (data) {
            if(eval('(' + data + ')').Code.toString()=="600"){
                window.location = "/sys/toErrorLogin"
                return;
            }
            layui.use('element', function () {
                var element = layui.element;

            });
        });
    });
    //点击加载添加学生页面
    $("#addStudent").click(function () {
        $("#content").load("/jump/addStudent", function (data) {
            if(eval('(' + data + ')').Code.toString()=="600"){
                window.location = "/sys/toErrorLogin"
                return;
            }
            layui.use('element', function () {
                var element = layui.element;
            });
        });
    });

    //点击加载班级积分记录
    $("#gradeIntegralRecord").click(function () {
        $("#content").load("/jump/integralGradeRecord", function (data) {
            if(eval('(' + data + ')').Code.toString()=="600"){
                window.location = "/sys/toErrorLogin"
                return;
            }
            layui.use('element', function () {
                var element = layui.element;
            });
        });
    });
});


