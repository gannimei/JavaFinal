	var base = document.getElementById("base").href;
	var $ = function(id){
		return document.getElementById(id);
	}
	
	$('plusNum').onclick = function(e){
		e = window.event || e;
		o = e.srcElement || e.target;
		var num = $('allNum').textContent;
		if(num > 0){
			num --;
			$('allNum').innerHTML = num;
		}else{
			alert("您没有购买任何商品");
		}
	};

	$('addNum').onclick = function(e){
		e = window.event || e;
		o = e.srcElement || e.target;
		var num = $('allNum').textContent;
		num ++;
		$('allNum').innerHTML = num;
	};
	
	var loading = new Loading();
	var layer = new Layer();

	
	$('add').onclick = function(e){
		var ele = e.target;
		var id = ele && ele.dataset.id;
		var num = $('allNum').innerHTML;
		e == window.event || e;
		layer.reset({
			content:'确认加入购物车吗？',
			onconfirm:function(){
				layer.hide();
				loading.show();
				ajax({
					data:{number:num,contentId:id},
					url:base+'/api/addShopping',
					success:function(result){
						loading.result('添加购物车成功');
					},
					error:function(message){
						loading.result(message||'添加失败');
					}
				});
			}.bind(this)
		}).show();
		return;
	};




