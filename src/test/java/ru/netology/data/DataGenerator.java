package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DataGenerator {

    private static final String [] numbers = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private DataGenerator() {}


    static String getOverdueMonth() {
        //Текущий месяц - 1 месяц:
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    static String getLowerMonthValue() {
        return "00";
    }

    static String getGreaterMonthValue() {
        return "13";
    }

    static String getValidMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    static String getYear(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("yy"));
    }
    static String getSpaceOrHyphen() {
        Random random = new Random();
        final String [] symbols = new String [] {" ", "-"};
        return symbols[random.nextInt(2)];  //Равноценно int диапазону [0;2)
    }

    static String getSpecialSymbols() {
        Random random = new Random();
        final String [] specialSymbols = new String[] {"!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+",
                ",", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^", "_", "`", "{", "|", "}", "~"};
        var fistSymbol = specialSymbols[random.nextInt(31)];
        var secondSymbol = specialSymbols[random.nextInt(31)];
        return fistSymbol + secondSymbol;
    }

    static String getNumbers() {
        Random random = new Random();
        var fistNumber = numbers[random.nextInt(10)];
        var secondNumber = numbers[random.nextInt(9) + 1];
        //Равноценно int диапазону [01;100):
        return fistNumber + secondNumber;
    }

    static String getOwner() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    static String getCVC() {
        Random random = new Random();
        var firstNumber = numbers[random.nextInt(10)];
        var secondNumber = getNumbers();
        //Равноценно int диапазону [001;1000):
        return firstNumber + secondNumber;
    }

}
