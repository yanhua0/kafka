package org.rpc.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    //{"timestamp":"2019-09-22T03:16:47.404+0000","status":404,"error":"Not Found","message":"No message available","path":"/valid/s10"}
    private String status;
    private String error;
    private String path;
    //接受的格式必须是这个模板，返回的时间格式会自动序列化成如下模板
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date nowTime;
    private String message;
    private T data;

    public Date getNowTime() {
        return new Date();
    }


}
