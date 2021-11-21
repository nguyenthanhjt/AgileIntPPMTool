package io.agileintelligence.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // this field cannot be updated, cannot be duplicated but this will be auto-generated => not update
    @Column(updatable = false, unique = true)
    private String projectSequence;
    @NotBlank(message = " Please include a project summary")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority; // group tasks base-on priority
    private Date dueDate;
    @Column(updatable = false)
    private String projectIdentifier;
    private Date creat_At;
    private Date update_At;

    // ManyToOne with BackLog:  a task can be long to one BackLog, and a BackLog can have many tasks
    // CascadeType.REFRESH: Can delete the projectTask that belong to a BackLog => refresh that BackLog and tell us the Project task no longer exist
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "backLog_id", updatable = false, nullable = false)
    @JsonIgnore // to avoid the issue infinite recursions: De quy vo han
    private BackLog backLog;

    public ProjectTask() {
    }

    @PrePersist
    protected void onCreate() {
        this.creat_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.update_At = new Date();
    }

    @Override
    public String toString() {
        return "ProjectTask{" +
                "id=" + id +
                ", projectSequence='" + projectSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", creat_At=" + creat_At +
                ", update_At=" + update_At +
                '}';
    }

    public BackLog getBackLog() {
        return backLog;
    }

    public void setBackLog(BackLog backLog) {
        this.backLog = backLog;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectSequence() {
        return projectSequence;
    }

    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Date getCreat_At() {
        return creat_At;
    }

    public void setCreat_At(Date creat_At) {
        this.creat_At = creat_At;
    }

    public Date getUpdate_At() {
        return update_At;
    }

    public void setUpdate_At(Date update_At) {
        this.update_At = update_At;
    }
}
