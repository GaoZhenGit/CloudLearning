package com.codetend.service.provider.service;

import com.codetend.common.entity.CommonDataItem;
import org.elasticsearch.action.index.IndexResponse;

import java.util.List;
import java.util.Map;

public interface IElasticSearchService {
    String add(String index, CommonDataItem item);
    List<Map<?,?>> getAll(String indexName, String id);
    List<Map<?, ?>> termQuery(String indexName, String msgKeywords);
    List<Map<?, ?>> matchPhraseQuery(String indexName, String field, String key);
}
