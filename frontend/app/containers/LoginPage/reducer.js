/*
 *
 * LoginPage reducer
 *
 */

import { fromJS } from 'immutable';
import { LOGIN_FAIL, LOGIN_REQUEST, LOGIN_SUCCESS } from './constants';

export const initialState = fromJS({
  error: false,
  loading: false,
  status: false,
});

function loginPageReducer(state = initialState, action) {
  switch (action.type) {
    case LOGIN_REQUEST:
      return state.set('loading', true)
                  .set('error', false);

    case LOGIN_SUCCESS:
      return state.set('loading', false)
                  .set('error', false)
                  .set('status', action.status);
    case LOGIN_FAIL:
      return state.set('loading', false)
                  .set('error', action.error);
    default:
      return state;
  }
}

export default loginPageReducer;
