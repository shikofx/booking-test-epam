package by.booking.pkt.recorded.appManager;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class AccountNavigator extends HelperBase{

  public AccountNavigator(WebDriver webDriver, WebDriverWait wait) {
    super(webDriver, wait);
  }



  public void hfr_goToWishlistsPage() {
    app_activateDropdownMenu(By.cssSelector("#current_account span[class=user_name_block]"), By.cssSelector(".profile-menu"));
    webDriver.findElement(By.cssSelector("div[class*=wishlists")).click();
  }

  public void hdr_login(String username, String password) throws NullPointerException{
    By logInButton = hdr_findLoginButton();
    boolean single_form = hdr_isSingleForm();
    hdr_openLoginForm(logInButton);
    WebElement signInForm = null;
    By usernameLocator = By.cssSelector("[name=username]");
    By passwordLocator = By.cssSelector("[name=password]");
    if (single_form) {
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm = webDriver.findElement(By.cssSelector("form.nw-signin"));
      signInForm.findElement(usernameLocator).click();
      signInForm.findElement(usernameLocator).clear();
      signInForm.findElement(usernameLocator).sendKeys(username);
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
      wait.until(visibilityOfElementLocated(passwordLocator));
      signInForm = webDriver.findElement(By.cssSelector("form.nw-signin"));
      signInForm.findElement(passwordLocator).click();
      signInForm.findElement(passwordLocator).clear();
      signInForm.findElement(passwordLocator).sendKeys(password);
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
    } else {
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm = webDriver.findElement(By.cssSelector("form[target=log_tar]"));
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm.findElement(usernameLocator).click();
      signInForm.findElement(usernameLocator).clear();
      signInForm.findElement(usernameLocator).sendKeys(username);

      signInForm.findElement(passwordLocator).click();
      signInForm.findElement(passwordLocator).clear();
      signInForm.findElement(passwordLocator).sendKeys(password);
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
    }
  }

  public void hdr_logOut() {
    app_activateDropdownMenu(By.cssSelector("#current_account span[class=user_name_block]"), By.cssSelector(".profile-menu"));
    webDriver.findElement(By.cssSelector("input[name=logout]+input")).click();
  }

  private boolean hdr_isSingleForm() {
    boolean single_form = false;
    try{
      single_form = webDriver.findElement(By.cssSelector("li#current_account a")).getAttribute("data-command-params").contains("redirect_uri");
    } catch (NullPointerException e) {
      single_form = false;
    }
    return single_form;
  }

  private void hdr_openLoginForm(By logInButton) {
    webDriver.findElement(logInButton).click();
  }

  @NotNull
  private By hdr_findLoginButton() {
    wait.until(presenceOfElementLocated(By.cssSelector("#current_account a")));
    boolean isDropedMenu = false;
    try{
      isDropedMenu = webDriver.findElement(By.cssSelector("#current_account a")).getAttribute("role").contains("button");
    } catch (NullPointerException e) {
      isDropedMenu = false;
    }
    if(isDropedMenu) {
      app_activateDropdownMenu(By.cssSelector("#current_account a"), By.cssSelector("div.profile-menu"));
      return By.cssSelector("div.profile-menu-auth-item a.js-header-hdr_login-link");
    } else {
      return By.cssSelector("#current_account a");
    }
  }
}
