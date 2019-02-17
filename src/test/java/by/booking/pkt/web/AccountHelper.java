package by.booking.pkt.web;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class AccountHelper extends HelperBase {

  public AccountHelper(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public void wishlists() {
    menu();
    //wait.until(visibilityOfElementLocated(By.cssSelector("div[class*=wishlists]")));
    webDriver.findElement(By.cssSelector("div[class*=wishlists]")).click();
  }

  public void logout() {
    menu();
    webDriver.findElement(By.cssSelector("input[name=logout]+input")).click();
  }

  public AccountHelper menu() {
    displayDropDown(By.cssSelector("#current_account span[class=user_name_block]"), By.cssSelector(".profile-menu"), 5);
    return this;
  }


  public void loginAs(String username, String password) throws NullPointerException {
    By logInButton = findLoginButton();
    boolean devidedForm = isDevidedForm();
    webDriver.findElement(logInButton).click();
    WebElement signInForm = null;
    By usernameLocator = By.cssSelector("[name=username]");
    By passwordLocator = By.cssSelector("[name=password]");
    if (devidedForm) {
      wait.until(visibilityOfElementLocated(By.cssSelector("form.nw-signin")));
      signInForm = webDriver.findElement(By.cssSelector("form.nw-signin"));
      typeText(signInForm, usernameLocator, username);
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
      wait.until(visibilityOfElementLocated(passwordLocator));
      signInForm = webDriver.findElement(By.cssSelector("form.nw-signin"));
      typeText(signInForm, passwordLocator, password);
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
    } else {
      wait.until(visibilityOfElementLocated(By.cssSelector("form[target=log_tar]")));
      signInForm = webDriver.findElement(By.cssSelector("form[target=log_tar]"));
      typeText(signInForm, usernameLocator, username);
      typeText(signInForm, passwordLocator, password);
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
    }
  }

  private boolean isDevidedForm() {
    boolean devided_form = false;
    try{
      devided_form = webDriver.findElement(By.cssSelector("li#current_account a")).getAttribute("data-command-params").contains("redirect_uri");
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
      isDropedMenu = webDriver.findElement(By.cssSelector("#current_account a")).getAttribute("role").contains("button");
    } catch (NullPointerException e) {
      isDropedMenu = false;
    }
    if(isDropedMenu) {
      displayDropDown(By.cssSelector("#current_account a"), By.cssSelector("div.profile-menu"), 5);
      return By.cssSelector("div.profile-menu-auth-item a.js-header-loginAs-link");
    } else {
      return By.cssSelector("#current_account a");
    }
  }
}
