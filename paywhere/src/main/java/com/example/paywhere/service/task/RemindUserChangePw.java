/**
 * FileName: ResendTimeoutMsg
 * Author:   haichaoyang3
 * Date:     2019/7/2 16:03
 * Description: resend time out msg
 * History:
 */
package com.example.paywhere.service.task;

import com.jc.demo.springbootdemo.dao.mapper.SysUserMapper;
import com.jc.demo.springbootdemo.dao.model.SysUser;
import com.jc.demo.springbootdemo.dao.model.SysUserExample;
import com.jc.demo.springbootdemo.service.service.MailService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 * Description:〈提醒用户修改密码〉
 *
 * @author haichaoyang3
 * @since 1.0.0
 */
@Component
public class RemindUserChangePw {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RemindUserChangePw.class);
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private MailService mailService;
     /*@Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private JedisUtils jedisUtils;

    @Scheduled(cron = "0 3/1 * * * ?")//每3分钟执行一次
    public void resendTimeoutMsg() {
        log.info("start process task ...");
        List<MsgLog> msgLogs = msgLogMapper.selectTimeoutMsg();
        if (msgLogs.size() == 0)
            return;
        msgLogs.forEach(msgLog -> {
            String msgId = msgLog.getMsgId();
            if (msgLog.getTryCount() >= 3) {
                msgLog.setStatus(Constant.MsgLogStatus.FAIL);
                msgLogMapper.updateStatus(msgLog);
                log.info("超过最大重试次数，信息投递失败，msgId：{}", msgId);
            } else {
                msgLog.setNextTryTime(JodaTimeUtil.plusMinutes(msgLog.getNextTryTime(), 1));
                msgLogMapper.updateTryCount(msgLog);
                CorrelationData correlationData = new CorrelationData();
                correlationData.setId(msgId);
                Message message = MessageHelper.objToMsg(JsonUtil.strToObj(msgLog.getMsg(), LoginLog.class));
                rabbitTemplate.convertAndSend(RabbitConfig.LOGIN_LOG_EXCHANGE_NAME, RabbitConfig.LOGIN_LOG_ROUTING_KEY_NAME, message, correlationData);
                String key = RabbitmqMessageKey.getKey(message);
                jedisUtils.set(key, RandomUtil.UUID36());
                log.info("第 " + (msgLog.getTryCount() + 1) + " 次重新投递消息");
            }
        });
        log.info("定时任务结束");
    }

    @Scheduled(cron = "0 1/1 * * * ?")//每1分钟执行一次
    public void productMessage() {
        LoginLog loginLog = new LoginLog();
        loginLog.setId(Math.incrementExact(System.currentTimeMillis()));
        loginLog.setCreateTime(new Date());
        loginLog.setUpdateTime(new Date());
        loginLog.setDescription("happy");
        loginLog.setUserId(Math.incrementExact(0));
        loginLog.setMsgId(RandomUtil.UUID36());
        loginLog.setType(2);
        GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = builder.create();
        String msg = gson.toJson(loginLog);
        MsgLog msgLog = new MsgLog();
        msgLog.setMsg(msg);
        msgLog.setStatus(Constant.MsgLogStatus.SENDING);
        msgLog.setMsgId(loginLog.getMsgId());
        msgLog.setTryCount(0);
        msgLog.setCreateTime(new Date());
        msgLog.setNextTryTime(JodaTimeUtil.plusMinutes(msgLog.getCreateTime(), 2));
        msgLog.setUpdateTime(new Date());
        msgLogMapper.insert(msgLog);
    }*/


    @Scheduled(cron = "0 0 1 1/1 * ?")//每天执行一次
    public void sendMailToRemindUserChangePw() {
        List<SysUser> users = sysUserMapper.selectByExample(new SysUserExample());
        for (SysUser user : users) {
            LocalDate umpw = user.getModifyPwTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Long diff = LocalDate.now().toEpochDay() - umpw.toEpochDay();
            if (diff == 90) {
                mailService.sendMail(user);
            }
            if (diff == 105) {
                SysUser sysUser = new SysUser();
                sysUser.setIsLock(true);
                sysUser.setId(user.getId());
                sysUser.setUserName(user.getUserName());
                sysUserMapper.updateByPrimaryKeySelective(sysUser);
            }
        }
    }

}