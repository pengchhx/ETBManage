package com.archermind.etb.information.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.common.BaseAction;
import com.archermind.etb.information.domain.Payment;
import com.archermind.etb.information.service.PaymentService;

/**
 * 缴费采集信息
 * @author 003468 wenlong.xiao
 * @version 1.0
 * @see com.archermind.etb.common.BaseAction
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/information")
@Action(value = "payment", results = { @Result(name = "listPayment", location = "payment_list.jsp") })
public class PaymentAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 注入缴费service */
	@Resource(name = "com.archermind.etb.information.service.PaymentService")
	private PaymentService paymentService;

	/** 缴费信息列表 */
	private List<Payment> paymentList;

	/** 缴费信息 */
	private Payment payment;

	/** 返回广告采集信息 */
	public String findPaymentList() {

		payment = payment == null ? new Payment() : payment;
		// 获取查询记录的条数
		this.totalCount = paymentService.getPaymentCount(payment.getClientUserInfoAccount(),
				payment.getPaymentId());

		// 获取查询广告信息集合
		paymentList = paymentService.findPaymentListByPage(
				payment.getClientUserInfoAccount(), payment.getPaymentId(), this.numPerPage,
				this.getPageNum());
		return "listPayment";
	}

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
