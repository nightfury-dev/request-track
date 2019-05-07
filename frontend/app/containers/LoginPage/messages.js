/*
 * LoginPage Messages
 *
 * This contains all the text for the LoginPage container.
 */

import { defineMessages } from 'react-intl';

export const scope = 'app.containers.LoginPage';

export default defineMessages({
  header: {
    id: `${scope}.header`,
    defaultMessage: 'This is the LoginPage container!',
  },
  titlePage: {
    id: `${scope}.page.title`,
    defaultMessage: 'Login Page',
  },
  title: {
    id: `${scope}.title`,
    defaultMessage: 'Login',
  },
  username: {
    id: `${scope}.username`,
    defaultMessage: 'Email',
  },
  password: {
    id: `${scope}.password`,
    defaultMessage: 'Password',
  },
  buttonLogin: {
    id: `${scope}.button.login`,
    defaultMessage: 'Login',
  },
  buttonLoginOther: {
    id: `${scope}.button.loginwsm`,
    defaultMessage: 'Login with WSM',
  },

  //state of loading
  statusLoading: {
    id: `${scope}.status.loading`,
    defaultMessage: 'Loading........',
  },

  //error pass/email
  statusWrongLogin: {
    id: `${scope}.status.wrong`,
    defaultMessage: 'wrong mail/pass',
  },
});
