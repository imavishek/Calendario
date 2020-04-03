/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.service
 * @FileName: UserService.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 9:16:15 pm
 */

package com.calendario.oauth.service;

import com.calendario.oauth.dto.PasswordDto;

public interface UserService {

	Boolean savePassword(PasswordDto passwordDto);
}
