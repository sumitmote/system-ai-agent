package com.example.springaidemo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
public class KnowledgeLoader implements ApplicationRunner {

    private final VectorStore vectorStore;
    private final ResourcePatternResolver resourcePatternResolver;

    public KnowledgeLoader(
            VectorStore vectorStore,
            ResourcePatternResolver resourcePatternResolver) {
        this.vectorStore = vectorStore;
        this.resourcePatternResolver = resourcePatternResolver;
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        Resource[] resources = resourcePatternResolver.getResources("classpath:/knowledge/*.md");
        List<Document> documents = new ArrayList<>(resources.length);
        FilterExpressionBuilder filterBuilder = new FilterExpressionBuilder();

        for (Resource resource : resources) {
            String source = resource.getFilename();
            if (source == null) {
                throw new IOException("Knowledge resource has no filename: " + resource);
            }
            Filter.Expression sourceFilter = filterBuilder.eq("source", source).build();
            vectorStore.delete(sourceFilter);
            String content;
            try (var inputStream = resource.getInputStream()) {
                content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            }
            documents.add(new Document(content, java.util.Map.of("source", source)));
        }

        if (!documents.isEmpty()) {
            vectorStore.add(documents);
        }
    }
}
