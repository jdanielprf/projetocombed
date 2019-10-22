package br.ufma.lsdi.energycontrol.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufma.lsdi.energycontrol.beans.intescity.CapabilityEnergy;

public class Main2 {
	public static void main(String[] args) throws IOException {
		String url = "http://cidadesinteligentes.lsdi.ufma.br/eq1/collector/resources/a93e5443-8db7-438a-a238-dc664c9a8d05/data";
		RestTemplate restTemplate;

		restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptors);
		
		ResponseEntity<String> response = restTemplate.getForEntity(
				url, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode jsonResources = root.findPath("teste");
		System.out.println("==============");
		System.out.println(jsonResources);
		Iterator<JsonNode> elems = jsonResources.elements();
		List<CapabilityEnergy> list=new ArrayList<CapabilityEnergy>();
		
		while(elems.hasNext()) {
			CapabilityEnergy value=new CapabilityEnergy();
			JsonNode cap = elems.next();
			value.setDate(cap.get("date").toString());
			
			if(cap.get("ufma_combed_build")!=null)
				value.setBuild(cap.get("ufma_combed_build").toString());
			
			if(cap.get("ufma_combed_electric_circuit")!=null)
				value.setCircuit(cap.get("ufma_combed_electric_circuit").toString());
			
			if(cap.get("ufma_combed_energy")!=null)
				value.setEnergy(cap.get("ufma_combed_energy").asDouble());
			
			if(cap.get("ufma_combed_power")!=null)
				value.setPower(cap.get("ufma_combed_power").asDouble());
			
			if(cap.get("ufma_combed_current")!=null)
				value.setPower(cap.get("ufma_combed_current").asDouble());
			
			System.out.println(value.getEnergy()+""+value.getDate());
			list.add(value);
		}
		
		
	}
}
