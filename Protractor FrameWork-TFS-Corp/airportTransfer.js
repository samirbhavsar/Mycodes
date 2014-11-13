describe('Booking Type Airport Transfer (Flat) Test:', function () {

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
    ptor.findElement(by.cssContainingText('option', 'Bangalore')).click();
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("myBookType")).click();
    ptor.sleep(2000); 	
    ptor.findElement(by.cssContainingText('option', 'Airport Transfer (Flat)')).click();
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.id("guestName")).sendKeys("San");
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("guestNumber")).sendKeys("999999999");
    ptor.sleep(2000); 	
    ptor.findElement(protractor.By.model("guestEmail")).sendKeys("test@mail.com");
    ptor.sleep(2000); 	
    ptor.findElement(by.cssContainingText('option', 'From')).click();
    ptor.sleep(2000); 
    ptor.findElement(by.cssContainingText('option', 'Bangalore Airport')).click();
    ptor.sleep(2000);
    ptor.findElement(protractor.By.model("dropLocation")).sendKeys("Majestic Bus Station, Majestic, Bangalore, Karnataka, India");
    ptor.sleep(2000); 
   // ptor.findElement(by.cssContainingText('span', '20')).click();
    //ptor.sleep(2000);	
    //ptor.findElement(protractor.By.model("hours")).sendKeys("11");
    //ptor.sleep(2000);
    //ptor.findElement(by.cssContainingText('button', 'PM')).click();	
    //ptor.sleep(2000); 	
	ptor.findElement(protractor.By.xpath("//div/table/tbody/tr[@class='text-center'][1]/td[1]/a")).click();
	ptor.sleep(2000);
    ptor.findElement(protractor.By.model("userInstructions")).sendKeys("Instructions");
    ptor.sleep(2000);
    ptor.findElement(by.cssContainingText('option', 'Sedan')).click();
    ptor.sleep(2000); 

//.btn-info
   // ptor.findElement(protractor.By.model("dt")).sendKeys("20-August-2014");

    //ptor.findElement(protractor.By.model("paymentMode")).click();
    ptor.findElement(by.cssContainingText('label', 'Cash')).click();
    ptor.sleep(2000);
 //   ptor.findElement(protractor.By.model("radioModel")).click();
    ptor.findElement(by.cssContainingText('button', 'Past')).click();	
    ptor.sleep(2000); 	
    ptor.findElement(by.cssContainingText('a', 'PREPAID')).click();
    ptor.sleep(2000); 	
    ptor.findElement(by.cssContainingText('button', 'CONFIRM')).click();
    ptor.sleep(5000);
    expect(ptor.findElement(by.xpath("//div[@class='modal-dialog']/div[1]/div[1]/h3")).getText(),'Booking Confirmed');
    ptor.sleep(2000);
     ptor.findElement(by.cssContainingText('button', 'OK')).click();
	ptor.findElement(by.cssContainingText('h4','BOOKING SUMMARY')).then(function(message){
      message.getText().then(function(text){
        console.log('Booking confirmed: '+text);
        expect(text).toContain('BOOKING SUMMARY');  
      });
    }); 
/* var handlePromise = ptor.getAllWindowHandles();
 var handles = handlePromise.then(function (handles) {
                popUpHandle = handles[0];
                var handle = browser.switchTo().window(popUpHandle);
                handle = browser.getWindowHandle();
                expect(handle).toEqual(popUpHandle);
                browser.driver.executeScript('window.focus();');
            }); */
    //ptor.getAllWindowHandles().then(function (handles) {
   // ptor.switchTo().window(handles[0]).then(function(alert) {
   //   expect(alert.getText()).toEqual("Congratulations! Your Booking Successful!");	
    //      });
    //    });
   // ptor.driver.switchTo().alert().then(function(alert) {
  //  expect(alert.getText()).toEqual("Congratulations! Your Booking Successful!");	
  //  }, function(error) {
        // Here we can handle the exception if we want
  //  });
   // var alertDialog = ptor.switchTo().alert();
   // expect(alertDialog.getText()).toEqual("Congratulations! Your Booking Successful!");	
//    ptor.findElement(protractor.By.model("searchBooking")).sendKeys("Audi");
    //ptor.findElement(by.cssContainingText('h4','BOOKING SUMMARY')).then(function(message){
    //  message.getText().then(function(text){
    //    console.log('Booking confirmed: '+text);
    //    expect(text).toContain('BOOKING SUMMARY');  
    //  });
   // }); 

  });
});
