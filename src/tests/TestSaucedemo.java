package tests;
/*Zadatak
Napisati program na programskom jeziku Java koji sluzi za testiranje sajta https://www.saucedemo.com/
Pokusati logovanje prvo sa nevalidnim, a potom sa validnim kredencijalima i proveriti da li se nakon toga korisnik nalazi na odgovarajucoj staranici.
Na stranici https://www.saucedemo.com/inventory.html sortirati proizvode po ceni (od najnize ka najvisoj).
Proveriti da li je sortiranje ispravno. Program pisati postujuci page object model.
Koristiti Test NG za proveru ispravnosti testova.
Kredencijale procitati iz datoteke data.xlsx.
Resenje okaciti na GitHub nalog i svoj folder na google drive-u (napraviti folder pod nazivom kontrolni3).*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.Home;
import objects.Inventory;

public class TestSaucedemo {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test(priority = 1)
	public void testInvalidUsernameLogIn() {
		File f = new File("data.xls");

		try {
			InputStream in = new FileInputStream(f);
			HSSFWorkbook wb = new HSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();

			Row row = sheet.getRow(0);

			Cell c0 = row.getCell(0);
			Cell c1 = row.getCell(1);

			String invalidUsername = c0.toString();
			String password = c1.toString();

			driver.navigate().to(Home.URL);

			Home.inputInvalidUsername(driver, invalidUsername);
			Home.inputValidPassword(driver, password);
			Home.loginBtn(driver);

			String currentUrl = driver.getCurrentUrl();
			String expectedUrl = Home.URL;

			sa.assertEquals(currentUrl, expectedUrl);

			sa.assertAll();
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	@Test(priority = 2)
	public void testInvalidPassLogIn() {
		File f = new File("data.xls");

		try {
			InputStream in = new FileInputStream(f);
			HSSFWorkbook wb = new HSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();

			Row row = sheet.getRow(1);

			Cell c0 = row.getCell(0);
			Cell c1 = row.getCell(1);

			String username = c0.toString();
			String invalidPassword = c1.toString();

			driver.navigate().to(Home.URL);

			Home.inputValidUsername(driver, username);
			Home.inputInvalidPassword(driver, invalidPassword);
			Home.loginBtn(driver);

			String currentUrl = driver.getCurrentUrl();
			String expectedUrl = Home.URL;

			sa.assertEquals(currentUrl, expectedUrl);

			sa.assertAll();
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void testInvalidPassUsernameLogIn() {
		File f = new File("data.xls");

		try {
			InputStream in = new FileInputStream(f);
			HSSFWorkbook wb = new HSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();

			Row row = sheet.getRow(2);

			Cell c0 = row.getCell(0);
			Cell c1 = row.getCell(1);

			String invalidUsername = c0.toString();
			String invalidPassword = c1.toString();

			driver.navigate().to(Home.URL);

			Home.inputValidUsername(driver, invalidUsername);
			Home.inputInvalidPassword(driver, invalidPassword);
			Home.loginBtn(driver);

			String currentUrl = driver.getCurrentUrl();
			String expectedUrl = Home.URL;

			sa.assertEquals(currentUrl, expectedUrl);

			sa.assertAll();
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	@Test(priority = 4)
	public void testValidLogIn() {
		File f = new File("data.xls");

		try {
			InputStream in = new FileInputStream(f);
			HSSFWorkbook wb = new HSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();

			int lastRow = sheet.getLastRowNum();

			for (int i = 3; i <= lastRow; i++) {

				Row row = sheet.getRow(i);

				Cell c0 = row.getCell(0);
				Cell c1 = row.getCell(1);

				String username = c0.toString();
				String password = c1.toString();

				driver.navigate().to(Home.URL);
				driver.manage().window().maximize();

				Home.inputValidUsername(driver, username);
				Home.inputValidPassword(driver, password);
				Home.loginBtn(driver);

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				String currentUrl = driver.getCurrentUrl();
				String expectedUrl = Inventory.URL;

				if (i == 4) {

					sa.assertEquals(currentUrl, Home.URL);
				} else {
					sa.assertEquals(currentUrl, expectedUrl);
				}

			}
			sa.assertAll();

			wb.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 5)
	public void testInventoryPriceSort() {
		File f = new File("data.xls");

		try {
			InputStream in = new FileInputStream(f);
			HSSFWorkbook wb = new HSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();

			Row row = sheet.getRow(3);

			Cell c0 = row.getCell(0);
			Cell c1 = row.getCell(1);

			String username = c0.toString();
			String password = c1.toString();

			driver.navigate().to(Home.URL);
			driver.manage().window().maximize();

			Home.inputValidUsername(driver, username);
			Home.inputValidPassword(driver, password);
			Home.loginBtn(driver);

			Inventory.lowToHighPrice(driver);

			List<WebElement> itemPrices = driver.findElements(By.className("inventory_item_price"));

			String actualResult1 = itemPrices.get(0).getText();
			String expectedLowestPrice = "$7.99";

			String actualResult2 = itemPrices.get(1).getText();
			String expectedPrice2 = "$9.99";

			String actualResult3 = itemPrices.get(2).getText();
			String expectedPrice3 = "$15.99";

			String actualResult4 = itemPrices.get(3).getText();
			String expectedPrice4 = "$15.99";

			String actualResult5 = itemPrices.get(4).getText();
			String expectedPrice5 = "$29.99";

			String actualResult6 = itemPrices.get(5).getText();
			String expectedHighestPrice = "$49.99";

			sa.assertEquals(actualResult1, expectedLowestPrice);
			sa.assertEquals(actualResult2, expectedPrice2);
			sa.assertEquals(actualResult3, expectedPrice3);
			sa.assertEquals(actualResult4, expectedPrice4);
			sa.assertEquals(actualResult5, expectedPrice5);
			sa.assertEquals(actualResult6, expectedHighestPrice);

			sa.assertAll();

			wb.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
