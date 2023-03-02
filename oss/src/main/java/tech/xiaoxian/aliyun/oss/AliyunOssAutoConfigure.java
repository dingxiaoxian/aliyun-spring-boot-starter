package tech.xiaoxian.aliyun.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import tech.xiaoxian.aliyun.common.constant.AliyunPrefix;
import tech.xiaoxian.aliyun.common.enums.AliyunAuthorizationMode;

/**
 * 阿里云OSS自动配置类
 */
@Configuration
@EnableConfigurationProperties(AliyunOssProperties.class)
@ConditionalOnClass(OSS.class)
@ConditionalOnProperty(AliyunPrefix.OSS + ".endpoint")
public class AliyunOssAutoConfigure {
    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public OSS ossClient(AliyunOssProperties properties) {
        Assert.isTrue(!ObjectUtils.isEmpty(properties.getEndpoint()), "Oss endpoint 配置为空");
        if (properties.getAuthorizationMode() == AliyunAuthorizationMode.AccessKey) {
            Assert.isTrue(!ObjectUtils.isEmpty(properties.getAccessKeyId()), "oss Access key 配置为空");
            Assert.isTrue(!ObjectUtils.isEmpty(properties.getAccessKeySecret()), "oss Secret key 配置为空");
            return new OSSClientBuilder().build(
                    properties.getEndpoint(),
                    properties.getAccessKeyId(),
                    properties.getAccessKeySecret(),
                    properties.getConfig()
            );
        } else if (properties.getAuthorizationMode() == AliyunAuthorizationMode.STS) {
            AliyunOssProperties.StsToken sts = properties.getSts();
            Assert.isTrue(!ObjectUtils.isEmpty(sts), "STS info 配置为空");
            Assert.isTrue(!ObjectUtils.isEmpty(sts.getAccessKeyId()), "sts Access key 配置为空");
            Assert.isTrue(!ObjectUtils.isEmpty(sts.getAccessKeySecret()), "sts Secret key 配置为空");
            Assert.isTrue(!ObjectUtils.isEmpty(sts.getSecurityToken()), "sts Security Token 配置为空");
            return new OSSClientBuilder().build(
                    properties.getEndpoint(),
                    sts.getAccessKeyId(),
                    sts.getAccessKeySecret(),
                    sts.getSecurityToken(),
                    properties.getConfig()
            );
        } else {
            throw new IllegalArgumentException("Unknown auth mode.");
        }
    }
}
