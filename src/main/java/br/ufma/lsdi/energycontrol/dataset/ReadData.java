package br.ufma.lsdi.energycontrol.dataset;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.ufma.lsdi.energycontrol.beans.intescity.CapabilityEnergy;

public class ReadData {
	public static final String current="Current.csv";
	public static final String energy="Energy.csv";
	public static final String power="Power.csv";
	
	public  String convertDate(Date data)   {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate=dateFormat.format(data)+"T";
		dateFormat = new SimpleDateFormat("hh:mm:ss.SSS");
		strDate+=dateFormat.format(data)+"Z";
		return strDate;
	}
	
	public  Date unixDate(String data)   {
		return new Date((long)Double.parseDouble(data));
	}
	
	public  List<CapabilityEnergy>  readFolder(String folder,String build, String circuit) throws Exception  {
		File fCurrent=new File(folder+"\\"+current);
		File fEnergy=new File(folder+"\\"+energy);
		File fPower=new File(folder+"\\"+power);
		
		List<CapabilityEnergy> list=new ArrayList<CapabilityEnergy>(); 
		if(fCurrent.isFile()) {
			Scanner scanCurrent=new Scanner(fCurrent);
			Scanner scanEnergy=new Scanner(fEnergy);
			Scanner scanPower=new Scanner(fPower);
			
			while(scanCurrent.hasNext()) {
				CapabilityEnergy capability=new CapabilityEnergy();
				Date data=new Date(); 
				capability.setDate(convertDate(data));
				
				String str[];
				Double value;
				
				str=scanCurrent.nextLine().split(",");
				value=Double.parseDouble(str[1]);
				capability.setCurrent(value);

				str=scanEnergy.nextLine().split(",");
				value=Double.parseDouble(str[1]);
				capability.setEnergy(value);
				
				str=scanPower.nextLine().split(",");
				value=Double.parseDouble(str[1]);
				capability.setPower(value);
				
				capability.setCircuit(circuit);
				capability.setBuild(build);
				list.add(capability);
			}
			scanPower.close();
			scanEnergy.close();
			scanCurrent.close();
		} 
		return list;
	}
}
