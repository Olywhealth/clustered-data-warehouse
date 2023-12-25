package com.johnson.clusteredDataWarehouse.utils;


import java.util.Currency;

public class CurrencyCodeValidator {

    public static boolean isISOCurrencyCodeNotValid(String currencyCode) {
        return Currency.getAvailableCurrencies().stream().noneMatch(c -> c.getCurrencyCode().equals(currencyCode));
    }

}
