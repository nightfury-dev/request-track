/**
 * Detail Page selectors
 */

import { createSelector } from 'reselect';
import { initialState } from './reducer';

const selectDetailPage = state => state.get('detailPage', initialState);

const selectRequestInterview = () =>
  createSelector(selectDetailPage, detailState => detailState.get('request_detail'));

const selectIdRequest = () =>
  createSelector(selectDetailPage, detailState => detailState.get('idRequest'));

const selectErrorRequest = () =>
  createSelector(selectDetailPage, detailState => detailState.get('error'));

const selectLoading = () =>
  createSelector(selectDetailPage, detailState => detailState.get('loading'));

export { selectDetailPage, selectRequestInterview, selectErrorRequest, selectLoading, selectIdRequest };
