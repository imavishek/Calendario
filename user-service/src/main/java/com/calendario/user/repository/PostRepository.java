/**
 * @ProjectName: calendario-build-all
 * @PackageName: com.calendario.user.repository
 * @FileName: PostRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 14-07-2020
 */

package com.calendario.user.repository;

import com.calendario.user.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

	Page<Post> findAllByUserUserId(UUID userId, Pageable pageable);
}
