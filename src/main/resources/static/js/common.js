/**
 * 
 */

function mScroll(id) {
	$("html,body").stop(true);
	var content = $("#" + id). height();
	// alert(content);
	$("html,body").animate({
		scrollTop : content -80
	}, 1000);
}

function alertNextFocus(str,focusId)
 {
	var shield = document.getElementById("shield");
	var alertFram = document.getElementById("alertFram");
	if (shield) {
		shield.parentNode.removeChild(shield);
		alertFram.parentNode.removeChild(alertFram);

	}
	shield = document.createElement("DIV");
	shield.id = "shield";
	shield.style.position = "absolute";
	shield.style.left = "0px";
	shield.style.top = "0px";
	shield.style.width = "100%";
	// shield.style.width = "450px";
	shield.style.height = document.body.scrollHeight + "px";
	// shield.style.height = "150px";
	// 弹出对话框时的背景颜色
	shield.style.background = "#ffffff";
	shield.style.textAlign = "center";
	shield.style.zIndex = "25";
	shield.style.opacity = "0.5";
	// 背景透明 IE有效
	shield.style.filter = "alpha(opacity=0.5)";
	alertFram = document.createElement("DIV");
	alertFram.id = "alertFram";
	alertFram.style.position = "absolute";
	alertFram.style.left = "50%";
	alertFram.style.top = "50%";
	alertFram.style.marginLeft = "-150px";
	alertFram.style.marginTop = "-75px";
	alertFram.style.width = "300px";

	// 此处高度可以判断提示字符长度，动态设定
	alertFram.style.height = "150px";

	alertFram.style.background = "#ffffff";
	alertFram.style.textAlign = "center";
	alertFram.style.lineHeight = "150px";
	alertFram.style.zIndex = "300";
	strHtml = "<ul style=\"list-style:none;margin:0px;padding:0px;width:100%\">\n";
	strHtml += " <li style=\"background:#9BCD9B;text-align:left;padding-left:20px;font-size:14px;font-weight:bold;height:25px;line-height:25px;border:1px solid #CCCCCC;\">提示</li>\n";
	strHtml += " <li style=\"background:#ffffff;text-align:center;font-size:16px;height:120px;line-height:120px;border-left:1px solid #CCCCCC;border-right:1px solid #CCCCCC;\">"
			+ "<div id =\"contentAlert\">" + str + "</div>" + "</li>\n";
	// strHtml += " <li
	// style=\"background:#CCCCCC;text-align:center;font-weight:bold;height:25px;line-height:25px;
	// border:1px solid #ffffff;\"><input type=\"button\" value=\"确 定\"
	// onclick=\"doOk()\" /></li>\n";
	strHtml += "<li style=\"background:#ffffff;text-align:center;font-weight:bold;height:50px;line-height:25px; border-left:1px solid #CCCCCC;border-right:1px solid #CCCCCC;border-bottom:1px solid #CCCCCC;\"><button id=\"btnOK\" type=\"button\" class=\"btn btn-info\" onclick=\"doOk()\">确定</button></li>";
	strHtml += "</ul>\n";
	alertFram.innerHTML = strHtml;
	document.body.appendChild(alertFram);
	document.body.appendChild(shield);
	// var ad = setInterval("doAlpha()",5);
	this.doOk = function() {
		alertFram.style.display = "none";
		shield.style.display = "none";
		if (focusId != "") {
			document.getElementById(focusId).focus();
		}
	};
	// alertFram.focus();
	document.getElementById("btnOK").focus();
	document.body.onselectstart = function() {
		return false;
	};

} 

function moduleList() {
	window.location = SettinClass.urlERPWeb + "/module/summary/index.html";
}

window.alert = function(str)
{
	alertNextFocus(str,"");
}