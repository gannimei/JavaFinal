package com.netease.JavaFinal.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class HtmlHelper implements TemplateDirectiveModel {

	@Override
	public void execute(Environment arg0, Map arg1, TemplateModel[] arg2, TemplateDirectiveBody arg3)
			throws TemplateException, IOException {
		// TODO Auto-generated method stub
		Writer writer = arg0.getOut();
		String value = arg0.getDataModel().get("userName").toString();
		writer.write("<h1>" + value + "</h1>");
		arg3.render(writer);
	}

}
