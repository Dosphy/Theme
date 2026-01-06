package com.work.Test;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Dosphy
 * @Date 2026/1/3 21:59
 */
@SpringBootTest
public class RAGTest {

    @Test
    public void testReadDocument() {
        Document document = FileSystemDocumentLoader.loadDocument("C:\\Users\\LHL\\Desktop\\1.md");
        System.out.println(document.text());
    }

    @Autowired
    private EmbeddingModel embeddingModel;

    @Autowired
    private EmbeddingStore embeddingStore;

    @Test
    public void testEmbeddingStore() {
        TextSegment segment1 = TextSegment.from("我叫Dosphy,我是个计算机学生");
        Embedding embedding1 = embeddingModel.embed(segment1).content();

        //存入向量数据库
        embeddingStore.add(embedding1,segment1);


//        TextSegment segment2 = TextSegment.from("");
//        Embedding embedding2 = embeddingModel.embed(segment2).content();

        //存入向量数据库
//        embeddingStore.add(embedding2,segment2);
    }

    //加载文档并存入内存向量数据库
    @Test
    public void testReadDocumentAndStore(){
        Document document = FileSystemDocumentLoader.loadDocument("C:\\Users\\LHL\\Desktop\\1.md");
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        EmbeddingStoreIngestor.ingest(document,embeddingStore);
        System.out.println(embeddingStore);
    }


}
