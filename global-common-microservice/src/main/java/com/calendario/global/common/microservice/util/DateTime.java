/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.util
 * @FileName: DateTime.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 4:38:13 pm
 */

package com.calendario.global.common.microservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.calendario.global.common.microservice.constant.DateTimeFormat;

public class DateTime {

	public Date getCurrentDateTime() {

		String format = DateTimeFormat.INDIA_LOCAL_DATE_TIME;
		String today = new SimpleDateFormat(format).format(new Date());

		try {
			return new SimpleDateFormat(format).parse(today);
		} catch (ParseException e) {
		}

		return null;
	}
}
