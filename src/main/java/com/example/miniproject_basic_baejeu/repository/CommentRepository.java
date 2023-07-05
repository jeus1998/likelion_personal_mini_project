package com.example.miniproject_basic_baejeu.repository;

import com.example.miniproject_basic_baejeu.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
