package com.company.entity.Cards;

import com.company.utils.CardType;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

public abstract class Card{
    private final String number;
    private final String password;
    private Long blockingTime;
    private BigDecimal funds;
    private final CardType cardType;

    protected Card(String number, String password, CardType cardType) {
        this.number = number;
        this.password = password;
        this.cardType = cardType;
        blockingTime = -1L;
        funds = new BigDecimal(0);
    }

    public void block(){
        blockingTime = Calendar.getInstance().getTimeInMillis();
    }

    public Boolean isBlocked(){
        if(Calendar.getInstance().getTimeInMillis() - blockingTime >= 1000*60*60*24) {
            blockingTime = -1L;
            return false;
        }
        return true;
    }

    public CardType getType() {
        return cardType;
    }

    public Boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public String getNumber() {
        return number;
    }

    public void setFunds(BigDecimal cash) {
        this.funds = cash;
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public void setBlockingTime(Long startTimeOfBlocking) {
        this.blockingTime = startTimeOfBlocking;
    }

    public abstract void depositFunds(BigDecimal count);

    public abstract BigDecimal writeOffFunds(BigDecimal count);

    public abstract BigDecimal subtractPercentage(BigDecimal count);

    public Boolean possibleWriteOffFunds(BigDecimal count){
        if(funds.compareTo(count) >= 0) return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return number.equals(card.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        String str = number + " " + password + " " + blockingTime + " " + funds + " " + cardType;
        return str;
    }
}
