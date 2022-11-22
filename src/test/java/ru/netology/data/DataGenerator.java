package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DataGenerator {
    private static final String validCard = "4444 4444 4444 4441";
    private static final String invalidCard = "4444 4444 4444 4442";
    private static final String [] numbers = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private DataGenerator() {}

    private static String getIncompleteCardNumber() {
        //Номер карты без последней цифры:
        return validCard.substring(0,18);
    }

    private static String getOverdueMonth() {
        //Текущий месяц - 1 месяц:
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String getLowerMonthValue() {
        return "00";
    }

    private static String getGreaterMonthValue() {
        return "13";
    }

    private static String getValidMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String getYear(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("yy"));
    }
    private static String getSpaceOrHyphen() {
        Random random = new Random();
        final String [] symbols = new String [] {" ", "-"};
        return symbols[random.nextInt(2)];  //Равноценно int диапазону [0;2)
    }

    private static String getSpecialSymbols() {
        Random random = new Random();
        final String [] specialSymbols = new String[] {"!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+",
                ",", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^", "_", "`", "{", "|", "}", "~"};
        var fistSymbol = specialSymbols[random.nextInt(31)];
        var secondSymbol = specialSymbols[random.nextInt(31)];
        return fistSymbol + secondSymbol;
    }

    private static String getNumbers() {
        Random random = new Random();
        var fistNumber = numbers[random.nextInt(10)];
        var secondNumber = numbers[random.nextInt(9) + 1];
        //Равноценно int диапазону [01;100):
        return fistNumber + secondNumber;
    }

    private static String getOwner() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    private static String getCVC() {
        Random random = new Random();
        var firstNumber = numbers[random.nextInt(10)];
        var secondNumber = getNumbers();
        //Равноценно int диапазону [001;1000):
        return firstNumber + secondNumber;
    }

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

    @Value
    public static class CardInfo {
        private String numberCard;
        private String month;
        private String year;
        private String owner;
        private String cvc;
    }
}
