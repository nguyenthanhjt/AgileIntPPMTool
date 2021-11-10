package io.agileintelligence.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class BackLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // basically the sequence of project tasks within backlog
    // to start working on the relationship, the entity relationship between these three classes,
    private Integer PTSequence = 0;
    private String projectIdentifier;

    public BackLog() {
    }

    public BackLog(long id, Integer PTSequence, String projectIdentifier) {
        this.id = id;
        this.PTSequence = PTSequence;
        this.projectIdentifier = projectIdentifier;
    }

    // OneToOne with the project: one Project has one Back-log
    // MappedBy ='project': the same attribute name that we have to give the project object on the BackLog side.
    // basically Lazy: doesn't load the relationships unless it is explicitly requested, while Eager actually load them all.
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;
    // Backlog only belong to a specific project
    /* OneTOMany: One BackLog can have one or more ProjectTasks,
    but a ProjectTask at least in the scope of this project, can only belong ot one BackLog*/

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getPTSequence() {
        return PTSequence;
    }

    public void setPTSequence(Integer PTSequence) {
        this.PTSequence = PTSequence;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
