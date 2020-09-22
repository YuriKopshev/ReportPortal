package ru.netology;

import com.github.javafaker.Faker;
import lombok.Data;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private DataGenerator() {}


    public static String getDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    public static String getCity(){
        Random random = new Random();
        String[] city = new String[]{"Москва", "Казань", "Калининград"};
        return city[random.nextInt(city.length)];
    }
    @Data
    public static class ClientGenerator {
        public static ClientCardInfo generateUser() {
            Faker faker = new Faker(new Locale("ru"));
            return new ClientCardInfo(getCity(),
                    faker.name().firstName() + " " + faker.name().lastName(),
                    faker.phoneNumber().phoneNumber());
        }
    }
}