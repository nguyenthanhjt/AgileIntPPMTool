import React, { Component } from "react";

 class ProjectTask extends Component {
  render() {
    const {projectTask} = this.props;

    return (
      <div className="card mb-1 bg-light">
        <div className="card-header text-primary">
          <p className="ds"><b>ID:{projectTask.projectSequence}</b></p>
          <p className="ds">
          Priority: {projectTask.priority}
          </p>
          
          
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{projectTask.summary}</h5>
          <p className="card-text text-truncate ">
            {projectTask.acceptanceCriteria}
          </p>
          <a href="/b" className="btn btn-primary">
            View / Update
          </a>

          <button className="btn btn-danger ml-4">Delete</button>
        </div>
      </div>
    );
  }
}
export default ProjectTask;