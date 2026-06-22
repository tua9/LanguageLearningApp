package com.tuan.lla.dto.response;

import java.util.List;

public record ExternalDictionaryResponse(
        String word,
        List<String> pos,
        List<Object> verbs,
        List<Pronunciation> pronunciation,
        List<Definition> definition
) {
    public record Pronunciation(
            String pos,
            String lang,
            String url,
            String pron
    ) {}

    public record Definition(
            int id,
            String pos,
            String source,
            String text,
            String translation,
            List<Example> example
    ) {}

    public record Example(
            int id,
            String text,
            String translation
    ) {}
}

