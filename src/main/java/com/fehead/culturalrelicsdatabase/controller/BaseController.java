package com.fehead.culturalrelicsdatabase.controller;


import com.fehead.culturalrelicsdatabase.core.error.BusinessException;
import com.fehead.culturalrelicsdatabase.core.error.EmBusinessError;
import com.fehead.culturalrelicsdatabase.core.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Zero
 * @Date: 2021/4/2 - 21:39
 * @since: jdk 1.8
 */
public class BaseController {

    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

    //定义exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class) //异常处理器（处理异常的范围）
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) //会返回一个状态码
    @ResponseBody
    public Object handlerException( Exception exception){
        Map<String,Object> responseData = new HashMap<>();
        if(exception instanceof BusinessException) {
            BusinessException businessException = (BusinessException) exception;
            responseData.put("errCode",businessException.getErrorCode());
            responseData.put("errMsg",businessException.getErrorMsg());
        } else if(exception instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            for(int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
                if(i > 0) {
                    FieldError fieldError = bindingResult.getFieldErrors().get(i);
                    responseData.put(fieldError.getField(),fieldError.getDefaultMessage());
                }
            }
        } else if(exception instanceof ConstraintViolationException){
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
            for (ConstraintViolation<?> item: constraintViolations
                 ) {
                responseData.put(item.getPropertyPath().toString(), item.getMessage());
            }
        } else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrorMsg());
        }

        return CommonReturnType.create(responseData,"fail");
    }
}
