import axios from "axios";
import { GET_ERRORS } from "./types";

export const addProjectTask =
  (backLogID, projectTask, history) => async (dispatch) => {
    try {
      await axios.post(`/api/backlog/${backLogID}`, projectTask);
      history.push(`/projectBoard/${backLogID}`);
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
