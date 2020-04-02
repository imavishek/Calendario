/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.constant.enums
 * @FileName: Status.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 4:51:31 pm
 */

package com.calendario.global.common.microservice.constant.enums;

public enum Status {

	C_0("Failed", 0),
	C_1("Success", 1);

	private final String message;
	private final Integer code;

	Status(String message, Integer code) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}
}
