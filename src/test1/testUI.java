package test1;
import Output.Output;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.List;



public final class testUI extends JFrame {

    public static String[] strrr;

	public static void main(String[] args) {
        testUI tp = new testUI();
        tp.setVisible(true);
    }
    
    JLabel label = new JLabel("Enter your spice file blow");
    JLabel label2 = new JLabel("Status");
    
    public testUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
     
        
        
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        
        //JPanel panel = new JPanel("Simulate");
        JButton button1 = new JButton("Simulate");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	String teststr = textArea.getText();
            	strrr = teststr.split("\\n");
            	//String testttt = new String("1"+" ");
            	//testttt = testttt+"2"+" ";
            	//int size = strrr.length;
            	String[] str = strrr;
            	int i = 0;
            	int k = 0;
            	int p = 0;
            	int size = str.length;
            	//componts contains all componts' name
            	List<String> componts = new ArrayList<String>();
            	List<String> rows = new ArrayList<String>();
            	List<Integer> powerinput = new ArrayList<Integer>();
            	List<Integer> powergnd = new ArrayList<Integer>();
            	String[] Rrows = new String[16];
            	int rcount = 0;
            	String[] Lrows = new String[16];
            	boolean isxop = false;
            	for(int ssi=0; ssi<size;ssi++){
            		if(str[ssi].contains("XOP")){
            			String[] compname = str[ssi].split(" ");
            			componts.add(compname[0]);
            			rcount++;
            			componts.add(compname[0]);
            			rcount++;
            			componts.add(compname[0]);
            			rcount++;
            			componts.add(compname[0]);
            			rcount++;
            			componts.add(compname[0]);
            			componts.add(compname[0]);
            			componts.add(compname[0]);
            			componts.add(compname[0]);
            			rows.add("null1");
            			rows.add(compname[2]);
            			rows.add(compname[1]);
            			rows.add("input-");
            			rows.add("null2");
            			rows.add("input+");
            			rows.add(compname[3]);
            			rows.add("null3");
            			isxop = true;
            		}
            	}
            	while(i<size)
            	{
            		
            		if(str[i].contains("C"))
            		{
            			String[] compname = str[i].split(" ");
            			componts.add(rcount,compname[0]);
            			rows.add(rcount,compname[1]);
            			if(isxop==true&&rcount==7){
            				rcount = 12;
            			}
            			else{rcount++;}
            			componts.add(rcount,compname[0]);
            			rows.add(rcount,compname[2]);
            			if(isxop==true&&rcount==7){
            				rcount = 12;
            			}
            			else{rcount++;}
            		}
            		if(str[i].contains("PHDID"))
            		{
            			String[] compname = str[i].split(" ");
            			//componts.add(rcount,compname[0] + "DA");
            			componts.add(rcount,"DK");
            			rows.add(rcount,compname[1]);
            			if(isxop==true&&rcount==7){
            				rcount = 12;
            			}
            			else{rcount++;}
            			//componts.add(rcount,compname[0]+"DK");
            			componts.add(rcount,"DA");
            			rows.add(rcount,compname[2]);
            			if(isxop==true&&rcount==7){
            				rcount = 12;
            			}
            			else{rcount++;}
            		}
            		if(str[i].contains("R"))
            		{
            			int trick = 3;
            			String[] compname = str[i].split(" ");
            			for(int ri = 0; ri < 16; ri++){
            				if(compname[1].equals(Rrows[ri])){
            					String tempcomp = new String(componts.get(ri));
            					tempcomp = tempcomp+" "+compname[0];
            					componts.set(ri, tempcomp);
            					trick = trick+1;
            				}
            				if(compname[2].equals(Rrows[ri])){
            					String tempcomp = new String(componts.get(ri));
            					tempcomp = tempcomp+" "+compname[0];
            					componts.set(ri, tempcomp);
            					trick = trick+2;
            				}
            				
            			}
            			if(trick == 3){
            				componts.add(rcount,compname[0]);
            				rows.add(rcount,compname[1]);
            				Rrows[rcount]=compname[1];
            				if(isxop==true&&rcount>7){
                				rcount = 12;
                			}
                			else{rcount++;}
            				componts.add(rcount,compname[0]);
            				rows.add(rcount,compname[2]);
            				Rrows[rcount]=compname[2];
            				if(isxop==true&&rcount>7){
                				rcount = 12;
                			}
                			else{rcount++;}
            			}
            			if(trick == 4){
            				componts.add(rcount,compname[0]);
            				rows.add(rcount,compname[2]);
            				Rrows[rcount]=compname[2];
            				if(isxop==true&&rcount>7){
                				rcount = 12;
                			}
                			else{rcount++;}
            			}
            			if(trick == 5){
            				componts.add(rcount,compname[0]);
            				rows.add(rcount,compname[1]);
            				Rrows[rcount]=compname[1];
            				if(isxop==true&&rcount>7){
                				rcount = 12;
                			}
                			else{rcount++;}
            			}
            		}
            		
            		if(str[i].contains("LED"))
            		{
            			int trick = 3;
            			String[] compname = str[i].split(" ");
            			for(int ri = 0; ri < 16; ri++){
            				if(compname[1].equals(Lrows[ri])){
            					String tempcomp = new String(componts.get(ri));
            					tempcomp = tempcomp+" "+compname[0]+"+";
            					componts.set(ri, tempcomp);
            					trick = trick+1;
            				}
            				if(compname[2].equals(Lrows[ri])){
            					String tempcomp = new String(componts.get(ri));
            					tempcomp = tempcomp+" "+compname[0]+"-";
            					componts.set(ri, tempcomp);
            					trick = trick+2;
            				}
            				
            			}
            			if(trick == 3){
            				componts.add(rcount,compname[0]+"+");
            				rows.add(rcount,compname[1]);
            				Lrows[rcount]=compname[1];
            				if(isxop==true&&rcount>7){
                				rcount = 12;
                			}
                			else{rcount++;}
            				componts.add(rcount,compname[0]+"-");
            				rows.add(rcount,compname[2]);
            				Lrows[rcount]=compname[2];
            				if(isxop==true&&rcount>7){
                				rcount = 12;
                			}
                			else{rcount++;}
            			}
            			if(trick == 4){
            				componts.add(rcount,compname[0]+"-");
            				rows.add(rcount,compname[2]);
            				Lrows[rcount]=compname[2];
            				if(isxop==true&&rcount>7){
                				rcount = 12;
                			}
                			else{rcount++;}
            			}
            			if(trick == 5){
            				componts.add(rcount,compname[0]+"+");
            				rows.add(rcount,compname[1]);
            				Lrows[rcount]=compname[1];
            				if(isxop==true&&rcount>7){
                				rcount = 12;
                			}
                			else{rcount++;}
            			}
            		}
            		i++;
            	}
            	
            	
            	int rowsize = rows.size();
            	List<String> used = new ArrayList<String>();
            	
            	
            //samenodes is the list of String for each node that shared by two or more componts
            	List<String> samenodes = new ArrayList<String>();
            	while(k<rowsize)
            	{
            		String nodename = new String(rows.get(k));
            		if(used.contains(nodename)==false){
            			String tempstr = new String(k + " ");
            			int count = 1;
            			for(int k2 = k+1; k2<rowsize; k2++){
            				if (rows.get(k2).equals(nodename)){
            					tempstr = tempstr+k2+" ";
            					count++;
            				}
                		}
            			if(count>1){
            				used.add(nodename);
            				samenodes.add(tempstr);
            			}
            		}
            		k++;
            	}
            	
            	while(p<size){
            		if(str[p].contains("V")){
            			String[] vstr = str[p].split(" ");
            			for(int vrow = 0; vrow < rowsize; vrow++){
            				if(rows.get(vrow).equals(vstr[1])){
            					powerinput.add(vrow);
            				}
            				if(rows.get(vrow).equals(vstr[2])){
            					powergnd.add(vrow);
            				}
            			}
            		}
            		p++;
            	}
            	
            	
            	
                //label2.setText(componts.get(13));
                //System.out.print(rows.size());
                
             Output results = new Output(componts, samenodes, powerinput, powergnd);
             results.outputMethod();
               
            }
        });
        
        
      
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(button1, BorderLayout.SOUTH);
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(label2, BorderLayout.SOUTH);

        pack();
    }

}
