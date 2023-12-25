package com.johnson.clusteredDataWarehouse.service;

import com.johnson.clusteredDataWarehouse.contracts.ExtractRequestResponse;
import com.johnson.clusteredDataWarehouse.exception.CustomException;
import com.johnson.clusteredDataWarehouse.model.FXDeal;
import com.johnson.clusteredDataWarehouse.payload.request.FXDealRequest;
import com.johnson.clusteredDataWarehouse.payload.response.FXDealResponse;
import com.johnson.clusteredDataWarehouse.repository.FXDealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Johnson on 24/12/2023
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class FXDealServiceImpl implements FXDealService{

  private final FXDealRepository fxDealRepository;
  private final ModelMapper modelMapper;


  @ExtractRequestResponse
  public FXDealResponse createFXDeal(FXDealRequest dealRequest) {
    log.info("creating new FXDeal {}", dealRequest);
    Optional<FXDeal> existingDeal = fxDealRepository.findFXDealByRequestId(dealRequest.getRequestId());
    if ( existingDeal.isPresent()) {
      log.info("Duplicate transaction request");
      throw new CustomException("This is a duplicate transaction");
    }
    FXDeal fxDeal = new FXDeal();
    fxDeal.setRequestId(dealRequest.getRequestId());
    fxDeal.setConvertingCurrency(dealRequest.getConvertingCurrency());
    fxDeal.setOrderingCurrency(dealRequest.getOrderingCurrency());
    fxDeal.setOrderTimeStamp(LocalDateTime.now());
    fxDeal.setAmount(dealRequest.getAmount());
    FXDeal savedDeal = fxDealRepository.save(fxDeal);
    log.info("new FXDeal created {}", dealRequest);

    return modelMapper.map(savedDeal, FXDealResponse.class);

  }

  @ExtractRequestResponse
  public List<FXDealResponse> getAllFXDeal() {
    log.info("getting all deals");
    List<FXDeal> allDeal = fxDealRepository.findAll();

    return allDeal.stream().map(x-> modelMapper.map(x, FXDealResponse.class))
            .collect(Collectors.toList());
  }

  @ExtractRequestResponse
  public FXDealResponse getUniqueDeal(Long id) {
    log.info("Getting unique FXDeal {}", id);

    FXDeal uniqueDeal = fxDealRepository.findById(id)
            .orElseThrow(()-> new CustomException("No FXDeal for the ID: "+id));
    log.info("Unique deal retrieved {}", uniqueDeal);
    return modelMapper.map(uniqueDeal, FXDealResponse.class);

  }


}
