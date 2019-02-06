package by.booking.pkt.recorded.appManager;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class HeaderHelper extends HelperBase {

  public HeaderHelper(WebDriver webDriver, WebDriverWait wait) {
    super(webDriver, wait);
  }

  public void selectCurrency(String currency) {
    showDropdown(By.cssSelector("[data-id=currency_selector]"), By.cssSelector("#current_currency_foldout"));
    clickOn(By.cssSelector("a[data-currency=" + currency));
  }

  public void goToWishlistPage() {
    showDropdown(By.cssSelector("#current_account span[class=user_name_block]"), By.cssSelector(".profile-menu"));
    clickOn(By.cssSelector("div[class*=wishlists"));
  }

  public void hdr_login(String username, String password) throws NullPointerException{
    By logInButton = findLoginButton();
    boolean devidedForm = isDevidedForm();
    clickOn(logInButton);
    WebElement signInForm = null;
    By usernameLocator = By.cssSelector("[name=username]");
    By passwordLocator = By.cssSelector("[name=password]");
    if (devidedForm) {
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm = getElement(By.cssSelector("form.nw-signin"));
      typeText(signInForm, usernameLocator, username);
      clickOn(signInForm, By.cssSelector("[type=submit]"));
      wait.until(visibilityOfElementLocated(passwordLocator));
      signInForm = getElement(By.cssSelector("form.nw-signin"));
      typeText(signInForm, passwordLocator, password);
      clickOn(signInForm, By.cssSelector("[type=submit]"));
    } else {
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm = getElement(By.cssSelector("form[target=log_tar]"));
      wait.until(visibilityOfElementLocated(usernameLocator));
      typeText(signInForm, usernameLocator, username);
      typeText(signInForm, passwordLocator, password);
      clickOn(signInForm, By.cssSelector("[type=submit]"));
    }
  }

  public void logOut() {
    showDropdown(By.cssSelector("#current_account span[class=user_name_block]"), By.cssSelector(".profile-menu"));
    clickOn(By.cssSelector("input[name=logout]+input"));
  }

  private boolean isDevidedForm() {
    boolean devided_form = false;
    try{
      devided_form = getAttribute(By.cssSelector("li#current_account a"), "data-command-params").contains("redirect_uri");
    } catch (NullPointerException e) {
      devided_form = false;
    }
    return devided_form;
  }

  @NotNull
  private By findLoginButton() {
    wait.until(presenceOfElementLocated(By.cssSelector("#current_account a")));
    boolean isDropedMenu = false;
    try{
      isDropedMenu = getAttribute(By.cssSelector("#current_account a"), "role").contains("button");
    } catch (NullPointerException e) {
      isDropedMenu = false;
    }
    if(isDropedMenu) {
      showDropdown(By.cssSelector("#current_account a"), By.cssSelector("div.profile-menu"));
      return By.cssSelector("div.profile-menu-auth-item a.js-header-hdr_login-link");
    } else {
      return By.cssSelector("#current_account a");
    }
  }
}
