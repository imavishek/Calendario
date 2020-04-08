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
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.calendario.global.common.microservice.dto.MessageDto;
import com.calendario.global.common.microservice.util.FileUtil;

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

	@Autowired
	private FileUtil fileUtil;

	@Autowired
	private ClassPathResourceUtil classPathResourceUtil;

	public Boolean sendEmail(MessageDto message) {

		Boolean success = false;
		String rootPath = "/images/logos/";
		String calendarAttachmentPath = "/calendar/";

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			mimeMessage.addHeaderLine("charset=UTF-8");
			mimeMessage.addHeaderLine("component=VEVENT");
			mimeMessage.addHeaderLine("method=REQUEST");

			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

			if (message.getContentDetails().containsKey("attachCalender"))
				helper.addAttachment("invite.ics", new File(fileUtil.getDirectoryPath(calendarAttachmentPath,
						message.getContentDetails().get("eventId") + "-invite.ics")));

			Template template = config.getTemplate(message.getTemplateName() + ".ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, message);

			helper.setTo(new InternetAddress(message.getTo(), "Calendario"));
			helper.setFrom(new InternetAddress(message.getForm()));
			if (message.getCc() != null)
				helper.setCc(new InternetAddress(message.getCc()));
			if (message.getBcc() != null)
				helper.setBcc(new InternetAddress(message.getBcc()));
			helper.setText(html, true);
			helper.addInline("logo", classPathResourceUtil.getClassPathResource(rootPath, "/Calendario.png").getFile());
			helper.addInline("google",
					classPathResourceUtil.getClassPathResource(rootPath, "/ico_google.png").getFile());
			helper.addInline("facebook",
					classPathResourceUtil.getClassPathResource(rootPath, "/ico_facebook.png").getFile());
			helper.addInline("twitter",
					classPathResourceUtil.getClassPathResource(rootPath, "/ico_twitter.png").getFile());
			helper.addInline("instagram",
					classPathResourceUtil.getClassPathResource(rootPath, "/ico_instagram.png").getFile());
			helper.addInline("linkedin",
					classPathResourceUtil.getClassPathResource(rootPath, "/ico_linkedin.png").getFile());
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
