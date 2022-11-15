package com.company.entity;

import java.util.Objects;

public class Card{
    private final String number;
    private final String password;
    private Long startTimeOfBlocking;

    public Card(String number, String password) {
        this.number = number;
        this.password = password;
        startTimeOfBlocking = -1L;
    }

    public Card(String number, String password, long startTimeOfBlocking) {
        this.number = number;
        this.password = password;
        this.startTimeOfBlocking = startTimeOfBlocking;
    }

    public void block(){
        startTimeOfBlocking = System.currentTimeMillis();
    }

    public Boolean isBlocked(){
        if(System.currentTimeMillis() - startTimeOfBlocking >= 1000*60*60*24) {
            startTimeOfBlocking = -1L;
            return false;
        }
        return true;
    }

    public Boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public String getNumber() {
        return number;
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
        String str = number + " " + password;
        if (startTimeOfBlocking != null) str += " " + startTimeOfBlocking;
        return str;
    }
}
