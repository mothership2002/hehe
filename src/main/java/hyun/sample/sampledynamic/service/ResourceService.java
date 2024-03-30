package hyun.sample.sampledynamic.service;

import hyun.sample.sampledynamic.repository.SampleRepository;
import hyun.sample.sampledynamic.vo.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final SampleRepository sampleRepository;

    public ResourceService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    public void save(Resource info) {
        sampleRepository.save(info);
    }

    public List<Resource> getAll() {
        return sampleRepository.getAll();
    }
}
