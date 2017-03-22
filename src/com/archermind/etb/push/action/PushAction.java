package com.archermind.etb.push.action;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.common.BaseAction;
import com.archermind.etb.device.domain.EtbClientDevice;
import com.archermind.etb.device.service.EtbClientDeviceService;
import com.archermind.etb.push.domain.Msg;
import com.archermind.etb.push.domain.PushMsg;
import com.archermind.etb.push.service.PushService;
import com.archermind.etb.push.service.PushToClient;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.DateUtil;

/**
 * 
 * 推送后台Action
 * 
 * @author 002582,keli.peng
 * @version 1.0 20130822
 * @see com.archermind.etb.common.BaseAction
 * @since 1.0
 */
@Controller
@Scope("prototype")
@Namespace("/push")
@Action(value = "pushinfo", results = {
		@Result(name = "list", location = "pushList.jsp"),
		@Result(name = "prePush", location = "push.jsp"),
		@Result(name = "listDevice", location = "deviceList.jsp")
})
public class PushAction extends BaseAction{

	private static final long serialVersionUID = -1L;
	
	private static final Logger log = Logger.getLogger(PushAction.class);
	
	@Resource(name = "com.archermind.etb.push.service.PushService")
	private PushService pushService;
	
	@Resource(name = "com.archermind.etb.push.service.PushToClient")
	private PushToClient pushToClient;
	
	/**
	 * 声明EtbClientDeviceService
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbClientDeviceService")
	private EtbClientDeviceService etbClientDeviceService;

	/**
	 *存在
	 */
	private static final int  ISEXIST = 0;
	/**
	 * 已删除
	 */
	private static final int  ISDELETE = 1;
	
	/**
	 * 前台传入的消息正文查询条件
	 */
	private String msgContent;
	/**
	 * 返回PushMsg泛型集合
	 */
	private List<PushMsg> list;
	/**
	 * 消息推送实体
	 */
	private Msg msg;
	/**
	 * 设备的imei号
	 */
	private String imei;
	
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	/** 终端设备信息集合 */
	private List<EtbClientDevice> etbClientDeviceList;
	
	public List<EtbClientDevice> getEtbClientDeviceList() 
	{
		return etbClientDeviceList;
	}

	public void setEtbClientDeviceList(List<EtbClientDevice> etbClientDeviceList) 
	{
		this.etbClientDeviceList = etbClientDeviceList;
	}

	public Msg getMsg()
	{
		return msg;
	}

	public void setMsg(Msg msg)
	{
		this.msg = msg;
	}

	public List<PushMsg> getList()
	{
		return list;
	}
	
	public void setList(List<PushMsg> list)
	{
		this.list = list;
	}
	
	public String getMsgContent()
	{
		return msgContent;
	}
	
	public void setMsgContent(String msgContent)
	{
		this.msgContent = msgContent;
	}
	
	public PushService getPushService()
	{
		return pushService;
	}

	public void setPushService(PushService pushService) 
	{
		this.pushService = pushService;
	}
	
	public PushToClient getPushToClient() 
	{
		return pushToClient;
	}

	public void setPushToClient(PushToClient pushToClient)
	{
		this.pushToClient = pushToClient;
	}
	
	public EtbClientDeviceService getEtbClientDeviceService() 
	{
		return etbClientDeviceService;
	}

	public void setEtbClientDeviceService(
			EtbClientDeviceService etbClientDeviceService)
	{
		this.etbClientDeviceService = etbClientDeviceService;
	}
	
	/**
	 * 查询出push消息列表
	 * @return
	 */
	public String getPushList()
	{
		this.totalCount = this.pushService.getOtaCount(msgContent, ISEXIST);
		list = this.pushService.getPushInfoList(msgContent, ISEXIST, this.getPageNum(), this.numPerPage);
		return "list";
	}
	
	public String prePush()
	{
		return "prePush";
	}
	/**
	 * 紧急消息推送
	 *   
	 */
	@SuppressWarnings("all")
	public String pushMessage()
	{
		Calendar systemDate = Calendar.getInstance();
		Calendar excuteDate = Calendar.getInstance();
		try {
			systemDate.setTime(DateUtil.formatDateYYYY_MM_DD_HH_MM_SS(new Date()));
			excuteDate.setTime(DateUtil.formatDateYYYY_MM_DD_HH_MM_SS(msg.getExcuteDate()));
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		//若系统时间在执行时间后面，错误执行时间提示
		if(systemDate.after(excuteDate))
		{
			this.writeTextResultToResponse(Constant.EXCUTEDATE_FAIL, Constant.HTTP_STATUS_FAILED,
					"push", null, null, null);
			return null;
		}
		//单推
		if(msg.getType()==0)
		{
			Set set =new HashSet();
			if(msg.getDevice()!=null&&!msg.getDevice().equals(""))
			{
				String[] devices = msg.getDevice().split(",");
				for (int i = 0, size = devices.length; i < size; i++) {
					String device = devices[i];
					if(device.length()>0)
					{
						set.add(device);
					}
				}
			}
			this.pushToClient.addToClientUser(msg.getMessage(), msg.getExcuteDate(),true, set);
		}else//全推
		{
			this.pushToClient.pushToClient(msg.getMessage(),msg.getExcuteDate());
		}
		this.writeDwzResultToResponse(Constant.SAVE_SUCCESS, Constant.HTTP_STATUS_OK,
				"push", null, null, null);
		return null;
	}
	
	/**
	 * 显示终端设备信息
	 * 
	 * @return
	 */
	public String showEtbClientDevice()
	{
		this.pageNumShown=5;
		this.targetType = Constant.TARGET_TYPE_DIALOG;
		this.totalCount = etbClientDeviceService
				.getDeviceInfoCount(this.imei);
		this.etbClientDeviceList = etbClientDeviceService.listUserInfoByPage(
				this.imei, this.numPerPage, this.getPageNum());
		return "listDevice";
	}
	
}
