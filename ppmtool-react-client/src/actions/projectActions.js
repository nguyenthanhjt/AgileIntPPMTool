import axios from "axios";
import { GET_ERRORS, GET_PROJECTS } from "./types";

// create project method
export const createProject = (project, history) => async dispatch => {
  // history: allow us to redirect once we submit the form
  // use: async that the function always return a promise
  // use: await that means JS will wait until that promise settles and returns its results

  // handle error
  try {
    const res = await axios.post("http://localhost:8080/api/project", project); // pass a valid project object
    history.push("/dashboard"); // redirect to dashboard to see the result
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

// Get all project function
export const getProjects = () => async dispatch => {
  const res = await axios.get("http://localhost:8080/api/project/all");
  dispatch({
    type: GET_PROJECTS,
    payload: res.data // Get data from DB and passing onto the projectReducer
  })
}