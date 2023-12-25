package com.johnson.clusteredDataWarehouse.utils;

import com.johnson.clusteredDataWarehouse.contracts.ValidCurrencyCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Currency;

/**
 * @author Johnson on 24/12/2023
 */
public class CurrencyCodeValidator implements ConstraintValidator<ValidCurrencyCode, String> {

  @Override
  public boolean isValid(String currencyCode, ConstraintValidatorContext context) {
    if (currencyCode == null) {
      return false;
    }

    return Currency.getAvailableCurrencies().stream()
            .anyMatch(c -> c.getCurrencyCode().equalsIgnoreCase(currencyCode));
  }
}
