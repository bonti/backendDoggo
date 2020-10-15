package com.example.petmanager.response.bean;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.petmanager.entity.BaseEntitiy;


public class ResponseWrapper {

	private static List<BaseResponseBean> emptyBaseResponseBeanLst = new ArrayList<>();
	
	@JsonProperty("responseData")
	List<? extends BaseResponseBean> responseData = emptyBaseResponseBeanLst;
	
	@JsonProperty("responseStatus")
	StatusBean statusBean;

 

	
	/*
	 * success, no failures
	 */
	public ResponseWrapper (BaseResponseBean dataBean) {
		this(Arrays.asList(dataBean));
	}


	public ResponseWrapper (List<? extends BaseResponseBean> dataBean) {

		this.statusBean = StatusBean.getSuccess();
		this.responseData	= dataBean;
	}
 

	public ResponseWrapper (StatusBean statusBean, List<? extends BaseResponseBean> dataBean) {
		this.statusBean = statusBean;
		
		if (dataBean != null && dataBean.size() > 0) {
			this.responseData	= dataBean;
		}
	}


	/**
	 * some Failure occurred
	 * @param dataBean
	 */
	public ResponseWrapper (StatusBean statusBean) {
		this.statusBean	= statusBean;
	}


	public ResponseWrapper (StatusBean statusBean, BaseResponseBean dataBean) {
		this(statusBean, Arrays.asList(dataBean));
	}


	public List<? extends BaseResponseBean> getResponseData() {
		return responseData;
	}
	public void setResponseData(List<BaseResponseBean> responseData) {
		this.responseData = responseData;
	}

	public StatusBean getStatusBean() {
		return statusBean;
	}


	  
}
