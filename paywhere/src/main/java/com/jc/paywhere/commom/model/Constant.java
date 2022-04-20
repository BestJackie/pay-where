/**
 * FileName: Constant
 * Author:   haichaoyang3
 * Date:     2019/6/14 15:26
 * Description: cl
 * History:
 */
package com.jc.paywhere.commom.model;

/**
 * Description:〈cl〉
 *
 * @author haichaoyang3
 * @since 1.0.0
 */
public class Constant {
    public interface Redis {
        String OK = "OK";
        Integer EXPIRE_TIME_MINUTE = 60;// 过期时间, 60s, 一分钟
        Integer EXPIRE_TIME_HOUR = 60 * 60;// 过期时间, 一小时
        Integer EXPIRE_TIME_DAY = 60 * 60 * 24;// 过期时间, 一天
        String TOKEN_PREFIX = "token:";
        String MSG_CONSUMER_PREFIX = "consumer:";
        String ACCESS_LIMIT_PREFIX = "accessLimit:";
    }

    public interface LogType {
        String LOGIN = "1";// 登录
        String LOGOUT = "2";// 登出
    }

    public interface MsgLogStatus {
        Integer SENDING = 0;
        Integer SUCCESS = 1;
        Integer FAIL = 2;
    }

}
