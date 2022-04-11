# ad-api-test

## Steps to run the project are below

1. install maven(3.8.4 or latest)
2. clone repo "https://github.com/Ankitsdet/ad-api-test.git"
3. go to project directory through terminal
4. run mvn test -DsuiteXmlFile=testng.xml which should produce a similar results as below in terminal.

This is response  :oYKIDxDHuRqPT4nG
This is unique id  :VX16Ey5Hq3qKxi9j
This is update response  :1
This is unique id  :DmZ1upw9SG7N0egr
This is update by street  :1
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.532 s - in TestSuite

5. once tests are finished there is a report generated in com.ad.api.test\target\surefire-reports\emailable-report.html

Observations:

1. No server side validations found that's why a test case is failing since it expects a 400 with error message but gets 200 instead.
2. No check for duplicate ads on server end.
3. The validation on UI i.e. Alphabets and numerals only, in ad name field does not hold true when api is called.
4. The update request could have been PATCH also, since we have to send all dataset again with PUT request.