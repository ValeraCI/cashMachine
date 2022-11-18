package com.company.entity;

import com.company.utils.CardType;
import java.math.BigDecimal;

public class GoldenCard extends Card {


    public GoldenCard(String number, String password) {
        super(number, password, CardType.GOLDEN);
    }

    @Override
    public void depositFunds(BigDecimal count) {
        this.setFunds(this.getFunds().add(count));
    }

    @Override
    public BigDecimal writeOffFunds(BigDecimal count) {
        if(this.getFunds().compareTo(count) < 0) throw new RuntimeException("На карте недостаточно средств");
        this.setFunds(this.getFunds().subtract(count));
        return count;
    }

    @Override
    public BigDecimal subtractPercentage(BigDecimal count) {
        return count;
    }
}
