// import GET_PROJECTS from types.js
import { DELETE_PROJECT, GET_PROJECT, GET_PROJECTS } from "../actions/types";

const initialState = {
  projects: [], // array object
  project: {}, // single object
};

export default function getAllProjects(state = initialState, action) {
  switch (action.type) {
    case GET_PROJECTS: // in case, GET_PROJECTS
      return {
        // return the state and going to load the projects list
        // with the pay load that getting from the server
        ...state,
        projects: action.payload,
      };

    case GET_PROJECT:
      return {
        ...state,
        project: action.payload,
      };
    case DELETE_PROJECT:
      return {
        ...state,
        // array of projects; filter the project
        projects: state.projects.filter(
          (project) => project.projectIdentifier !== action.payload
        ),
      };
    default:
      return state;
  }
}
// this is going to dispatch to the store of redux
// => how to hook up this reducer into the store
// => bring it to our reducer which is the reducer/index.js: hook
