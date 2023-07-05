package com.example.miniproject_basic_baejeu.repository;

import com.example.miniproject_basic_baejeu.entity.NegotiationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NegotiationRepository extends JpaRepository<NegotiationEntity, Long> {
    List<NegotiationEntity> findByPasswordAndWriter(String password, String writer);
    List<NegotiationEntity> findByStatusAndItemId(String status, Long itemId);

}



