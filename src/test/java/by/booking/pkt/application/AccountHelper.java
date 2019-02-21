package by.booking.pkt.application;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class AccountHelper extends HelperBase {

  public AccountHelper(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
    PageFactory.initElements(webDriver, this);
  }

  @FindBy(css = "#current_account span[class=user_name_block]")
  private WebElement initCurrentAccountMenu;

  @FindBy(css = ".profile-menu")
  private WebElement navigationMenu;

  @FindBy(css = "div[class*=wishlists]")
  private WebElement toWishlists;

  @FindBy(css = "input[name=logout]+input")
  private WebElement logout;

  @FindBy(css = "#current_account a")
  private WebElement signInButton;

  @FindBy(css = "div.profile-navigationMenu-auth-item a.js-header-loginAs-link")
  private WebElement loginAsLink;

  @FindBy(css = "div.profile-navigationMenu")
  private WebElement profileMenu;

  @FindBy(css = "form.nw-signin [name=username]")
  private WebElement usernameOnPage;

  @FindBy(css = "form.nw-signin [name=password]")
  private WebElement passwordOnPage;

  @FindBy(css = "form.nw-signin [type=submit]")
  private WebElement submitOnPage;

  @FindBy(css = "form[target=log_tar] [name=username]")
  private WebElement usernameInForm;

  @FindBy(css = "form[target=log_tar] [name=password]")
  private WebElement passwordInForm;

  @FindBy(css = "form[target=log_tar] [type=submit]")
  private WebElement submitInForm;

  public AccountHelper navigationMenu() {
    displayDropDown(initCurrentAccountMenu, navigationMenu, 5);
    return this;
  }

  public void wishlists() {
    navigationMenu().toWishlists.click();
    wait.until(presenceOfAllElementsLocatedBy(By.cssSelector(".listview__title")));
  }

  public void logout() {
    navigationMenu().logout.click();
  }


  public void loginAs(String username, String password) throws NullPointerException {
    WebElement logInButton = findLoginButton();
    boolean isDevidedForm = isDevidedForm();
    logInButton.click();
    if (isDevidedForm) {
      isElementPresentAndVisible(usernameOnPage);
      inputText(usernameOnPage, username);
      submitOnPage.click();
      isElementPresentAndVisible(passwordOnPage);
      inputText(passwordOnPage, password);
      submitOnPage.click();
    } else {
      wait.until(visibilityOf(usernameInForm));
      inputText(usernameInForm, username);
      inputText(passwordInForm, password);
      submitInForm.click();
    }
  }

  private boolean isDevidedForm() {
    try {
      return signInButton.getAttribute("data-command-params").contains("redirect_uri");
    } catch (NullPointerException e) {
      return false;
    }
  }

  @NotNull
  private WebElement findLoginButton() {
    wait.until((WebDriver d) -> (signInButton));
    try {
      if (signInButton.getAttribute("role").contains("button")) {
        displayDropDown(signInButton, profileMenu, 5);
        return loginAsLink;
      }
    } catch (NullPointerException e) {
    }
    return signInButton;
  }
}
