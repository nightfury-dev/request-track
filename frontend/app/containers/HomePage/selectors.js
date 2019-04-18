/**
 * Homepage selectors
 */

import { createSelector } from 'reselect';
import { initialState } from './reducer';

const selectHome = state => state.get('home', initialState);

const selectRequestInterview = () =>
  createSelector(selectHome, homeState => homeState.get('request_interviews'));

const selectErrorRequest = () =>
  createSelector(selectHome, homeState => homeState.get('error'));

const selectLoading = () =>
  createSelector(selectHome, homeState => homeState.get('loading'));

export { selectHome, selectRequestInterview, selectErrorRequest, selectLoading };
