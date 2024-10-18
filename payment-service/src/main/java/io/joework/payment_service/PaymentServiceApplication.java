package io.joework.payment_service;

import io.joework.payment_service.dto.PaymentServiceRequest;
import io.joework.payment_service.dto.PaymentServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@Slf4j
@EnableConfigurationProperties({PaymentConfigurationProperties.class})
public class PaymentServiceApplication {



	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	@RestController
	@RequestMapping("/payments")
	@RequiredArgsConstructor
	static class PaymentController{
		private final PaymentConfigurationProperties paymentConfigurationProperties;

		@PostMapping
		public ResponseEntity<?> processPayments(@RequestBody PaymentServiceRequest request){
			log.info("application version: {} ", paymentConfigurationProperties.getVersion());
			log.info("new request coming with the following body {}", request);
			Random rand = new Random();
			if(rand.nextInt() % 2 == 0){
				log.info("request succeeded...");
				return ResponseEntity.ok(new PaymentServiceResponse("success", "SUC222", "transaction success"));
			}
			log.info("request failed...");
			return ResponseEntity.badRequest().body(new PaymentServiceResponse("failed", "FAIL222", "transaction failed"));
		}

	}
}
