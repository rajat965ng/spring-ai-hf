package com.hf.app.service;

import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptService {

    public String getPromptText(String query,  List<Document> similarDocs){
        // Create a prompt using similar documents as context
        StringBuilder contextBuilder = new StringBuilder();
        similarDocs.forEach(doc ->
                contextBuilder.append("Context: ").append(doc.getContent()).append("\n")
        );

        String promptText = String.format("""
            Based on the following context, answer the question: %s
            
            %s
            
            Please provide a concise answer using the given context.
            """, query, contextBuilder.toString());

        return promptText;
    }

}
