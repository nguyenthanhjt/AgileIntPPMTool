package io.agileintelligence.ppmtool.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    // Backlog only belong to a specific project
    /* OneTOMany: One BackLog can have one or more ProjectTasks,
    but a ProjectTask at least in the scope of this project, can only belong ot one BackLog*/

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
