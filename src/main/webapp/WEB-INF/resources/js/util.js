//工具
var util =  {
    	//F：弹出modal 且使其居中
        openModal: function(modal) {
            modal.on('show.bs.modal', // 测试 bootstrap 居中
            function() {
                var $this = $(this);
                var $modal_dialog = $this.find('.modal-dialog');
                $this.css('display', 'block');// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
                $modal_dialog.css({
                    'margin-top': Math.max(0, ($(window).height() - $modal_dialog.height()) / 2)
                });
            });
            modal.modal({
                show: true,// 显示弹出层
                backdrop: 'static', // 禁止位置关闭
                keyboard: false // 关闭键盘事件
            });
        },
        // F：弹出消息框
        openMessenger: function(message, type) {
            $.globalMessenger().post({
                message: message,// 提示信息
                type: type,// 消息类型。error、info、success
                hideAfter: 3,// 多长时间消失
            });
        },
    };

//日期格式化
var dateFormat = function(date,format){
	 var o = {
	 "M+" : date.getMonth()+1, //month
	 "d+" : date.getDate(),    //day
	 "h+" : date.getHours(),   //hour
	 "m+" : date.getMinutes(), //minute
	 "s+" : date.getSeconds(), //second
	 "q+" : Math.floor((date.getMonth()+3)/3),  //quarter
	 "S" : date.getMilliseconds() //millisecond
	 }
	 if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
	 (date.getFullYear()+"").substr(4 - RegExp.$1.length));
	 for(var k in o)if(new RegExp("("+ k +")").test(format))
	 format = format.replace(RegExp.$1,
	 RegExp.$1.length==1 ? o[k] :
	 ("00"+ o[k]).substr((""+ o[k]).length));
	 return format;
};