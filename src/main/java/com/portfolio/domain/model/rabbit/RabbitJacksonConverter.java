package com.portfolio.domain.model.rabbit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class RabbitJacksonConverter {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                // potential 에러 내용 : com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // @OneToMany relation 을 lazy, 비어있는 bean 을 serialization 하다 에러가 발생할 수 있다. 아마도?
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false) //
                // 모르는 property 에 대해 무시하고 넘어간다. DTO 의 하위 호환성 보장에 필요
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
                // 시간 관련 객체(LocalDateTime, java.util.Date)를 직렬화 할 때 timestamp 숫자값이 아닌 포맷팅 문자열로 한다.
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false)
                // 역직렬화 과정 Time-zone 유지하기. tell jackson not to adjus the time-zone
                .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE,false)
                .registerModule(new JavaTimeModule());
    }

    private RabbitJacksonConverter() {
        throw new RuntimeException("construct not support");
    }

    public static ObjectMapper getInstance(){
        return mapper;
    }

    public static String toJson(Object value){
        try{
            return mapper.writeValueAsString(value);
        } catch (Exception e){
            throw new RuntimeException("convert error");
        }
    }
}
