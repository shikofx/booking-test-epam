#To launch data generator use commands:
    java -cp target/test-classes by.booking.pkt.data.DataGenerators.DataGenerator -pl New_York -c 5 -fn data-for-search -ff xml -t search
##Parameters
#####{-pl} Town or place to travel
#####{-c}  Count of test data
#####{-fn} Data file name:
    For  ex.: 'data-for-search'
#####{-ff} Data file format. 
    'xml'   - to generate file with .xml
    'json'  - to generate file with .json 
#####{-t} Target - For what do you need these data: 
     's': for testing of search page
     'w': for testing of wishlists page
     'h': for testing of hotel page