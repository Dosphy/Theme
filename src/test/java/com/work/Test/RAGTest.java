package com.work.Test;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;


import dev.langchain4j.store.embedding.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.List;

/**
 * @Author Dosphy
 * @Date 2026/1/3 21:59
 */
@SpringBootTest
public class RAGTest {

    @Test
    public void testReadDocument() {
        Document document = FileSystemDocumentLoader.loadDocument("D:\\IDEA\\Project\\Theme\\data\\1.md");
        System.out.println(document.text());
    }

    @Autowired
    private EmbeddingModel embeddingModel;

    @Autowired
    private EmbeddingStore embeddingStore;

    @Test
    public void testEmbeddingStore() {
        TextSegment segment1 = TextSegment.from("七个老八");
        Embedding embedding1 = embeddingModel.embed(segment1).content();

        //存入向量数据库
        embeddingStore.add(embedding1,segment1);
    }


    @Test
    public void testUploadKnowledgeLibrary() {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.md");
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("D:\\IDEA\\Project\\Theme\\data", pathMatcher);

        //文本向量化并存入向量数据库：将每个片段进行向量化，得到一个嵌入向量
        IngestionResult ingest = EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .build()
                .ingest(documents);
        System.out.println(ingest.toString());
    }

    @Test
    public void testEmbeddingSearch() {
        //提问，并将问题转成向量数据
        Embedding queryEmbedding = embeddingModel.embed("你是谁？").content();
        //创建搜索对象
        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1)
                .build();

        //根据搜索请求在向量存储中进行相似度搜索
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);
        //获取搜索结果匹配列表中的第一个匹配项
        EmbeddingMatch<TextSegment> embeddingMatch = searchResult.matches().get(0);

        System.out.println(embeddingMatch.score());
        System.out.println(embeddingMatch.embedded().toString());
    }


}
