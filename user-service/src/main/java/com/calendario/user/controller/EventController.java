/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.controller
 * @FileName: EventController.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 9:36:00 pm
 */

package com.calendario.user.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.calendario.global.common.microservice.constant.UserServiceEndpointUrl;
import com.calendario.global.common.microservice.constant.enums.Status;
import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.exceptions.CalendarioBadRequestApiException;
import com.calendario.global.common.microservice.exceptions.CalendarioNotFoundApiException;
import com.calendario.global.common.microservice.util.ResponseUtil;
import com.calendario.user.dto.EventDetailsDto;
import com.calendario.user.dto.EventDto;
import com.calendario.user.properties.MessageProperties;
import com.calendario.user.service.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RefreshScope
@Api(tags = "Event API", description = "Handle event operation")
public class EventController {

	@Autowired
	private EventService eventService;

	@Autowired
	private MessageProperties messageProperties;

	/**
	 * Method is used as a endpoint to create slot.
	 * 
	 * @param eventDto : Object containing the value of event fields.
	 * @return Response : Object containing the response status, message and data.
	 * @throws CalendarioBadRequestApiException
	 * @throws CalendarioNotFoundApiException
	 */
	@PostMapping(value = UserServiceEndpointUrl.CREATE_EVENT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create event")
	public Response<Object> create(@RequestBody EventDto eventDto)
			throws CalendarioNotFoundApiException, CalendarioBadRequestApiException {

		String message = null;
		Status status = null;
		Boolean success = false;

		success = eventService.createEvent(eventDto);

		if (success) {
			status = Status.C_1;
			message = messageProperties.getEvent_create_success();
		} else {
			status = Status.C_0;
			message = messageProperties.getEvent_create_failed();
		}

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, null);
	}

	/**
	 * Method is used as a endpoint to get event details.
	 * 
	 * @param eventId
	 * @return Response : Object containing the response status, message and data.
	 * @throws CalendarioNotFoundApiException
	 */
	@GetMapping(value = UserServiceEndpointUrl.GET_EVENT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get available slot")
	public Response<Object> getEventDetails(@PathVariable UUID eventId) throws CalendarioNotFoundApiException {

		String message = null;
		Status status = Status.C_1;

		EventDetailsDto event = eventService.getEventDetailsByEventId(eventId);

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, event);
	}
}
