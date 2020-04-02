/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.exceptions
 * @FileName: CalendarioBadRequestApiException.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 10:46:11 pm
 */

package com.calendario.global.common.microservice.exceptions;

public class CalendarioBadRequestApiException extends CalendarioApiException {

	public CalendarioBadRequestApiException(String message) {
		super(message);
	}
}
