package com.portfolio.web.results;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResult extends HashMap<String, Object> {

    private static final long serialVersionUID = -6311566681672669647L;

    private static final String MESSAGE_KEY = "message";
    private static final String ERROR_CODE_KEY = "errorReferenceCode";
    private static final String SUCCESS_KEY = "success";

    public static ApiResult blank() {
        return new ApiResult();
    }

    public static ApiResult message(String message){
        ApiResult apiResult = new ApiResult();
        apiResult.put(MESSAGE_KEY, message);
        return apiResult;
    }

    public static ApiResult error(String message, String errorReferenceConde){
        ApiResult apiResult = new ApiResult();
        apiResult.put(MESSAGE_KEY, message);
        apiResult.put(ERROR_CODE_KEY, errorReferenceConde);
        return apiResult;
    }

    public static ApiResult success(boolean result) {
        ApiResult apiResult = new ApiResult();
        apiResult.put(SUCCESS_KEY, result);
        return apiResult;
    }

    public static ApiResult list(Page page, List data){
        ApiResult apiResult = new ApiResult();
        return apiResult.blank()
                .add("total",page.getTotalElements())
                .add("from",page.getPageable().getOffset())
                .add("to",page.getPageable().getOffset()+page.getNumberOfElements()-1)
                .add("data", data)
                .add("per_page",page.getPageable().getPageSize())
                .add("last_page",page.getTotalPages());
    }

    public static ApiResult list(Page<?> page) {
        ApiResult apiResult = new ApiResult();
        return apiResult.blank()
                .add("total", page.getTotalElements())
                .add("from", page.getPageable().getOffset())
                .add("to", page.getPageable().getOffset() + page.getNumberOfElements() - 1)
                .add("data", page.getContent())
                .add("per_page", page.getPageable().getPageSize())
                .add("last_page", page.getTotalPages());
    }

//    // restPage
//    public static ApiResult cacheList(RestPage restPage, List data) {
//        ApiResult apiResult = new ApiResult();
//        return apiResult.blank()
//                .add("total",restPage.getTotalElements())
//                .add("from",restPage.getPageable().getOffset())
//                .add("to",restPage.getPageable().getOffset()+restPage.getNumberOfElements()-1)
//                .add("data", data)
//                .add("per_page",restPage.getPageable().getPageSize())
//                .add("last_page",restPage.getTotalPages());
//    }

//    // restPage
//    public static ApiResult cacheList(RestPage<?> restPage) {
//        ApiResult apiResult = new ApiResult();
//        return apiResult.blank()
//                .add("total", restPage.getTotalElements())
//                .add("from", restPage.getPageable().getOffset())
//                .add("to", restPage.getPageable().getOffset() + restPage.getNumberOfElements() - 1)
//                .add("data", restPage.getContent())
//                .add("per_page", restPage.getPageable().getPageSize())
//                .add("last_page", restPage.getTotalPages());
//    }

    public static ApiResult data(Object data) {
        ApiResult apiResult = new ApiResult();
        return apiResult.blank()
                .add("data", data);
    }



    public ApiResult add(String key, Object value){
        this.put(key,value);
        return this;
    }


}

