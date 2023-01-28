import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {
    private String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    public void shouldHappyPath() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        String currentDate = generateDate(3);
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(currentDate);
        $("[name='name']").setValue("Петров-Водкин Петр");
        $("[name='phone']").setValue("+79107852369");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        //$(".notification__content");
        $("div[data-test-id=notification").shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("div[data-test-id=notification").shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));

    }
}

