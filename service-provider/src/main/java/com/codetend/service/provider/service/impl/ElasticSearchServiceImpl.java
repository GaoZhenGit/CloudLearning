package com.codetend.service.provider.service.impl;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.service.provider.service.IElasticSearchService;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ElasticSearchServiceImpl implements IElasticSearchService {
    @Resource
    private Gson gson;
    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private RequestOptions requestOptions;

    @SneakyThrows
    @Override
    public String add(String index, CommonDataItem item) {
        // 指定索引
        IndexRequest request = new IndexRequest(index);
//        request.type("_doc");
        // 文档内容，source传参，第一种时按照 fieldName, fieldValue 成对的方式传入；下面是采用json串 + 指定ContentType的方式传入
        request.source(gson.toJson(item), XContentType.JSON);
        // 指定特殊的id，不指定时自动生成id
        request.id(item.getId());
        IndexResponse response = client.index(request, requestOptions);
        log.info("添加数据返回结果:{}", response.toString());
        return response.toString();
    }

    @SneakyThrows
    public List<Map<?, ?>> getAll(String indexName, String id) {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchResponse searchResponse = client.search(searchRequest, requestOptions);
        List<Map<?, ?>> ret = new ArrayList<>();
        for (SearchHit documentFields : searchResponse.getHits()) {
            ret.add(documentFields.getSourceAsMap());
        }
        return ret;
    }

    /**
     * 精确查询（查询条件不会进行分词，但是查询内容可能会分词，导致查询不到）
     * 多个内容在一个字段中进行查询
     */
    @SneakyThrows
    public List<Map<?, ?>> termQuery(String indexName, String msgKeywords) {
        String[] keywords = msgKeywords.split(",");
        // 构建查询条件（注意：termQuery 支持多种格式查询，如 boolean、int、double、string 等，这里使用的是 string 的查询）
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termsQuery("msg.keyword", keywords));
        // 创建查询请求对象，将查询对象配置到其中
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(searchSourceBuilder);
        // 执行查询，然后处理响应结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        // 根据状态和数据条数验证是否返回了数据
        List<Map<?, ?>> ret = new ArrayList<>();
        if (RestStatus.OK.equals(searchResponse.status())) {
            for (SearchHit hit : searchResponse.getHits()) {
                ret.add(hit.getSourceAsMap());
            }
        }
        return ret;
    }

    /**
     * 词语匹配查询
     */
    @SneakyThrows
    public List<Map<?, ?>> matchPhraseQuery(String indexName, String field, String key) {
        // 构建查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (field.contains(",")) {
            String[] fields = field.split(",");
            //多字段匹配
            searchSourceBuilder.query(QueryBuilders.multiMatchQuery("*" + key + "*", fields));
        } else {
            //单字段匹配
            searchSourceBuilder.query(QueryBuilders.matchPhraseQuery(field, "*" + key + "*"));
        }
        // 创建查询请求对象，将查询对象配置到其中
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(searchSourceBuilder);
        // 执行查询，然后处理响应结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        // 根据状态和数据条数验证是否返回了数据
        List<Map<?, ?>> ret = new ArrayList<>();
        if (RestStatus.OK.equals(searchResponse.status())) {
            for (SearchHit hit : searchResponse.getHits()) {
                ret.add(hit.getSourceAsMap());
            }
        }
        return ret;
    }

}
