package hyun.sample.sampledynamic.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("resource-path.directory")
@Getter
@Setter
public class ResourcePathInfo {

    private String absolutePath;
    private Boolean isDebug;
}
