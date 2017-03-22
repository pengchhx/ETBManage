package com.archermind.etb.user.action;

/**
 * 系统认证及授权
 * @author  003468 wenlong.xiao
 * @version 1.0
 * @see org.apache.shiro.realm.AuthorizingRealm
 * @since 1.0
 */
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;




import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.archermind.etb.user.domain.PowerInfo;
import com.archermind.etb.user.domain.UserInfo;
import com.archermind.etb.user.service.UserInfoService;

public class ServerAuthorizingRealm extends AuthorizingRealm {
    
	/**用户Service*/
	@Resource(name = "com.archermind.etb.user.service.UserInfoService")
	private UserInfoService userInfoService;
    
	
	/***
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		// null usernames are invalid
		if (principals == null) {
			throw new AuthorizationException(
					"PrincipalCollection method argument cannot be null.");
		}
		String username = (String) getAvailablePrincipal(principals);
		List<UserInfo> userInfoList = userInfoService
				.getUserInfoByUsername(username);
		if (userInfoList.size() == 0) {
			throw new AuthenticationException("用户不存在！");
		} else if (userInfoList.size() > 1) {
			throw new AuthenticationException("系统错误，存在重复用户！");
		} else {
			UserInfo user = userInfoService.getUserInfoByUsername(username).get(0);
			/*Set<String> roleIds = new HashSet<String>();
			// 获取用户角色ID
			long roleId = user.getRoleInfo().getRoleInfoId();
			roleIds.add(String.valueOf(roleId));
			SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			// 保存登录用户所具有的角色ID
			simpleAuthorizationInfo.setRoles(roleIds);
			return simpleAuthorizationInfo;*/
			
			Set<String> powerIds = new HashSet<String>();
			// 获取用户角色ID
			Set<PowerInfo> powerList=user.getPowerInfoList();
			for(PowerInfo powerInfo:powerList){
				powerIds.add(String.valueOf(powerInfo.getPowerInfoId()));
			}
			SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			// 保存登录用户所具有的角色ID
			simpleAuthorizationInfo.setRoles(powerIds);
			return simpleAuthorizationInfo;
			
		}
	}
   
	/***
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		List<UserInfo> userInfoList = userInfoService.getUserInfoByUsername(token.getUsername());
		if (userInfoList.size() == 0) {
			throw new AuthenticationException("用户不存在！");
		} else if (userInfoList.size() > 1) {
			throw new AuthenticationException("系统错误，存在重复用户！");
		} else {
			UserInfo userInfo = userInfoList.get(0);
			return new SimpleAuthenticationInfo(userInfo.getUserInfoUsername(),
					userInfo.getUserInfoPwd().toCharArray(), getName());
		}
	}

}
