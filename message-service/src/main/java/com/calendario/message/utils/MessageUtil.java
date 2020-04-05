/**
 * @ProjectName: message-service
 * @PackageName: com.calendario.message.utils
 * @FileName: MessageUtil.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 11:54:54 am
 */

package com.calendario.message.utils;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.calendario.global.common.microservice.dto.MessageDto;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageUtil {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration config;

	public Boolean sendEmail(MessageDto message) {

		Boolean success = false;
		String rootPath = "";
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		rootPath = path.substring(0, path.length() - 1) + "src/main/resources/images/logos/";

		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			Template template = config.getTemplate(message.getTemplateName() + ".ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, message);

			helper.setTo(new InternetAddress(message.getTo()));
			helper.setFrom(new InternetAddress(message.getForm()));
			helper.setText(html, true);
			helper.addInline("logo", new File(rootPath + "/Calendario.png"));
			helper.addInline("google", new File(rootPath + "/ico_google.png"));
			helper.addInline("facebook", new File(rootPath + "/ico_facebook.png"));
			helper.addInline("twitter", new File(rootPath + "/ico_twitter.png"));
			helper.addInline("instagram", new File(rootPath + "/ico_instagram.png"));
			helper.addInline("linkedin", new File(rootPath + "/ico_linkedin.png"));
			helper.setSubject(message.getSubject());

			mailSender.send(mimeMessage);

			log.info("Mail Sent Successfully. To: " + message.getContentDetails().get("recipientName") + "<"
					+ message.getTo() + ">," + " Subject: " + message.getSubject());
			success = true;

		} catch (MessagingException | IOException | TemplateException e) {

			log.error("Fail To Send Mail. To: " + message.getContentDetails().get("recipientName") + "<"
					+ message.getTo() + ">," + " Subject:-" + message.getSubject());

			log.error(e.getMessage() + " [Exception " + e.getClass() + "]");

			success = false;
		}

		return success;
	}
}
