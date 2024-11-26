package com.hf.app.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimilaritySearchSearvice {
    private VectorStore vectorStore;

    public SimilaritySearchSearvice(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public List<Document> getSimilaritySearchResponse(String query, int k){
        SearchRequest searchRequest = SearchRequest.defaults()
                .withQuery(query)
                .withTopK(k);

       return vectorStore.similaritySearch(searchRequest);
    }
}
