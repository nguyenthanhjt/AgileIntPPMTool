import axios from "axios";

export const addProjectTask =
  (backLogID, projectTask, history) => async (dispatch) => {
      await axios.post(`/api/backlog/${backLogID}`,projectTask);
      history.push(`/projectBoard/${backLogID}`);
  };
