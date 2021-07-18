/**
 * FileName: RabbitmqMessageKey
 * Author:   haichaoyang3
 * Date:     2019/7/3 19:01
 * Description: 获取mq信息在redis中的key，做幂等性校验
 * History:
 */
package com.example.paywhere.commom.util;


import com.jc.demo.springbootdemo.commom.model.Constant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.util.Map;

/**
 * Description:〈获取mq信息在redis中的key，做幂等性校验〉
 *
 * @author haichaoyang3
 * @create 2019/7/3
 * @since 1.0.0
 */
public class RabbitmqMessageKey {

    public static String getKey(Message message) {
        return Constant.Redis.MSG_CONSUMER_PREFIX + getCorrelationId(message);
    }

    public static String getCorrelationId(Message message) {
        String correlationId = null;
        MessageProperties properties = message.getMessageProperties();
        Map<String, Object> headers = properties.getHeaders();
        for (Map.Entry entry : headers.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if ("spring_returned_message_correlation".equals(key)) {
                correlationId = value;
            }
        }
        return correlationId;
    }

}