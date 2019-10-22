package br.ufma.lsdi.energycontrol.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufma.lsdi.energycontrol.beans.intescity.CapabilityEnergy;
import br.ufma.lsdi.energycontrol.beans.intescity.GetResources;
import br.ufma.lsdi.energycontrol.beans.intescity.ListResources;
import br.ufma.lsdi.energycontrol.beans.intescity.Resource;
import br.ufma.lsdi.energycontrol.beans.intescity.SendData;
import br.ufma.lsdi.energycontrol.beans.intescity.SendaDataValues;

public class ResourceRest {
	public static String URL = "http://cidadesinteligentes.lsdi.ufma.br/eq1";
	public String url = "http://cidadesinteligentes.lsdi.ufma.br/eq1";
	private RestTemplate restTemplate ;
	
	public ResourceRest(String url){
		this.url=url;
		restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptors);
	}
	
	public Resource[] getAll() {
		ListResources resources = restTemplate.getForObject(
				url+"/catalog/resources/", ListResources.class);
		return resources.getResources();
	}

	public Resource get(String uuid) {
		GetResources resposta = restTemplate.getForObject(url+"/catalog/resources/"+uuid, GetResources.class);
		return resposta.getData();
	}

	public Resource[] findDescription(String description) {
		ListResources resources = restTemplate.getForObject(
				url+"/catalog/resources/search?description="+description, ListResources.class);
		return resources.getResources();
	}

	public Resource create(Resource r) {
		GetResources request=new GetResources();
		request.setData(r);
		GetResources resposta = restTemplate.postForObject(url+"/catalog/resources/", request, GetResources.class);
		return resposta.getData();
	}
	
	public void update(Resource r) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("uuid",r.getUuid());
		r.setUuid(null);
		GetResources request=new GetResources();
		request.setData(r);
		restTemplate.put(url+"/adaptor/resources/{uuid}", request, param);
	//	restTemplate.exchange(url+"/adaptor/resources/{uuid}",HttpMethod.PUT, r,GetResources.class,param);
	}
	
	public void sendDataResource(String uuid,List<CapabilityEnergy> dados) {
		SendaDataValues values=new SendaDataValues();
		values.setData((CapabilityEnergy[])dados.toArray());
		SendData request=new SendData();
		request.setData(values);
		GetResources resposta = restTemplate.postForObject(url+"/adaptor/resources/"+uuid+"/data", request, GetResources.class);
	}
	
	public void sendDataResource(String uuid,CapabilityEnergy dados) {
		SendaDataValues values=new SendaDataValues();
		values.setData(new CapabilityEnergy[] {dados});
		SendData request=new SendData();
		request.setData(values);
		GetResources resposta = restTemplate.postForObject(url+"/adaptor/resources/"+uuid+"/data", request, GetResources.class);
	}
	
	public List<CapabilityEnergy> getData(String uuid) {
		ResponseEntity<String> response = restTemplate.getForEntity(
				url+"/collector/resources/"+uuid+"/data", String.class);
		List<CapabilityEnergy> list=new ArrayList<CapabilityEnergy>();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		try {
			root = mapper.readTree(response.getBody());
		} catch (IOException e) {
			return list;
		}
		JsonNode jsonResources = root.findPath("teste");
		
		System.out.println(jsonResources);
		Iterator<JsonNode> elems = jsonResources.elements();
				
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
		
		return list;
	}

}
