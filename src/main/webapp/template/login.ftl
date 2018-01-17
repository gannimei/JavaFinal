<!DOCTYPE html>
<html>
<#include "/include/head.ftl">
<body>
<#include "/include/support.ftl">
<#import "spring.ftl" as spring />
<form class="m-form m-form-ht n-login" id="loginForm" onsubmit="return false;" autocomplete="off">
    <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt">
            <input class="u-ipt" name="userName" value="${(loginModel.userName)!}" autofocus/>
            <#if loginModel??>  
			    <@spring.bind "loginModel.userName" />  
			    <@spring.showErrors "<br>"/>  
			</#if>  
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" name="password" value="${(loginModel.password)!}" />
            <#if loginModel??>  
			    <@spring.bind "loginModel.password" />  
			    <@spring.showErrors "<br>"/>  
			</#if>  
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block">登 录</button>
        </div>
    </div>
</form>
<#include "/include/footer.ftl">
<script type="text/javascript" src="${base}/js/md5.js"></script>
<script type="text/javascript" src="${base}/js/global.js"></script>
<script type="text/javascript" src="${base}/js/pageLogin.js"></script>
</body>
</html>