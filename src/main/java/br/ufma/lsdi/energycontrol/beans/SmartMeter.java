package br.ufma.lsdi.energycontrol.beans;

public class SmartMeter  {
	private String name;
	private ElectricalCircuit circuity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ElectricalCircuit getCircuity() {
		return circuity;
	}

	public void setCircuity(ElectricalCircuit circuity) {
		this.circuity = circuity;
	}
	@Override
	public String toString() {
		return getName();
	}

	
	
}
