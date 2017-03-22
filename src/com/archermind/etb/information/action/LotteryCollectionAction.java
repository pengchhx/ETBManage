package com.archermind.etb.information.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.archermind.etb.common.BaseAction;
import com.archermind.etb.information.domain.LotteryCollection;
import com.archermind.etb.information.service.LotteryCollectionService;

/**
 * 彩票信息采集Action
 * 
 * @author 003445 张瑞
 * @version v1.0
 * @see com.archermind.etb.common.BaseAction
 * @time 2013-08-22 10:32
 */
@Controller
@Scope("prototype")
@Namespace("/information")
@Action(value = "lotteryCollection", results = { 
		@Result(name = "listLotteryCollection", location = "lotterycollection_list.jsp"), })
public class LotteryCollectionAction extends BaseAction {

	private static final long serialVersionUID = -8012587562634772679L;

	/** 彩票采集信息 */
	private LotteryCollection lotteryCollection;

	/** 彩票采集信息集合 */
	private List<LotteryCollection> lotteryCollectionList;

	/** 声明 LotteryCollectionService */
	@Resource(name = "com.archermind.etb.information.service.LotteryCollectionService")
	private LotteryCollectionService lotteryCollectionService;

	/**
	 * 列表展现彩票采集信息
	 * 
	 * @return
	 */
	public String listLotteryCollection() {

		lotteryCollection = lotteryCollection == null ? new LotteryCollection()
				: lotteryCollection;
		this.totalCount = lotteryCollectionService.getLotteryCollectionCount(
				lotteryCollection.getClientUserInfoAccount(),
				lotteryCollection.getLotteryId());
		this.lotteryCollectionList = lotteryCollectionService
				.listLotteryCollectionByPage(
						lotteryCollection.getClientUserInfoAccount(),
						lotteryCollection.getLotteryId(), this.numPerPage,
						this.getPageNum());
		return "listLotteryCollection";
	}

	public LotteryCollection getLotteryCollection() {
		return lotteryCollection;
	}

	public void setLotteryCollection(LotteryCollection lotteryCollection) {
		this.lotteryCollection = lotteryCollection;
	}

	public List<LotteryCollection> getLotteryCollectionList() {
		return lotteryCollectionList;
	}

	public void setLotteryCollectionList(
			List<LotteryCollection> lotteryCollectionList) {
		this.lotteryCollectionList = lotteryCollectionList;
	}

}
