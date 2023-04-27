package com.Web.StudentEnquiry.Service;

import com.Web.StudentEnquiry.Binding.LoginForm;
import com.Web.StudentEnquiry.Binding.SignUpForm;
import com.Web.StudentEnquiry.Binding.UnlockForm;

public interface UserService {
	
	public String login(LoginForm form);
	public boolean signUp(SignUpForm form);
	public boolean unlockAccount(UnlockForm form);
	public String forgotPwd(String email);

}
