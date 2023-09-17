package amazon;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonCart {

	public WebDriver driver;
	public String url = "https://www.amazon.in/";

	public void invokeBrowser() {
		System.setProperty("webdriver.chrome.driver", "/home/ramkumarra/Documents/chromedriver_linux64/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);

	}

	public void getPageInfo() throws Exception {
		System.out.println("=====Window Info=====");
		System.out.println("The title is " + driver.getTitle());
		System.out.println("The URL of the webpage is " + driver.getCurrentUrl());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		Thread.sleep(3000);
	}

	public void amazonEndToEnd(String id, String passWord) throws Exception {

		WebElement accounts, signIn, email, continueBtn, password, signInBtn, searchBar, searchBtn, cart, firstProduct,
				firstRate, addToCart, cartDelete, signOut;
		WebElement nameValidator;
		String productName, productRate, cartValue, newWindowProduct, newWindowrate, newCartValue, cartProductName,
				cartProductRate, updatedCartValue;
		int cartvalueInt, productrateInt, newWindowrateInt, newcartvalueInt, cartProductrateInt, updatedCartvaluInt;

		String name = "Ram";
		String search = "shoes";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		Actions action = new Actions(driver);

		accounts = driver.findElement(By.xpath("//span[text()='Account & Lists']"));
		wait.until(ExpectedConditions.elementToBeClickable(accounts));
		action.moveToElement(accounts).perform();

		signIn = driver.findElement(By.xpath("(//span[text()='Sign in'])[1]"));
		action.moveToElement(signIn).perform();
		signIn.click();

		email = driver.findElement(By.xpath("//input[@type='email']"));
		email.sendKeys(id);

		continueBtn = driver.findElement(By.xpath("//input[@id='continue']"));
		continueBtn.click();

		password = driver.findElement(By.xpath("//input[@type='password']"));
		password.sendKeys(passWord);

		signInBtn = driver.findElement(By.xpath("//input[@id='signInSubmit']"));
		signInBtn.click();

		nameValidator = driver.findElement(By.xpath("//span[text()='Hello, Ram']"));

		if (nameValidator.toString().contains(name)) {
			System.out.println("=============Account Validation=============");
			System.out.println("The Account has been logged in successfully");
		} else {
			System.out.println("=============Account Validation=============");
			System.out.println("The Account cann't be able to login");
			System.exit(0);
		}

		searchBar = driver.findElement(By.xpath("//input[@type='text']"));
		searchBar.sendKeys(search);

		searchBtn = driver.findElement(By.xpath("//div[@class='nav-search-submit nav-sprite']"));
		searchBtn.click();

		cart = driver.findElement(By.xpath("//span[@id='nav-cart-count']"));
		cartValue = cart.getText().toString();
		cartvalueInt = Integer.parseInt(cartValue);

		productName = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base'])[1]")).getText()
				.toString() + " "
				+ driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]"))
						.getText().toString();
		productRate = driver.findElement(By.xpath("(//span[@class='a-price'])[1]")).getText().toString();
		productRate = productRate.replaceAll("[^0-9]", "");
		productrateInt = Integer.parseInt(productRate);

		firstProduct = driver
				.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]"));
		action.moveToElement(firstProduct).perform();
		firstProduct.click();

		System.out.println("=============Home Page Output=============");
		System.out.println("The First Product of your search : " + productName);
		System.out.println("The rate of the first searched product : " + productrateInt);
		System.out.println("The cart value before adding the product : " + cartvalueInt);

		switchToNewWindowClass();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		getPageInfo();

		newWindowProduct = driver.findElement(By.xpath("//span[@class='a-size-large product-title-word-break']"))
				.getText().toString();
		newWindowrate = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[6]")).getText().toString()
				.replaceAll("[^0-9]", "").replaceAll(" ", "");
		newWindowrateInt = Integer.parseInt(newWindowrate);

		System.out.println("=============New Window Output====================");
		System.out.println("The name of the product in new window : " + newWindowProduct);
		System.out.println("The rate of the product in new window : " + newWindowrateInt);

		if (productrateInt == newWindowrateInt) {
			System.out.println("================Product Validation=====================");
			System.out.println("The products in the first of homepage and selected are same");
		} else {
			System.out.println("================Product Validation=====================");
			System.out.println("The products in the first of homepage and selected are not same");
			System.exit(0);
		}

		addToCart = driver.findElement(By.xpath("//input[@value='Add to Cart']"));
		addToCart.click();

		cart = driver.findElement(By.xpath("//span[@id='nav-cart-count']"));
		newCartValue = cart.getText().toString();
		newcartvalueInt = Integer.parseInt(newCartValue);

		if (newcartvalueInt > cartvalueInt) {
			System.out.println("=============Cart Value Validation=================");
			System.out.println("The item has been added to the cart");
			System.out.println("The cart value after adding the product : " + newcartvalueInt);
		} else {
			System.out.println("=============Cart Value Validation=================");
			System.out.println("The item has not been added to the cart");
		}

		cart = driver.findElement(By.xpath("//span[@id='nav-cart-count']"));
		action.moveToElement(cart).perform();
		cart.click();

		cartProductName = driver.findElement(By.xpath("//span[@class='a-truncate-cut']")).getText().toString();
		cartProductRate = driver.findElement(By.xpath(
				"//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']"))
				.getText().toString().replaceAll(",", "").replaceAll(" ", "");
		Double price = Double.parseDouble(cartProductRate);
		cartProductrateInt = (int) Math.round(price);

		if (productrateInt == cartProductrateInt) {
			System.out.println("===========Cart Product Validation");
			System.out.println("The product added and present in the cart are same");
		} else {
			System.out.println("===========Cart Product Validation");
			System.out.println("The product added and present in the cart are not same");
		}

		cartDelete = driver.findElement(By.xpath("(//span[@class='a-declarative'])[2]"));
		action.moveToElement(cartDelete).perform();
		cartDelete.click();

		cart = driver.findElement(By.xpath("//span[@id='nav-cart-count']"));
		updatedCartValue = cart.getText().toString();
		updatedCartvaluInt = Integer.parseInt(updatedCartValue);
		System.out.println("The updated cart value : " + updatedCartvaluInt);

		if (cartvalueInt == updatedCartvaluInt) {
			System.out.println("======Cart Validation after Delete=======");
			System.out.println("The item has been deleted");
		} else {
			System.out.println("======Cart Validation after Delete========");
			System.out.println("The item is not deleted");
		}

		Set<String> windowHandles = driver.getWindowHandles();
		driver.switchTo().window(windowHandles.toArray()[0].toString());

		accounts = driver.findElement(By.xpath("//span[text()='Account & Lists']"));
		wait.until(ExpectedConditions.elementToBeClickable(accounts));
		action.moveToElement(accounts).perform();

		signOut = driver.findElement(By.xpath("//span[text()='Sign Out']"));
		action.moveToElement(signOut).perform();
		signOut.click();

		if (driver.findElement(By.xpath("//h1[@class='a-spacing-small']")).isDisplayed()) {
			System.out.println("========Sign Out Validation===========");
			System.out.println("The account has been logged out");
		} else {
			System.out.println("========Sign Out Validation===========");
			System.out.println("The account has not been logged out");
		}
	}

	public void switchToNewWindowClass() throws Exception {

		String newWindow = null;

		String currentWindow = driver.getCurrentUrl();

		Set<String> windows = driver.getWindowHandles();

		if (windows.size() > 0) {
			for (String window : windows) {
				if (!window.equals(currentWindow)) {
					newWindow = window;
				}
			}
		}
		driver.switchTo().window(newWindow);
	}

	public void closeBrowser() throws Exception {
		Thread.sleep(5000);
		driver.close();
		driver.quit();
	}

	public static void main(String[] args) throws Exception {

		AmazonCart cart = new AmazonCart();
		cart.invokeBrowser();
		cart.getPageInfo();
		cart.amazonEndToEnd("8220992808", "sonicmaster");
		cart.closeBrowser();

	}
}