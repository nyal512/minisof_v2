package com.nyal.minisof.repository;

import com.nyal.minisof.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query(value = "select * from  `order` where account_id= ?1 and `status` = ?2 order by id desc", nativeQuery = true)
    public List<OrderEntity> getByAccountIdAndStatus(Integer accountId, Integer status);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update `order` set status = ?1 where id = ?2", nativeQuery = true)
    public void updateStatus(Integer status, Integer order);
    @Query(value = "select * from `order` where status= ?1 order by desc", nativeQuery = true)
    public List<OrderEntity> getAllByStatus(Integer status);
}
