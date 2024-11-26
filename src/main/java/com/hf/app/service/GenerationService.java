package com.hf.app.service;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.huggingface.HuggingfaceChatModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GenerationService {
    private HuggingfaceChatModel chatModel;
    private SimilaritySearchSearvice similaritySearchSearvice;
    private PromptService promptService;
    private Function<String,String> result;
    private Function<String,String> promptGenerator;

    public GenerationService(HuggingfaceChatModel chatModel, SimilaritySearchSearvice similaritySearchSearvice, PromptService promptService) {
        this.chatModel = chatModel;
        this.similaritySearchSearvice = similaritySearchSearvice;
        this.promptService = promptService;
        result = s -> chatModel.call(new Prompt(s)).getResult().getOutput().getContent();
        promptGenerator = s -> Stream.of(s)
                .map(a -> similaritySearchSearvice.getSimilaritySearchResponse(a,3))
                .map(documents -> promptService.getPromptText(s,documents))
                .collect(Collectors.joining());
    }

    public String chat(String input) {
        return promptGenerator.andThen(result::apply).apply(input);
    }

}
