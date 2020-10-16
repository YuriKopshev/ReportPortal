package ru.netology;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.DataGenerator.ClientGenerator.generateUser;
import static ru.netology.DataGenerator.getCity;
import static ru.netology.DataGenerator.getDate;

public class CardOrderDateTest {
    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure",new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll(){
    SelenideLogger.removeListener("allure");
    }

    String date = getDate(4);
    String rescheduleDate = getDate(5);
    ClientCardInfo info = generateUser();

    //Этот тест проверяет первичный заказ доставки, дата не занята
    @Test
    void shouldCardOrderDeliveryTestSuccessesDate() {
        open("http://localhost:9999");
        $("[type='text']").setValue(info.getCity());
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(date);
        $("[data-test-id= name] input.input__control ").setValue(info.getName());
        $("[data-test-id= phone] input.input__control ").setValue(info.getTelNumber());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 5000);
        $("[data-test-id=success-notification] .notification__content ").waitUntil(visible, 10000).shouldHave(text(date));
    }

    //Этот тест моделирует перепланировку даты доставки
    @Test
    void shouldCardOrderDeliveryTestRescheduleDate() {
        open("http://localhost:9999");
        $("[type='text']").setValue(info.getCity());
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(date);
        $("[data-test-id= name] input.input__control ").setValue(info.getName());
        $("[data-test-id= phone] input.input__control ").setValue(info.getTelNumber());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 5000);
        $("[data-test-id=success-notification] .notification__content ").waitUntil(visible, 5000).shouldHave(text(date));
        $$("[type='button']").find(exactText("Запланировать")).click();
        $$("[type='button']").find(exactText("Перепланировать")).click();
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(rescheduleDate);
        $(withText("Успешно!")).waitUntil(visible, 5000);
        $("[data-test-id=success-notification] .notification__content ").waitUntil(visible, 5000).shouldHave(text(rescheduleDate));
    }

    //Этот тест негативный на проверку невалидного номера
    @Test
    void shouldCardOrderDeliveryTestWrongPhoneNumber() {
        open("http://localhost:9999");
        $("[type='text']").setValue(info.getCity());
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(date);
        $("[data-test-id= name] input.input__control ").setValue(info.getName());
        $("[data-test-id= phone] input.input__control ").setValue("+700");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 5000);
        $("[data-test-id=success-notification] .notification__content ").waitUntil(visible, 10000).shouldHave(text(date));
    }

    //Этот тест негативный на проверку невалидного города
    @Test
    void shouldCardOrderDeliveryTestWrongCity() {
        open("http://localhost:9999");
        $("[type='text']").setValue("Сочи");
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(date);
        $("[data-test-id= name] input.input__control ").setValue(info.getName());
        $("[data-test-id= phone] input.input__control ").setValue(info.getTelNumber());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText("Доставка в выбранный город недоступна")).waitUntil(visible, 5000);
    }

    //Этот тест негативный на проверку невалидного имени
    @Test
    void shouldCardOrderDeliveryTestWrongName() {
        open("http://localhost:9999");
        $("[type='text']").setValue(info.getCity());
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(date);
        $("[data-test-id= name] input.input__control ").setValue("-");
        $("[data-test-id= phone] input.input__control ").setValue(info.getTelNumber());
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 5000);
        $("[data-test-id=success-notification] .notification__content ").waitUntil(visible, 5000).shouldHave(text(date));
    }
}
