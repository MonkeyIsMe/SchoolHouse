package com.csu.utils;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.struts2.ServletActionContext;

import com.csu.dao.UserDAO;
import com.csu.dao.impl.UserDAOImpl;
import com.csu.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class SendEmail {

	
	public void ForgetPassword(int userid) throws IOException {
		String myEmailAccount = "real251024@163.com";
	    String myEmailPassword = "ccaipeipei001";
	    UserDAO ud = new UserDAOImpl();
	    User user= ud.query(userid);
	    String email = user.getUserEmail();
	    //返回结果
	  	PrintWriter out = null;
	  	out = ServletActionContext.getResponse().getWriter();
	    if(email == null || email == "") {
	    	out.println("FAIL");
	    }
	    else {
	    	 String myEmailSMTPHost = "smtp.163.com";

	 	    // 收件人邮箱（替换为自己知道的有效邮箱）
	 	    String receiveMailAccount = email;
	 	    Properties props = new Properties();                    // 参数配置
	         props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
	         props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
	         props.setProperty("mail.smtp.auth", "true"); 


	         // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
	         //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
	         //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
	         /*
	         // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
	         //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
	         //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
	         final String smtpPort = "465";
	         props.setProperty("mail.smtp.port", smtpPort);
	         props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	         props.setProperty("mail.smtp.socketFactory.fallback", "false");
	         props.setProperty("mail.smtp.socketFactory.port", smtpPort);
	         */
	      // 2. 根据配置创建会话对象, 用于和邮件服务器交互
	         Session session = Session.getInstance(props);
	         session.setDebug(true); 
	         // 3. 创建一封邮件
	         MimeMessage message = new MimeMessage(session);
	         try {
	 			message.setFrom(new InternetAddress(myEmailAccount, "非一手房验证系统", "UTF-8"));
	 			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, "用户", "UTF-8"));
	 			message.setSubject("非一手房验证系统密码", "UTF-8");
	 			Long num =(long) ((Math.random()*9+1)*100000000);
	 	        String password = num.toString();
	 	        password = MD5.md5(password);
	 	        user.setUserPassword(password);
	 	        ud.update(user);
	 	        message.setContent("用户你好, 新密码为"+ num +"犹豫密码过于简单，请及时登录后修改您的密码！！", "text/html;charset=UTF-8");

	 	        // 6. 设置发件时间
	 	        message.setSentDate(new Date());

	 	        // 7. 保存设置
	 	        message.saveChanges();
	 		} catch (MessagingException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 		}

	         // 4. 根据 Session 获取邮件传输对象
	         Transport transport = null;
	 		try {
	 			transport = session.getTransport();
	 		} catch (NoSuchProviderException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	         try {
	        	// 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
	             // 
	             //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
	             //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
	             //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
	             //
	             //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
	             //           (1) 邮箱没有开启 SMTP 服务;
	             //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
	             //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
	             //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
	             //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
	             //
	             //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
	 			transport.connect(myEmailAccount, myEmailPassword);
	 		} catch (MessagingException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}

	         // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
	         try {
	 			transport.sendMessage(message, message.getAllRecipients());
	 		} catch (MessagingException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}

	         // 7. 关闭连接
	         try {
	 			transport.close();
	 		} catch (MessagingException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	    }
	}
}
