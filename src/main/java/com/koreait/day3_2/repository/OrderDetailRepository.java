package com.koreait.day3_2.repository;

import com.koreait.day3_2.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    Optional<OrderDetail> findByItemId(Long itemId);
    Optional<OrderDetail> findFirstByStatusOrderById(String status);
    Optional<OrderDetail> findByQuantity(Integer quantity);

   Optional<OrderDetail> findFirstByIdOrderByIdDesc(Long id);
    Optional<OrderDetail> findById(Long id);
   Optional<OrderDetail> findByIdAndStatus(Long id,String status);
}
