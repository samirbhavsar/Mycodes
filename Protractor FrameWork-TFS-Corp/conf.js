var HtmlReporter = require('protractor-html-screenshot-reporter');
exports.config = {  
  // The address of a running selenium server.
  seleniumAddress: 'http://localhost:4444/wd/hub',


  // Capabilities to be passed to the webdriver instance.
  capabilities: {
    'browserName': 'firefox'
  },

  /*capabilities: {
      'browserName': 'chrome',
      'platform': 'ANY',  
      'version': 'ANY'
    },*/

  onPrepare: function() {
    //require('jasmine-reporters');
    // Add a screenshot reporter and store screenshots to `/tmp/screnshots`:
    jasmine.getEnv().addReporter(new HtmlReporter({
      baseDirectory: 'E:/Protractor/results'
    }));
  },	
  
  
  // Spec patterns are relative to the current working directly when protractor is called.
  specs: ['halfDay.js' ],

  // Options to be passed to Jasmine-node.
  jasmineNodeOpts: {
    isVerbose:true,
    showColors: true,
    defaultTimeoutInterval: 600000
  }
};
