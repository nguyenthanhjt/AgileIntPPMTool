import { GET_ERRORS } from "../actions/types";

const initialState = {};

export default function handleError(state = initialState, action) { // at first, do not have error (initialState is null)
  switch (action.type) {
    case GET_ERRORS: // case: get the error 
      return action.payload; // this is going to dispatch to the store of redux
      // => how to hook up this reducer into the store 
      // => bring it to our reducer which is the reducer/index.js: hook

    default: // default action for this reducer
      return state;
  }
}
