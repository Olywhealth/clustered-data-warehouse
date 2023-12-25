package com.johnson.clusteredDataWarehouse.controller;

import com.johnson.clusteredDataWarehouse.payload.request.FXDealRequest;
import com.johnson.clusteredDataWarehouse.payload.response.FXDealResponse;
import com.johnson.clusteredDataWarehouse.service.FXDealService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Johnson on 25/12/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/fxdeal")
@Slf4j
public class FXDealController {

  private final FXDealService fxDealService;

  @PostMapping("/import-deal")
  @ApiOperation(value = "import new FXDeal", notes = "import new FXDeal", response = FXDealResponse.class)
  public ResponseEntity<FXDealResponse> importDeal(@Valid @RequestBody FXDealRequest dealRequest) {
    FXDealResponse fxDeal = fxDealService.createFXDeal(dealRequest);
    return new ResponseEntity<>(fxDeal, HttpStatus.CREATED);
  }

  @GetMapping("/all-deals")
  @ApiOperation(value = "retrieve all FXDeal", notes = "retrieve all FXDeal", response = FXDealResponse.class, responseContainer = "List")
  public ResponseEntity<List<FXDealResponse>> importDeal() {
    List<FXDealResponse> allFXDeal = fxDealService.getAllFXDeal();
    return new ResponseEntity<>(allFXDeal, HttpStatus.OK);
  }

  @GetMapping("/{dealId}/unique-deal")
  @ApiOperation(value = "retrieve unique FXDeal", notes = "retrieve unique FXDeal", response = FXDealResponse.class)
  public ResponseEntity<FXDealResponse> getUniqueDeal(@PathVariable Long dealId) {
    FXDealResponse uniqueDeal = fxDealService.getUniqueDeal(dealId);
    return new ResponseEntity<>(uniqueDeal, HttpStatus.OK);
  }

}
