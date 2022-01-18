package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService validationErrorService;


    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result, Principal principal) {

        ResponseEntity<?> errorMap = validationErrorService.validateError(result);
        if (errorMap != null) return errorMap;

        Project targetProject = projectService.saveOrUpdateProject(project, principal.getName());
        return new ResponseEntity<>(targetProject, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorService.validateError(result);
        if (errorMap != null) return errorMap;

        projectService.updateProject(project);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId, Principal principal) {
        Project project = projectService.findProjectByIdentifier(projectId, principal.getName());

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(Principal principal) {
        return projectService.findAllProjects(principal.getName());
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteProjectById(@PathVariable String projectId, Principal principal) {
        projectService.deleteProjectById(projectId, principal.getName());
        return new ResponseEntity<>("Project with Id: '" + projectId + "' was deleted.", HttpStatus.OK);
    }
}
