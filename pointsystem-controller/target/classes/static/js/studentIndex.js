$(function() {
    // 全局过滤器:过滤时间
    Vue.filter("dateFormat", function(dateStr, type) {
        let now = new Date(dateStr);
        let year = now.getFullYear();
        let month = (now.getMonth() + 1).toString().padStart(2, '0');
        let day = now.getDate().toString().padStart(2, '0');
        let hour = now.getHours().toString().padStart(2, '0');
        let minute = now.getMinutes().toString().padStart(2, '0');
        let seconds = now.getSeconds().toString().padStart(2, '0');

        if(type == 1){
            return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + seconds
        }
        else{
            return year + "-" + month + "-" + day
        }
    })

    var vm = new Vue({
        el: '#app',
        data: {
            gradePoint: 300, // 当前班级积分
            showOut: false, // 是否显示注销选项
            gradeList: [], // 班级top3集合
            integralRecordList: [], // 个人加扣分详情集合
            recordExShow: -1, // 设置加扣分筛选编号 -1为全部 0为加分 1为减分
            sumPoint: 0, // 筛选后的积分合计,加分合计,减分合计...
            causes: [], // 扣加分详情信息集合
            prizeList: [], // 奖品集合
            usablePoint: 0, // 可用积分
            popMessage: '', // 弹出层显示内容
            prizeRedeemedList: [], // 已兑换奖品集合
            htmlFontSize: 0, // html标签字体大小
            integralRecordShow: false, // 积分详情模块是否显示
            prizeShow: false, // 礼物兑换模块是否显示
            prizeRedeemedShow: false, // 已兑换奖品模块是否显示
            integralRecordMsg: '',
            prizeMsg: '',
            prizeRedeemedMsg: ''
        },
        methods: {
            /**
             * 初始化当前班级积分
             */
            getGradePoint() {
                this.$http.get('///student/grade/getPointByGradeId').then(result => {
                    var result = result.body
                    if (result.Code == 200) {
                    this.gradePoint = result.Data
                } else if (result.Code == 500) {
                    alert("初始化班级积分出现异常，请联系管理员")
                } else if (result.Code == 600) {
                    window.location = "//student/user/toErrorLogin"
                }
            })
            },
            /**
             * 初始化班级排行
             */
            getGradeList() {
                this.$http.get('/student/grade/getGradeListOrderByPoint').then(result => {
                    var result = result.body
                    if (result.Code == 200) {
                    this.gradeList = result.Data
                } else if (result.Code == 500) {
                    alert("初始化班级top3出现异常，请刷新页面")
                } else if (result.Code == 600) {
                    window.location = "/student/user/toErrorLogin"
                }
            })
            },
            /**
             * 获取扣加分详情集合
             */
            getIntegralRecordList() {
                this.recordExShow = -1; // 每次点击重新查询全部
                this.$http.get('/student/point/getIntegralRecordListByCurrentUser').then(result => {
                    var result = result.body
                    if (result.Code == 200) {
                    this.integralRecordList = result.Data
                    this.getListAndSumPoint(this.recordExShow)
                    this.moduleHidden()
                    this.integralRecordShow = true
                    if(this.integralRecordList == null || this.integralRecordList == ""){
                        this.integralRecordMsg = "暂无加扣分详情"
                    }
                } else if (result.Code == 500) {
                    alert("获取扣加分详情出现异常，请刷新页面")
                } else if (result.Code == 600) {
                    window.location = "/student/user/toErrorLogin"
                }
            })
            },
            /**
             * 获取奖罚原由
             */
            getCause() {
                this.$http.get('/student/cause/getCauses').then(result => {
                    var result = result.body
                    if (result.Code == 200) {
                    this.causes = result.Data
                } else if (result.Code == 500) {
                    alert("获取奖罚原由出现异常，请刷新页面")
                } else if (result.Code == 600) {
                    window.location = "/student/user/toErrorLogin"
                }
            })
            },
            /**
             * 点击筛选后执行的方法
             */
            getListAndSumPoint(data) {
                this.sumPoint = 0 // 清空总积分
                if (data == 1) { // 减分
                    this.recordExShow = 1
                    this.integralRecordList.forEach(item => {
                        if (item.isAdd == 0) {
                        this.sumPoint += item.point
                    }
                })
                } else if (data == 0) { // 加分
                    this.recordExShow = 0
                    this.integralRecordList.forEach(item => {
                        if (item.isAdd == 1) {
                        this.sumPoint += item.point
                    }
                })
                } else if (data == -1) { // 全部
                    this.recordExShow = -1
                    this.integralRecordList.forEach(item => {
                        this.sumPoint += item.point
                    })
                }
            },
            /**
             * 点击查看奖品按钮执行
             */
            getPrizeListAndUsablePoint() {
                var pointValue = this.$refs.userPoint.value.replace(/,/, '')
                let point = parseFloat(pointValue)
                // 计算可兑换积分
                if (point > 100) {
                    this.usablePoint = point - 100
                } else {
                    this.usablePoint = 0
                }
                // 获取奖品集合
                this.$http.get('/student/prize/getPrizeList').then(result => {
                    var result = result.body
                    if (result.Code == 200) {
                    this.prizeList = result.Data
                    this.moduleHidden()
                    this.prizeShow = true
                    if(this.prizeList == "" || this.prizeList == null){
                        this.prizeMsg = "暂无奖品信息"
                    }
                } else if (result.Code == 500) {
                    alert("初始化奖品信息出现异常，请刷新页面")
                } else if (result.Code == 600) {
                    window.location = "/student/user/toErrorLogin"
                }
            })
            },
            /**
             * 点击兑换奖品按钮执行
             * id: 奖品编号
             */
            exchange(id, point) {
                // 判断可兑换积分是否足够
                if (this.usablePoint >= point) {
                    this.$http.post('/student/prizeExchangeRecord/addPrizeExchangeRecord', {
                        prizeId: id
                    }, {
                        emulateJSON: true
                    }).then(result => {
                        var result = result.body
                        if (result.Code == 200) { // 兑换成功
                        this.popMessage = '奖品兑换成功，稍后将刷新页面，可在个人主页查看已兑换奖品'
                        $(".pop-up").slideDown(300);
                        setTimeout(function() {
                            //刷新页面
                            location.reload();
                        }, 2000)
                    } else if (result.Code == 500) { // 兑换奖品时出现异常
                        alert("奖品兑换出现异常，请刷新页面")
                    } else if (result.Code == 505) { // 奖品已经被兑换空了
                        alert("奖品已经被兑换完了")
                        // 刷新奖品
                        getPrizeListAndUsablePoint()
                    } else if (result.Code == 600) {
                        window.location = "/student/user/toErrorLogin"
                    }
                })
                } else {
                    alert('可用积分不足!')
                }
            },
            /**
             * 点击已兑换奖品获取已兑换奖品列表
             */
            getPrizeRedeemedList() {
                this.$http.get('/student/prizeExchangeRecord/getPrizeExchangeRecordByCurrentUser').then(result => {
                    var result = result.body
                    if (result.Code == 200) {
                    this.prizeRedeemedList = result.Data
                    this.moduleHidden()
                    this.prizeRedeemedShow = true
                    if(this.prizeRedeemedList == "" || this.prizeRedeemedList == null){
                        this.prizeRedeemedMsg = "暂无已兑换奖品信息"
                    }
                } else if (result.Code == 500) {
                    alert('获取个人已兑换奖品信息异常，请刷新页面')
                } else if (result.Code == 600) {
                    window.location = "/student/user/toErrorLogin"
                }
            })
            },
            /**
             * 弹出层弹出方法
             */
            popUp(message) {
                this.popMessage = message
                $(".pop-up").slideDown(300);
            },
            /**
             * 注销按钮事件
             */
            cancellationClick() {
                this.$http.get('/student/user/sessionCancellation').then(result => {
                    console.log(result)
                    var result = result.body
                    if (result.toString() == 'true') {
                    // 注销成功 跳转至登录页面
                    window.location.href = '/'
                } else if (result.Code == 600) {
                    window.location = "/student/user/toErrorLogin"
                } else {
                    alert('注销失败，请手动关闭浏览器')
                }
            })
            },
            /**
             * 隐藏所有学生模块
             */
            moduleHidden(){
                this.integralRecordShow = false
                this.prizeShow = false
                this.prizeRedeemedShow = false
            }
        },
        // 定义自定义指令
        directives: {
            /**
             * 根据详情id获取详情信息并注入到元素中
             * 用于奖罚详情列表
             */
            cause: {
                inserted: function(el, causeId){
                    vm.causes.forEach(cause => {
                        if (cause.id == causeId.value) {
                        el.innerText = cause.cause
                        return true
                    }
                })
                }
            },
            /**
             * 根据奖品id获取奖品的名字并注入到元素中
             * 用于查看用户已兑换奖品表
             */
            prize: {
                inserted: function(el, prizeId) {
                    console.log(prizeId)
                    vm.prizeList.forEach(prize => {
                        if (prize.id == prizeId.value) {
                        el.innerText = prize.name
                        return true
                    }
                })
                }
            },
            /**
             * 判断奖品兑换按钮是否为禁用
             */
            disabled: {
                inserted: function(el, number) {
                    if (number.value == 0) {
                        el.disabled = true
                    } else {
                        el.disabled = false
                    }
                }
            }
        },
        created() {
            // 调用初始化方法
            this.getGradePoint()
            this.getGradeList()
            this.getCause()
        },
        mounted() {
            this.getPrizeListAndUsablePoint()
        }
    })

    // 自适应字体大小
    function setRem(){
        var clientWidth = $(window).width();
        var rem = clientWidth / 1920 * 18;
        $('html').css('font-size', parseInt(rem) + 'px');
    }
    setRem()
    var setRemTimer;
    $(window).bind('resize', function() {
        clearTimeout(setRemTimer);
        setRemTimer = setTimeout(function() {
            setRem();
        }, 100);
    });
})
