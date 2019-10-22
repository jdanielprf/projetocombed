package br.ufma.lsdi.energycontrol.beans.intescity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListResources implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Resource resources[];
	public ListResources(){
		
	}
	public Resource[] getResources() {
		return resources;
	}

	public void setResources(Resource[] resources) {
		this.resources = resources;
	} 
}
