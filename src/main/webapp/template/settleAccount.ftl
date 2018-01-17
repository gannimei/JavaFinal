<!DOCTYPE html>
<html>
<#include "/include/head.ftl">
<body>
<#include "/include/support.ftl">
<#include "/include/header.ftl">
<div class="g-doc" id="settleAccount">
	<div class="m-tab m-tab-fw m-tab-simple f-cb" >
        <h2>已添加到购物车的内容</h2>
    </div>
	<#if !shoppingList || !shoppingList?has_content>
		<div class="n-result">
	        <h3>暂无内容！</h3>
	    </div>
	<#else>
		<table id="newTable" class="m-table m-table-row n-table g-b3">
			<colgroup><col class="img"/><col/><col class="time"/><col class="price"/></colgroup>
	        <thead>
	            <tr>
	            	<th>内容图片</th>
	            	<th>内容名称</th>
	            	<th>购买时间</th>
	            	<th>数量</th>
	            	<th>购买价格</th>
            	</tr>
	        </thead>
        	<tbody>
        		<#list shoppingList as item>
        			<tr>
        				<td><a href="show?id=${item.contentId}"><img src="${item.image}" alt=""></a></td>
        				<td><h4><a href="show?id=${item.contentId}">${item.title}</a></h4></td>
        				<td><span class="v-time">${item.time?number_to_datetime?string("yyyy-MM-dd HH:mm")}</span></td>
		                <td><span class="v-num">${item.number}</span></td>
		                <td><span class="v-unit">¥</span><span class="value">${item.price}</span></td>
        			</tr>
        		</#list>
        	</tbody>
        	<tfoot>
	            <tr>
	               <td><button class="u-btn u-btn-primary" id="back">退出</button></td>
	               <td><button class="u-btn u-btn-primary" id="Account">购买</button></td>
	            </tr>
	        </tfoot>
	 	</table>
	</#if>
</div>
<#include "/include/footer.ftl">
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/settleAccount.js"></script>
</body>
</html>