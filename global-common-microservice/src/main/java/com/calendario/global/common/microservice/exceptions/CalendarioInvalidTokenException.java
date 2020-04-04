/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.exceptions
 * @FileName: CalendarioInvalidTokenException.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:46:25 pm
 */

package com.calendario.global.common.microservice.exceptions;

public class CalendarioInvalidTokenException extends CalendarioException {

	public CalendarioInvalidTokenException(String message) {
		super(message);
	}
}
