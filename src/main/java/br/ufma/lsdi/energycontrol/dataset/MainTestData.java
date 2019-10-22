package br.ufma.lsdi.energycontrol.dataset;

import java.util.Iterator;
import java.util.List;

import br.ufma.lsdi.energycontrol.beans.intescity.CapabilityEnergy;
import br.ufma.lsdi.energycontrol.rest.ResourceRest;

public class MainTestData {
	public static void main(String[] args) throws Exception {
		String folder = "F:\\dataset\\iiitd\\Academic Block\\AHU\\0\\";
		
		 List<CapabilityEnergy> data=new ReadData().readFolder(folder,"Academic Block","AHU");
		 ResourceRest rest=new ResourceRest(ResourceRest.URL);
		 for (Iterator<CapabilityEnergy> iterator = data.iterator(); iterator.hasNext();) {
			CapabilityEnergy capabilityEnergy = (CapabilityEnergy) iterator.next();
			System.out.println(capabilityEnergy.getCurrent());
			System.out.println(capabilityEnergy.getEnergy());
			System.out.println(capabilityEnergy.getPower());
			System.out.println(capabilityEnergy.getDate());
			System.out.println("==");
			rest.sendDataResource("b117bb23-f1ca-4cd3-b9a7-b263b9355e00", capabilityEnergy);
		}
	}
}
