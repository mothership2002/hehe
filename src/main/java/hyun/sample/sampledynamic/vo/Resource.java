package hyun.sample.sampledynamic.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    private String url;
    private String method;
    private String fileName;
    private Map<String, Object> content;

}
