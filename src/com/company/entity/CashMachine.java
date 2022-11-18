package com.company.entity;

import com.company.service.FileService;
import java.math.BigDecimal;

public class CashMachine {
    private BigDecimal amountOfFunds;
    private boolean containsCard;

    public CashMachine() {
        amountOfFunds = FileService.getCashMachineAmountOfFunds();
        containsCard = false;
    }

    public Boolean containsCard() {
        return containsCard;
    }

    public void setContainsCard(boolean containsCard) {
        this.containsCard = containsCard;
    }

    public BigDecimal getAmountOfFunds() {
        return amountOfFunds;
    }

    public Boolean withdrawFunds(BigDecimal count){
        if(amountOfFunds.compareTo(count) < 0) return false;
        amountOfFunds = amountOfFunds.subtract(count);
        return true;
    }

    public void depositFunds(BigDecimal count){
        amountOfFunds = amountOfFunds.add(count);
    }
}
