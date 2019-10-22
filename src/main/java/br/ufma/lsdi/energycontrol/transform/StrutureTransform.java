package br.ufma.lsdi.energycontrol.transform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ufma.lsdi.energycontrol.beans.Build;
import br.ufma.lsdi.energycontrol.beans.ElectricalCircuit;
import br.ufma.lsdi.energycontrol.beans.SmartMeter;
import br.ufma.lsdi.energycontrol.beans.intescity.Resource;

public class StrutureTransform {
	public List<Resource> builds(List<Build> lista) {
		List<Resource> list=new ArrayList<Resource>();
		for (Iterator<Build> iterator = lista.iterator(); iterator.hasNext();) {
			Build build = (Build) iterator.next();
			List<Resource> l=circuits(build.getListElectricCircuit());
			list.addAll(l);		
		}
		return list;
	}
	
	private List<Resource> circuits(List<ElectricalCircuit> lista) {
		List<Resource> list=new ArrayList<Resource>();
		for (Iterator<ElectricalCircuit> iterator = lista.iterator(); iterator.hasNext();) {
			ElectricalCircuit electricCircuit = (ElectricalCircuit) iterator.next();
			List<Resource> l=smartmeters(electricCircuit.getListSmartMeter());
			list.addAll(l);		
		}
		return list;
	}
	
	private List<Resource> smartmeters(List<SmartMeter> lista) {
		List<Resource> list=new ArrayList<Resource>();
		for (Iterator<SmartMeter> iterator = lista.iterator(); iterator.hasNext();) {
			SmartMeter smartMeter = (SmartMeter) iterator.next();
			Resource r=transformSmartmeter(smartMeter);
			list.add(r);
		}
		return list;
	}
	
	public static String[] capabilities() {
		String capabilities[]=new String[5];
		capabilities[0]="ufma_combed_power";
		capabilities[1]="ufma_combed_build";
		capabilities[2]="ufma_combed_eletrical_circuit";
		capabilities[3]="ufma_combed_energy";
		capabilities[4]="ufma_combed_current";
		
		return capabilities;
	}
	
	Resource transformSmartmeter(SmartMeter smart) {
		Resource resource=new Resource();
		resource.setDescription(smart.getCircuity().getBuild().getName()+"-"+smart.getCircuity().getName()+"-"+smart.getName());
		resource.setCapabilities(capabilities());
		resource.setLat(-2.558251 );
		resource.setLon(-44.308325);
		return resource;
	}
}
