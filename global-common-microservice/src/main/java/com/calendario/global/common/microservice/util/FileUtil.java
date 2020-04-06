/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.util
 * @FileName: FileUtil.java
 * @Author: Avishek Das
 * @CreatedDate: 05-04-2020
 * @Modified_By avishekdas @Last_On 05-Apr-2020 1:14:58 pm
 */

package com.calendario.global.common.microservice.util;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class FileUtil {

	public String getDirectoryPath(String directory, String filename) {
		File currDir = new File(".");
		String directoryLocation = StringUtils.chop(currDir.getAbsolutePath()) + directory;
		File uploadDirectory = new File(directoryLocation);

		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdirs();
		}

		return directoryLocation + filename;
	}
}
