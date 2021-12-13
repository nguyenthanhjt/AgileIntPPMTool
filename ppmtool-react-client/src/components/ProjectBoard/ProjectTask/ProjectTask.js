import React, { Component } from "react";
import { Link } from "react-router-dom";
import { deleteProjectTask } from "../../../actions/backlogActions";
import propTypes from "prop-types";
import { connect } from "react-redux";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";

class ProjectTask extends Component {
  deleteSubmit(projectID, projectTaskSeq) {
    confirmAlert({
      title: "Confirm to submit",
      message: `Are you sure to delete this project task with ID: ${projectTaskSeq}`,
      buttons: [
        {
          label: "Yes",
          onClick: this.props.deleteProjectTask(projectID, projectTaskSeq),
        },
        {
          label: "No",
          onClick: () => {},
        },
      ],
    });
  }

  onDeleteClick(projectID, projectTaskSeq) {
    this.props.deleteProjectTask(projectID, projectTaskSeq);
  }

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
            Update
          </Link>
          <button
            className="btn btn-danger ml-4"
            onClick={this.onDeleteClick.bind(
              this,
              projectTask.projectIdentifier,
              projectTask.projectSequence
            )}
          >
            Delete
          </button>
          <button
            className="btn btn-danger ml-4"
            onClick={this.deleteSubmit.bind(
              this,
              projectTask.projectIdentifier,
              projectTask.projectSequence
            )}
          >
            Delete pop up
          </button>
        </div>
      </div>
    );
  }
}

ProjectTask.propTypes = {
  deleteProjectTask: propTypes.func.isRequired,
};

export default connect(null, { deleteProjectTask })(ProjectTask);
