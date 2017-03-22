package com.archermind.etb.information.action;

import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.archermind.etb.common.BaseAction;
import com.archermind.etb.information.domain.AdInformation;
import com.archermind.etb.information.service.AdInformationService;

/**
 * 广告采集信息
 * 
 * @author 003468 wenlong.xiao
 * @version 1.0
 * @see com.archermind.etb.common.BaseAction
 * @Time 2013-8-12 14:11:56
 */
@Controller
@Scope("prototype")
@Namespace("/information")
@Action(value = "adInformation", results = { @Result(name = "listAdInformation", location = "adInformation_list.jsp") })
public class AdInformationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 注入AdInformationService */
	@Resource(name = "com.archermind.etb.information.service.AdInformationService")
	private AdInformationService adInformationService;

	/** 广告采集信息集合 */
	private List<AdInformation> adInformationList;

	/** 广告采集信息 */
	private AdInformation adInformation;

	/** 返回广告采集信息 */
	public String findAdInformationList() {

		adInformation = adInformation == null ? new AdInformation()
				: adInformation;
		// 获取查询记录的条数
		this.totalCount = adInformationService.getAdInformationCount(
				adInformation.getClientUserInfoAccount(), adInformation.getAdCollectionContent());

		// 获取查询广告信息集合
		adInformationList = adInformationService.findInformationListByPage(
				adInformation.getClientUserInfoAccount(), adInformation.getAdCollectionContent(),
				this.numPerPage, this.getPageNum());
		return "listAdInformation";
	}

	public List<AdInformation> getAdInformationList() {
		return adInformationList;
	}

	public void setAdInformationList(List<AdInformation> adInformationList) {
		this.adInformationList = adInformationList;
	}

	public AdInformation getAdInformation() {
		return adInformation;
	}

	public void setAdInformation(AdInformation adInformation) {
		this.adInformation = adInformation;
	}

}
