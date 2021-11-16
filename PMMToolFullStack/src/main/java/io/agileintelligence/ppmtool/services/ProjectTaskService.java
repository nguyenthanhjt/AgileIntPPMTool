package io.agileintelligence.ppmtool.services;

import static io.agileintelligence.ppmtool.constants.CommonCoreContants.MINUS;
import static io.agileintelligence.ppmtool.constants.CommonCoreContants.BLANK;

import io.agileintelligence.ppmtool.domain.BackLog;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.repository.BacklogRepository;
import io.agileintelligence.ppmtool.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
        // Exceptions: Project not found

        // PTs to be added to a specific Project, project != null: existed project and BackLog also exists
        BackLog backLog = backlogRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());

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
        if (projectTask.getPriority() == null) {
            projectTask.setPriority(3);
        }

        // Initial Status when status is null
        if (null == projectTask.getStatus() || BLANK.equals(projectTask.getStatus())) {
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);

    }
}
