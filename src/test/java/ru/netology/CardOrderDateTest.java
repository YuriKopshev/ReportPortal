package ru.netology;


import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class CardOrderDateTest {
    //String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    //String rescheduleDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));



    //Этот тест проверяет первичный заказ доставки, дата не занята
    @Test
    void shouldCardOrderDeliveryTestSuccessesDate() {
        open("http://localhost:9999");
        $("[type='text']").setValue(DataGenerator.ClientCardInfo.generateUser().getCity());
        $("[data-test-id=date] input.input__control").setValue(DataGenerator.ClientCardInfo.dateGenerator());
        $("[data-test-id= name] input.input__control ").setValue(DataGenerator.ClientCardInfo.generateUser().getName());
        $("[data-test-id= phone] input.input__control ").setValue(DataGenerator.ClientCardInfo.generateUser().getTelNumber());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 5000);
        $("[data-test-id=success-notification] .notification__content ").waitUntil(visible, 10000).shouldHave(text(DataGenerator.ClientCardInfo.dateGenerator()));
    }

    //Этот тест моделирует перепланировку даты доставки
//    @Test
//    void shouldCardOrderDeliveryTestRescheduleDate() {
//        open("http://localhost:9999");
//        $("[type='text']").setValue("Санкт-Петербург");
//        $("[data-test-id=date] input.input__control").setValue(info.getDate());
//        $("[data-test-id= name] input.input__control ").setValue(info.getName());
//        $("[data-test-id= phone] input.input__control ").setValue(info.getTelNumber());
//        $("[data-test-id='agreement']").click();
//        $$("[type='button']").find(exactText("Запланировать")).click();
//        $(withText("Успешно!")).waitUntil(visible, 5000);
//        $$("[type='button']").find(exactText("Запланировать")).click();
//        $$("[type='button']").find(exactText("Перепланировать")).click();
//        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
//        $("[data-test-id=date] input.input__control").setValue(rescheduleDate);
//        $(withText("Успешно!")).waitUntil(visible, 5000);
//        $("[data-test-id=success-notification] .notification__content ").waitUntil(visible, 10000).shouldHave(text(rescheduleDate));
//    }
//
//    //Этот тест негативный на проверку невалидного номера
//    @Test
//    void shouldCardOrderDeliveryTestWrongPhoneNumber() {
//        open("http://localhost:9999");
//        $("[type='text']").setValue("Екатеринбург");
//        $("[data-test-id=date] input.input__control").setValue(info.getDate());
//        $("[data-test-id= name] input.input__control ").setValue(info.getName());
//        $("[data-test-id= phone] input.input__control ").setValue("+700");
//        $("[data-test-id='agreement']").click();
//        $$("[type='button']").find(exactText("Запланировать")).click();
//        $(withText("Успешно!")).waitUntil(visible, 5000);
//        $("[data-test-id=success-notification] .notification__content ").waitUntil(visible, 10000).shouldHave(text(info.getDate()));
//    }
//
//    //Этот тест негативный на проверку невалидного города
//    @Test
//    void shouldCardOrderDeliveryTestWrongCity() {
//        open("http://localhost:9999");
//        $("[type='text']").setValue("Сочи");
//        $("[data-test-id=date] input.input__control").setValue(info.getDate());
//        $("[data-test-id= name] input.input__control ").setValue(info.getName());
//        $("[data-test-id= phone] input.input__control ").setValue(info.getTelNumber());
//        $("[data-test-id='agreement']").click();
//        $$("[type='button']").find(exactText("Запланировать")).click();
//        $(withText("Доставка в выбранный город недоступна")).waitUntil(visible, 5000);
//    }
//
//    //Этот тест негативный на проверку невалидного имени
//    @Test
//    void shouldCardOrderDeliveryTestWrongName() {
//        open("http://localhost:9999");
//        $("[type='text']").setValue("Астрахань");
//        $("[data-test-id=date] input.input__control").setValue(info.getDate());
//        $("[data-test-id= name] input.input__control ").setValue("-");
//        $("[data-test-id= phone] input.input__control ").setValue(info.getTelNumber());
//        $("[data-test-id='agreement']").click();
//        $$("[type='button']").find(exactText("Запланировать")).click();
//        $(withText("Успешно!")).waitUntil(visible, 5000);
//        $("[data-test-id=success-notification] .notification__content ").waitUntil(visible, 5000).shouldHave(text(info.getDate()));
//    }
}
