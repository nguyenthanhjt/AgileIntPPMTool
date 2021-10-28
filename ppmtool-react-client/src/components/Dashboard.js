import React, { Component } from "react";
import CreateProjectButton from "./Project/CreateProjectButton";
import ProjectItem from "./ProjectItem";
import { connect } from "react-redux"; //  connect store to state
import { getProjects } from "../actions/projectActions";
import PropTypes from "prop-types";

class Dashboard extends Component {
  // Life cycle hook: 
  //this life-cycle hook is basically dictated what needs to happen when we mount the component.
  // Called immediately after a component is mounted. Setting state here will trigger re-rendering.
    componentDidMount(){
        this.props.getProjects(); // call getProject() action  from projectActions.js
    }


    render() {
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              <CreateProjectButton />
              <br />
              <hr />
              <ProjectItem />
            </div>
          </div>
        </div>
      </div>
    );
  }
}
// set up PropType
Dashboard.prototypes = {
  project: PropTypes.object.isRequired,
  getProjects: PropTypes.func.isRequired,
};

// map state to props: state to component property
const mapStateToProps = (state) => ({
  project: state.project,
});
// mapping/connect getProjects() to the store, 
// and then mount it when loading the component- componentDidMount() 
export default connect(mapStateToProps, { getProjects })(Dashboard);
