/*
 *
 * LoginPage actions
 *
 */

import { LOGIN_FAIL, LOGIN_REQUEST, LOGIN_SUCCESS } from './constants';


/**
 *
 * @param username
 * @param password
 * @returns {{type: string, username: *, password: *}}
 */
export function loginRequest(username, password) {
  return {
    type: LOGIN_REQUEST,
    username,
    password,
  };
}

/**
 *
 * @param status
 * @returns {{type: string, status: *}}
 */
export function loginSucess(status) {
  return {
    type: LOGIN_SUCCESS,
    status,
  };
}

/**
 *
 * @param error
 * @returns {{type: string, error: *}}
 */
export function loginFail(error) {
  return {
    type: LOGIN_FAIL,
    error,
  };
}
