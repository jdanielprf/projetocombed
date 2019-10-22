package br.ufma.lsdi.energycontrol.beans;

import java.util.Iterator;
import java.util.List;

public class ElectricalCircuit {
	private String name;
	private List<SmartMeter> listSmartMeter;
	private Build build;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SmartMeter> getListSmartMeter() {
		return listSmartMeter;
	}

	public void setListSmartMeter(List<SmartMeter> listSmartMeter) {
		this.listSmartMeter = listSmartMeter;
	}

	@Override
	public String toString() {
		String str = getName() + "\n";
		if (listSmartMeter != null && !listSmartMeter.isEmpty()) {
			str += "smartmeter:\n";
			for (Iterator<SmartMeter> iterator = listSmartMeter.iterator(); iterator.hasNext();) {
				SmartMeter smartMeter = (SmartMeter) iterator.next();
				str += ">>>"+smartMeter + "\n";
			}
		}
		return str;
	}

	public Build getBuild() {
		return build;
	}

	public void setBuild(Build build) {
		this.build = build;
	}
}
