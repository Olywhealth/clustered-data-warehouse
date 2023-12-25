package com.johnson.clusteredDataWarehouse.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

/**
 * @author Johnson on 24/12/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FXDealResponse {

  private Currency orderingCurrency;
  private Currency convertingCurrency;
  private LocalDateTime orderTimeStamp;
  private BigDecimal amount;

}
