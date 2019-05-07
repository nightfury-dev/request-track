import { createSelector } from 'reselect';
import { initialState } from './reducer';

/**
 * Direct selector to the loginPage state domain
 */

const selectLoginPageDomain = state => state.get('loginPage', initialState);

/**
 * Other specific selectors
 */

/**
 * Default selector used by LoginPage
 */
const selectLoading = () =>
  createSelector(selectLoginPageDomain, loginState => loginState.get('loading'));
const selectError = () =>
  createSelector(selectLoginPageDomain, loginState => loginState.get('error'));
const selectStatus = () =>
  createSelector(selectLoginPageDomain, loginState => loginState.get('status'));

export default selectLoginPageDomain;
export { selectLoading, selectError, selectStatus };
