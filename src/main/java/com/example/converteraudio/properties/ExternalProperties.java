package com.example.converteraudio.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "external")
public class ExternalProperties {

    /**
     * The external test URL to call. Can be configured via 'external.test-url' or 'external.testUrl'.
     */
    private String testUrl = "https://chatgptm.com/test";
}
