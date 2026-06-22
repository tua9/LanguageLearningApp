package com.tuan.lla.client;

import com.tuan.lla.dto.response.ExternalDictionaryResponse;
import com.tuan.lla.dto.response.VocabularyResponse;
import com.tuan.lla.model.Vocabulary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "dictionaryClient", url = "https://dictionary-api.eliaschen.dev/api/dictionary")
public interface DictionaryClient {
    @GetMapping("/en/{word}")
    ExternalDictionaryResponse getWord(@PathVariable("word") String word);
}
