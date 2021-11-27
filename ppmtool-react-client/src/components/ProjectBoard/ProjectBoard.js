import React, { Component } from "react";
import { Link } from "react-router-dom";
import BackLog from "./BackLog";
import { connect } from "react-redux"; //  connect store to state
import PropTypes from "prop-types";
import { getBackLog } from "../../actions/backlogActions";

class ProjectBoard extends Component {

  // constructor to handle errors
  componentDidMount(){
    const {id} = this.props.match.params;
    this.props.getBackLog(id);
  }

  render() {
    const { id } = this.props.match.params;
    return (
      <div className="container">
        <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />
        <BackLog />
      </div>
    );
  }
}
ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBackLog: PropTypes.func.isRequired
}

const mapStateToProps = (state) => ({
  backlog: state.backlog,
});

export default connect(mapStateToProps, { getBackLog })(ProjectBoard);
