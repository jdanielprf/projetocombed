package br.ufma.lsdi.energycontrol.beans.intescity;

import java.io.Serializable;

public class CapabilityEnergy implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String build;
	private String circuit;
	private Double power;
	private Double energy;
	private Double current;
	private String date;
	
	
	
	public Double getPower() {
		return power;
	}
	public void setPower(Double power) {
		this.power = power;
	}
	public Double getEnergy() {
		return energy;
	}
	public void setEnergy(Double energy) {
		this.energy = energy;
	}
	public Double getCurrent() {
		return current;
	}
	public void setCurrent(Double current) {
		this.current = current;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBuild() {
		return build;
	}
	public void setBuild(String build) {
		this.build = build;
	}
	public String getCircuit() {
		return circuit;
	}
	public void setCircuit(String circuit) {
		this.circuit = circuit;
	}


}
