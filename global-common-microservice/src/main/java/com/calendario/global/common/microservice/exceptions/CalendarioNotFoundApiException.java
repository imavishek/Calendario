/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.exceptions
 * @FileName: CalendarioNotFoundApiException.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 10:44:44 pm
 */

package com.calendario.global.common.microservice.exceptions;

public class CalendarioNotFoundApiException extends CalendarioApiException {

	public CalendarioNotFoundApiException(String message) {
		super(message);
	}
}
