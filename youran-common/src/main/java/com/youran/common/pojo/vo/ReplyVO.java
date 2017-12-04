package com.youran.common.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: 通用响应对象
 * Description:
 * Author: cbb
 * Create Time:2017/8/24 15:51
 */
@ApiModel
public class ReplyVO<T> extends AbstractVO {


    @ApiModelProperty(notes = "响应代码【0正确,非0错误】",example = "true",required = true)
    private int errorCode;

    @ApiModelProperty(notes = "结果描述",example = "执行成功！",required = true)
    private String errorMsg;

    @ApiModelProperty(notes = "返回数据")
    private T data;

    public ReplyVO() {
    }

    public ReplyVO(T data) {
        this.data = data;
    }

    public ReplyVO(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static ReplyVO fail(String errorMsg) {
        return new ReplyVO(-1, errorMsg);
    }

    public static ReplyVO success() {
        return new ReplyVO(0, "success");
    }


    /**
     * 设置数据
     * @param data
     * @return
     */
    public ReplyVO data(T data){
        setData(data);
        return this;
    }

    /**
     * 添加返回数据
     * @param key
     * @param value
     * @return
     */
    public ReplyVO add(String key, Object value) {
        Map<String, Object> map;
        if (this.data == null) {
            map = new HashMap<>();
        } else if (this.data instanceof Map) {
            map = (Map<String, Object>) this.data;
        } else {
            throw new RuntimeException("not support");
        }
        map.put(key, value);
        setData((T) map);
        return this;
    }


    /**
     * 删除数据
     * @param keys
     * @return
     */
    public ReplyVO remove(String... keys) {
        if (this.data == null || !(this.data instanceof Map)) {
            return this;
        }
        Map<String, Object> map = (Map<String, Object>) this.data;
        for (String key : keys) {
            map.remove(key);
        }
        return this;
    }

    /**
     * 清空返回数据
     * @return
     */
    public ReplyVO clear() {
        this.data = null;
        return this;
    }

    /**
     * 获取dataMap 的值
     * @param key key
     * @return
     */
    public Object get(String key) {
        if (this.data == null || StringUtils.isBlank(key) || !(this.data instanceof Map)) {
            return null;
        }
        Map<String, Object> map = (Map<String, Object>) this.data;
        return map.get(key);
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}