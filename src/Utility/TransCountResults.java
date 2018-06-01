package Utility;

public class TransCountResults {
	
	public String MasterTaxRollId;
	public String exCount;
	public String esCount;
	
	public TransCountResults(String MasterTaxRollId, String exCount, String esCount) {
		this.MasterTaxRollId = MasterTaxRollId;
		this.exCount = exCount;
		this.esCount = esCount;
	}
}