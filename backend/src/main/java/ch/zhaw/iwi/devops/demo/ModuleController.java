package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ModuleController {

    private Map<Integer, Module> modules = new HashMap<Integer, Module>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.modules.put(1, new Module(1, "DevOps", "CI/CD und Deployment", "Sommer 2023", "3"));
        this.modules.put(2, new Module(2, "Software Engineering", "Architektur und Clean Code", "Sommer 2023", "3"));
        this.modules.put(3, new Module(3, "Web Development", "Frontend und Backend Entwicklung", "Winter 2023", "6"));

        System.out.println("Init Module Data");
    }

    @GetMapping("/services/module")
    public List<PathListEntry<Integer>> module() {
        var result = new ArrayList<PathListEntry<Integer>>();
        for (var module : this.modules.values()) {
            var entry = new PathListEntry<Integer>();
            entry.setKey(module.getId(), "moduleKey");
            entry.setName(module.getTitle());
            entry.getDetails().add(module.getDescription());
// added by Zerayasl
            entry.getDetails().add("Semester: " + module.getSemester());
            entry.getDetails().add("ECTS: " + module.getEcts());
            entry.setTooltip(module.getDescription() + " | " + module.getSemester() + " | " + module.getEcts() + " ECTS");
            result.add(entry);
        }
        return result.stream().sorted(Comparator.comparing(PathListEntry::getName)).toList();
    }

// added by Zerayasl
    @GetMapping("/services/module/count")
    public int countModules() {
        return this.modules.size();
    }

// added by Zerayasl
    @GetMapping("/services/module/semester/{semester}")
    public List<PathListEntry<Integer>> getModulesBySemester(@PathVariable("semester") String semester) {
        var result = new ArrayList<PathListEntry<Integer>>();

        for (var module : this.modules.values()) {
            if (module.getSemester() != null && module.getSemester().equalsIgnoreCase(semester)) {
                var entry = new PathListEntry<Integer>();
                entry.setKey(module.getId(), "moduleKey");
                entry.setName(module.getTitle());
                entry.getDetails().add(module.getDescription());
                entry.getDetails().add("Semester: " + module.getSemester());
                entry.getDetails().add("ECTS: " + module.getEcts());
                entry.setTooltip(module.getDescription());
                result.add(entry);
            }
        }

        return result.stream().sorted(Comparator.comparing(PathListEntry::getName)).toList();
    }

    @GetMapping("/services/module/{key}")
    public Module getModule(@PathVariable("key") Integer key) {
        return this.modules.get(key);
    }

    @PostMapping("/services/module")
    public void createModule(@RequestBody Module module) {
        var newId = this.modules.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        module.setId(newId);
        this.modules.put(newId, module);
    }

    @PutMapping("/services/module/{key}")
    public void updateModule(@PathVariable("key") Integer key, @RequestBody Module module) {
        module.setId(key);
        this.modules.put(key, module);
    }

    @DeleteMapping("/services/module/{key}")
    public Module deleteModule(@PathVariable("key") Integer key) {
        return this.modules.remove(key);
    }
}