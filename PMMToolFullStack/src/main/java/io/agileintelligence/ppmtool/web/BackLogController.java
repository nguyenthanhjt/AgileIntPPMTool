package io.agileintelligence.ppmtool.web;

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
import java.security.Principal;

@RestController
@RequestMapping(value = "/api/backlog",
        method = {RequestMethod.DELETE,
                RequestMethod.POST,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.PATCH
        })
@CrossOrigin(origins = "http://localhost:3000")
public class BackLogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/{projectIdentifier}")
    public ResponseEntity<?> addProjectTaskToBackLog(@Valid @RequestBody ProjectTask projectTask,
                                                     BindingResult bindingResult, @PathVariable String projectIdentifier, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationErrorService.validateError(bindingResult);
        if (errorMap != null) return errorMap;

        ProjectTask projectTask1 = projectTaskService.addProjectTask(projectIdentifier, projectTask, principal.getName());

        return new ResponseEntity<>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/get-backlog/{projectIdentifier}")
    public Iterable<ProjectTask> getProjectBackLog(@PathVariable String projectIdentifier, Principal principal) {
        projectService.findProjectByIdentifier(projectIdentifier, principal.getName());

        return projectTaskService.findBackLogByProjectID(projectIdentifier);

    }

    @GetMapping("/get-project-task/{backLogID}/{projectTaskID}")
    public ResponseEntity<Object> getProjectTask(@PathVariable String backLogID, @PathVariable String projectTaskID) {

        ProjectTask projectTask = projectTaskService.findProjectTaskByProjectSequence(backLogID, projectTaskID);

        return new ResponseEntity<>(projectTask, HttpStatus.OK);

    }

    @PatchMapping(value = "/{projectID}/{projectTaskSeq}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                               @PathVariable String projectID, @PathVariable String projectTaskSeq) {

        ResponseEntity<?> errorMap = mapValidationErrorService.validateError(result);
        if (null != errorMap) return errorMap;

        ProjectTask updatedTask = projectTaskService.updateProjectTaskByProjectSequence(projectTask, projectID, projectTaskSeq);

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{projectID}/{projectTaskSeq}")
    public ResponseEntity<Object> deleteProjectTask(@PathVariable String projectID, @PathVariable String projectTaskSeq) {
        projectTaskService.deleteProjectTaskByProjectSequence(projectID, projectTaskSeq);

        return new ResponseEntity<>("Project task '" + projectTaskSeq + "' was deleted successfully.", HttpStatus.OK);
    }
}
