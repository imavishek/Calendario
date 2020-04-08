/**
 * @ProjectName: message-service
 * @PackageName: com.calendario.message.utils
 * @FileName: ClassPathResourceUtil.java
 * @Author: Avishek Das
 * @CreatedDate: 09-04-2020
 * @Modified_By avishekdas @Last_On 09-Apr-2020 2:37:03 am
 */

package com.calendario.message.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ClassPathResourceUtil {

	public Resource getClassPathResource(String directoryName, String resourcesName) {
		return new ClassPathResource(directoryName + "/" + resourcesName);
	}
}
