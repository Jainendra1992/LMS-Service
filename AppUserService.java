package com.website.lms.Service;

import com.website.lms.Repository.AppUserRepository;
import com.website.lms.dto.ChangePasswordStatus;
import com.website.lms.dto.EmailVerificationStatus;
import com.website.lms.dto.LoginSuccessStatus;
import com.website.lms.dto.ResetPasswordStatus;
import com.website.lms.dto.SendMailOnForgottenPasswordStatus;
import com.website.lms.dto.SignupSuccessStatus;
import com.website.lms.entity.AppUser;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;


@Service
public class AppUserService {
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private MailService mailService;
	
	public SignupSuccessStatus save(AppUser appUser) throws Exception
	{
		String token = generateToken();
		appUser.setToken(token);
		appUserRepository.save(appUser);
		mailService.sendMailToVerifyEmailId(token,appUser.getEmail());
		SignupSuccessStatus signupSuccessStatus = new SignupSuccessStatus();
		signupSuccessStatus.setMessage("Signup is very success.we have sent a mail.Pls verify your email by clicking on activating email from your email box");
		signupSuccessStatus.setStatus(true);
		return signupSuccessStatus;
	}
	
	private String generateToken()
	{
		String str  = "abcsdodidodbiowdjBSASAAIWBSLSUSUSHG0123456789";
		String token = "";
		for(int i = 1 ; i <= 5 ; i++)
		{
			token += str.charAt((int)(str.length() * Math.random()));
		}
		return token;
		
	}
	public LoginSuccessStatus login(AppUser clientObject)
	{
		LoginSuccessStatus loginSuccessStatus = new LoginSuccessStatus();
		loginSuccessStatus.setMessage("user is not available");
		Optional<AppUser> optional = appUserRepository.findById(clientObject.getEmail());
		
		{
		AppUser dbObject = appUserRepository.findById(clientObject.getEmail()).get();	
		loginSuccessStatus.setMessage("Invalid Password");
		if(dbObject.getPassword().equals(clientObject.getPassword()))
		{
			loginSuccessStatus.setMessage("Email is not verified."
					+"we have sent a mail to nail box");
			if(dbObject.getStatus() == 1)
			loginSuccessStatus.setMessage("login success");
			loginSuccessStatus.setStatus(true);
		}
	}
		return loginSuccessStatus;
	}
	
	
	public EmailVerificationStatus verifyMailId(String token, String email)
	{
		EmailVerificationStatus emailVerificationStatus = new EmailVerificationStatus();
				emailVerificationStatus.setMessage("Sorry the token was wrong");
				AppUser appUser = appUserRepository.findByTokenAndEmail(token,email);
				
				if(appUser != null)
				{
					appUser.setStatus(1);
					appUserRepository.save(appUser);
					emailVerificationStatus.setStatus(true);
					emailVerificationStatus.setMessage("your email verified successfully");
				}
              return emailVerificationStatus;						
	}
	
	public SendMailOnForgottenPasswordStatus 
	sendMailOnForgottenPassword(AppUser appUser) throws Exception
	{
		Optional<AppUser> optional = 
				appUserRepository.findById(appUser.getEmail());
		SendMailOnForgottenPasswordStatus forgottenPasswordStatus = 
				new SendMailOnForgottenPasswordStatus();
		forgottenPasswordStatus.setMessage("This user is not available");
		if(optional.isPresent())
		{
			mailService.sendMailOnForgottenPassword(appUser.getEmail());
			forgottenPasswordStatus.setMessage("Mail sent to " + 
					appUser.getEmail() + " with a password reset form");
			forgottenPasswordStatus.setStatus(true);
		}
		return forgottenPasswordStatus;
	}
	
	
	public ResetPasswordStatus
	resetPassword(String email, 
				  String password, 
				  String confirmPassword)
	{
		ResetPasswordStatus resetPasswordStatus = new ResetPasswordStatus();
		resetPasswordStatus.setMessage("password and confirm password "
				+ "both are not same");
		if(password.equals(confirmPassword))
		{
			AppUser appUser = appUserRepository.findById(email).get();
			appUser.setPassword(password);
			appUserRepository.save(appUser);	
			resetPasswordStatus.setMessage("Password changed successfully");
			resetPasswordStatus.setStatus(true);
		}		
		return resetPasswordStatus;
	}
	
	
	public ChangePasswordStatus
	changePassword(String email,
				   String oldPassword, 
				   String password, 
				   String confirmPassword)
	{
		ChangePasswordStatus changePasswordStatus = new ChangePasswordStatus();
		changePasswordStatus.setMessage("Old password is not correct");
		AppUser appUser = appUserRepository.findById(email).get();
		if(appUser.getPassword().equals(oldPassword))
		{
			changePasswordStatus.setMessage("password and confirm password "
					+ "both are not same");
			if(password.equals(confirmPassword))
			{
				appUser.setPassword(password);
				appUserRepository.save(appUser);	
				changePasswordStatus.setMessage("Password changed successfully");
				changePasswordStatus.setStatus(true);
			}
		}
		return changePasswordStatus;
	}
	
	
	
	
	
	
	
	
	
	
}


//it shud have service mandotry, its intercing with repositry