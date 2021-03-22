package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListPageForms {
	
	@FindBy(xpath = "/html/body/section[2]/div/div/div[2]/p/input")
	WebElement uNumForm;
	
	@FindBy(xpath = "/html/body/section[2]/div/div/div[3]/p/input")
	WebElement uTextForm;

	public void NumInfo(String num) {
		uNumForm.sendKeys(num);
		uNumForm.submit();
	}
	
	public void TextInfo(String text) {
		uTextForm.sendKeys(text);
		uTextForm.submit();
	}
	
}
