import React, { Component } from "react";
import ProjectTask from "./ProjectTask/ProjectTask";

class BackLog extends Component {
  render() {
    const { projectTaskList } = this.props;

    const tasks = projectTaskList.map(projectTask => (
      <ProjectTask key={projectTask.id} projectTask={projectTask} />
    ));

    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>
            {
              // insert tasks here
              tasks
            }
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {
              
            }
          </div>

          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {
              
            }
          </div>
        </div>
      </div>
    );
  }
}
export default BackLog;
