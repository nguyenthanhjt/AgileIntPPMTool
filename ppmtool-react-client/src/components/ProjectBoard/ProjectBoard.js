import React, { Component } from "react";
import { Link } from "react-router-dom";
import BackLog from "./BackLog";
import { connect } from "react-redux"; //  connect store to state
import PropTypes from "prop-types";
import { getBackLog } from "../../actions/backlogActions";

class ProjectBoard extends Component {
  // constructor to handle errors
  constructor() {
    super();

    this.state = {
      errors: {},
    };
  }

  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBackLog(id);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({
        errors: nextProps.errors,
      });
    }
  }

  render() {
    // use JS Destructuring
    const { id } = this.props.match.params;
    const { projectTasks } = this.props.backlog;
    const { errors } = this.props;

    let BoardContent;

    const boardAlgorithm = (errors, projectTasks) => {
      if (projectTasks.length < 1) {
        if (errors.message) {
          return (
            <div className="alert alert-danger text-center" role="alert">
              {errors.message}
            </div>
          );
        } else {
          return (
            <div className="alert-info text-center" role="alert">
              No Project Tasks on this board
            </div>
          );
        }
      } else {
        return <BackLog projectTaskList={projectTasks} />;
      }
    };

    BoardContent = boardAlgorithm(errors, projectTasks);

    return (
      <div className="container">
        <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />
        {BoardContent}
      </div>
    );
  }
}
ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBackLog: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  backlog: state.backlog,
  errors: state.errors,
});

export default connect(mapStateToProps, { getBackLog })(ProjectBoard);
