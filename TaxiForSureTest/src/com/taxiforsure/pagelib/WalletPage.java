package com.taxiforsure.pagelib;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WalletPage {
	
	@FindBy(xpath="//div[@class='balanceDetails']/descendant::button")
	public WebElement btn_Recharge;
	
	@FindBy(id="wallet-bal")
	public WebElement waletBalance;
	
	@FindBy(id="refuel-wallet-amt")
	public WebElement txt_refuelAmt;
	
	@FindBy(className="refillBtn")
	public WebElement btn_Refill;
	
	@FindBy(xpath="//li[contains(@class,'DC')]")
	public WebElement option_Debitcard;
	
	
	@FindBy(css="div.walletBtn > p")
	public WebElement waletButton;
	
	@FindBy(id="ccname")
	public WebElement cardName;
	

	@FindBy(id="ccnum")
	public WebElement cardNumber;

	@FindBy(xpath="//div[@class='forExpiryMonth rel']/input[@class='expiryMonth']")
	public WebElement month_Expire;
	
	@FindBy(xpath="//div[@class='forExpiryMonth rel']/ul/li")
	public List<WebElement> months;
	

	@FindBy(id="ccexpyr")
	public WebElement expireYear;
	

	@FindBy(xpath="//div[@class='forExpiryYear rel']/ul/li")
	public List<WebElement> years;
	
	@FindBy(id="ccvv")
	public WebElement cvvNumber;
	
	/*@FindBy(id="debit_card_select")
	public WebElement select_DebitCard;
	

	@FindBy(id="dcard_number")
	public WebElement debitcard_number;

	@FindBy(id="dname_on_card")
	public WebElement debit_CardName;
	
	@FindBy(id="dcvv_number")
	public WebElement debit_CvvNumber;
	
	@FindBy(id="dexpiry_date_month")
	public WebElement dCard_ExpireMonth;
	

	@FindBy(id="dexpiry_date_year")
	public WebElement dCard_ExpireYear;*/
	
	@FindBy(id="submitPayment")
	public WebElement btn_Pay;
	
	@FindBy(xpath="//a[text()='OK']")
	public WebElement btn_OK;
	
	@FindBy(xpath="//div[@class='paymentStatus']/descendant::h3")
	public WebElement paidBalance;
	
	@FindBy(linkText="Refills")
	public WebElement creditLink;
	
	@FindBy(xpath="//tbody[@class='txntablecr']/tr[1]/td[3]")
	public WebElement currentRechargedAmt;
	
	
	
	
	
	
	
}
