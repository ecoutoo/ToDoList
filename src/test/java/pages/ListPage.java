package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListPage {
	
	public static final String URL = "D:/Documents/QAWork/eclipse-workspace/todolist/webapp/index.html";
	
	@FindBy(xpath = "/html/body/section[2]/div/div/div[2]/p/input")
	WebElement uNumForm;
	
	@FindBy(xpath = "/html/body/section[2]/div/div/div[3]/p/input")
	WebElement uTextForm;
	
	@FindBy(xpath = "/html/body/section[2]/div/div/div[4]/div/input")
	WebElement uIdForm;
	
	@FindBy(xpath = "/html/body/section[2]/div/div/div[2]/div/button[1]")
	WebElement uReadAllButton;
	
	@FindBy(xpath = "/html/body/section[2]/div/div/div[2]/div/button[2]")
	WebElement uCreateButton;
	
	@FindBy(xpath = "/html/body/section[2]/div/div/div[2]/div/button[3]")
	WebElement uDeleteButton;

	public void NumInfo(String num) {
		uNumForm.sendKeys(num);
	}
	
	public void IdInfo(String num) {
		uIdForm.sendKeys(num);
	}
	
	public void TextInfo(String text) {
		uTextForm.sendKeys(text);
	}
	
	public void clickReadAll() {
		uReadAllButton.click();
	}
	
	public void clickCreate() {
		uCreateButton.click();
	}
	
	public void clickDelete() {
		uDeleteButton.click();
	}
	
}
