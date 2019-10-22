package br.ufma.lsdi.energycontrol.beans;

import java.util.Iterator;
import java.util.List;

public class Build {

	private double lat, log;
	private String name;
	private List<ElectricalCircuit> listElectricCircuit;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLog() {
		return log;
	}

	public void setLog(double log) {
		this.log = log;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ElectricalCircuit> getListElectricCircuit() {
		return listElectricCircuit;
	}

	public void setListElectricCircuit(List<ElectricalCircuit> listElectricCircuit) {
		this.listElectricCircuit = listElectricCircuit;
	}

	@Override
	public String toString() {
		String str = getName() + "\n";
		if (listElectricCircuit != null && !listElectricCircuit.isEmpty()) {
			str += "electricCircuit:\n";
			for (Iterator<ElectricalCircuit> iterator = listElectricCircuit.iterator(); iterator.hasNext();) {
				ElectricalCircuit electricCircuit = (ElectricalCircuit) iterator.next();
				str +=">"+ electricCircuit + "\n";
			}
		}
		return str;
	}
}
