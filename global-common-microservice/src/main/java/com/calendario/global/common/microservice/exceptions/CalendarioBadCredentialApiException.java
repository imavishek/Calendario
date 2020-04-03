/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.exceptions
 * @FileName: CalendarioBadCredentialApiException.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 9:01:03 pm
 */

package com.calendario.global.common.microservice.exceptions;

public class CalendarioBadCredentialApiException extends CalendarioApiException {

	public CalendarioBadCredentialApiException(String message) {
		super(message);
	}
}
