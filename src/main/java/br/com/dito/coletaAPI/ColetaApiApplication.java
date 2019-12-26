package br.com.dito.coletaAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootApplication
public class ColetaApiApplication {

	public static void main(String[] args) {

		ZoneId fusoHorarioDeSaoPaulo = ZoneId.of("America/Sao_Paulo");

		LocalDateTime fimDoHorarioDeVerao2013SemFusoHorario =
				LocalDateTime.now();

		ZonedDateTime fimDoHorarioVerao2013ComFusoHorario =
				fimDoHorarioDeVerao2013SemFusoHorario.atZone(fusoHorarioDeSaoPaulo);
		System.out.println(fimDoHorarioVerao2013ComFusoHorario); //2014-02-15T23:00-02:00[America/Sao_Paulo]

		SpringApplication.run(ColetaApiApplication.class, args);
	}

}
