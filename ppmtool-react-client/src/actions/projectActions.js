import axios from "axios";
import { GET_ERRORS, GET_PROJECT, GET_PROJECTS, DELETE_PROJECT } from "./types";

// create project method
export const createProject = (project, history) => async (dispatch) => {
  // history: allow us to redirect once we submit the form
  // use: async that the function always return a promise
  // use: await that means JS will wait until that promise settles and returns its results

  // handle error
  try {
    const res = await axios.post("/api/project", project); // pass a valid project object
    history.push("/dashboard"); // redirect to dashboard to see the result
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  }
};

// Get all project function
export const getProjects = () => async (dispatch) => {
  const res = await axios.get("/api/project/all");
  dispatch({
    type: GET_PROJECTS,
    payload: res.data, // Get data from DB and passing onto the projectReducer
  });
};

//
export const getProject = (id, history) => async (dispatch) => {
  try {
    const res = await axios.get(`/api/project/${id}`);
    dispatch({
      type: GET_PROJECT,
      payload: res.data,
    });
  } catch (error) {
    history.push("/dashboard");
  }
};

export const deleteProject = (id) => async (dispatch) => {
  if (
    window.confirm(
      "Are you sure ? This will delete the project and all the data related to it"
    )
  ) {
    try {
      const res = await axios.delete(`/api/project/${id}`);
      dispatch({
        type: DELETE_PROJECT,
        payload: id,
      });
    } catch (error) {
      console.log("Delete error" + id);
    }
  }
};
