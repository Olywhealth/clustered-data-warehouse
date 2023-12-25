package com.johnson.clusteredDataWarehouse.service;

import com.johnson.clusteredDataWarehouse.payload.request.FXDealRequest;
import com.johnson.clusteredDataWarehouse.payload.response.FXDealResponse;

import java.util.List;

/**
 * @author Johnson on 24/12/2023
 */
public interface FXDealService {
    FXDealResponse createFXDeal(FXDealRequest dealRequest);

    List<FXDealResponse> getAllFXDeal();

    FXDealResponse getUniqueDeal(Long id);
}
