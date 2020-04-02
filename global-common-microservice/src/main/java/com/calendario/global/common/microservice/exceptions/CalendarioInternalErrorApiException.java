/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.exceptions
 * @FileName: CalendarioInternalErrorApiException.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 10:46:29 pm
 */

package com.calendario.global.common.microservice.exceptions;

public class CalendarioInternalErrorApiException extends CalendarioApiException {

	public CalendarioInternalErrorApiException(String message) {
		super(message);
	}
}
