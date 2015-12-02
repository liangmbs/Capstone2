package Output;

public class Sockets {

	
	private int Nrows;
	private int Ncols;
	private String[][] sockets;
	
	public Sockets (int Nrows, int Ncols){
		this.Nrows = Nrows;
		this.Ncols = Ncols;
		sockets = new String[Nrows][Ncols];
	}
	
	public void AssignedValue(int row, int column, String value){
		sockets[row][column] = value;
	}
	
	public String returnValue(int row, int column){
		return sockets[row][column];
	}
	
	public void PrintValue(){
		for (int i = 0; i < Nrows ; i++) {
		    for (int j = 0; j < Ncols; j++) {
		        System.out.print(sockets[i][j] + " ");
		    }
		    System.out.print("    "+"\n");
		}
	}
}
