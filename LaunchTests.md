#To launch tests use commands:
###mvn test 
tests with default profiles
###mvn test -P chrome, booking, nogrid, pkt, hotel
launching tests for hotel page on "https://booking.com/ in Google Chrome without Grid Hub whith account "pkt"

#Profile groups:
##1. Browser profiles
      chrome - Google Chrome as default
      firefox - Mozilla FireFox
      ie - Internet Explorer
      opera - Opera
      safari - Safari
      phantomjs - PhantomJS Browser (without UI)
      htmlunit - HtmlUnit

##2. Environment profiles
      booking - https://booking.com/  as default
      localhost - doesn't work (only for example)

##3. Grid launcher profiles
      nogrid - No use
      grid - using hub URL: http://localhost:4444/wd/hub/

##4. Accounts profiles
      pkt - username = pkt.autotests@gmail.com
            password = 0123456789
      user1 - doesn't work (only for example)
            username = user1
            password = user1
##5. Tests profiles
      alltests - All test items
      
      hotel    
               testAddHotelToNewList
               
      search   
               testFilterByBudget
               testFilterByStars
               testSortByDistance
               testSortByPrice
               
      profile  
               testListCreate   
               testListSendLink
                
      positive 
               testAddHotelToNewList 
               testListCreate   
               testListSendLink
               testFilterByBudget
               testFilterByStars
               testSortByDistance
               testSortByPrice
               
      smoke    
               testListSendLink 
               testListCreate
               testFilterByStars
               testSortByPrice