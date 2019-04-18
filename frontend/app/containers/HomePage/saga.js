/**
 * Gets the repositories of the user from Github
 */

import { call, put, fork,takeLatest,takeEvery } from 'redux-saga/effects';

import request from 'utils/request';
import { fetchRequestInterview, fetchRequestInterviewFail, fetchRequestInterviewSuccess } from './actions';
import { FETCH_REQUEST_INTERVIEW, FETCH_REQUEST_INTERVIEW_SUCCESS } from './constants';
import { selectRequestInterview } from './selectors';
import { createStructuredSelector } from 'reselect';

/**
 * fetch information of request job
 */

export function* getRequestInterviewFromApi() {
  const requestURL = 'http://5c87325bcc034a0014bd267a.mockapi.io/test';

  try {
    const request_interviews = yield call(request, requestURL);

    yield put(fetchRequestInterviewSuccess(request_interviews))
  } catch (error) {
    console.log("error:", new Error(error))
    yield put(fetchRequestInterviewFail(error.toString()))
  }
}
export function* watchInterview(){
  yield call(getRequestInterviewFromApi)
}

/**
 * Root saga manages watcher lifecycle
 */
export default function* job() {
  // yield fork(getRequestInterviewFromApi);
  yield takeLatest(FETCH_REQUEST_INTERVIEW,watchInterview)
}
