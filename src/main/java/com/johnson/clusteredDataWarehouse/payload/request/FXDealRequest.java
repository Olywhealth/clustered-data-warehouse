package com.johnson.clusteredDataWarehouse.payload.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Johnson on 24/12/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FXDealRequest {

  @NotNull(message ="Ordering currency cannot be null")
  @NotBlank(message = "requestId cannot be blank")
  @Column(name = "requestId", nullable = false)
  private String requestId;

  @NotNull(message ="Ordering currency cannot be null")
  @NotBlank(message = "orderingCurrency cannot be blank")
  @Column(name = "ordering_currency", nullable = false)
  private String orderingCurrency;

  @NotNull(message ="Converting currency cannot be null")
  @NotBlank(message = "convertingCurrency cannot be blank")
  @Column(name = "converting_currency", nullable = false)
  private String convertingCurrency;

  @Column(name ="fxDeal_amount", precision = 10)
//  @Pattern(regexp = "^\\d{1,10}(\\.\\d{1,2})?$", message = "Invalid amount format")
  private BigDecimal amount;
}
