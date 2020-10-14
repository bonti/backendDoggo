package com.example.petmanager.service.implementation;
 
import java.util.ArrayList;
 
import java.util.List;
 
import java.util.stream.Collectors;
 
import org.springframework.data.domain.Pageable;
 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
 
import com.example.petmanager.entity.BaseEntitiy;
import com.example.petmanager.response.bean.BaseResponseBean;
import com.example.petmanager.response.bean.EPSerializer;
import com.example.petmanager.response.bean.ResponseWrapper;
import com.example.petmanager.response.bean.StatusBean;
import com.example.petmanager.util.PortalUtil;
import com.example.petmanager.exception.ApiError;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BaseService {
	 
	protected ObjectMapper objectMapper = new ObjectMapper();

	public BaseService () {
		objectMapper.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(PortalUtil.epDateFormatter);
	}
	
	protected ResponseWrapper getResponseWrapper (List<? extends BaseResponseBean> beansLst) {
		
		ResponseWrapper responseWrapper = null;
		
		if (beansLst == null || beansLst.isEmpty()) {
			StatusBean statusBean = StatusBean.getSuccess(HttpStatus.NO_CONTENT);
			responseWrapper = new ResponseWrapper(statusBean);
		} else {
			responseWrapper = new ResponseWrapper(StatusBean.getSuccess(), beansLst);
		}

		return responseWrapper;
	}
	
	
 
	
	protected ResponseWrapper getResponseWrapper (List<? extends BaseResponseBean> beansLst,
				Pageable page, long total) {
		
		ResponseWrapper responseWrapper = null;
		
		if (beansLst == null || beansLst.isEmpty()) {
			StatusBean statusBean = StatusBean.getSuccess(HttpStatus.NO_CONTENT);
			responseWrapper = new ResponseWrapper(statusBean);
		} else {
			responseWrapper = new ResponseWrapper(StatusBean.getSuccess(), beansLst);
		}

		if (responseWrapper != null) {
			responseWrapper.setPageNumber(page.getPageNumber() + 1);
			responseWrapper.setPageSize(page.getPageSize());
			responseWrapper.setSortField(page.getSort().isSorted()?page.getSort().toString():"");
			responseWrapper.setTotalRows(total);
		}
		return responseWrapper;
	}
	
	
	protected ResponseWrapper getUpdateSuccessResponseWrapper () {
		
		StatusBean statusBean = StatusBean.getSuccess(HttpStatus.NO_CONTENT);
		return new ResponseWrapper(statusBean);
	}
	
	protected ResponseWrapper getCreateSuccessResponseWrapper () {
		
		StatusBean statusBean = StatusBean.getSuccess(HttpStatus.CREATED);
		return new ResponseWrapper(statusBean);
	}
	
protected ResponseWrapper getErrorResponseWrapper (List<ApiError> error) {
		
		StatusBean statusBean =  StatusBean.getValidationFailure(error); 
		return new ResponseWrapper(statusBean);
	}

	
	protected ResponseWrapper getResponseWrapper (BaseResponseBean bean) {
		List<BaseResponseBean> beansLst = new ArrayList<>(1);
		beansLst.add(bean);
		ResponseWrapper responseWrapper = new ResponseWrapper(beansLst);
		return responseWrapper;
	}
	
	protected List<BaseResponseBean> convertEntityToView (List<? extends BaseEntitiy> entityBean, Class<? extends BaseResponseBean> baseClass) {
		
		List<BaseResponseBean> responseBeans = entityBean.stream().map(
					entity -> objectMapper.convertValue(entity, baseClass)).collect(Collectors.toList());
		
		return responseBeans;
		
	}

	protected BaseResponseBean convertEntityToView (BaseEntitiy entityBean, Class<? extends BaseResponseBean> baseClass) {

		return objectMapper.convertValue(entityBean, baseClass);
	}

	 
	protected List<BaseEntitiy> convertViewToEntity (List<? extends EPSerializer> viewBean, Class<? extends BaseEntitiy> entityClass) {
		
		List<BaseEntitiy> entities = viewBean.stream().map(
					bean -> objectMapper.convertValue(bean, entityClass)).collect(Collectors.toList());
		
		return entities;
		
	}
}

