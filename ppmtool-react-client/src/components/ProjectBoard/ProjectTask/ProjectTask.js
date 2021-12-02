import React, { Component } from "react";
import { Link } from "react-router-dom";

class ProjectTask extends Component {
  render() {
    const { projectTask } = this.props;
    let priorityString;
    let priorityClass;

    if (projectTask.priority === 1) {
      // change style to a boostrap danger class
      priorityClass = "bg-danger text-light";
      priorityString = "HIGH";
    } else if (projectTask.priority === 2) {
      // change style to a boostrap warning class
      priorityClass = "bg-warning text-light";
      priorityString = "MEDIUM";
    } else if (projectTask.priority === 3) {
      // change style to a boostrap danger class
      priorityClass = "bg-info text-light";
      priorityString = "LOW";
    }

    return (
      <div className="card mb-1 bg-light">
        <div className={`card-header text-primary ${priorityClass}`}>
          <p className="ds">
            ID: <b>{projectTask.projectSequence}</b> {" -- "}
            Priority: <b>{priorityString}</b>
          </p>
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{projectTask.summary}</h5>
          <p className="card-text text-truncate ">
            {projectTask.acceptanceCriteria}
          </p>
          <Link
            to={`/update-project-task/${projectTask.projectIdentifier}/${projectTask.projectSequence}`}
            className="btn btn-primary"
          >
            View / Update
          </Link>
          <button className="btn btn-danger ml-4">Delete</button>
        </div>
      </div>
    );
  }
}
export default ProjectTask;
