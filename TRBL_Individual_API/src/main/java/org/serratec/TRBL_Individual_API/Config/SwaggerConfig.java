package org.serratec.TRBL_Individual_API.Config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI myOpenAPI() {
		Contact contato = new Contact();
		contato.setEmail("carloscarvalho201532@gmail.com");
		contato.setName("Carlos");
		
		Info info = new Info() 
				.title("TRBL_Api")
				.contact(contato);
				
			
		return new OpenAPI().info(info);
		
	}
}
