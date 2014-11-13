describe('Point2Point Booking tests:', function () {

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

  it('Point2Point Booking Test', function() {
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
    ptor.findElement(by.cssContainingText('option', 'Bangalore')).click();
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("myBookType")).click();
    ptor.sleep(2000); 	
    ptor.findElement(by.cssContainingText('option', 'Point2Point')).click();
    
    ptor.sleep(2000); 
    ptor.findElement(protractor.By.id("guestName")).sendKeys("Sabya");
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("guestNumber")).sendKeys("8892566529");
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("guestEmail")).sendKeys("test@mail.com");
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("pickLocation")).sendKeys("J P Nagar, Bangalore, Karnataka, India");
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("dropLocation")).sendKeys("Majestic Bus Station, Majestic, Bangalore, Karnataka, India");
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
    ptor.findElement(by.cssContainingText('label', 'Cash')).click();
    ptor.sleep(2000);
 //   ptor.findElement(protractor.By.model("radioModel")).click();
    ptor.findElement(by.cssContainingText('button', 'Past')).click();	
    ptor.sleep(2000); 	
    ptor.findElement(by.cssContainingText('a', 'PREPAID')).click();
    ptor.sleep(2000); 	
    ptor.findElement(by.cssContainingText('button', 'CONFIRM')).click();
    ptor.sleep(2000);

    expect(ptor.findElement(by.xpath("//div[text()='Booking Confirmed']")).getText(),'Booking Confirmed');
    ptor.sleep(2000);
     ptor.findElement(by.cssContainingText('button', 'OK')).click();
    // Ptor.quit();	  /// ptor.findElement(by.xpath())
	//Booking Failed !
	//expect(alertDialog.getText()).toEqual("Booking Failed !");
	
//    ptor.findElement(protractor.By.model("searchBooking")).sendKeys("Audi");
    ptor.findElement(by.cssContainingText('h4','BOOKING SUMMARY')).then(function(message){
      message.getText().then(function(text){
        console.log('Booking confirmed: '+text);
        expect(text).toContain('BOOKING SUMMARY');  
      });
    }); 

  });
});
