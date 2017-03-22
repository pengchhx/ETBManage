package com.archermind.etb.push.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.archermind.etb.util.DateUtil;

/**
 * push消息数据库对应实体
 * 
 * @author 002582,keli.peng
 * @version 1.0 20130827
 * @see 
 * @since 1.0
 */
@Entity
@Table(name = "ETB_PUSH_MSG")
public class PushMsg implements Serializable{
	
	private static final long serialVersionUID = -1L;
	
	@Id
	@Column(name="ETB_PUSH_MSGID",length = 128)
	private String id;
	
	@Column(name="ETB_PUSH_MSGCONTENT",length = 3000)
	private String msgContent;
	
	@Column(name="ETB_PUSH_DEADLINE")
	private Date deadLine;
	
	@Column(name="ETB_PUSH_MSGCREATER",length = 48)
	private String msgCreater;

	@Column(name="ETB_PUSH_MSGCREATETIME")
	private Date msgCreateTime;
	
	@Column(name="DATA_STAT")
	private int datastat;
	
	@Transient
	private String deadLineDsp;
	
	@Transient
	private String msgCreateTimeDsp;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public String getMsgCreater() {
		return msgCreater;
	}

	public void setMsgCreater(String msgCreater) {
		this.msgCreater = msgCreater;
	}

	public Date getMsgCreateTime() {
		return msgCreateTime;
	}

	public void setMsgCreateTime(Date msgCreateTime) {
		this.msgCreateTime = msgCreateTime;
	}

	public int getStat() {
		return datastat;
	}

	public void setStat(int datastat) {
		this.datastat = datastat;
	}

	public String getDeadLineDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getDeadLine());
	}

	public String getMsgCreateTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getMsgCreateTime());
	}

}
