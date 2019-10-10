package io.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.app.dto.PurchaseOrder;
@Repository	
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, String> {

}
