package com.archermind.etb.user.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;







import com.archermind.etb.common.BaseAction;
import com.archermind.etb.user.domain.PowerInfo;
import com.archermind.etb.user.domain.UserInfo;
import com.archermind.etb.user.service.UserInfoService;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.Md5Util;


/**
 * 登录管理Action
 * @author 002525 陈洋
 * @version 1.0
 * @see com.archermind.etb.common.BaseAction
 * @Time 2013-8-2-15:23:05
 */
@Controller
@Scope("prototype")
@Namespace("/user")
@Action(value = "Login", results = {
		@Result(name = "LoginFailed", type = "redirect", location = "/login.jsp"),
//		@Result(name = "LoginSuccess", type = "redirect", location = "/superLogin.jsp"),
		@Result(name = "logout", type = "redirect", location = "/login.jsp"),
		
		@Result(name = "super", location = "superLogin.jsp"),
		@Result(name = "notsuper",location = "adminLogin.jsp")
		})
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 2852965379868553742L;

	private static final Logger log = Logger.getLogger(LoginAction.class);

	/** 用户信息 */
	private UserInfo userInfo;
	
	/** 用户集合 */
	private List<UserInfo> userInfoList;
	


    
	/** 用户信息service*/
	@Resource(name = "com.archermind.etb.user.service.UserInfoService")
	private UserInfoService userInfoService;

	/**
	 * 登录
	 * 
	 * @return
	 */
	public String checkLogin() {
		try {
			userInfo = userInfo == null ? new UserInfo() : userInfo;
			UsernamePasswordToken token = new UsernamePasswordToken(
					userInfo.getUserInfoUsername(), Md5Util.Md5(userInfo.getUserInfoPwd()));
			Subject subject = SecurityUtils.getSubject();

			subject.login(token);

			UserInfo user=userInfoService.getUserByLoginID(subject.getPrincipal().toString());
			System.out.println(user.getCompanyInfo().getCompanyInfoName());
			// 将用户放入session中
			subject.getSession().setAttribute(Constant.SESSION_USER, user);
			token.clear();
			Set<PowerInfo> powerList=user.getPowerInfoList();
			for(PowerInfo powerInfo:powerList){
				if(powerInfo.getPowerInfoId()==1){
					return "super";
				}else{
					return "notsuper";
				}	
			}
			return "super";
			
		} catch (Exception ex) {
			System.out.println("1111");
			log.error("User login Failure", ex);
			return "LoginFailed";
		}
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	public String logout() {
		// session失效
		SecurityUtils.getSubject().logout();
		return "logout";
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}


	
}
