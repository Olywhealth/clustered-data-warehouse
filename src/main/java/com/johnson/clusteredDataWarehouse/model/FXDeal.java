package com.johnson.clusteredDataWarehouse.model;

import jakarta.persistence.*;
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


  private String requestId;
  private Currency orderingCurrency;
  private Currency convertingCurrency;
  private LocalDateTime orderTimeStamp;
  private BigDecimal amount;

}
