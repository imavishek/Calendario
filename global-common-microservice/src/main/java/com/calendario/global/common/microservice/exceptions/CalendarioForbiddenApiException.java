/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.exceptions
 * @FileName: CalendarioForbiddenApiException.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 10:47:04 pm
 */

package com.calendario.global.common.microservice.exceptions;

public class CalendarioForbiddenApiException extends CalendarioApiException {

	public CalendarioForbiddenApiException(String message) {
		super(message);
	}
}
