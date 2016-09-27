package com.zq.fin.seckill.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	private String host = ""; // smtp服务器
	private String from = ""; // 发件人地址
	private String to = ""; // 收件人地址
	private String user = ""; // 用户名
	private String pwd = ""; // 密码
	private String subject = ""; // 邮件标题
	private String body = "";//正文内容
	
	public void setAddress(String from, String to, String subject) {
		this.from = from;
		this.to = to;
		this.subject = subject;
	}

	public void setBody(String body){
		this.body = body;
	}
	
	public void send(String host, String user, String pwd) {
		this.host = host;
		this.user = user;
		this.pwd = pwd;

		Properties props = new Properties();

		// 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
		props.put("mail.smtp.host", this.host);
		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
		props.put("mail.smtp.auth", "true");

		// 用刚刚设置好的props对象构建一个session
		Session session = Session.getDefaultInstance(props);

		// 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
		// 用（你可以在控制台（console)上看到发送邮件的过程）
		session.setDebug(true);

		// 用session为参数定义消息对象
		MimeMessage message = new MimeMessage(session);
		try {
			// 加载发件人地址
			message.setFrom(new InternetAddress(from));
			// 加载收件人地址
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 加载标题
			message.setSubject(subject);
			
			message.setText(body);

			// 保存邮件
			message.saveChanges();
			// 发送邮件
			Transport transport = session.getTransport("smtp");
			// 连接服务器的邮箱
			transport.connect(this.host, this.user, this.pwd);
			// 把邮件发送出去
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		MailSender cn = new MailSender();
//		// 设置发件人地址、收件人地址和邮件标题
//		cn.setAddress(BaseConstant.MAIL_USER, "wox666@sina.com", "一个带附件的JavaMail邮件");
//		// 设置要发送附件的位置和标题
//		cn.setBody("邮件测试使用................................");
//		// 设置smtp服务器以及邮箱的帐号和密码
//		cn.send(BaseConstant.MAIL_SMTP_HOST, BaseConstant.MAIL_USER, BaseConstant.MAIL_PW);
//	}
}
