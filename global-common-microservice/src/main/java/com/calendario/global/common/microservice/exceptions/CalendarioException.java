/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.exceptions
 * @FileName: CalendarioException.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:46:55 pm
 */

package com.calendario.global.common.microservice.exceptions;

public abstract class CalendarioException extends Exception {

	private String message;

	public CalendarioException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
