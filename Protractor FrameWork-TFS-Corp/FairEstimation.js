describe('FairEstimationTest:', function () {

  var ptor = protractor.getInstance();

  //ptor.waitForAngular();
  //ptor.get('http://localhost:8000/static/index.html#/');
   // ptor.get('http://46.4.64.209:8082/static/index.html#/');
     ptor.get('http://corp.aws.rtfs.in/#/');
  ptor.sleep(2000); 
 browser.driver.manage().window().maximize();
  /*it('Page Title test', function(){
    ptor.getTitle().then(function(title) {
      console.log('Page title= '+title);
      expect(title).toEqual('Corporate Login');
    });
  });*/

  it('First Test', function() {
    browser.ignoreSynchronization = true;
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.id("exampleInputEmail1")).sendKeys("Geron");
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.id("exampleInputPassword1")).sendKeys("tfs123");
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.tagName("button")).click();
    ptor.sleep(2000); 	
   // ptor.findElement(protractor.By.model("myCity")).click();
    ptor.sleep(2000); 	
    ptor.findElement(by.cssContainingText('option', 'Hyderabad')).click();
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("myBookType")).click();
    ptor.sleep(2000); 	
    ptor.findElement(by.cssContainingText('option', '3hrs 30Kms')).click();
    
    ptor.sleep(2000); 
    ptor.findElement(protractor.By.id("guestName")).sendKeys("Sabya");
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("guestNumber")).sendKeys("999999999");
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("guestEmail")).sendKeys("test@mail.com");
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("pickLocation")).sendKeys("Hyder Nagar, Hyderabad, Telangana, India");
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("dropLocation")).sendKeys("Hyderguda, Hyderabad, Telangana, India");
    ptor.sleep(2000); 
//.btn-info
   // ptor.findElement(protractor.By.model("dt")).sendKeys("20-August-2014");
    //ptor.findElement(by.cssContainingText('span', '13')).click();
    //ptor.sleep(2000);	
	//ptor.findElement(protractor.By.model("hours")).clear();
   // ptor.findElement(protractor.By.model("hours")).sendKeys("07");
    ptor.findElement(protractor.By.xpath("//div/table/tbody/tr[@class='text-center'][1]/td[1]/a")).click();
	ptor.sleep(2000); 	
    ptor.findElement(by.cssContainingText('option', 'Sedan')).click();
	ptor.sleep(2000); 
    ptor.findElement(protractor.By.model("userInstructions")).sendKeys("Instructions");
    ptor.sleep(2000);
    //ptor.findElement(protractor.By.model("paymentMode")).click();
    ptor.findElement(by.cssContainingText('label', 'Prepaid')).click();
    ptor.sleep(2000);
 //   ptor.findElement(protractor.By.model("radioModel")).click();
    ptor.findElement(by.cssContainingText('button', 'Past')).click();	
    ptor.sleep(2000); 	
    ptor.findElement(by.cssContainingText('a', 'PREPAID')).click();
     
	ptor.sleep(2000);
		ptor.actions().mouseMove(ptor.findElement(protractor.By.xpath("//*[@id='newForm']/div[6]/div/button[1]"))). perform();
		ptor.sleep(2000);
		//var prepaid_bal=ptor.findElement(protractor.By.xpath("//label[text()='Prepaid Balance']/following-sibling::h4")).getText();
		
		/* var size=prepaid_bal.length;
		var actual_bal=prepaid_bal.toString().substr(0,size-4);
		
		console.log(actual_bal);
		var esimated_bal=ptor.findElement(protractor.By.xpath("//*[@id='newForm']/div[6]/div/button[1]")).getAttribute("popover")
		.then (function(text){console.log(text); }); */
		ptor.findElement(by.xpath("//label[text()='Prepaid Balance']/following-sibling::h4")).getText()
		.then( function(prepaid_bal) {
	console.log(prepaid_bal);
  ptor.findElement(by.xpath("//*[@id='newForm']/div[6]/div/button[1]")).getAttribute("popover").then( function(esimated_bal){
  console.log(esimated_bal);
    if(prepaid_bal.split(" ",1)<esimated_bal){
	console.log('Your prepaid balance is:'+prepaid_bal+' and esimated balance is  '+esimated_bal+' Please recharge to use prepaid mode');
	return false;
	//ptor.sleep(2000);
	ptor.close();
	}
  });

});
		
		
		
    ptor.findElement(by.cssContainingText('button', 'CONFIRM')).click();
    ptor.sleep(2000);
    expect(ptor.findElement(by.xpath("//div[@class='modal-dialog']/div[1]/div[1]/h3")).getText(),'Booking Confirmed');
    console.log('Booking confirmed');
    ptor.sleep(2000);
     ptor.findElement(by.cssContainingText('button', 'OK')).click();
	//var alertDialog = ptor.switchTo().alert();
	//Booking Failed !
	//expect(alertDialog.getText()).toEqual("Booking Failed !");
	//alertDialog.accept();
//    ptor.findElement(protractor.By.model("searchBooking")).sendKeys("Audi");
     ptor.findElement(by.cssContainingText('h4','BOOKING SUMMARY')).then(function(message){
      message.getText().then(function(text){
        //console.log('Booking confirmed: '+text);
        expect(text).toContain('BOOKING SUMMARY');  
		// ptor.close();
      });
    }); 

  });
 
});
