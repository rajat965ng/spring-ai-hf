package com.hf.app.controller;


import com.hf.app.service.GenerationService;
import com.hf.app.service.IngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class RagController {

    @Autowired
    IngestionService ingestionService;
    @Autowired
    GenerationService generationService;

    @PostMapping("/index")
    public ResponseEntity index(@RequestParam List<String> urls){
        ingestionService.ingest(urls);
        return ResponseEntity.created(URI.create(urls.stream().collect(Collectors.joining(",")))).build();
    }

    @PostMapping("/chat")
    public ResponseEntity chat(@RequestParam String input) {
        return ResponseEntity.ok(generationService.chat(input));
    }

}
