package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListPageButtons {
	
	@FindBy(xpath = "/html/body/section[2]/div/div/div[2]/div/button[1]")
	WebElement uReadAllButton;

	public void clickReadAll() {
		uReadAllButton.click();
	}
}
