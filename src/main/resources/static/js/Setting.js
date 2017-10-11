//公用配置及方法
var common =
	{
		// urlPublicity: "http://www.jinianen.com/",
		urlPublicity: "http://192.168.31.12:8080/publicity/",
		// url:"http://web1.jinianen.com/"
		url:"http://192.168.31.12:8080/"
	}

var SettinClass =
{
	urlERPWeb: common.url + "", //公用绝对路径地址
    cross_domain: 0, //是否允许跨域,0-不可以
    urlDownload:common.urlPublicity + "download/summary/index.html",
    urlContact:common.urlPublicity + "contact/summary/summary/index.html",
    
  //获取指定URL参数的值
    getUrlParam: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
}




