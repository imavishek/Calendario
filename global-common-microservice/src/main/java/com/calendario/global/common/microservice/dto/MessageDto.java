/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.dto
 * @FileName: MessageDto.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 11:53:48 am
 */

package com.calendario.global.common.microservice.dto;

import java.util.Map;

import lombok.Data;

@Data
public class MessageDto {

	private String form;

	private String to;

	private String cc;

	private String bcc;

	private String subject;

	private String templateName;

	private Map<String, String> contentDetails;
}
