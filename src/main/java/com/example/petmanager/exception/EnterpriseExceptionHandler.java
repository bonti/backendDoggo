package com.example.petmanager.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.WebUtils;

import com.example.petmanager.response.bean.BaseResponseBean;
import com.example.petmanager.response.bean.ResponseWrapper;
import com.example.petmanager.response.bean.StatusBean;


// Catches the types of exceptions and tries to give more targeted status codes and error response.
@ControllerAdvice
@Controller
public class EnterpriseExceptionHandler implements ErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(EnterpriseExceptionHandler.class);
	
	public static final String MAJOR_EXCEPTION = "MAJOR EXCEPTION OCURRED : ";
	public static final String MINOR_EXCEPTION = "MINOR EXCEPTION OCURRED : ";
	
	public static final String DEFAULT_ERROR_MESSAGE = "Sorry, we were unable to process your request, Please try again later";
	public static final String DATA_INTEGRITY_VIOLATED = "Data integrity violated.";
	
	
	@RequestMapping("/error")
	public void handleError(final HttpServletRequest request,
	      final HttpServletResponse response, Throwable ex) throws Throwable {

	    Object errorObj = request.getAttribute("javax.servlet.error.exception");

	    if (errorObj != null && errorObj instanceof Throwable) {
	    	throw (Throwable)errorObj;
	    } else {
	    	logger.warn("error object is either null or not of type Throwable");
	    } 
	  }


	//Spring HttpFirewall throws this RequestRejectedException
	@ExceptionHandler(RequestRejectedException.class)
	@SuppressWarnings({ "unchecked" })
	protected ResponseEntity<ResponseWrapper> handleRequestRejectedException(RequestRejectedException ex) {
		return prepErrorResponse(HttpStatus.NOT_FOUND, ex);
	}


	@ExceptionHandler(Throwable.class)
	@SuppressWarnings({ "unchecked" })
	protected ResponseEntity<ResponseWrapper> handleAnyException(Throwable ex) {
		return prepErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(MissingServletRequestParameterException.class)
	ResponseEntity<ResponseWrapper>  onMissingServletRequestParameterException(MissingServletRequestParameterException e) {

		List<ApiError> errorsLst = new ArrayList<>();
		
		// get missing param
	    String paramName = e.getParameterName();
	    ApiError epError =  new ApiError(paramName,e.getMessage(), null);
	    errorsLst.add(epError);

	    StatusBean statusBean = StatusBean.getValidationFailure(errorsLst);
	    ResponseWrapper responseWrapper = new ResponseWrapper(statusBean);

	    return new ResponseEntity(responseWrapper, HttpStatus.BAD_REQUEST);
	  }
	
	
	@ExceptionHandler(AuthenticationException.class)
	@SuppressWarnings({ "unchecked" })
	protected ResponseEntity<ResponseWrapper> handleAuthenticationException(Exception ex) {
		if (ex instanceof AuthenticationError) {
			AuthenticationError authenticationError = (AuthenticationError) ex;
			if (authenticationError.getHttpSubStatus() != null && authenticationError.getUserInfo() != null) {
				return prepErrorResponse(HttpStatus.UNAUTHORIZED,
						HttpStatus.valueOf(authenticationError.getHttpSubStatus()), ex);
			} else if (authenticationError.getHttpSubStatus() != null) {
				return prepErrorResponse(HttpStatus.UNAUTHORIZED,
						HttpStatus.valueOf(authenticationError.getHttpSubStatus()), ex);
			}
		}
		return prepErrorResponse(HttpStatus.UNAUTHORIZED, ex);
	}
	  

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ResponseWrapper>  onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ResponseWrapper responseWrapper = prepException(e.getBindingResult().getFieldErrors(), e.getBindingResult().getGlobalErrors());
	    return new ResponseEntity(responseWrapper, HttpStatus.BAD_REQUEST);
	}
	

	@SuppressWarnings({ })
	@ExceptionHandler(BindException.class)
	ResponseEntity<ResponseWrapper>  onBindException(BindException e) {
		ResponseWrapper responseWrapper = prepException(e.getBindingResult().getFieldErrors(), e.getBindingResult().getGlobalErrors());
	    return new ResponseEntity<ResponseWrapper>(responseWrapper, HttpStatus.BAD_REQUEST);
	}

	
 

	private ResponseWrapper prepException (List<FieldError> fieldErrors, List<ObjectError>objectErrors) {
		List<ApiError> errorsLst = new ArrayList<>();
		// get field level validations
		if (fieldErrors != null && fieldErrors.size() > 0) {
		    for (FieldError fieldError : fieldErrors) {
		        errorsLst.add(new ApiError(fieldError.getField(), fieldError.getDefaultMessage(), String.valueOf(fieldError.getRejectedValue())));
		    }
		}
		// get class level validations
	    if (objectErrors != null && objectErrors.size() > 0) {
		    for (ObjectError objectError : objectErrors) {
		    	errorsLst.add(new ApiError(objectError.getCode(), objectError.getDefaultMessage(), null));
		    }
	    }
	    StatusBean statusBean = StatusBean.getValidationFailure(errorsLst);
	    ResponseWrapper responseWrapper = new ResponseWrapper(statusBean);
	    return responseWrapper;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(ConstraintViolationException.class) // This is not getting triggered!!
	ResponseEntity<ResponseWrapper>  onConstraintViolationException(ConstraintViolationException e) {
		String fieldName = null;
		List<ApiError> errorsLst = new ArrayList<>();
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    for (ConstraintViolation<?> violation : violations ) {
	    	for (Path.Node node : violation.getPropertyPath()) {
	            fieldName = node.getName();
	        }
	         errorsLst.add(new ApiError(fieldName, violation.getMessage(), String.valueOf(violation.getInvalidValue())));
	    }
	    StatusBean statusBean = StatusBean.getValidationFailure(errorsLst);
	    ResponseWrapper responseWrapper = new ResponseWrapper(statusBean);
	    return new ResponseEntity(responseWrapper, HttpStatus.BAD_REQUEST);
	  }

	@SuppressWarnings({ "unchecked" })
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	ResponseEntity<ResponseWrapper>  onMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {

		return prepErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, e);
	  }
	  
	
	@SuppressWarnings({ "unchecked" })
	@ExceptionHandler(HttpMessageNotReadableException.class)
	ResponseEntity<ResponseWrapper>  onMessageNotReadableException(HttpMessageNotReadableException e) {

		return prepErrorResponse(HttpStatus.BAD_REQUEST, e);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(ValidationException.class)
	ResponseEntity<ResponseWrapper>  onValidationException(ValidationException e) {
		List<ApiError> errorsLst = new ArrayList<>();
		ApiError epError = new ApiError(e.getField(), e.getMessage(), e.getValue());
    	errorsLst.add(epError);
	    StatusBean statusBean = StatusBean.getValidationFailure(errorsLst);
		ResponseWrapper responseWrapper = new ResponseWrapper(statusBean);
	    return new ResponseEntity(responseWrapper, HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings({ "unchecked" })
	@ExceptionHandler(NoHandlerFoundException.class)
	ResponseEntity<ResponseWrapper>  onHandlerFoundException(NoHandlerFoundException e) {
		return prepErrorResponse(HttpStatus.NOT_FOUND, e);
	}

	protected ResponseEntity<Object> handleExceptionInternal(
              Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
      }
      
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResponseEntity prepErrorResponse (HttpStatus httpStatus, Throwable ex) {
		logger.error("Exception Occurred with HTTP Status: " + httpStatus.value() + " : ",ex);
		StatusBean statusBean = StatusBean.getFailure(httpStatus, ex.getMessage());
		ResponseWrapper responseWrapper = new ResponseWrapper(statusBean);
		return new ResponseEntity(responseWrapper, httpStatus);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResponseEntity prepErrorResponse (HttpStatus httpStatus, HttpStatus httpSubStatus, Throwable ex) {
		logger.error("Exception Occurred with HTTP Status: " + httpStatus.value() + " & HTTP Sub Status: " + httpSubStatus.value() + " : ",ex);
		StatusBean statusBean = StatusBean.getFailure(httpStatus,  ex.getMessage());
		ResponseWrapper responseWrapper = new ResponseWrapper(statusBean);
		return new ResponseEntity(responseWrapper, httpStatus);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResponseEntity prepErrorResponse (HttpStatus httpStatus, HttpStatus httpSubStatus, BaseResponseBean responseBody, Throwable ex) {
		logger.error("Exception Occurred with HTTP Status: " + httpStatus.value() + " & HTTP Sub Status: " + httpSubStatus.value() + " : ",ex);
		StatusBean statusBean = StatusBean.getFailure(httpStatus,  ex.getMessage());
		ResponseWrapper responseWrapper = new ResponseWrapper(statusBean, responseBody);
		return new ResponseEntity(responseWrapper, httpStatus);
	}


	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(EnterpriseException.class)
	ResponseEntity<ResponseWrapper> onEnterpriseException(EnterpriseException e) {
		if(e.getStatus() != null) {
			return prepErrorResponse(e.getStatus(), e);
		}else {
			return prepErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(DataAccessException.class)
	ResponseEntity<ResponseWrapper> onDataAccessException(DataAccessException e) {
		logger.error("SQL Exception occurred: ", e);
		if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
			StatusBean statusBean = StatusBean.getFailure(HttpStatus.BAD_REQUEST, DATA_INTEGRITY_VIOLATED);
			ResponseWrapper responseWrapper = new ResponseWrapper(statusBean);
			return new ResponseEntity(responseWrapper, HttpStatus.BAD_REQUEST);
		}
		return prepErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	@SuppressWarnings({ "unchecked" })
	protected ResponseEntity<ResponseWrapper> handleAccessDeniedException(AccessDeniedException ex) {
		return prepErrorResponse(HttpStatus.FORBIDDEN, ex);
	}

}