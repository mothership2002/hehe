package hyun.sample.sampledynamic.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hyun.sample.sampledynamic.config.ResourcePathInfo;
import hyun.sample.sampledynamic.vo.Resource;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Repository
@Slf4j
public class SampleRepository {

    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final String DIRECTORY_PATH;
    private final Boolean IS_DEBUG;
    private final Map<String, Resource> urlMap = new LinkedHashMap<>();
    private final ObjectMapper objectMapper;

    public SampleRepository(ResourcePathInfo resourcePathInfo, ObjectMapper objectMapper) {
        this.DIRECTORY_PATH = resourcePathInfo.getAbsolutePath();
        this.IS_DEBUG = resourcePathInfo.getIsDebug();
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() throws JsonProcessingException {
        log.info("url mapping initialization");
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        Arrays.stream(Objects.requireNonNull(directory.list()))
                .filter(e -> e.contains(".json"))
                .map(this::jsonFileToResource)
                .forEach(e -> urlMap.put(e.getUrl(), e));
        log.info("repository init complete");
        if (IS_DEBUG) {
            for (String key : urlMap.keySet()) {
                Resource value = urlMap.get(key);
                log.info("url: {}, Resource: {}", key, objectMapper.writeValueAsString(value));
            }
        }
    }

    public void save(Resource resource) {
        Resource obj = urlMap.get(resource.getUrl());
        if (!Objects.isNull(obj)) {
            // ??
        }
        executorService.submit(() -> {
            File file = new File(DIRECTORY_PATH + File.separator + resource.getFileName());
            try {
                String json = objectMapper.writeValueAsString(resource);
                Files.write(file.toPath(), json.getBytes());
            } catch (IOException e) {
                log.error("didnt create json. try again");
                throw new RuntimeException("Failed to save resource to file", e);
            }
        });
        urlMap.put(resource.getUrl(), resource);
    }

    public List<Resource> getAll() {
        return urlMap.values().stream().toList();
    }

    private Resource jsonFileToResource(String filename) {
        String filePath = DIRECTORY_PATH + File.separator + filename;
        File file = new File(filePath);
        try {
            String json = new String (Files.readAllBytes(Paths.get(file.getPath())));
            return objectMapper.readValue(json, Resource.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
     }
}
