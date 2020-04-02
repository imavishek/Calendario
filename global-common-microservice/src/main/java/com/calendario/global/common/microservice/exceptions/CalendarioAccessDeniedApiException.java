/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.exceptions
 * @FileName: CalendarioAccessDeniedApiException.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 10:47:22 pm
 */

package com.calendario.global.common.microservice.exceptions;

public class CalendarioAccessDeniedApiException extends CalendarioApiException {

	public CalendarioAccessDeniedApiException(String message) {
		super(message);
	}
}
