/**
 * @ProjectName: message-service
 * @PackageName: com.calendario.message.listener
 * @FileName: EventNotificationMailListener.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 10:25:15 pm
 */

package com.calendario.message.listener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.calendario.global.common.microservice.constant.RabbitMQConstant;
import com.calendario.global.common.microservice.dto.MessageDto;
import com.calendario.global.common.microservice.exceptions.CalendarioICalendarException;
import com.calendario.global.common.microservice.util.FileUtil;
import com.calendario.message.service.MessageService;
import com.calendario.message.utils.MessageUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.CuType;
import net.fortuna.ical4j.model.parameter.PartStat;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.parameter.Rsvp;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Status;
import net.fortuna.ical4j.model.property.Transp;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Url;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;
import net.fortuna.ical4j.validate.ValidationException;

@Component
@Slf4j
public class EventNotificationMailListener {

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private MessageService messageService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private FileUtil fileUtil;

	@RabbitListener(queues = RabbitMQConstant.EVENT_NOTIFICATION_QUEUE)
	public void sendMail(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
			throws IOException, CalendarioICalendarException {

		MessageDto messageDto = objectMapper.readValue(message, MessageDto.class);

		log.info("Message (Event Notification Mail) Consumed Successfully. Message: " + messageDto.toString());
		log.info("Mail Sending... Subject: " + messageDto.getSubject());

		generateICalendar(messageDto);

		Boolean success = messageUtil.sendEmail(messageDto);

		messageService.saveMessage(messageDto);

		if (success) {
			channel.basicAck(tag, false);
			log.info("Event Notification Mail Acknowledged");
		} else {
			channel.basicReject(tag, true);
			log.info("Do Not Discard (Requeue) Registration Mail");
		}
	}

	private void generateICalendar(MessageDto messageDto) throws CalendarioICalendarException {
		try {

			String calendarFileName = messageDto.getContentDetails().get("eventId") + "-invite.ics";

			// Start Date & End Date
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String sDate = messageDto.getContentDetails().get("slotDate") + " "
					+ messageDto.getContentDetails().get("startTime");
			String eDateDate = messageDto.getContentDetails().get("slotDate") + " "
					+ messageDto.getContentDetails().get("endTime");
			LocalDateTime start = LocalDateTime.parse(sDate, formatter);
			LocalDateTime end = LocalDateTime.parse(eDateDate, formatter);

			// Creating an event
			VEvent event = new VEvent(start, end, messageDto.getContentDetails().get("topic"));

			// Generate unique identifier
			UidGenerator uidGenerator = new RandomUidGenerator();
			Uid uid = uidGenerator.generateUid();
			event.getProperties().add(uid);

			event.getProperties().getProperty(Property.DTSTART).getParameters().add(Value.DATE_TIME);
			event.getProperties().getProperty(Property.DTEND).getParameters().add(Value.DATE_TIME);

			// Add organizer
			Organizer organizer = new Organizer(URI.create("mailto:calendario.online.2020@gmail.com"));
			organizer.getParameters().add(new Cn("Calendario"));
			event.getProperties().add(organizer);

			event.getProperties().add(Status.VEVENT_CONFIRMED);
			event.getProperties().add(Transp.OPAQUE);

			// Add url
			Url url = new Url(new URI(messageDto.getContentDetails().get("link")));
			event.getProperties().add(url);

			// Add attendees
			String hostMail = messageDto.getTo();
			String hostName = messageDto.getContentDetails().get("hostName");
			String partipantMail = messageDto.getCc();
			String partipantName = messageDto.getContentDetails().get("participantName");

			Attendee attendee1 = new Attendee(URI.create("mailto:" + hostMail));
			attendee1.getParameters().add(CuType.INDIVIDUAL);
			attendee1.getParameters().add(Role.REQ_PARTICIPANT);
			attendee1.getParameters().add(Rsvp.TRUE);
			attendee1.getParameters().add(PartStat.ACCEPTED);
			attendee1.getParameters().add(new Cn(hostName));
			event.getProperties().add(attendee1);

			Attendee attendee2 = new Attendee(URI.create("mailto:" + partipantMail));
			attendee2.getParameters().add(CuType.INDIVIDUAL);
			attendee2.getParameters().add(Role.OPT_PARTICIPANT);
			attendee2.getParameters().add(Rsvp.TRUE);
			attendee2.getParameters().add(PartStat.ACCEPTED);
			attendee2.getParameters().add(new Cn(partipantName));
			event.getProperties().add(attendee2);

			// Creating a new calendar
			Calendar calendar = new Calendar();
			calendar.getProperties().add(Version.VERSION_2_0);
			calendar.getProperties().add(new ProdId("-//Calendario//Calendar"));
			calendar.getProperties().add(CalScale.GREGORIAN);

			// Add the event
			calendar.getComponents().add(event);

			log.info("ICalender data: /n" + calendar.toString());

			String path = fileUtil.getDirectoryPath("/calendar/", calendarFileName);
			System.out.println(path);
			FileOutputStream fos = new FileOutputStream(path);
			CalendarOutputter outputter = new CalendarOutputter();
			outputter.output(calendar, fos);

		} catch (ValidationException | IOException | URISyntaxException e) {
			throw new CalendarioICalendarException(e.getMessage());
		}
	}
}
