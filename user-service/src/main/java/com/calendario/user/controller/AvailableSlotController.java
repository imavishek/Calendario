/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.controller
 * @FileName: AvailableSlotController.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 6:48:51 pm
 */

package com.calendario.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.calendario.global.common.microservice.constant.UserServiceEndpointUrl;
import com.calendario.global.common.microservice.constant.enums.Status;
import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.exceptions.CalendarioNotFoundApiException;
import com.calendario.global.common.microservice.util.ResponseUtil;
import com.calendario.user.dto.SlotDto;
import com.calendario.user.properties.MessageProperties;
import com.calendario.user.service.AvailableSlotService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RefreshScope
@Api(tags = "Available Slot API", description = "Handle slot operation")
public class AvailableSlotController {

	@Autowired
	private AvailableSlotService slotService;

	@Autowired
	private MessageProperties messageProperties;

	/**
	 * Method is used as a endpoint to create slot.
	 * 
	 * @param slotDto : Object containing the value of slot fields.
	 * @return Response : Object containing the response status, message and data.
	 * @throws CalendarioNotFoundApiException
	 */
	@PostMapping(value = UserServiceEndpointUrl.CREATE_AVAILABLE_SLOT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create slot")
	public Response<Object> create(@RequestBody SlotDto slotDto) throws CalendarioNotFoundApiException {

		String message = null;
		Status status = null;
		Boolean success = false;

		success = slotService.createSlots(slotDto);

		if (success) {
			status = Status.C_1;
			message = messageProperties.getSlot_create_success();
		} else {
			status = Status.C_0;
			message = messageProperties.getSlot_create_failed();
		}

		return ResponseUtil.getResponse(status.getCode(), status.getMessage(), message, null);
	}
}
