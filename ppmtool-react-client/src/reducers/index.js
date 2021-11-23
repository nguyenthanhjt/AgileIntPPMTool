import { combineReducers } from "redux";
import backlogReducer from "./backlogReducer";
import errorReducer from "./errorReducer";
import projectReducer from "./projectReducer";

export default combineReducers({
    errors: errorReducer,
    project: projectReducer,
    backlog: backlogReducer
});
