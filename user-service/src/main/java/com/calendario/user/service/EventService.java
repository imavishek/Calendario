/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.service
 * @FileName: EventService.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 9:38:06 pm
 */

package com.calendario.user.service;

import com.calendario.global.common.microservice.exceptions.CalendarioBadRequestApiException;
import com.calendario.global.common.microservice.exceptions.CalendarioNotFoundApiException;
import com.calendario.user.dto.EventDto;

public interface EventService {

	Boolean createEvent(EventDto eventDto) throws CalendarioNotFoundApiException, CalendarioBadRequestApiException;
}
