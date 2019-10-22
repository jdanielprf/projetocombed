package br.ufma.lsdi.restclient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

public class App 
{
	   private static final Logger log = LoggerFactory.getLogger(App.class);

	    public static void main(String args[]) {
	        RestTemplate restTemplate = new RestTemplate();
	        Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
	        log.info(quote.toString());
	    }
}
