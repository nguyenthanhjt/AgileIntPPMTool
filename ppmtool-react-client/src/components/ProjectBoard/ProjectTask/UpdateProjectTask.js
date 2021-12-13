import React, { Component } from "react";
import { Link } from "react-router-dom";
import classnames from "classnames";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import {
  updateProjectTask,
  getProjectTask,
} from "../../../actions/backlogActions";
import Moment from "moment";

class UpdateProjectTask extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: "",
      projectSequence: "",
      summary: "",
      acceptanceCriteria: "",
      status: "",
      priority: "",
      dueDate: "",
      projectIdentifier: "",
      createAt: "",
      errors: {}
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    const {
      id,
      projectSequence,
      summary,
      acceptanceCriteria,
      status,
      priority,
      dueDate,
      projectIdentifier,
      createAt,
    } = nextProps.projectTask;

    this.setState({
      id,
      projectSequence,
      summary,
      acceptanceCriteria,
      status,
      priority,
      dueDate,
      projectIdentifier,
      createAt,
    });

    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  componentDidMount() {
    const { projectID, projectTaskSequence } = this.props.match.params;
    this.props.getProjectTask(
      projectID,
      projectTaskSequence,
      this.props.history
    );
  }
  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();

    const updateProjectTask = {
      id: this.state.id,
      projectSequence: this.state.projectSequence,
      summary: this.state.summary,
      acceptanceCriteria: this.state.acceptanceCriteria,
      status: this.state.status,
      priority: this.state.priority,
      dueDate: this.state.dueDate,
      projectIdentifier: this.state.projectIdentifier,
      createAt: this.state.createAt,
    };

    this.props.updateProjectTask(
      this.state.projectIdentifier, //this.props.match.params.projectID, // 
      this.state.projectSequence, // this.props.projectTask.projectSequence, // 
      updateProjectTask,
      this.props.history
    );
  }

  render() {
    const { errors } = this.state;
    const { projectID } = this.props.match.params;

    return (
      <div className="add-PBI">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <Link to={`/projectBoard/${projectID}`} className="btn btn-light">
                Back to Project Board
              </Link>
              <h4 className="display-4 text-center">Update Project Task</h4>
              <p className="lead text-center">{`${this.state.projectSequence}`}</p>
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.summary,
                    })}
                    name="summary"
                    placeholder="Project Task summary"
                    value={this.state.summary}
                    onChange={this.onChange}
                  />
                  {errors.summary && (
                    <div className="invalid-feedback">{errors.summary}</div>
                  )}
                </div>
                <div className="form-group">
                  <textarea
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.acceptanceCriteria,
                    })}
                    placeholder="Acceptance Criteria"
                    name="acceptanceCriteria"
                    value={this.state.acceptanceCriteria}
                    onChange={this.onChange}
                  ></textarea>
                  {errors.acceptanceCriteria && (
                    <div className="invalid-feedback">
                      {errors.acceptanceCriteria}
                    </div>
                  )}
                </div>
                <h6>Due Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="dueDate"
                    value={Moment(this.state.dueDate).format("YYYY-MM-DD")}
                    onChange={this.onChange}
                  />
                </div>
                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="priority"
                    value={this.state.priority}
                    onChange={this.onChange}
                  >
                    <option value={0}>Select Priority</option>
                    <option value={1}>High</option>
                    <option value={2}>Medium</option>
                    <option value={3}>Low</option>
                  </select>
                  {errors.priority && (
                    <div className="invalid-feedback">{errors.priority}</div>
                  )}
                </div>

                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="status"
                    value={this.state.status}
                    onChange={this.onChange}
                  >
                    <option value="">Select Status</option>
                    <option value="TO_DO">TO DO</option>
                    <option value="IN_PROGRESS">IN PROGRESS</option>
                    <option value="DONE">DONE</option>
                  </select>
                  {errors.status && (
                    <div className="invalid-feedback">{errors.status}</div>
                  )}
                </div>

                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

UpdateProjectTask.propTypes = {
  projectTask: PropTypes.object.isRequired,
  getProjectTask: PropTypes.func.isRequired,
  updateProjectTask: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = (state) => ({
  projectTask: state.backlog.projectTask,
  errors: state.errors
});
export default connect(mapStateToProps, { getProjectTask, updateProjectTask })(
  UpdateProjectTask
);
