package hyun.sample.sampledynamic.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceResponse {

    private String url;
    private String method;
    private String fileName;
    private String content;

    public ResourceResponse(Resource resource, ObjectMapper objectMapper) {
        this.url = resource.getUrl();
        this.method = resource.getMethod();
        this.fileName = resource.getFileName();
        try {
            this.content = objectMapper.writeValueAsString(resource.getContent());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
