/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.service
 * @FileName: LogOutService.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 9:16:25 pm
 */

package com.calendario.oauth.service;

import javax.servlet.http.HttpServletRequest;

public interface LogOutService {

	Boolean revokeToken(HttpServletRequest request);
}
