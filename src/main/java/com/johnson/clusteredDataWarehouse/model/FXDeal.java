package com.johnson.clusteredDataWarehouse.model;

import com.johnson.clusteredDataWarehouse.contracts.ValidCurrencyCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

/**
 * @author johnson on 24/12/2023
 */

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "warehouse_fXDeal")
public class FXDeal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull(message ="Ordering currency cannot be null")
  @Column(name = "requestId", nullable = false)
  private String requestId;

  @NotNull(message ="Ordering currency cannot be null")
  @Column(name = "ordering_currency", nullable = false)
  @ValidCurrencyCode
  private Currency orderingCurrency;

  @NotNull(message ="Converting currency cannot be null")
  @Column(name = "converting_currency", nullable = false)
  @ValidCurrencyCode
  private Currency convertingCurrency;

  @Column(name = "fxDeal_timestamp", nullable = false)
  private LocalDateTime orderTimeStamp;

  @Column(name ="fxDeal_amount", precision = 10)
  private BigDecimal amount;

}
