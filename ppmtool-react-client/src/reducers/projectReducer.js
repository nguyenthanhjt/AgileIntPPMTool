// import GET_PROJECTS from types.js
import { GET_PROJECTS } from "../actions/types";

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
    default:
      return state;
  }
}
// this is going to dispatch to the store of redux
// => how to hook up this reducer into the store 
// => bring it to our reducer which is the reducer/index.js: hook