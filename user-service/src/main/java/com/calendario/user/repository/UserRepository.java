/**
 * @ProjectName: user-service
 * @PackageName: com.calendario.user.repository
 * @FileName: UserRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 04-04-2020
 * @Modified_By avishekdas @Last_On 04-Apr-2020 9:52:35 pm
 */

package com.calendario.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calendario.user.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByEmail(String email);

	Optional<User> findByToken(UUID token);
}
