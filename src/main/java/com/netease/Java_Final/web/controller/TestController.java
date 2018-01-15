package com.netease.Java_Final.web.controller;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public void home(Writer writer) {
		try {
			writer.write("hello world!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
