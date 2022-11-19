package com.company.entity;

import com.company.utils.CardType;
import com.company.utils.Rounding;

import java.math.BigDecimal;

public class BronzeCard extends Card {
    public BronzeCard(String number, String password) {
        super(number, password, CardType.BRONZE);
    }

    @Override
    public void depositFunds(BigDecimal count) {
        count = Rounding.bigDecimalToHundredths(count.subtract(count.multiply(BigDecimal.valueOf(0.05))));

        this.setFunds(this.getFunds().add(count));
    }

    @Override
    public BigDecimal writeOffFunds(BigDecimal count) {
        if(this.getFunds().compareTo(count) < 0) throw new RuntimeException("На карте недостаточно средств");

        this.setFunds(this.getFunds().subtract(count));
        return Rounding.bigDecimalToHundredths(count.subtract(count.multiply(BigDecimal.valueOf(0.05))));
    }

    @Override
    public BigDecimal fundsForIssuance(BigDecimal count) {
        return Rounding.bigDecimalToHundredths(count.subtract(count.multiply(BigDecimal.valueOf(0.05))));
    }
}
