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


	Integer pageSize;
	Long totalRows;
	Integer pageNumber;
	String sortOrder;
	String sortField;

	
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


	public Integer getPageSize() {
		return pageSize;
	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	public Long getTotalRows() {
		return totalRows;
	}


	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}


	public Integer getPageNumber() {
		return pageNumber;
	}


	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}


	public String getSortOrder() {
		return sortOrder;
	}


	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}


	public String getSortField() {
		return sortField;
	}


	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	
}
