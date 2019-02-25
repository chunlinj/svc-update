var contextPath = window.location.origin;
var pathName=window.document.location.pathname;
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);


//生产服务器
var SVC_UPDATER_IP=contextPath + projectName;
//本机开发环境
//var SVC_UPDATER_IP=contextPath;






/**
 * 重新登陆
 */
function common_relogin(){
	sessionStorage.setItem("access_token","");
	location.href = WEB_SERVER_IP + "/app/html/login.html";
}

/**
 * 日期转字符串
 */
function common_datetime_to_str(date){
	date = new Date(date);
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();
	
	date = year +"-"+ (month<10?"0"+month:month) +"-"+ (day<10?"0"+day:day);
	var time = (hour<10?"0"+hour:hour) +":"+ (minute<10?"0"+minute:minute) +":"+ (second<10?"0"+second:second);
	return date + " " + time;
}

/**
 * 日期转字符串
 */
function common_date_to_str(date,separator){
	if(separator == null){
		separator = "-";
	}
	date = new Date(date);
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	
	date = year +separator+ (month<10?"0"+month:month) +separator+ (day<10?"0"+day:day);
	return date;
}

/**
 * 分钟转字符串
 * @param minute
 * @returns
 */
function common_minute_to_str(minute){
	if(minute < 60){
		return minute + "分钟";
	}else if(minute >=60 && minute < 1440){
		var hours = parseInt(minute / 60);
		return hours + "小时" + (minute % 60) + "分钟";
	}else if(minute >= 1440){
		var day = parseInt(minute / 1440);
		var hours = parseInt((minute % 1440) / 60);
		minute = (minute % 1440) % 60;
		return day + "天" + hours + "小时" + minute + "分钟";
	}
	return "";
}

$.ajaxSetup({  
    error: function(jqXHR, textStatus, errorThrown){  
    	if(jqXHR.responseJSON != null && jqXHR.responseJSON.status == 0){
    		alert(jqXHR.responseJSON.message);
    	}
    }
});  

//设置表单数据
$.fn.setForm = function(jsonValue){
	var obj = this;
	$.each(jsonValue,function(name,ival){
		if(ival == null){
			return;
		}
		//对象型特殊赋值
	  	if(typeof(ival) == "object"){
	  		$.each(ival,function(subName,subIval){
	  			subName = name + "." + subName;
	  			setFormComponent(obj,subName,subIval);
	  		});
	  	}else{
	  		setFormComponent(obj,name,ival);
	  	}
	});
	function setFormComponent(obj,name,ival){
		var $oinput = obj.find("input[name='"+name+"']");
		if($oinput.attr("type")=="checkbox"){
	  		if(ival !== null){
		        var checkboxObj = $("[name='"+name+"']");
		        var checkArray = ival.split(";");
		        for(var i=0;i<checkboxObj.length;i++){
	      			for(var j=0;j<checkArray.length;j++){
	        			if(checkboxObj[i].value == checkArray[j])
	          				checkboxObj[i].click();
	      			}
	    		}
	  		}
		}else if($oinput.attr("type")=="radio"){
	  		$oinput.each(function(){
	        	var radioObj = $("[name='"+name+"']");
	        	for(var i=0;i<radioObj.length;i++){
	          		if(radioObj[i].value == ival)
	            		radioObj[i].click();
	        	}
	      	});
		}else if($oinput.attr("type")=="textarea"){
	  		obj.find("[name='"+name+"']").html(ival);
		}else{
	  		obj.find("[name='"+name+"']").val(ival);
		}
	}
}

function common_loadRegionData($container,parentId){
	$.ajax({
        url: SYS_SERVER_IP + "/sys/region/list",
        type: 'post',
        data: {"parentId":parentId},
        success: function (data) {
        	if(data && data.status == 1){
        		var optionsHTML = "<option value=''>请选择</option>";
    			for(var i=0;i<data.data.length;i++){
    				optionsHTML = optionsHTML + "<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>";
    			}
    			$container.html(optionsHTML);
    			$container.selectpicker('refresh');
        	}
        }
  	});
}

function common_loadOrganizationData($container){
	var url = SYS_SERVER_IP + "/sys/organization/list";
    $.ajax({
        type: "post",
        url: url,
        success:function(data){
            if(data.status == 1 && data.data != null){ 
            	var optionsHTML = "<option value=''>请选择</option>";
                for(var i=0;i<data.data.length;i++){
                	optionsHTML = optionsHTML + "<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>";
                }
                $container.html(optionsHTML);
    			$container.selectpicker('refresh');
            }
        }
    })
}

function common_initButton(){
	var menuId = common_getUrlParameter("menuId");
	var token  = sessionStorage.getItem("access_token")
	$.ajax({
		type:"get",
		url:SYS_SERVER_IP + "/sys/menu/children/permit?token="+token+"&id="+menuId.menuId,
		success:function(res){
			var data  = res.data;
			for(var i=0;i<data.length;i++){
				var btn=document.createElement("button");
				btn.classList.add('btn');
				btn.classList.add('btn-default');
				btn.setAttribute("type","button");
				btn.setAttribute("id",data[i].link);
				btn.innerText=data[i].name;
				var element=document.getElementById("btn-box");
				element.appendChild(btn)
			}
		}
	});
}

function common_getUrlParameter() {
	var url = location.search;
	var parameter = new Object(); 
	if(url.indexOf("?") != -1) { 
		var strs = url.substr(1).split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
			parameter[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
		} 
	} 
	return parameter; 
}

function initEditForm(){
    var list = $("#table").bootstrapTable("getSelections");
    if(list.length != 1){
        layer.msg("请选择一条需要编辑的数据！");
        return;
    }

    openEditForm(list[0].id);
}