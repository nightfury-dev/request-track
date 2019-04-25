/*
 *
 * DetailPage reducer
 *
 */

import { fromJS } from 'immutable';
import {
  FETCH_REQUEST_INTERVIEW,
  FETCH_REQUEST_INTERVIEW_ERROR,
  FETCH_REQUEST_INTERVIEW_SUCCESS,
} from './constants';

export const initialState = fromJS({
  request_detail: {},
  idRequest: false,
  loading: false,
  error: false,
});

function detailPageReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_REQUEST_INTERVIEW:
      return state
        .set('loading', true)
        .set('error', false)
        .set('idRequest', action.idRequest);
    case FETCH_REQUEST_INTERVIEW_SUCCESS:
      return state
        .set('loading', false)
        .set('request_detail', action.request);
    case FETCH_REQUEST_INTERVIEW_ERROR:
      return state
        .set('error', action.error)
        .set('loading', false);

    default:
      return state;
  }
}

export default detailPageReducer;
