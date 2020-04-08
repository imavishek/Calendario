/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.util
 * @FileName: FileUtil.java
 * @Author: Avishek Das
 * @CreatedDate: 09-04-2020
 * @Modified_By avishekdas @Last_On 09-Apr-2020 3:27:02 am
 */

package com.calendario.global.common.microservice.util;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class FileUtil {

	public String getDirectoryPath(String directory, String fileName) {
		File currDir = new File(".");
		String directoryLocation = StringUtils.chop(currDir.getAbsolutePath()) + directory;
		File uploadDirectory = new File(directoryLocation);

		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdirs();
		}

		return directoryLocation + fileName;
	}
}
