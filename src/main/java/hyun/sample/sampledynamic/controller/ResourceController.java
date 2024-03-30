package hyun.sample.sampledynamic.controller;

import hyun.sample.sampledynamic.config.ResourcePathInfo;
import hyun.sample.sampledynamic.service.ResourceService;
import hyun.sample.sampledynamic.vo.Resource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ResourceController {

    private final ResourceService resourceService;
    private final String PATH;

    public ResourceController(ResourceService resourceService, ResourcePathInfo info) {
        this.resourceService = resourceService;
        this.PATH = info.getAbsolutePath();
    }

    @PostMapping("/resource/save")
    @ResponseBody
    public ResponseEntity<String> saveResource(@RequestBody Resource resource) {
        resourceService.save(resource);
        return ResponseEntity.ok("Resource saved successfully");
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("resourcesUrl", resourceService.getAll());
        model.addAttribute("currentPath", PATH);
        return "index";
    }
}
