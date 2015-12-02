package Output;


	
public class DipSwitch {
	private int Nrows;
	private int Ncols;
	private String[][] dipSwitches;
	
	public DipSwitch(int Nrows, int Ncols){
		this.Nrows = Nrows;
		this.Ncols = Ncols;
		dipSwitches = new String[Nrows][Ncols];	
	}
	
	public void AssignedValue(int Nrow, int Ncol, String Value){
		dipSwitches[Nrow][Ncol] = Value;
	}
	
	
	public String ReturnValue(int row, int column){
		return dipSwitches[row][column];
	}
	
	public void PrintValue(){
		
		for (int i = 0; i < Nrows ; i++) {
		    for (int j = 0; j < Ncols; j++) {
		        System.out.print(dipSwitches[i][j] + " ");
		    }
		    System.out.print("    "+"\n");
		}
	}		
}

