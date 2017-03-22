package com.archermind.etb.information.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.archermind.etb.information.dao.PaymentDao;
import com.archermind.etb.information.domain.Payment;

/**
 * 缴费service
 * @author 003468 wenlong.xiao
 * @version 1.0
 * @see
 * @Time2013-8-12 14:15:26
 */
@Service("com.archermind.etb.information.service.PaymentService")
public class PaymentService {

	/** 注入缴费Dao */
	@Resource(name = "com.archermind.etb.information.dao.PaymentDao")
	private PaymentDao paymentDao;

	/***
	 * 根据条件查询缴费采集信息
	 * 
	 * @param clientUserInfoAccount
	 *            用户账号
	 * @param paymentId
	 *            缴费流水号
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Payment> findPaymentListByPage(String clientUserInfoAccount,
			String paymentId, int pageSize, int pageNum) {

		return paymentDao.findPaymentListByPage(clientUserInfoAccount, paymentId, pageSize,
				pageNum);
	}

	/**
	 * 根据条件查询获得总记录数
	 * 
	 * @param clientUserInfoAccount
	 * @param paymentId
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public int getPaymentCount(String clientUserInfoAccount, String paymentId) {
		return paymentDao.getPaymentCount(clientUserInfoAccount, paymentId);
	}
}
