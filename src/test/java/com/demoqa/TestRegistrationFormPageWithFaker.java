package com.demoqa;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TestRegistrationFormPageWithFaker extends TestSetup {

    Faker faker = new Faker();

    String firstname = faker.name().firstName(),
            lastname = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            number = "9852222222",
            address = faker.address().streetAddress(),
            gender = "Female",
            picture = "cat.jpeg",
            day = "15",
            month = "July",
            year = "1990",
            subject = "History",
            hobby = "Reading",
            state = "Haryana",
            city = "Karnal";

    @Test
    @DisplayName("Successful fill registration form")
    void fillFormTest() {
        step("Open registration form", () -> {
            open("/automation-practice-form");
            Selenide.executeJavaScript("document.querySelector(\"footer\").hidden = 'true';" +
                    "document.querySelector(\"#fixedban\").hidden = 'true'");
        });
        step("Fill registration form", () -> {
            $("#firstName").setValue(firstname);
            $("#lastName").setValue(lastname);
            $("#userEmail").setValue(email);
            $("#genterWrapper").$(byText(gender)).click();
            $("#userNumber").setValue(number);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(month);
            $(".react-datepicker__year-select").selectOption(year);
            $(byText(day)).click();
            $("#subjectsInput").setValue(subject).pressEnter();
            $("#hobbiesWrapper").$(byText(hobby)).click();
            $("#uploadPicture").uploadFromClasspath(picture);
            $("#currentAddress").setValue(address);
            $("#state").click();
            $(byText(state)).click();
            $("#city").click();
            $(byText(city)).click();
            $("#submit").click();
        });
        step("Check registration form", () -> {
            $(".modal-body").shouldHave
                    (text(firstname),
                            text(lastname),
                            text(email),
                            text(gender),
                            text(number),
                            text(day),
                            text(month),
                            text(year),
                            text(subject),
                            text(hobby),
                            text(picture),
                            text(address),
                            text(state),
                            text(city));
        });
    }
}
