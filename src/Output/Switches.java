package Output;

public class Switches {
	private int NSwitches;
	private String[] switches;
	

	public Switches(int NSwitches){
		this.NSwitches = NSwitches;
		switches = new String[NSwitches];
	}
	
	public void AssignedValue(int spot, String value){
		switches[spot] = value;
	}
	
	public String ReturnValue(int spot){
		return switches[spot];
	}
	
	public void PrintValue(){
		for(int i = 0; i< NSwitches; i++){
			System.out.println(switches[i]+ "    ");
		}
		
	}
}
