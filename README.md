# jpm-messageprocesser
This is a standalone program to process sales message recieved from a thirdparty.

Requirement Understanding:
	> Develop application which can process 50 messages in one go
	> Messages could of 3 types
		. Having sale detail with unit price for the product
		. Having sale detail with unit price and number of occurences of this sale for the product
		. Having adjustment done on the sale for the given product class
	> After every 10th message it should print number of sales done along with total amount of sales done for each Product class
	> After 50th message it should print all the adjustments performed
	> Application should not process anything after it has recieved 50th message
	
Assumptions:
	> One message will have only one of the said message types
	> TotalSaleValue of a Product = Sum of (NumberOfOccurence * UnitPrice)
	
Input Data:
	> Have used file based input to simulate message retreival
	> Below format is used to parse the message, here depending on type of message relevant fields will be populated
		. Type,Product,Price,Currency,NumberOfOccurences,AdjustmentOperator,AdjustmentValue

Execution:
	> Run MessageHandler class as a Java Application
	> You can modify sample_message.txt to provide input data in below format
		. Type,Product,Price,Currency,NumberOfOccurences,AdjustmentOperator,AdjustmentValue
	> Junits can be run to test certain positive as well as negative scenarios, Ensure Junit jars is in classpath. The same has been provided in lib folder
	
