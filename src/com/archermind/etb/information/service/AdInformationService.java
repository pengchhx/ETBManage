package com.archermind.etb.information.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.information.dao.AdInformationDao;
import com.archermind.etb.information.domain.AdInformation;

/**
 * 广告记录service
 * @author 003468 wenlong.xiao
 * @version 1.0
 * @see
 * @Time 2013-8-12 14:14:27
 */
@Service("com.archermind.etb.information.service.AdInformationService")
public class AdInformationService {

	/**注入广告记录Dao*/
	@Resource(name = "com.archermind.etb.information.dao.AdInformationDao")
	private AdInformationDao adInformationDao;

	/**
	 * 根据条件查询广告采集信息
	 * @param clientUserInfoAccount 用户账号
	 * @param adId     广告编号
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<AdInformation> findInformationListByPage(String clientUserInfoAccount,
			String adId, int pageSize, int pageNum) {

		return adInformationDao.findInformationListByPage(clientUserInfoAccount, adId,
				pageSize, pageNum);
	}

	/**
	 * 根据条件查询获得总记录数
	 * @param clientUserInfoAccount
	 * @param adId
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public int getAdInformationCount(String clientUserInfoAccount, String adCollectionContent) {
		return adInformationDao.getAdInformationCount(clientUserInfoAccount, adCollectionContent);
	}
}
