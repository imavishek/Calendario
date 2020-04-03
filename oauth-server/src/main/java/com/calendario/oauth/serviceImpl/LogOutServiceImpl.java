/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.serviceImpl
 * @FileName: LogOutServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 9:19:39 pm
 */

package com.calendario.oauth.serviceImpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calendario.oauth.service.LogOutService;

@Service
@Transactional
public class LogOutServiceImpl implements LogOutService {

	@Autowired
	private DefaultTokenServices tokenServices;

	@Override
	public Boolean revokeToken(HttpServletRequest request) {
		Boolean success = false;
		String authorization = request.getHeader("Authorization");

		if (authorization != null && authorization.contains("Bearer")) {
			String accessToken = authorization.substring("Bearer".length() + 1);

			success = tokenServices.revokeToken(accessToken);
		}

		return success;
	}

}
