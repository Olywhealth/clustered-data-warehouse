package com.johnson.clusteredDataWarehouse.repository;

import com.johnson.clusteredDataWarehouse.model.FXDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Johnson on 24/12/2023
 */
public interface FXDealRepository extends JpaRepository<FXDeal, Long> {

    Optional<FXDeal> findFXDealByRequestId(String requestId);
}
