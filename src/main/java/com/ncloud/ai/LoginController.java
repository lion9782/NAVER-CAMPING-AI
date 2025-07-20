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
import org.springframework.web.bind.annotation.RequestMethod;

import com.ncloud.common.JsonHndr;

import com.ncloud.domain.LoginVO;
import com.ncloud.service.LoginService;


@Controller
@RequestMapping("/Login")
public class LoginController {
	
	@Inject
	LoginService service;
	
	@PostMapping(value = "/signin" )
	public void insertLogin(@ModelAttribute("LoginVO") LoginVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		String user_email = request.getParameter("email");
		String user_pwd = request.getParameter("password");

		vo.setEmail(user_email);
		vo.setPassword(user_pwd);
		String[] mobStr = user_email.split("@");
		String user = mobStr[0];
		
		int result = service.checkUser(vo);
		
		JSONObject json = new JSONObject();
		if(result == 1) {
			json.put("user", user);		
		}else {
			json.put("fail", "fail");
		}
	
		JsonHndr.print(json, response);
	}
}
