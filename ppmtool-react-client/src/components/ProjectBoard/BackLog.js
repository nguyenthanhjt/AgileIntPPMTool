import React, { Component } from "react";
import ProjectTask from "./ProjectTask/ProjectTask";

class BackLog extends Component {
  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>
            <ProjectTask/>
            
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {
              //<!-- SAMPLE PROJECT TASK STARTS HERE -->
              //<!-- SAMPLE PROJECT TASK ENDS HERE -->
            }
          </div>

          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {
              //<!-- SAMPLE PROJECT TASK STARTS HERE -->
              //<!-- SAMPLE PROJECT TASK ENDS HERE -->
            }
          </div>
        </div>
      </div>
    );
  }
}
export default BackLog;
