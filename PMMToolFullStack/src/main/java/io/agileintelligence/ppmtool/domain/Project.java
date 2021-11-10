package io.agileintelligence.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name is required")
    @org.hibernate.validator.constraints.NotBlank
    private String projectName;

    @NotBlank(message = "Project Identifier is required")
    @Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;

    @NotBlank(message = " Project description is required")
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date start_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date end_date;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    // One backlog to one project: one project has one backlog
    // EAGER: that way when we load a project object, then the backlog information is readily, EAGER loads all the relationship
    /*CascadeType.All: owning side of the relationship, meaning that if  delete the project:
    then everything that's a child to the project, in this case is going to be the backlog And obviously the project tasks.
    => everything should go away/deleted whenever I delete a project.
    <=> But if delete a backlog or anything downstream => It do not affect the Project
    Every time there's a new project created, it creates the backlog automatically for that project.*/
    // MappedBy ='project': the same attribute name that we have to give the project object on the BackLog side.
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL , mappedBy = "project")
    private BackLog backLog;

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    public BackLog getBackLog() {
        return backLog;
    }

    public void setBackLog(BackLog backLog) {
        this.backLog = backLog;
    }

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
        //this.created_At = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }

}
