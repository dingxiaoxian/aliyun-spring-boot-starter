package tech.xiaoxian.aliyun.common.enums;

/**
 * 阿里云默认鉴权方式
 */
public enum AliyunAuthorizationMode {
    /**
     * 使用主账户或RAM账户的AccessKeyId和AccessKeySecret来进行鉴权
     */
    AccessKey,
    /**
     * 使用阿里云STS授权方式进行鉴权
     */
    STS,
    ;
}
