package com.lottiefiles.dataGenerator;

import com.github.javafaker.*;

public class DataGenerator {
    private static final Faker faker = new Faker();

    private DataGenerator(){

    }

    public static String createRandomEmail() { //делаем отдельный пакет
        return faker.internet().emailAddress();
    }

    public static String createRandomPassword() {
        return faker.internet().password(8, 16);
    }

    public static String generateRandomWord() {
        return faker.lorem().word();
    }
}
