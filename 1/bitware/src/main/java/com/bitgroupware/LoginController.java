package com.bitgroupware;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public void login() {
	}
	@RequestMapping("/loginFail")
	public void loginFail() {
	}
	@RequestMapping("/loginSuccess")
	public void loginSuccess() {
	}
	@RequestMapping("/accessDenied")
	public void accessDenied() {
	}
}
