/**
 * FileName: MailServiceImpl
 * Author:   haichaoyang3
 * Date:     2019/7/2 11:56
 * Description: mailservice
 * History:
 */
package com.example.paywhere.service.impl;


import com.example.paywhere.commom.model.MailTemplateNameEnum;
import com.example.paywhere.dao.entity.UserProfile;
import com.example.paywhere.service.MailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:〈mailservice〉
 *
 * @author haichaoyang3
 * @create 2019/7/2
 * @since 1.0.0
 */
@Service
public class MailServiceImpl implements MailService {


    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MailServiceImpl.class);
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

   /* @Autowired
    private TemplateEngine templateEngine;*/

    @Value("${spring.mail.username}")
    private String USERNAME = "管理员";


    private String getMailTextByFreeMarkerTemplate(String templateName, Map<String, Object> params) throws IOException, TemplateException {
        String mailText = "";
        //通过指定模板名获取FreeMarker模板实例
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
        //FreeMarker通过Map传递动态数据
        //注意动态数据的key和模板标签中指定的属性相匹配
        //解析模板并替换动态数据，最终code将替换模板文件中的${code}标签。
        mailText = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        return mailText;
    }


    /*private String getMailTextByThymeleafTemplate(String templateName, Map<String, Object> params) {
        String mailText = "";
        Context context = new Context();
        context.setVariables(params);
        mailText = templateEngine.process(templateName, context);
        return mailText;
    }*/

    public boolean sendWithHTMLTemplate(Mail mail) {
        try {
            //发件人的昵称
            String nick = MimeUtility.encodeText(USERNAME);
            //发件人是谁
            InternetAddress from = new InternetAddress(nick + "<" + USERNAME + ">");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            //添加默认收件人
            String email = mail.getEmail();
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject(mail.getSubject());
            // 使用模板生成html邮件内容
            String result = getMailTextByFreeMarkerTemplate(mail.getTemplate(), mail.getParams());
            mimeMessageHelper.setText(result, true);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            log.error("发送邮件失败" + e.getMessage());
            return false;
        }
    }


    @Override
    public void sendMail(Map date) {
        Mail mail = new Mail();
        mail.setSubject("异常监控邮件");
        mail.setParams(date);
        mail.setTemplate(MailTemplateNameEnum.DATA_MONITOR_REPORT.getCode());
        sendWithHTMLTemplate(mail);
    }

    @Override
    public void sendRemindMail(UserProfile sysUser) {
        Mail mail = new Mail();
        mail.setEmail(sysUser.getEmail());
        mail.setSubject("密码过期提醒邮件");
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", sysUser);
        mail.setParams(data);
        mail.setTemplate(MailTemplateNameEnum.REMAIND_USER_CPW.getCode());
        sendWithHTMLTemplate(mail);
    }

    @Override
    public void sendMail(String code, String receiveEmail) {
        Mail mail = new Mail();
        mail.setEmail(receiveEmail);
        mail.setSubject("验证码");
        HashMap<String, Object> data = new HashMap<>();
        data.put("code", code);
        mail.setParams(data);
        mail.setTemplate(MailTemplateNameEnum.VERIFY_CODE.getCode());
        sendWithHTMLTemplate(mail);
    }

    @Getter
    @Setter
    private class Mail {
        private String email;
        private String subject;
        private Map params;
        private String template;
    }
}