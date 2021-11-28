import React, { Component } from "react";
import ProjectTask from "./ProjectTask/ProjectTask";

class BackLog extends Component {
  render() {
    const { projectTaskList } = this.props;

    // extract from props
    const tasks = projectTaskList.map((projectTask) => (
      <ProjectTask key={projectTask.id} projectTask={projectTask} />
    ));

    // filtering the project task by status/priority
    let todoItems = tasks.filter(
      (task) => task.props.projectTask.status === "TO_DO"
    );
    let inProgressItems = tasks.filter(
      (task) => task.props.projectTask.status === "IN_PROGRESS"
    );
    let doneItems = tasks.filter(
      (task) => task.props.projectTask.status === "DONE"
    );

    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>
            {todoItems}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {inProgressItems}
          </div>

          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {doneItems}
          </div>
        </div>
      </div>
    );
  }
}
export default BackLog;
