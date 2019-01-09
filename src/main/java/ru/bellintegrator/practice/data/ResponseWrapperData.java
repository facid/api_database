package ru.bellintegrator.practice.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.practice.error.ResponseError;

@RestControllerAdvice
public class ResponseWrapperData implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType){
        return true;
    }

    /**
     * Вызывается перед тем, как HttpMessageConverter запишет тело ответа.
     * Оборачивает тело ответа в "{data}".
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response){

        if (body instanceof ResponseError){
            return body;
        }
        return new WrapperObject<Object>(body);
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonSerialize
    private class WrapperObject<T>{
        private final Object data;

        WrapperObject(Object data){
            this.data = data;
        }
    }
}
