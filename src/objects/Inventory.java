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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Inventory {
	
	public static final String URL = "https://www.saucedemo.com/inventory.html";
	public static final String PRODUCT_SORT_XPATH = "//*[@id=\"header_container\"]/div[2]/div[2]/span/select";
	public static final String CHEAPEST_PROD_XPATH = "//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div/text()[2]";
	
	
		
	public static void lowToHighPrice(WebDriver driver) {
		WebElement sortBtn = driver.findElement(By.xpath(PRODUCT_SORT_XPATH));
		sortBtn.click();
		sortBtn.sendKeys("Price (low to high)");
		sortBtn.sendKeys(Keys.ENTER);
		
	}

}
