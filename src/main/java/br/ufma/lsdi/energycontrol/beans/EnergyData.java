package br.ufma.lsdi.energycontrol.beans;

import java.sql.Date;

import br.ufma.lsdi.energycontrol.beans.intescity.Capability;

public class EnergyData extends Capability{
	private Date timeStamp;
	private float power;
	private float current;
	private float energy;
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public float getPower() {
		return power;
	}
	public void setPower(float power) {
		this.power = power;
	}
	public float getCurrent() {
		return current;
	}
	public void setCurrent(float current) {
		this.current = current;
	}
	public float getEnergy() {
		return energy;
	}
	public void setEnergy(float energy) {
		this.energy = energy;
	}
}
