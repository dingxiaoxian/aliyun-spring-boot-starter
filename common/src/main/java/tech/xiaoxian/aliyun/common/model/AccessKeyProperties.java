package tech.xiaoxian.aliyun.common.model;

/**
 * 阿里云AccessKey鉴权方式所需配置信息
 */
public class AccessKeyProperties {
    private String accessKeyId;
    private String accessKeySecret;

    /**
     * @return AccessKeyId 信息
     */
    public String getAccessKeyId() {
        return accessKeyId;
    }

    /**
     * @param accessKeyId 信息
     */
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    /**
     * @return AccessKeySecret 信息
     */
    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    /**
     * @param accessKeySecret 信息
     */
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
