package com.lltech.manager.entity.me;

import com.lltech.manager.entity.Req;

public class SystemMsgReq extends Req {
	private static final long serialVersionUID = 1L;
	private String Recipient;
	private String RecipientState;
	private String MessageTitle;
	private String MessageContent;
	private String MessageType;
	private String SendTime;
	private String Sender;
	private String SysMsgRecipientID;
	private String ReceptionTime;
	private String State;
	private String Remark;
	private String RecipientListID;

	public String getRecipient() {
		return Recipient;
	}

	public void setRecipient(String recipient) {
		Recipient = recipient;
	}

	public String getRecipientState() {
		return RecipientState;
	}

	public void setRecipientState(String recipientState) {
		RecipientState = recipientState;
	}

	public String getMessageTitle() {
		return MessageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		MessageTitle = messageTitle;
	}

	public String getMessageContent() {
		return MessageContent;
	}

	public void setMessageContent(String messageContent) {
		MessageContent = messageContent;
	}

	public String getMessageType() {
		return MessageType;
	}

	public void setMessageType(String messageType) {
		MessageType = messageType;
	}

	public String getSendTime() {
		return SendTime;
	}

	public void setSendTime(String sendTime) {
		SendTime = sendTime;
	}

	public String getSender() {
		return Sender;
	}

	public void setSender(String sender) {
		Sender = sender;
	}

	public String getSysMsgRecipientID() {
		return SysMsgRecipientID;
	}

	public void setSysMsgRecipientID(String sysMsgRecipientID) {
		SysMsgRecipientID = sysMsgRecipientID;
	}

	public String getReceptionTime() {
		return ReceptionTime;
	}

	public void setReceptionTime(String receptionTime) {
		ReceptionTime = receptionTime;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getRecipientListID() {
		return RecipientListID;
	}

	public void setRecipientListID(String recipientListID) {
		RecipientListID = recipientListID;
	}
}
