package interview.jpm.mps.model;

public class SaleMessage {
	
	private String productName;
	private double price = 0.0;
	private int numberOfOccurences = 1; 
	private boolean hasAdjustments = false;
	private Operator adjustmentOperator;
	private double adjustmentValue = 0.0;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNumberOfOccurences() {
		return numberOfOccurences;
	}
	public void setNumberOfOccurences(int numberOfOccurences) {
		this.numberOfOccurences = numberOfOccurences;
	}
	public boolean isHasAdjustments() {
		return hasAdjustments;
	}
	public void setHasAdjustments(boolean hasAdjustments) {
		this.hasAdjustments = hasAdjustments;
	}
	public Operator getAdjustmentOperator() {
		return adjustmentOperator;
	}
	public void setAdjustmentOperator(Operator adjustmentOperator) {
		this.adjustmentOperator = adjustmentOperator;
	}
	public double getAdjustmentValue() {
		return adjustmentValue;
	}
	public void setAdjustmentValue(double adjustmentValue) {
		this.adjustmentValue = adjustmentValue;
	}
	
	@Override
	public String toString() {
		return "SaleMessage [" + (productName != null ? "productName=" + productName + ", " : "") + "price=" + price
				+ ", numberOfOccurences=" + numberOfOccurences + ", hasAdjustments=" + hasAdjustments + ", "
				+ (adjustmentOperator != null ? "adjustmentOperator=" + adjustmentOperator + ", " : "")
				+ "adjustmentValue=" + adjustmentValue + "]";
	}
	
	

}
