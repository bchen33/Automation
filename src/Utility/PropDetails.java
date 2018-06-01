package Utility;

public class PropDetails {
	
	public String tcID;
	public String field;
	public String esValue;
	public String uiValue;
	public double esDblValue;
	public double uiDblValue;
	public boolean esBoolValue;
	public boolean uiBoolValue;
	
	public PropDetails(String tcID, String field, String esValue, String uiValue) {
		this.tcID = tcID;
		this.field = field;
		this.esValue = esValue;
		this.uiValue = uiValue;
	}
	
	public PropDetails(String tcID, String field, double esDblValue, double uiDblValue) {
		this.tcID = tcID;
		this.field = field;
		this.esDblValue = esDblValue;
		this.uiDblValue = uiDblValue;
	}
	
	public PropDetails(String tcID, String field, boolean esBoolValue, boolean uiBoolValue) {
		this.tcID = tcID;
		this.field = field;
		this.esBoolValue = esBoolValue;
		this.uiBoolValue = uiBoolValue;
	}
}