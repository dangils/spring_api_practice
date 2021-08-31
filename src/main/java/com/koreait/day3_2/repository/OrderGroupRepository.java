package com.koreait.day3_2.repository;

import com.koreait.day3_2.model.entity.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderGroupRepository extends JpaRepository<OrderGroup,Long> {
    Optional<OrderGroup> findFirstByIdOrderByIdDesc(Long id);
    Optional<OrderGroup> findById(Long id);
}
