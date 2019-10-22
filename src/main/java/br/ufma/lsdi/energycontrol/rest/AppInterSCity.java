package br.ufma.lsdi.energycontrol.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import br.ufma.lsdi.energycontrol.beans.intescity.ListResources;
import br.ufma.lsdi.restclient.App;

public class AppInterSCity {
	private static final Logger log = LoggerFactory.getLogger(App.class);


	public static void main(String[] args) {
		  RestTemplate restTemplate = new RestTemplate();
	      ListResources quote = restTemplate.getForObject("http://cidadesinteligentes.lsdi.ufma.br/eq1/catalog/resources/search", ListResources.class);
	      log.info(quote.toString());
	}
}
