package healthgateway.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class WaitConditions {

    public static ExpectedCondition<Boolean> textToBePresentInElements(final List<WebElement> elements, final String text) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = "";
                    for (WebElement e : elements) {
                        elementText = e.getText();
                    }
                    return elementText.contains(text);
                } catch (StaleElementReferenceException var3) {
                    return false;
                }
            }

            public String toString() {
                return String.format("text ('%s') to be present in elements %s", text, elements);
            }
        };
    }
}
