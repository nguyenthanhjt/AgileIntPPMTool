package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.ProjectService;
import io.agileintelligence.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BackLogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/{projectIdentifier}")
    public ResponseEntity<?> addProjectTaskToBackLog(@Valid @RequestBody ProjectTask projectTask,
                                                     BindingResult bindingResult, @PathVariable String projectIdentifier) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if (errorMap != null) return errorMap;

        ProjectTask projectTask1 = projectTaskService.addProjectTask(projectIdentifier, projectTask);

        return new ResponseEntity<>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public Iterable<ProjectTask> getProjectBackLog(@PathVariable String projectIdentifier) {
        Project project = projectService.findProjectByIdentifier(projectIdentifier);

        return projectTaskService.findBackLogById(project.getProjectIdentifier());

    }

    @GetMapping("/{backLogID}/{projectTaskID}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backLogID, @PathVariable String projectTaskID) {

        ProjectTask projectTask = projectTaskService.findProjectTaskByProjectSequence(backLogID, projectTaskID);

        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);

    }

}
