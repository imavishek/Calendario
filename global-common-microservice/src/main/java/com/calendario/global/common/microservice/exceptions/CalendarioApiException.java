/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.exceptions
 * @FileName: CalendarioApiException.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 10:35:39 pm
 */

package com.calendario.global.common.microservice.exceptions;

public abstract class CalendarioApiException extends Exception {

	private String message;

	public CalendarioApiException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
