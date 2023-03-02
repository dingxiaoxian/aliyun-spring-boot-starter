package tech.xiaoxian.aliyun.oss;

import com.aliyun.oss.ClientBuilderConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import tech.xiaoxian.aliyun.common.constant.AliyunPrefix;
import tech.xiaoxian.aliyun.common.enums.AliyunAuthorizationMode;
import tech.xiaoxian.aliyun.common.model.AccessKeyProperties;

@ConfigurationProperties(AliyunPrefix.OSS)
public class AliyunOssProperties extends AccessKeyProperties {
    /**
     * Endpoint, please see <a href=
     * "https://help.aliyun.com/document_detail/32010.html?spm=a2c4g.11186623.6.659.29f145dc3KOwTh">oss
     * docs</a>.
     */
    private String endpoint;

    /**
     * oss仓库名称, 帮助业务系统更快找到仓库
     */
    private String bucket;

    /**
     * Authorization Mode, please see <a href=
     * "https://help.aliyun.com/document_detail/32010.html?spm=a2c4g.11186623.6.659.29f145dc3KOwTh">oss
     * docs</a>.
     */
    private AliyunAuthorizationMode authorizationMode;

    /**
     * Sts token, please see <a href=
     * "https://help.aliyun.com/document_detail/32010.html?spm=a2c4g.11186623.6.659.29f145dc3KOwTh">oss
     * docs</a>.
     */
    private StsToken sts;

    /**
     * Client Configuration, please see <a href=
     * "https://help.aliyun.com/document_detail/32010.html?spm=a2c4g.11186623.6.659.29f145dc3KOwTh">oss
     * docs</a>.
     */
    private ClientBuilderConfiguration config;

    public AliyunAuthorizationMode getAuthorizationMode() {
        return authorizationMode;
    }

    public void setAuthorizationMode(AliyunAuthorizationMode authorizationMode) {
        this.authorizationMode = authorizationMode;
    }

    public ClientBuilderConfiguration getConfig() {
        return config;
    }

    public void setConfig(ClientBuilderConfiguration config) {
        this.config = config;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public StsToken getSts() {
        return sts;
    }

    public void setSts(StsToken sts) {
        this.sts = sts;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public static class StsToken extends AccessKeyProperties {

        private String securityToken;

        public String getSecurityToken() {
            return securityToken;
        }

        public void setSecurityToken(String securityToken) {
            this.securityToken = securityToken;
        }
    }
}