/*
 * HomeReducer
 *
 * The reducer takes care of our data. Using actions, we can change our
 * application state.
 * To add a new action, add it to the switch statement in the reducer function
 *
 * Example:
 * case YOUR_ACTION_CONSTANT:
 *   return state.set('yourStateVariable', true);
 */
import { fromJS } from 'immutable';

import { FETCH_REQUEST_INTERVIEW, FETCH_REQUEST_INTERVIEW_ERROR, FETCH_REQUEST_INTERVIEW_SUCCESS } from './constants';

// The initial state of the App
export const initialState = fromJS({
  request_interviews: [],
  loading: false,
  error:false
});

function homeReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_REQUEST_INTERVIEW:
      return state
        .set("loading",true)
        .set("error",false)
    case FETCH_REQUEST_INTERVIEW_SUCCESS:
      return state
        .set("loading",false)
        .set("request_interviews",action.request);
    case FETCH_REQUEST_INTERVIEW_ERROR:
      console.log("redux",action.error)
      return state
        .set("error",action.error)
        .set("loading",false)

    default:
      return state;
  }
}

export default homeReducer;
