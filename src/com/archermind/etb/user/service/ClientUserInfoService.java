package com.archermind.etb.user.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.user.dao.ClientUserInfoDao;
import com.archermind.etb.user.domain.ClientUserInfo;
import com.archermind.etb.util.Constant;

/**
 * 终端用户Service
 * 
 * @author 003445
 * @version 1.0
 * @see
 * @Time 2013-8-2-15:35:10
 */
@Service("com.archermind.etb.user.service.ClientUserInfoService")
public class ClientUserInfoService {

	/** 终端用户Dao */
	@Resource(name = "com.archermind.etb.user.dao.ClientUserInfoDao")
	private ClientUserInfoDao clientUserInfoDao;

	/**
	 * 添加终端用户信息
	 * 
	 * @param clientUserInfo
	 */
	public void addClientUserInfo(ClientUserInfo clientUserInfo) {
		clientUserInfoDao.save(clientUserInfo);
	}

	/**
	 * 修改终端用户信息
	 * 
	 * @param clientUserInfo
	 * @throws NoSuchAlgorithmException
	 */
	public void saveClientUserInfo(ClientUserInfo clientUserInfo)
			throws NoSuchAlgorithmException {
		// 设置数据状态
		String[] provinceName = {"北京市","上海市","天津市","重庆市","河北省","山西省","辽宁省","吉林省","河南省","江苏省","浙江省","安徽省","福建省","江西省","山东省","湖北省","湖南省","广东省","海南省","四川省","贵州省","云南省","陕西省","甘肃省","青海省","黑龙江省","内蒙古自治区","广西壮族自治区","西藏自治区","宁夏回族自治区","新疆维吾尔自治区","台湾省","香港特别行政区","澳门特别行政区","其它"};
		boolean flag = true;
		try{
			Integer.valueOf(clientUserInfo.getClientUserInfoPro());
		}catch(Exception e){
			flag = false;
		}
		if(flag){
			clientUserInfo.setClientUserInfoPro(provinceName[Integer.valueOf(clientUserInfo.getClientUserInfoPro())-1]);
		}
		clientUserInfo.setDataStat(Constant.DATA_STAT_ON);
		clientUserInfoDao.saveOrUpdate(clientUserInfo);
	}

	/**
	 * 根据主键id删除终端用户信息
	 * 
	 * @param clientUserInfoId
	 */
	public void delClientUserInfoById(long clientUserInfoId) {

		ClientUserInfo clientUserInfo = clientUserInfoDao.findById(
				ClientUserInfo.class, clientUserInfoId);
		if (null != clientUserInfo) {
			clientUserInfo.setDataStat(Constant.DATA_STAT_OFF);
			// 物理删除用户与设备的关联表中对应的记录
			clientUserInfoDao.delClientDeviceById(clientUserInfo
					.getClientUserInfoId());
		}
	}

	/**
	 * 根据 用户id 获取 终端用户信息
	 * 
	 * @param clientUserInfoId
	 * @return
	 */
	public ClientUserInfo getClientUserInfoById(long clientUserInfoId) {
		return clientUserInfoDao.findById(ClientUserInfo.class,
				clientUserInfoId);
	}

	/**
	 * 根据查询条件，分页查询终端用户信息
	 * 
	 * @param params
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<ClientUserInfo> listClientUserInfoByPage(
			String clientUserInfoName, String clientUserInfoAccount,
			String clientUserInfoIdentity, int pageSize, int pageNum) {

		return clientUserInfoDao.listClientUserInfoByPage(clientUserInfoName,
				clientUserInfoAccount, clientUserInfoIdentity, pageSize,
				pageNum);
	}

	/**
	 * 根据查询条件，查询终端用户信息总数
	 * 
	 * @param params
	 * @return
	 */
	public int getClientUserInfoCount(String clientUserInfoName,
			String clientUserInfoAccount, String clientUserInfoIdentity) {

		return clientUserInfoDao.getClientUserInfoCount(clientUserInfoName,
				clientUserInfoAccount, clientUserInfoIdentity);
	}

	/**
	 * 根据用户名获取终端用户信息
	 * 
	 * @param clientUserInfoName
	 * @return
	 */
	public List<ClientUserInfo> getClientUserInfoByName(
			String clientUserInfoName) {
		return clientUserInfoDao.getClientUserInfoByName(clientUserInfoName);
	}

	/**
	 * 根据该终端用户账号，获取该用户实例
	 * 
	 * @return
	 */
	public boolean findClientUserInfoByAccount(String clientUserInfoAccount,
			long clientUserInfoId) {
		ClientUserInfo clientUserInfo = clientUserInfoDao
				.findClientUserInfoByAccount(clientUserInfoAccount,
						clientUserInfoId);
		if (clientUserInfo == null) {
			return true;
		} else {
			return false;
		}
	}

}
