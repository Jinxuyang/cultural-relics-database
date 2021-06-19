package com.fehead.culturalrelicsdatabase.core.error;

/**
 * @author lmwis
 * @description:通用错误接口
 * @date 2019-08-28 20:29
 * @Version 1.0
 */
public interface CommonError {
    int getErrorCode();

    String getErrorMsg();

    CommonError setErrMsg(String errMsg);
}