/*
 * Home Actions
 *
 * Actions change things in your application
 * Since this boilerplate uses a uni-directional data flow, specifically redux,
 * we have these actions which are the only way your application interacts with
 * your application state. This guarantees that your state is up to date and nobody
 * messes it up weirdly somewhere.
 *
 * To add a new Action:
 * 1) Import your constant
 * 2) Add a function like this:
 *    export function yourAction(var) {
 *        return { type: YOUR_ACTION_CONSTANT, var: var }
 *    }
 */

import { FETCH_REQUEST_INTERVIEW, FETCH_REQUEST_INTERVIEW_ERROR, FETCH_REQUEST_INTERVIEW_SUCCESS } from './constants';

/**
 * start get fetch interview job
 *
 * @returns {{type: string}}
 */
export function fetchRequestInterview() {
  return {
    type:FETCH_REQUEST_INTERVIEW
  }
}

/**
 * Gets all request interview job sucess
 *
 * @param request
 * @returns {{type: string, request: *}}
 */
export function fetchRequestInterviewSuccess(request) {
  return {
    type: FETCH_REQUEST_INTERVIEW_SUCCESS,
    request,
  };
}

/**
 * fetch request and get error
 * @param error
 * @returns {{type: string, error: *}}
 */
export function fetchRequestInterviewFail(error) {
  return{
    type:FETCH_REQUEST_INTERVIEW_ERROR,
    error
  }
}
