package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.BackLog;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.repository.BacklogRepository;
import io.agileintelligence.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase(Locale.ROOT));

            if (null == project.getId()) {
                BackLog backLog = new BackLog();
                project.setBackLog(backLog);
                backLog.setProject(project);
                backLog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }
            if (null != project.getId()) {
                project.setBackLog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

            //logic
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project Id '" + project.getProjectIdentifier().toUpperCase(Locale.ROOT) + "' already exists.");
        }
    }

    public Project updateProject(Project project) {
        Project currentProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
        if (currentProject == null) {
            throw new ProjectIdException("Project Id '" + project.getProjectIdentifier() + "' does not exists.");
        }
        project.setBackLog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));

        return projectRepository.save(project);
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase(Locale.ROOT));
        if (project == null) {
            throw new ProjectIdException("Project Id '" + projectId + "' does not exists.");
        }

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectById(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase(Locale.ROOT));
        if (project == null) {
            throw new ProjectIdException("Cannot project with Id '" + projectId + "'. This project does not exists. ");
        }
        projectRepository.delete(project);
    }
}
