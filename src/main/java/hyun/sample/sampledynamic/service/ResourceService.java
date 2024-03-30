package hyun.sample.sampledynamic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyun.sample.sampledynamic.repository.SampleRepository;
import hyun.sample.sampledynamic.vo.Resource;
import hyun.sample.sampledynamic.vo.ResourceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final SampleRepository sampleRepository;

    private final ObjectMapper objectMapper;

    public ResourceService(SampleRepository sampleRepository, ObjectMapper objectMapper) {
        this.sampleRepository = sampleRepository;
        this.objectMapper = objectMapper;
    }

    public void save(Resource info) {
        sampleRepository.save(info);
    }

    public List<ResourceResponse> getAll() {
        return sampleRepository.getAll().stream().map(e -> new ResourceResponse(e, objectMapper)).toList();
    }
}
