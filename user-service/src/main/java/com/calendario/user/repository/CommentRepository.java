/**
 * @ProjectName: calendario-build-all
 * @PackageName: com.calendario.user.repository
 * @FileName: CommentRepository.java
 * @Author: Avishek Das
 * @CreatedDate: 13-07-2020
 */

package com.calendario.user.repository;

import com.calendario.user.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

	Page<Comment> findAllByPostPostIdAndParentCommentCommentId
			(UUID postId,UUID parentCommentId, Pageable pageable);

	Page<Comment> findAllByParentCommentCommentId
			(UUID commentId, Pageable pageable);
}
