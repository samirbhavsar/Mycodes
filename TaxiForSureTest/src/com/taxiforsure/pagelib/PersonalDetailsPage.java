package com.taxiforsure.pagelib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalDetailsPage {

	@FindBy(linkText = "Personal Details")
	private WebElement personalDetailLink;

	@FindBy(id = "id_name")
	private WebElement nameTxt;

	@FindBy(id = "id_birthday")
	private WebElement dobCal;

	@FindBy(xpath = "//select[@class='picker__select--month']")
	private WebElement selectMonthCal;

	@FindBy(xpath = "//div[@class='control-group radioWap']/div/label")
	private List<WebElement> genderList;

	@FindBy(id = "id_wedding")
	private WebElement weddingCal;

	@FindBy(xpath = "//input[@type='submit' or @class='update js_prof_sub']")
	private WebElement subMitBtn;

	@FindBy(xpath = "//select[@class='picker__select--year']")
	private WebElement selectYearCal;

	public WebElement nameTxt() {
		return nameTxt;
	}

	public WebElement dpbCalendar() {
		return dobCal;
	}

	public WebElement weddingCalendar() {
		return weddingCal;
	}

	public List<WebElement> genderList() {
		return genderList;
	}

	public WebElement submitBtn() {
		return subMitBtn;
	}

	public WebElement selectMonthList() {
		return selectMonthCal;
	}

	public WebElement selectYearList() {
		return selectYearCal;
	}
}
