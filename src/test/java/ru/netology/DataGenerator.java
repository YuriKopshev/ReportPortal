package ru.netology;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private DataGenerator() {}


    @Data
    public static class ClientCardInfo {
        private String city;
        private String name;
        private String telNumber;

       public ClientCardInfo(String city, String name, String telNumber) {
           this.city = city;
           this.name = name;
           this.telNumber = telNumber;
       }

       public static ClientCardInfo generateUser() {
           String[]city = new String[]{"Москва","Казань","Калининград"};
           Random random = new Random();
           String randomCity = city[random.nextInt(city.length)];
               Faker faker = new Faker(new Locale("ru"));
               return new ClientCardInfo(randomCity,
                       faker.name().firstName()+" "+faker.name().lastName(),
                       faker.phoneNumber().phoneNumber());
           }

           public static String dateGenerator(){
               String date = LocalDate.now().plusDays(3).
                       format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
               return date;
           }

    }
}
