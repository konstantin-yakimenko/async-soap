package ru.jakimenko.migration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.jakimenko.migration.async.AsyncExecutor;
import ru.jakimenko.migration.async.Client;
import ru.jakimenko.migration.sync.SyncExecutor;

//import hello.wsdl.GetQuoteResponse;

@SpringBootApplication
@Slf4j
public class MigrationApplication {
	public static void main(String[] args) {
		SpringApplication.run(MigrationApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(PpdClient ppdClient) {
		return args -> {
//            new SyncExecutor().execute(ppdClient);
//            new Client().some();
			new AsyncExecutor().execute();
        };
	}
}
