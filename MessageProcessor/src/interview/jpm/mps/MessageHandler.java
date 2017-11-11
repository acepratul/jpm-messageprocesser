package interview.jpm.mps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MessageHandler {

	public static void main(String[] args) {
		BufferedReader reader = null;
		int messageCounter = 0;
		SaleRegister saleRegister = new SaleRegister();
		try {
			reader = new BufferedReader(new FileReader("sale_message.txt"));
			String saleMessage = null;
			while ((saleMessage = reader.readLine()) != null) {
				messageCounter++;
				MessageProcessor messageProcessor = new SaleMessageProcessor(saleRegister);
				boolean isMessageProcessed = messageProcessor.process(saleMessage);
				if (!isMessageProcessed) {
					System.out.println(String.format("Message [%s] was not processed, fix the message and retry"));
				}
				
				if((messageCounter%10)==0) {
					saleRegister.logProcessedProducts();
				}
				
				if(messageCounter == 50) {
					System.out.println("Processed 50 messages and applied below adjustments, No more messages will be processed as threshold is reached");
					saleRegister.logProductAdjustments();
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println("Could not close the resource");
			}
		}

	}

}
