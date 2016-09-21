package com.ligeng.service.impl;

import com.ligeng.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;

@Service
public class MailServiceImpl implements IMailService {
	@Autowired
	private JavaMailSender mailSender;

	private static final Logger logger = LoggerFactory.getLogger("serviceLogger");

	@Override
	public boolean sendMail(String subject, String content, String[] recipient) {

		if(recipient==null||recipient.length<=0){
			return false;
		}
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"utf-8");
			messageHelper.setFrom("914076530@qq.com","ligeng");
			for(int i=0;i<recipient.length;i++){
				messageHelper.addTo(recipient[i]);
			}
			messageHelper.setSubject(subject);
			messageHelper.setText(content, true);//true 启用html格式
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("send mail error", e);
			return false;
		} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
	}

//	@Override
	public boolean sendMailWithAttachment(String subject, String content, File[] files,String[] recipient) {
		if(recipient==null||recipient.length<=0){
			return false;
		}
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"utf-8");
			messageHelper.setFrom("914076530@qq.com","ligeng");
			for(int i=0;i<recipient.length;i++){
				messageHelper.addTo(recipient[i]);
			}

			messageHelper.setSubject(subject);
			messageHelper.setText(content,true);//true 启用html格式
			if(null!=files&&files.length>0){
				for(File file:files){
					messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()), file);
				}
			}

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			logger.error("send mail error", e);
			return false;
		}
			return true;
	}

	@Override
	public boolean sendMail(String subject, String content, String[] recipient,String[] cc) {
		if(recipient==null||recipient.length<=0){
			return false;
		}
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"utf-8");
			messageHelper.setFrom("914076530@qq.com");
			for(int i=0;i<recipient.length;i++){
				messageHelper.addTo(recipient[i]);
			}
			//抄送
			if(null!=cc&&cc.length>0){
				messageHelper.setCc(cc);
			}
			messageHelper.setSubject(subject);
			messageHelper.setText(content,true);//true 启用html格式
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("send mail error", e);
			return false;
		}
			return true;
	}

	@Override
	public boolean sendMailWithAttachment(String subject, String content,File[] files, String[] recipient, String[] cc) {
		if(recipient==null||recipient.length<=0){
			return false;
		}
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"utf-8");
//			messageHelper.setFrom(PropertyUtil.getContextProperty("email_send"));
			for(int i=0;i<recipient.length;i++){
				messageHelper.addTo(recipient[i]);
			}
			//抄送
			if(null!=cc&&cc.length>0){
				messageHelper.setCc(cc);
			}
			messageHelper.setSubject(subject);
			messageHelper.setText(content,true);//true 启用html格式
			if(null!=files&&files.length>0){
				for(File file:files){
					messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()), file);
				}
			}

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			logger.error("send mail error", e);
			return false;
		}
			return true;
	}


}
