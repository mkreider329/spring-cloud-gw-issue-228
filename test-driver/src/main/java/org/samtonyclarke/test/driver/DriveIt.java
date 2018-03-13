package org.samtonyclarke.test.driver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class DriveIt
{
    public static void main(String args[]) throws InterruptedException
    {
        Flux<Integer> intFlux = Flux.range(1, 200000);       
        intFlux.parallel().subscribe(i -> callOnce());
    }

    private static void callOnce()
    {
        Mono<ClientResponse> exchange = WebClient.create("http://localhost:8080").get()
                .uri("integrationTest/1.0/app-name").exchange();
        exchange.subscribe(response ->
        {
            HttpStatus statusCode = response.statusCode();
            if (statusCode.is5xxServerError())
            {
                // error has occured we can bail out
                System.exit(-1);
            }
        });
    }

}
