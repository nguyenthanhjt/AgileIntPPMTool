import {
  GET_BACKLOG,
  GET_PROJECT_TASK,
  DELETE_PROJECT_TASK,
} from "../actions/types";

const initialState = {
  projectTasks: [],
  projectTask: {},
};

export default function backLogActions(state = initialState, action) {
  switch (action.type) {
    case GET_BACKLOG:
      return {
        ...state,
        projectTasks: action.payload,
      };

    case GET_PROJECT_TASK:
      return {
        ...state,
        projectTask: action.payload,
      };
    case DELETE_PROJECT_TASK:
      return {
        ...state,

        // TODO: implement logic here
        // after delete ProjecTask: do filter() to remove the deleted ProjecTask in ProjectBoard 
        // with projectTaskSeq(setting in backLogReducer.js deleteProjectTask function) in payload:
        // compare it to projectTask.projectTaskSeq of projectTaskList in state
        projectTasks: state.projectTasks.filter(
          (projectTask) => projectTask.projectSequence !== action.payload
        ),
      };

    default:
      return state;
  }
}
