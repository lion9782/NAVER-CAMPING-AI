package com.ncloud.ai;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ncloud.common.JsonHndr;

import com.ncloud.domain.MBVO;

import com.ncloud.service.MBService;

@Controller
@RequestMapping("/Member")
public class MBController {

    @Inject
    MBService service;

    @PostMapping(value = "/signup")
    public void insertMB(@ModelAttribute("MBVO") MBVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception {
    
        String user_name = request.getParameter("name");
        String user_email = request.getParameter("email");
        String user_pwd = request.getParameter("password");
        String user_id = request.getParameter("user_id");
		
		/*
		 * String[] mobStr = user_email.split("@"); String user_id = mobStr[0];
		 */
		 
		/* System.out.println("유저아이디좀보자" + user_id +"띄어쓰기"+ user_name); */
		 
		 vo.setUser_id(user_id);
		 vo.setName(user_name);
		 vo.setEmail(user_email);
		 vo.setPassword(user_pwd);
		 
		 /*
         
String create_at = request.getParameter("currentTime"); String update_at =
request.getParameter("currentTime");/


 
    /
vo.setCreate_at(create_at); vo.setUpdate_at(update_at);*/


  int result = service.registerUser(vo);

	JSONObject json = new JSONObject();
	if(result == 1) {
		json.put("user", result);		
		json.put("sucess", "sucess");		
	}else {
		json.put("fail", "fail");
	}


	JsonHndr.print(json, response);
    }
}
