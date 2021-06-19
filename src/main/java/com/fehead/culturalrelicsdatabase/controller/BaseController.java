package com.fehead.culturalrelicsdatabase.controller;


import com.fehead.culturalrelicsdatabase.core.error.BusinessException;
import com.fehead.culturalrelicsdatabase.core.error.EmBusinessError;
import com.fehead.culturalrelicsdatabase.core.response.CommonReturnType;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author: Zero
 * @Date: 2021/4/2 - 21:39
 * @since: jdk 1.8
 */
@RestControllerAdvice
public class BaseController {

    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

    @ExceptionHandler(Exception.class) //异常处理器（处理异常的范围）
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) //会返回一个状态码
    @ResponseBody
    public Object handlerException( Exception exception){
        Map<String,Object> responseData = new HashMap<>();
        if(exception instanceof BusinessException) { //自定义义务逻辑异常
            BusinessException businessException = (BusinessException) exception;
            responseData.put("errCode",businessException.getErrorCode());
            responseData.put("errMsg",businessException.getErrorMsg());
        } else if(exception instanceof MethodArgumentNotValidException){ //参数校验异常
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            bindingResult.getFieldErrors().forEach(fieldError -> responseData.put(fieldError.getField(),fieldError.getDefaultMessage()));
        } else if(exception instanceof ConstraintViolationException) { //自定义校验异常
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
            for (ConstraintViolation<?> item: constraintViolations
                 ) {
                responseData.put(item.getPropertyPath().toString(), item.getMessage());
            }
        }  else if (exception instanceof DataAccessException) { //数据库连接错误
            responseData.put(EmBusinessError.DATARESOURCE_CONNECT_FAILURE.getErrorMsg(),EmBusinessError.DATARESOURCE_CONNECT_FAILURE.getErrorMsg());
        } else if (exception instanceof HttpMessageNotReadableException) { // 序列化异常
            responseData.put(EmBusinessError.JSON_SEQUENCE_WRONG.getErrorMsg(),EmBusinessError.JSON_SEQUENCE_WRONG.getErrorMsg());
        }else if(exception instanceof AccessDeniedException){
            AccessDeniedException accessDeniedException = (AccessDeniedException) exception;
            responseData.put(accessDeniedException.getCause().toString(),accessDeniedException.getMessage());
        } else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrorMsg());
        }
        return CommonReturnType.fail(responseData);
    }
}
