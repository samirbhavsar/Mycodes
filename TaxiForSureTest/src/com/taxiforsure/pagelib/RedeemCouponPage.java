package com.taxiforsure.pagelib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RedeemCouponPage {
	
	@FindBy(linkText="Redeem Coupon")
	private WebElement redeemCouponLink;
	
	@FindBy(id="id_mob_number")
	private WebElement mobileNo;
	
	@FindBy(id="id_tfs_code")
	private WebElement taxiforSureCode;
	
	@FindBy(xpath="//input[@type='button' and @class='submit']")
	private WebElement vouvherCodeBtn;
	
	
	public WebElement redeemCouponLink(){
		return redeemCouponLink;
	}
	
	public WebElement mobileNo(){
		return mobileNo;
	}
	public WebElement taxiforSureCode(){
		return taxiforSureCode;
	}
	public WebElement vouvherCodeBtn(){
		return vouvherCodeBtn;
	}
}
