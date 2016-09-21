package com.ligeng.service;

import java.io.File;

public interface IMailService {
    /***
     * 
     * @param subject 主题
     * @param content 正文，支持html
     * @param recipient 收件人邮箱
     * @return
     */
	public boolean sendMail(String subject, String content, String[] recipient);
	
	/***
	 *
	 * @param subject 主题
	 * @param content 正文。支持html
	 * @param recipient 主送人列表
	 * @param cc  抄送人列表
	 * @return
	 */
	public boolean sendMail(String subject, String content, String[] recipient, String[] cc);
	/***
	 *
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 * @param files[] 附件
	 * @param recipient 收件人
	 * @return
	 */
	public boolean sendMailWithAttachment(String subject, String content, File[] files, String[] recipient);

	/***
	 *
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 * @param file[] 附件
	 * @param recipient 收件人
	 * @param cc[] 抄送
	 * @return
	 */
	public boolean sendMailWithAttachment(String subject, String content, File[] files, String[] recipient, String[] cc);
}
