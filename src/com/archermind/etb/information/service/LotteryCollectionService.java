package com.archermind.etb.information.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.information.dao.LotteryCollectionDao;
import com.archermind.etb.information.domain.LotteryCollection;

/**
 * 彩票信息采集Service
 * 
 * @author 003445 张瑞
 * @version 1.0
 * @since v1.0
 * @time 2013-08-22 10:33
 */
@Service("com.archermind.etb.information.service.LotteryCollectionService")
public class LotteryCollectionService {

	/** 彩票信息采集Dao */
	@Resource(name = "com.archermind.etb.information.dao.LotteryCollectionDao")
	private LotteryCollectionDao lotteryCollectionDao;

	/**
	 * 根据查询条件，分页查询终端用户信息
	 * 
	 * @param params
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<LotteryCollection> listLotteryCollectionByPage(
			String clientUserInfoAccount, String lotteryId, int pageSize,
			int pageNum) {

		return lotteryCollectionDao.listLotteryCollectionByPage(
				clientUserInfoAccount, lotteryId, pageSize, pageNum);
	}

	/**
	 * 根据查询条件，查询终端用户信息总数
	 * 
	 * @param params
	 * @return
	 */
	public int getLotteryCollectionCount(String clientUserInfoAccount,
			String lotteryId) {

		return lotteryCollectionDao.getLotteryCollectionCount(
				clientUserInfoAccount, lotteryId);
	}

}
