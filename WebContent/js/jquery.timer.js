/** 
 * jQuery时间插件
 */
(function($){
	/** 添加对象的方法 */
	$.fn.extend({
		timeRun : function(){
			var d = new Date();
			var res = new Array();
			// 年
			res.push(d.getFullYear() + "年");
			// 月
			res.push(this.calc(d.getMonth() + 1) + "月");
			// 日
			res.push(this.calc(d.getDate()) + "日");
			// 星期
			res.push("&nbsp;" + this.weeks[d.getDay()] + "&nbsp;");
			// 小时
			res.push(this.calc(d.getHours()) + ":");
			// 分
			res.push(this.calc(d.getMinutes()) + ":");
			// 秒
			res.push(this.calc(d.getSeconds()));
			
			this.html(res.join(""));
			
			// 把 this声明成局部变量
			var obj = this;
			
			// 开启定时器 1000毫秒调一次
			setTimeout(function(){
				obj.timeRun();
			}, 1000);
			
		},
		weeks : ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
		calc : function(str){
			return str > 9 ? str : "0" + str;
		}
	});
})(jQuery);