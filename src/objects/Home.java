package objects;
/*Zadatak
Napisati program na programskom jeziku Java koji sluzi za testiranje sajta https://www.saucedemo.com/
Pokusati logovanje prvo sa nevalidnim, a potom sa validnim kredencijalima i proveriti da li se nakon toga korisnik nalazi na odgovarajucoj staranici.
Na stranici https://www.saucedemo.com/inventory.html sortirati proizvode po ceni (od najnize ka najvisoj).
Proveriti da li je sortiranje ispravno. Program pisati postujuci page object model.
Koristiti Test NG za proveru ispravnosti testova.
Kredencijale procitati iz datoteke data.xlsx.
Resenje okaciti na GitHub nalog i svoj folder na google drive-u (napraviti folder pod nazivom kontrolni3).*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {
	
	public static final String URL = "https://www.saucedemo.com/";
	public static final String TXT_USERNAME_XPATH = "//*[@id=\"user-name\"]";
	public static final String TXT_PASSWORD_XPATH = "//*[@id=\"password\"]";
	public static final String LOGIN_BTN_XPATH = "//*[@id=\"login-button\"]";
	
	
	public static void inputInvalidUsername (WebDriver driver, String invalidUsername) {
		driver.findElement(By.xpath(TXT_USERNAME_XPATH)).sendKeys(invalidUsername);
	}
	
	public static void inputValidUsername (WebDriver driver, String validUsername) {
		driver.findElement(By.xpath(TXT_USERNAME_XPATH)).sendKeys(validUsername);
	}
	
	public static void inputInvalidPassword (WebDriver driver, String invalidPassword) {
		driver.findElement(By.xpath(TXT_PASSWORD_XPATH)).sendKeys(invalidPassword);
	}
	
	public static void inputValidPassword (WebDriver driver, String validPassword) {
		driver.findElement(By.xpath(TXT_PASSWORD_XPATH)).sendKeys(validPassword);
	}
	
	public static void loginBtn(WebDriver driver) {
		driver.findElement(By.name("login-button")).click();
	}
		
		
		
	}


