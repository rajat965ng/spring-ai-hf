package com.hf.app.service;

import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngestionService {
    @Autowired
    VectorStore vectorStore;
    @Autowired
    TokenTextSplitter splitter;
    public void ingest(List<String> urls) {
        urls.stream().map(TikaDocumentReader::new)
                .map(TikaDocumentReader::read)
                .map(documents -> splitter.apply(documents))
                .forEach(vectorStore::add);
    }
}
