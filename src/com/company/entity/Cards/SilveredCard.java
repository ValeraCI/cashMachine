package com.company.entity.Cards;

import com.company.utils.CardType;

import java.math.BigDecimal;

public class SilveredCard extends Card {
    public SilveredCard(String number, String password) {
        super(number, password, CardType.SILVERED);
    }


    @Override
    public void depositFunds(BigDecimal count) {
        count = count.subtract(count.multiply(BigDecimal.valueOf(0.03)));

        this.setFunds(this.getFunds().add(count));
    }

    @Override
    public BigDecimal writeOffFunds(BigDecimal count) {
        if(this.getFunds().compareTo(count) < 0) throw new RuntimeException("На карте недостаточно средств");

        this.setFunds(this.getFunds().subtract(count));
        return count.subtract(count.multiply(BigDecimal.valueOf(0.03)));
    }

    @Override
    public BigDecimal subtractPercentage(BigDecimal count) {
        return count.subtract(count.multiply(BigDecimal.valueOf(0.03)));
    }
}
