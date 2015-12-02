package Output;
import Output.Sockets;
import Output.Switches;
import Output.DipSwitch;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Output {
	
	
	public static int Nrows = 16;
	public static int Ncols = 6;
	
	public static int Ndipswitchesrows = 16;
	public static int Ndipswitchescols = 8;
	
	public static int NSwitches = 16;
	
	private static List<String> components;
	private static List<String> samenodes;
	private static List<Integer> powerinput;
	private static List<Integer> powerground;

	public Output(List<String> components, List<String> samenodes,List<Integer> powerinput, List<Integer> powerground ){
		this.components =  components;
		this.samenodes =  samenodes;
		this.powerinput = powerinput;
		this.powerground = powerground;
	}
	
	public void outputMethod() {

		//Initialize sockets and Switches
		Sockets sockets =  new Sockets(Nrows, Ncols);
		Switches switches  = new Switches(NSwitches);
		DipSwitch dipswitches = new DipSwitch(Ndipswitchesrows, Ndipswitchescols);
		
		//Assigned the Sockets Value
		for(int i = 0; i <Nrows; i++){
			for(int j= 0; j< Ncols; j++ ){
				sockets.AssignedValue(i, j, "---");
			}
		}
		
		//Assigned the Switches Value
		for(int i = 0; i < NSwitches; i++){
			switches.AssignedValue(i, "---");
		}
		
		//Assigned the DipSwitches Value
		for(int i=0; i < Ndipswitchesrows; i++){
			for(int j=0; j<Ndipswitchescols; j++){
				dipswitches.AssignedValue(i, j, "X");
			}
		}		
		
		//Define Component and AssignedValue on the Sockets;
		//int count = 0;
		for(int i = 0; i<components.size(); i++){
			String[] checkCom =components.get(i).split(" ");
			for(int k =0; k<checkCom.length; k++){
				int k2 = k;
				if(checkCom[k].equals("XOP")&& i<4){
					k2 = 5;
				}
				sockets.AssignedValue(i, k2, checkCom[k]);	
				
				/*if (checkCom.get(k).contains("R")){
					sockets.AssignedValue(count, 0, components.get(i));
					sockets.AssignedValue(count+1, 0, components.get(i));
					count=count +2;
				}
				if(checkCom.get(i).contains("C")){
					sockets.AssignedValue(count,0,components.get(i));
					sockets.AssignedValue(count+1, 0, components.get(i));
					count = count +2;
				}
				if(components.get(i).contains("LED")){
					sockets.AssignedValue(count,0,components.get(i));
					sockets.AssignedValue(count+1, 0, components.get(i));
					count = count +2;
				}*/
			}
		}
		
		//Use the List of samenodes to turn on the dipSwitches
		int[] usedDipSwitchesrow;
		for(int i =0; i< samenodes.size(); i++){
			String[] temp = samenodes.get(i).split(" ");
			for(int k =0; k <temp.length; k++){
				int temprow = Integer.parseInt(temp[k]);
				dipswitches.AssignedValue(temprow, i, "O");
			}
		}
		
		//Direct the power and GND
		for(int i =0; i< powerinput.size(); i++){
			int on = powerinput.get(i);
			switches.AssignedValue(on, "PWR");
		}
		for(int i = 0; i< powerground.size(); i++){
			int off = powerground.get(i);
			switches.AssignedValue(off, "GND");
		}
		
		
		//print the sockets value
		System.out.println("Sockets:");
		sockets.PrintValue();
		System.out.println("\n DipSwitches:");
		dipswitches.PrintValue();
		System.out.println("\n Switches:");
		switches.PrintValue();
		
		
		
		try{
			FileWriter writer = new FileWriter("output.txt",false);
			
			
			writer.write("Sockets: \r\n" );
			int count1 = 0;
			for(int i =0;i<Nrows/2; i++){
				for (int j = 0; j < Ncols ; j++) {
				    writer.write(String.format("%6s", sockets.returnValue(i, j))+ "   ");
					//writer.write(sockets.returnValue(i, j)+ "   ");
				    }
				count1 = i + 8;
				writer.write("    ");
				for (int j = 0; j < Ncols ; j++) {
					writer.write(String.format("%6s",sockets.returnValue(count1, j))+ "   ");
					//writer.write(sockets.returnValue(count1, j)+ "   ");
				}				
				writer.write("\r\n");
			}
			writer.write("Dip Switches: \r\n" );
			
			
			int count2 = 0;
			for (int i = 0; i < Ndipswitchesrows/2 ; i++) {
			    for (int j = 0; j < Ndipswitchescols; j++) {
			    	writer.write(String.format("%2s",dipswitches.ReturnValue(i, j)) + "   ");	
			    	//writer.write(dipswitches.ReturnValue(i, j) + "   ");
			    }
			    count2 = i + 8;
				writer.write("    ");
				for (int j = 0; j < Ndipswitchescols; j++) {
					writer.write(String.format("%2s",dipswitches.ReturnValue(count2, j)) + "   ");	
				}
				writer.write("\r\n");
			}

			writer.write("Switches: \r\n" );
			
			int count3 =0;
			for(int i =0; i < NSwitches/2; i++){
				writer.write(String.format("%4s",switches.ReturnValue(i)) +"   ");
				//writer.write(switches.ReturnValue(i) +"   ");
				count3 = i + 8;
				writer.write(String.format("%4s",switches.ReturnValue(count3)));
				//writer.write(switches.ReturnValue(count3));
				writer.write("\r\n");
			}
			
			writer.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}