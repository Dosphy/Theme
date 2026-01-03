package com.work.Test;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
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

    //加载文档并存入内存向量数据库
    @Test
    public void testReadDocumentAndStore(){
        Document document = FileSystemDocumentLoader.loadDocument("C:\\Users\\LHL\\Desktop\\1.md");
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        EmbeddingStoreIngestor.ingest(document,embeddingStore);
        System.out.println(embeddingStore);
    }

    @Test
    public void testReadDocumentAndStore(){
        Document document = FileSystemDocumentLoader.loadDocument("C:\\Users\\LHL\\Desktop\\1.md");
        // 替换内存存储 → 文件持久化存储（指定存储文件路径）
        EmbeddingStore<TextSegment> embeddingStore = new FileSystemEmbeddingStore<>("D:\\vector-data");

        EmbeddingStoreIngestor.ingest(document,embeddingStore);
        System.out.println(embeddingStore);
    }
}
