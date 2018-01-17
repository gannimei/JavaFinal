(function(w,d,u){
	var settleAccount = util.get('settleAccount');
	if(!settleAccount){
		return;
	}
	var name = 'products';
	var products = util.getCookie(name);
	var $ = function(id){
		return document.getElementById(id);
	}
	
	var str = "<tr>" + 
			  "<th>" + '内容名称'  + "</th>"+ 
			  "<th>" + '数量' + "</th>" +
			  "<th>" + '价格' + "</th>" +
			  "</tr>";

	for(var i = 0; i < products.length; i++){
		str = str + 
		"<tr>" + 
		"<td>" + products[i].title  + "</td>"+
		"<td>" + 
		"<span class=\"lessNum\">"+ "-" + "</span>" +
		"<span class=\"totalNum\" id=\"allNum\">" + products[i].num + "</span>" +
		"<span id=\"thisId\">" + products[i].id + "</span>" +
		"<span class=\"moreNum\">"+ "+" + "</span>" + "</td>" +
		"<td>" + products[i].price + "</td>" +
		"</tr>";
	}

	var loading = new Loading();
	var layer = new Layer();
	$('Account').onclick = function(e){
		var newProducts = products.map(function(arr){
			return {'id':arr.id,'number':arr.num};
		});
		console.log(newProducts);
		var ele = e.target;
			layer.reset({
				content:'确认购买吗？',
				onconfirm:function(){
					layer.hide();
					loading.show();
					
					var xhr = new XMLHttpRequest();
					var data = JSON.stringify(newProducts);
					xhr.onreadystatechange = function(){
						 if(xhr.readyState == 4){
				                var status = xhr.status;
				                if(status >= 200 && status < 300 || status == 304){
				                	var json = JSON.parse(xhr.responseText);
				                	if(json && json.code == 200){
				                		loading.result('购买成功',function(){location.href = './account.html';});
				                	}else{
				                		loading.result(json.message||'购买失败');
				                	}
				                }else{
				                	loading.result(message||'购买失败');
				                }
				            }
					};
					 xhr.open('post','api/buy');
					 xhr.setRequestHeader('Content-Type','application/json');
					 xhr.send(data);
				}.bind(this)
			}).show();
			return;
	};
	$('back').onclick = function(){
		window.history.back();
	}
})(window,document);