import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CardDeliveryTest {

    private String generatedDate (int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    public void shouldHappyPath() {
        Configuration.holdBrowserOpen = true;
        //Configuration.timeout = 15;
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Королев");
        String todayDate = generatedDate(7, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(todayDate);
        $("[name='name']").setValue("Петров-Водкин Петр");
        $("[name='phone']").setValue("+79107852369");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("notification__content");
        $("div[data-test-id=notification").shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("div[data-test-id=notification").shouldHave(Condition.exactText("Встреча успешно забронирована на " + todayDate));

    }
}

