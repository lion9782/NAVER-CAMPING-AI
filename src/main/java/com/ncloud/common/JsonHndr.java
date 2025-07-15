package com.ncloud.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class JsonHndr {

	public static void print(JSONObject json, HttpServletResponse response) throws IOException {

		response.setContentType("Application/json");
		response.setCharacterEncoding("UTF-8");    
		//response.setContentType("text/html");
		PrintWriter printwriter = response.getWriter();
		printwriter.println(json);
		
		printwriter.flush();
		printwriter.close();
		
	}
}
