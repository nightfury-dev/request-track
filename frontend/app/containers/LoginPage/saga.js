import { call, put, takeLatest } from 'redux-saga/effects';
import request from 'utils/request';
import { urlApi } from 'utils/consonant';

import { LOGIN_REQUEST } from './constants';
import { loginFail, loginSucess } from './actions';

export function* actionLogin(action) {
  try {
    let username = action.username;
    let password = action.password;

    let formData = new FormData();
    formData.append('username', username);
    formData.append('password', password);

    const url = `${urlApi}/login`;
    const header = {
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Accept': 'application/json, application/xml, text/plain, text/html, *.*',
        'Access-Control-Allow-Credentials': true,
      },
      method: 'POST',
      body: formData,
    };
    const loginData = yield call(request, url, header);

    const statusResponsive = loginData.code;
    const messageResponsive = loginData.message;
    if (statusResponsive == 200) {
      const token = loginData.authorization;
      localStorage.setItem('token', token);
      localStorage.setItem('role', "div");

      yield put(loginSucess(messageResponsive));
    } else {
      //error server responsive
      yield put(loginFail(messageResponsive));
    }
  } catch (error) {
    //error while request
    yield put(loginFail(error.toString()));
  }
}

export default function* loginPageSaga() {
  yield takeLatest(LOGIN_REQUEST, actionLogin);
}
