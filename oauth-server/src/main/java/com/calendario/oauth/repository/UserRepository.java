/**
 * @ProjectName: oauth-server
 * @PackageName: com.calendario.oauth.repository
 * @FileName: UserRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 03-04-2020
 * @Modified_By avishekdas @Last_On 03-Apr-2020 8:56:51 pm
 */

package com.calendario.oauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendario.oauth.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUsername(String username);

}
