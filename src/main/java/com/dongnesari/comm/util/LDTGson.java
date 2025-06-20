package com.dongnesari.comm.util;

import com.google.gson.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime을 자동으로 처리할 수 있도록 구성된 Gson 유틸리티.
 */
public class LDTGson {

    // 고정된 ISO_LOCAL_DATE_TIME 포맷 사용 ("yyyy-MM-dd'T'HH:mm:ss")
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // static 메서드로 바로 Gson 객체 반환
    public static Gson create() {
        return new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,
                (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                    new JsonPrimitive(src.format(formatter)))
            .registerTypeAdapter(LocalDateTime.class,
                (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                    LocalDateTime.parse(json.getAsString(), formatter))
            .create();
    }
}