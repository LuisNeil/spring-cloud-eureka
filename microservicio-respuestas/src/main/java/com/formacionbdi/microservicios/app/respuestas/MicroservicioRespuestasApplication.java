package com.formacionbdi.microservicios.app.respuestas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableAutoConfiguration(exclude ={DataSourceAutoConfiguration.class} )
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
/*@EntityScan({"com.formacionbdi.microservicios.app.respuestas.models.entity",
//		"com.formacionbdi.microservicios.commons.students.models.entity",
		"com.formacionbdi.microservicios.commons.models.entity"})*/
public class MicroservicioRespuestasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioRespuestasApplication.class, args);
	}

}
