import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject } from "../../actions/projectActions";
import classNames from "classnames";

class AddProject extends Component {
  constructor() {
    super();
    this.state = {
      projectName: "",
      projectIdentifier: "",
      description: "",
      start_date: "",
      end_date: "",
      errors: {},
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  // Life cycle hooks
  // basically when have errors, because the submit failed,
  // we have new props come in, which is that errors object, that we actually declared in PropTypes.
  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      // if next props has errors,
      // grab error object in the component, load it from the new props
      this.setState({ errors: nextProps.errors }); 
    }
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const newProject = {
      projectName: this.state.projectName,
      projectIdentifier: this.state.projectIdentifier,
      description: this.state.description,
      start_date: this.state.start_date,
      end_date: this.state.end_date,
    };
    this.props.createProject(newProject, this.props.history);
  }

  render() {
    // extract errors from state
    const {errors} = this.state;

    return ( 
      <div>
        <h1>Add Project Form</h1>

        {
          // check name atribute input fields
          // Create constructor
          // Set state
          // Set value on input fields
          // Create onChange function
          // set onChange on each input field
          // bind on constructor
          // check state change in the react extension
        }
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Create Project form</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classNames("form-control form-control-lg ",{
                        // whenever there are errors in the projectName, then going to add the 'is-invalid'
                        // to the className: this make Bootstrap know to render the style of <input> for invalid case
                        "is-invalid": errors.projectName  
                      })}
                      placeholder="Project Name"
                      name="projectName"
                      value={this.state.projectName}
                      // onChange={this.onChange.bind(this)}
                      onChange={this.onChange}
                    />
                    {errors.projectName && (
                      <div className="invalid-feedback">{errors.projectName}</div>
                    )}
                    {
                      // <p>{errors.projectName}</p>
                    }
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classNames("form-control form-control-lg", {
                        "is-invalid": errors.projectIdentifier
                      })}
                      placeholder="Unique Project ID"
                      //disabled
                      name="projectIdentifier"
                      value={this.state.projectIdentifier}
                      onChange={this.onChange}
                    />
                    {
                      errors.projectIdentifier && (
                        <div className="invalid-feedback"> {errors.projectIdentifier}</div>
                      )
                    }
                  </div>
                  <div className="form-group">
                    <textarea
                      className={classNames("form-control form-control-lg",{
                        "is-invalid": errors.description
                      })
                    }
                      placeholder="Project Description"
                      name="description"
                      value={this.state.description}
                      onChange={this.onChange}
                    ></textarea>
                    {errors.description && (
                      <div className="invalid-feedback">{ errors.description}</div>
                    )}
                  </div>
                  <h6>Start Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="start_date"
                      value={this.state.start_date}
                      onChange={this.onChange}
                    />
                  </div>
                  <h6>Estimated End Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="end_date"
                      value={this.state.end_date}
                      onChange={this.onChange}
                    />
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
      </div>
    );
  }
}

AddProject.propTypes = {
  // Basically telling React that they create project func is a require functions or a required prop type
  // for this component to work properly
  // => this is a setting like a constraints
  createProject: PropTypes.func.isRequired, // Need to delcare right type: createProject is a function
  errors: PropTypes.object.isRequired, // errors is a object
};

const mapStateToProps = (state) => ({
  errors: state.errors,
});

export default connect(mapStateToProps, { createProject })(AddProject);
// map our State to our component Props once we start getting errors
// at first: null
