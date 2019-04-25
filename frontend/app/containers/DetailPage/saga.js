import { call, put,take, select, takeLatest } from 'redux-saga/effects';
import { fetchRequestInterviewFail, fetchRequestInterviewSuccess } from './actions';
import { FETCH_REQUEST_INTERVIEW } from './constants';
import request from 'utils/request';
import { selectIdRequest } from './selectors';

export function* getRequestInterviewFromApi(action) {
  const idRequest = action.idRequest;
  const requestURL = `http://5c87325bcc034a0014bd267a.mockapi.io/test/${idRequest}`;

  try {
    const request_interviews = yield call(request, requestURL);
    yield put(fetchRequestInterviewSuccess(request_interviews));
  } catch (error) {
    yield put(fetchRequestInterviewFail(error.toString()));
  }
}

/**
 * Root saga manages watcher lifecycle
 */
export default function* detailPage() {
  yield takeLatest(FETCH_REQUEST_INTERVIEW, getRequestInterviewFromApi);
}
