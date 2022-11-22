package ru.netology.data;

import lombok.Value;
import static ru.netology.data.DataGenerator.*;

public class CardInf {

    private static final String validCard = "4444 4444 4444 4441";
    private static final String invalidCard = "4444 4444 4444 4442";

    public static CardInfo getApprovedCard() {
        return new CardInfo(validCard, getValidMonth(), getYear(0), getOwner(), getCVC());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo(invalidCard, getValidMonth(), getYear(0), getOwner(), getCVC());
    }

    public static CardInfo getCardWithIncompleteCardNumber() {
        return new CardInfo(getIncompleteCardNumber(), getValidMonth(), getYear(0), getOwner(), getCVC());
    }

    public static CardInfo getCardWithOverdueMonth() {
        if (getValidMonth().equals("01")) {
            return new CardInfo(validCard, getOverdueMonth(), getYear(-1), getOwner(), getCVC());
        }
        return new CardInfo(validCard, getOverdueMonth(), getYear(0), getOwner(), getCVC());
    }

    public static CardInfo getCardWithLowerMonthValue() {
        return new CardInfo(validCard, getLowerMonthValue(), getYear(1), getOwner(), getCVC());
    }

    public static CardInfo getCardWithGreaterMonthValue() {
        return new CardInfo(validCard, getGreaterMonthValue(), getYear(1), getOwner(), getCVC());
    }

    public static CardInfo getCardWithOverdueYear() {
        return new CardInfo(validCard, getValidMonth(), getYear(-1), getOwner(), getCVC());
    }

    public static CardInfo getCardWithYearFromFuture() {
        return new CardInfo(validCard, getValidMonth(), getYear(6), getOwner(), getCVC());
    }

    public static CardInfo getCardWithSpaceOrHyphenOwner() {
        return new CardInfo(validCard, getValidMonth(), getYear(0), getSpaceOrHyphen(), getCVC());
    }

    public static CardInfo getCardWithSpecialSymbolsOwner() {
        return new CardInfo(validCard, getValidMonth(), getYear(0), getSpecialSymbols(), getCVC());
    }

    public static CardInfo getCardWithNumbersOwner() {
        return new CardInfo(validCard, getValidMonth(), getYear(0), getNumbers(), getCVC());
    }

    public static CardInfo getCardWithIncompleteCVC() {
        return new CardInfo(validCard, getValidMonth(), getYear(0), getOwner(), getNumbers());
    }

    static String getIncompleteCardNumber() {
        //Номер карты без последней цифры:
        return validCard.substring(0,18);
    }

    @Value
    public static class CardInfo {
        private String numberCard;
        private String month;
        private String year;
        private String owner;
        private String cvc;
    }
}
