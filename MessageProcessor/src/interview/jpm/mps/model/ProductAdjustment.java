package interview.jpm.mps.model;

public class ProductAdjustment {
	
	private String productType;
	private Operator adjustmentOperator;
	private double adjustmentValue;
	
	public ProductAdjustment(String productType, Operator adjustmentOperator, double adjustmentValue) {
		super();
		this.productType = productType;
		this.adjustmentOperator = adjustmentOperator;
		this.adjustmentValue = adjustmentValue;
	}
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
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
	
	

}
