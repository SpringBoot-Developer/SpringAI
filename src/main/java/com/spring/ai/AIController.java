package com.spring.ai;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private OllamaChatClient client;

    // private static final String PROMPT = "What is Java";

   /*  @GetMapping("/prompt")
    public String promptResponse(@RequestParam("prompt") String prompt) {
        return client.call(prompt);
    } */

// URL http://localhost:8080/ai/prompt?prompt=what is kafka
    @GetMapping("/prompt")
    public Flux<String> promptResponse(@RequestParam("prompt") String prompt) {
        System.out.println(prompt);
        Flux<String> response = client.stream(prompt);
        response.subscribe(
                System.out::println,  // Print each emitted element
                error -> System.err.println("Error: " + error),  // Handle errors
                () -> System.out.println("Stream completed")  // Handle stream completion
        );
        return response;


    }
}
