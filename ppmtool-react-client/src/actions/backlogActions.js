import axios from "axios";
import { GET_ERRORS, GET_PROJECT_TASK, GET_BACKLOG } from "./types";

export const addProjectTask =
  (projectID, projectTask, history) => async (dispatch) => {
    try {
      await axios.post(`/api/backlog/${projectID}`, projectTask);
      history.push(`/projectBoard/${projectID}`);
      dispatch({
        type: GET_ERRORS,
        payload: {},
      });
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        payload: error.response.data,
      });
    }
  };
export const updateProjectTask =
  (projectID, projectTaskSeq, projectTask, history) => async (dispatch) => {
    try {
      await axios.patch(
        `/api/backlog/${projectID}/${projectTaskSeq}`,
        projectTask
      );
      history.push(`/projectBoard/${projectID}`);
      dispatch({
        type: GET_ERRORS,
        payload: {},
      });
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        payload: error.response.data,
      });
    }
  };

export const getBackLog = (projectID) => async (dispatch) => {
  try {
    const response = await axios.get(`/api/backlog/get-backlog/${projectID}`);
    dispatch({
      type: GET_BACKLOG,
      payload: response.data,
    });
  } catch (error) {
    //history.push("/project")
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data,
    });
  }
};

export const getProjectTask =
  (projectID, projectTaskSeq, history) => async (dispatch) => {
    try {
      const response = await axios.get(
        `/api/backlog/get-project-task/${projectID}/${projectTaskSeq}`
      );
      dispatch({
        type: GET_PROJECT_TASK,
        payload: response.data,
      });
    } catch (error) {
      history.push("/projectBoard");
    }
  };
