package com.Web.StudentEnquiry.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Web.StudentEnquiry.Binding.LoginForm;
import com.Web.StudentEnquiry.Binding.SignUpForm;
import com.Web.StudentEnquiry.Binding.UnlockForm;
import com.Web.StudentEnquiry.Entity.UserDtlsEntity;
import com.Web.StudentEnquiry.Service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/login")
	public String loginPage(Model model)
	{
		model.addAttribute("login",new LoginForm());
		return "login";
	}
	@PostMapping("/login")
	public String handleLogin(@ModelAttribute("login")LoginForm log,Model model )
	{
			String data=userService.login(log);
			if(data.contains("Success"))
			{
				return "redirect:/dashboard";
			}
		model.addAttribute("error", data);
		return "login";
		
	}

	@GetMapping("/signup")
	public String signupPage(Model model)
	{
		model.addAttribute("user",new SignUpForm());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String handleSignupPage(@ModelAttribute("user")SignUpForm sign,Model model)
	{
 		boolean status=userService.signUp(sign);
		if(status)
		{
		model.addAttribute("success","Check your email");	
		}
		else
		{
			model.addAttribute("error","Something went wrong try again");
		}
		return "signup";
	}
	
	
	
	@GetMapping("/unlock")
	public String unlock(@RequestParam("email") String email,Model model)
	{
		UnlockForm unlock=new UnlockForm();
		unlock.setEmail(email);
		model.addAttribute("unlock",unlock);
		return "unlock";
	}
	
	@PostMapping("/unlock")
	public String handleUnlock(@ModelAttribute("unlock")UnlockForm unlock,Model model)
	{
		if(!(unlock.getNewPassword().equals(unlock.getConfirmPassword()))||unlock.getNewPassword()==""||unlock.getConfirmPassword()=="")
		{
		model.addAttribute("error","new password and conform password are not matched or empty");
		}
		else
		{
			boolean status=userService.unlockAccount(unlock);
			if(status)
			{
				model.addAttribute("success","your account unlocked successfully");
			}
			else
			{
				model.addAttribute("error","Given Temporary password is incorrect check your email");
			}
		}
		return "/unlock";
		
	}
	
	@GetMapping("/forgot")
	public String forgotPwdPage()
	{
		return "forgotPwd";
	}
	@PostMapping("/forgotPwd")
	public String forgotPwd(@RequestParam("email") String email,Model model)
	{
		String msg=userService.forgotPwd(email);
		model.addAttribute("msg", msg);
		return "forgotPwd";
	}
}
