/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.exceptions
 * @FileName: CalendarioUserEmailExistsException.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:48:31 pm
 */

package com.calendario.global.common.microservice.exceptions;

public class CalendarioUserEmailExistsException extends CalendarioException {

	public CalendarioUserEmailExistsException(String message) {
		super(message);
	}
}
