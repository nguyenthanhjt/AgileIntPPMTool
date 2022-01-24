package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.BackLog;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.exceptions.ApplicationCheckedException;
import io.agileintelligence.ppmtool.exceptions.ProjectNotFoundException;
import io.agileintelligence.ppmtool.repository.BacklogRepository;
import io.agileintelligence.ppmtool.repository.ProjectRepository;
import io.agileintelligence.ppmtool.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.agileintelligence.ppmtool.constants.CommonCoreConstants.BLANK;
import static io.agileintelligence.ppmtool.constants.CommonCoreConstants.MINUS;

@Service
public class ProjectTaskService {
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {

        try {
            // PTs to be added to a specific Project, project != null: existed project and BackLog also exists
            // BackLog backLog = backlogRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
            BackLog backLog = projectService.findProjectByIdentifier(projectIdentifier, username).getBackLog();

            // Exceptions: Project not found
            if (backLog == null) {
                throw new ProjectNotFoundException("Project with ID: " + projectIdentifier + " not found.");
            }
            // set the BackLog to the ProjectTask: => projectSequence ???
            projectTask.setBackLog(backLog);

            // We want project sequence to be like this: IDPRO-1  IDPRO-2 ... 100 101
            Integer backLogSeq = backLog.getPTSequence();
            // Update the BackLog Sequence:
            backLogSeq++;

            backLog.setPTSequence(backLogSeq);

            // Add seq to the project task
            projectTask.setProjectSequence(projectIdentifier + MINUS + backLogSeq);
            projectTask.setProjectIdentifier(projectIdentifier);

            // In the future we need to projectTask.getPriority == 0 to handle to UI Form
            // Initial priority: when priority is null (low-medium-high => to group task by priority)
            if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
                projectTask.setPriority(3);
            }

            // Initial Status when status is null
            if (null == projectTask.getStatus() || BLANK.equals(projectTask.getStatus())) {
                projectTask.setStatus("TO_DO");
            }

            return projectTaskRepository.save(projectTask);
        } catch (Exception e) {
            throw new ApplicationCheckedException(e.getMessage());
        }


    }

    public Iterable<ProjectTask> findBackLogByProjectID(String id) {
        Project project = projectRepository.findByProjectIdentifier(id);

        if (null == project) throw new ProjectNotFoundException(" Project with ID: " + id + " does not exist.");

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findProjectTaskByProjectSequence(String projectID, String projectTaskSequenceID) {
        // Ensure: searching on the right backlog
        BackLog backLog = backlogRepository.findByProjectIdentifier(projectID);
        if (null == backLog) {
            throw new ProjectNotFoundException(" Project with ID : '" + projectID + "' does not exist.");
        }

        // make sure that our project task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectTaskSequenceID);
        if (null == projectTask) {
            throw new ProjectNotFoundException(" Project task with ID '" + projectTaskSequenceID + "' do not exist");
        }

        // make sure that the backlog/project id in the path corresponds to the right project
        if (!projectTask.getBackLog().getProjectIdentifier().equals(backLog.getProjectIdentifier())) {
            throw new ApplicationCheckedException(" Project Task with ID '" + projectTaskSequenceID + "' do not exist in Project with ID '" + projectID + "'");
        }


        return projectTask;
    }

    public ProjectTask updateProjectTaskByProjectSequence(ProjectTask updatedProjectTask, String projectID, String projectTaskSequenceID) {
        // Validation: exist and consistency
        ProjectTask projectTask = findProjectTaskByProjectSequence(projectID, projectTaskSequenceID);
        updatedProjectTask.setId(projectTask.getId());
        updatedProjectTask.setBackLog(projectTask.getBackLog());

        return projectTaskRepository.save(updatedProjectTask);
    }

    public void deleteProjectTaskByProjectSequence(String projectID, String projectTaskSequenceID) {
        // Validation:
        ProjectTask projectTask = findProjectTaskByProjectSequence(projectID, projectTaskSequenceID);

        projectTaskRepository.deleteById(projectTask.getId());
    }
}
