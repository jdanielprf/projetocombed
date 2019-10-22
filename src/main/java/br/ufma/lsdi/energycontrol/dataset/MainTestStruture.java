package br.ufma.lsdi.energycontrol.dataset;

import java.util.Iterator;
import java.util.List;

import br.ufma.lsdi.energycontrol.beans.Build;
import br.ufma.lsdi.energycontrol.beans.intescity.Resource;
import br.ufma.lsdi.energycontrol.dataset.ReadStruture;
import br.ufma.lsdi.energycontrol.rest.ResourceRest;
import br.ufma.lsdi.energycontrol.transform.StrutureTransform;

public class MainTestStruture {
	public static void main(String[] args) throws Exception {
		String folder="F:\\dataset\\iiitd";
	//	String folder="/mnt/remover/dataset/iiitd/";
		List<Build> lista = new ReadStruture().readFolder(folder);
		
		System.out.println("===========================s");
		List<Resource> resources=new StrutureTransform().builds(lista);
		ResourceRest rest=new ResourceRest(ResourceRest.URL);
		for (Iterator<Resource> iterator = resources.iterator(); iterator.hasNext();) {
			Resource resource = (Resource) iterator.next();
			System.out.println(resource.getDescription());
			rest.create(resource);
		}
		
		
	}
}
