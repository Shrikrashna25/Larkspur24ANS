Feature:this feature explain parameterization in count 

Scenario:Parameterizing a single step
Given I have number 5 and 6 
When I add them
Then Verify if result is prime 



Scenario:Passing list as arguments
Given I have following fruits:
|Mango|6|
|Banana|12|
|Apple|4|
|Pineapple|20|
|Strawberry|20|

Then print them one by one

Scenario Outline:
Given I have <pincode>
Then print the pincode 
Examples:
|pincode|
|411014|
|411013|
|411234|