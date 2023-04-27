package com.Web.StudentEnquiry.ServiceImpl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Web.StudentEnquiry.Binding.LoginForm;
import com.Web.StudentEnquiry.Binding.SignUpForm;
import com.Web.StudentEnquiry.Binding.UnlockForm;
import com.Web.StudentEnquiry.Entity.UserDtlsEntity;
import com.Web.StudentEnquiry.Repository.UserDtlsRepo;
import com.Web.StudentEnquiry.Service.UserService;
import com.Web.StudentEnquiry.Utility.EmailUtils;
import com.Web.StudentEnquiry.Utility.PwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDtlsRepo userDtlsRepo;

	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private HttpSession session;

	@Override
	public String login(LoginForm form) {
		UserDtlsEntity use=userDtlsRepo.findByEmailAndPassword(form.getEmail(),form.getPassword());
		if(use==null)
		{
			return "Invalid Credentials";
		}
		if(use.getAccStatus()=="LOCKED")
		{
			return "Your Account Locked";
		}
		session.setAttribute("userId", use.getUserId());
		return "Success";
	}

	@Override
	public boolean signUp(SignUpForm form) {
		UserDtlsEntity use = userDtlsRepo.findByEmail(form.getEmail());
		if (use != null) {
			return false;
		}

		UserDtlsEntity user = new UserDtlsEntity();
		BeanUtils.copyProperties(form, user);
		user.setAccStatus("LOCKED");
		String pwd = PwdUtils.generatePassword();
		user.setPassword(pwd);
		userDtlsRepo.save(user);

		String to = form.getEmail();
		String subject = "Unlock Your Account";

		StringBuffer body = new StringBuffer();

		body.append("<h1> Use below temporary password to unlock your account</h1>");
		body.append("Temporary Password:" + pwd);
		body.append("<br/>");
		body.append("<a href=\"http://localhost:8081/unlock?email=" + to + "\">Click hear to unlock</a>");

		emailUtils.sendEmail(subject, body.toString(), to);
		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {
		
		UserDtlsEntity user=userDtlsRepo.findByEmail(form.getEmail());
		if(form.getTemPassword().equals(user.getPassword()))
		{
			user.setPassword(form.getNewPassword());
			user.setAccStatus("UNLOCKED");
			userDtlsRepo.save(user);
			return true;
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public String forgotPwd(String email) {
		UserDtlsEntity user=userDtlsRepo.findByEmail(email);
		if(user==null)
		{
			return "Invalid email id";
		}
		String subject="Recover Password";
		String body="Your Pwd :"+user.getPassword();
		emailUtils.sendEmail(subject, body, email);
		return "Password sent to your mail";
	}

}
