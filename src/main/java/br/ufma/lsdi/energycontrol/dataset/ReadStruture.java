package br.ufma.lsdi.energycontrol.dataset;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.ufma.lsdi.energycontrol.beans.Build;
import br.ufma.lsdi.energycontrol.beans.ElectricalCircuit;
import br.ufma.lsdi.energycontrol.beans.SmartMeter;

public class ReadStruture {
	private static final String separator="\\";
	public List<Build> readFolder(String folder) throws Exception {
		File f=new File(folder);
		List<Build> builds=new ArrayList<Build>();
		if(f.isDirectory()) {
			String folders[]=f.list();
			for (int i = 0; i < folders.length; i++) {
				String path=folder+separator+folders[i];
				Build build=readBuild(path); 
				if(build!=null)
					builds.add(build);
			}
		}else {
			throw new Exception();
		}
		return builds;	
	}
	private Build readBuild(String folder) {
		
		
		if(isDirectory(folder) ) {
			File f=new File(folder);
			Build b=new Build();
			b.setName(f.getName().trim().replace(" ","_" ));
			
			String folders[]=f.list();
			List<ElectricalCircuit> list=new ArrayList<ElectricalCircuit>();
			b.setListElectricCircuit(list);
			for (int i = 0; i < folders.length; i++) {
				String ff=folder+separator+folders[i];
				ElectricalCircuit circuits=readCircuito(ff,b);
				if(circuits!=null)
					list.add(circuits);
			}
			System.out.println(b);
			return b;
		}
		return null;
	}
	
	private ElectricalCircuit readCircuito(String folder,Build build) {
	
		if(isDirectory(folder)) {	
			File dir=new File(folder);
	
			String folders[]=dir.list();
			List<SmartMeter> list=new ArrayList<SmartMeter>();
			
			ElectricalCircuit c=new ElectricalCircuit();
			c.setName(dir.getName().trim().replace(" ","_" ).replace("-", "_"));
			c.setBuild(build);
			c.setListSmartMeter(list);
			for (int i = 0; i < folders.length; i++) {
				String ff=folder+separator+folders[i];
				SmartMeter smart=readSmartMeter(ff, c);
				if(smart!=null)
					list.add(smart);
			}
			return c;
		}
		return null;
	}
	
	private SmartMeter readSmartMeter(String folder,ElectricalCircuit circuit) {
		 
		if(isDirectory(folder)) {
			File f=new File(folder);
			SmartMeter smart=new SmartMeter();
			smart.setName(f.getName().trim());
			smart.setCircuity(circuit);
			return smart;
		}	
		return null;
	}
	public static boolean isDirectory(String path) {
	    return path !=null && new File(path).isDirectory();
	}
}
