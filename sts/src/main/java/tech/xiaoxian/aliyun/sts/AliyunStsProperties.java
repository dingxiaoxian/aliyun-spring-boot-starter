package tech.xiaoxian.aliyun.sts;

import org.springframework.boot.context.properties.ConfigurationProperties;
import tech.xiaoxian.aliyun.common.constant.AliyunPrefix;
import tech.xiaoxian.aliyun.common.model.AccessKeyProperties;

@ConfigurationProperties(AliyunPrefix.STS)
public class AliyunStsProperties extends AccessKeyProperties {
    private String endpoint;
    private String roleArn;
    private String regionId;
    private Long durationSeconds;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRoleArn() {
        return roleArn;
    }

    public void setRoleArn(String roleArn) {
        this.roleArn = roleArn;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Long getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Long durationSeconds) {
        this.durationSeconds = durationSeconds;
    }
}
