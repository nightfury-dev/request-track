import {
  FETCH_REQUEST_INTERVIEW,
  FETCH_REQUEST_INTERVIEW_ERROR,
  FETCH_REQUEST_INTERVIEW_SUCCESS,
} from './constants';

/**
 * start get fetch request interview job
 * @param idRequest : id of request
 * @returns {{type: string}}
 */

export function fetchRequestInterview(idRequest) {
  return {
    type: FETCH_REQUEST_INTERVIEW,
    idRequest,
  };
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
  return {
    type: FETCH_REQUEST_INTERVIEW_ERROR,
    error,
  };
}
