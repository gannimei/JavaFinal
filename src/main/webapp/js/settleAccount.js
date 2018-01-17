(function(w,d,u){
	var base = document.getElementById("base").href;
	var $ = function(id){
		return document.getElementById(id);
	}

	var loading = new Loading();
	var layer = new Layer();
	$('Account').onclick = function(e){
		var ele = e.target;
			layer.reset({
				content:'确认购买吗？',
				onconfirm:function(){
					layer.hide();
					loading.show();
					var xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function(){
						 if(xhr.readyState == 4){
				                var status = xhr.status;
				                if(status >= 200 && status < 300 || status == 304){
				                	var json = JSON.parse(xhr.responseText);
				                	if(json && json.code == 200){
				                		loading.result('购买成功',function(){location.href = base+'/account';});
				                	}else{
				                		loading.result(json.message||'购买失败');
				                	}
				                }else{
				                	loading.result(message||'购买失败');
				                }
				            }
					};
					 xhr.open('post',base+'/api/buy');
					 xhr.setRequestHeader('Content-Type','application/json');
					 xhr.send();
				}.bind(this)
			}).show();
			return;
	};
	$('back').onclick = function(){
		window.history.back();
	}
})(window,document);