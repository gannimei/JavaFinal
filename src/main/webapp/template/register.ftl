<!DOCTYPE html>
<html>
<#include "/include/head.ftl">
<body>
<#include "/include/support.ftl">
<form class="m-form m-form-ht n-login" action="${base}/register" method="post" id="register">
    <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt">
            <input class="u-ipt" id="userName" name="userName" value="${(registerModel.userName)!}" autofocus/>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
            <input class="u-ipt" id="password" type="password" name="password" value="${(registerModel.password)!}" />
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">确认密码：</label>
        <div class="fmipt">
            <input class="u-ipt" id="confirmPassword" type="password" name="confirmPassword" value="${(registerModel.password)!}" />
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block">注册</button>
        </div>
    </div>
</form>
<#include "/include/footer.ftl">
<script type="text/javascript" src="${base}/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${base}/js/jquery.validate.js"></script>
<@valid className="com.netease.JavaFinal.web.viewmodel.RegisterEditModel" formName="register"> </@valid>
</body>
</html>