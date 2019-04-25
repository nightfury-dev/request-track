/*
 * DetailPage Messages
 *
 * This contains all the text for the DetailPage container.
 */

import { defineMessages } from 'react-intl';

export const scope = 'app.containers.DetailPage';

export default defineMessages({
  header: {
    id: `${scope}.header`,
    defaultMessage: 'This is the DetailPage container!',
  },

  //container : request
  titleContainerRequest: {
    id: `${scope}.title.request`,
    defaultMessage: 'Request Information',
  },
  requestName: {
    id: `${scope}.request.name`,
    defaultMessage: 'Name',
  },
  requestQuantity: {
    id: `${scope}.request.quantity`,
    defaultMessage: 'Quantity',
  },
  requestDeadline: {
    id: `${scope}.request.deadline`,
    defaultMessage: 'Deadline',
  },
  requestStatus: {
    id: `${scope}.request.status`,
    defaultMessage: 'Status',
  },
  requestOffice: {
    id: `${scope}.request.office`,
    defaultMessage: 'Office',
  },
  requestDivision: {
    id: `${scope}.request.division`,
    defaultMessage: 'Division',
  },

  //container: step
  titleContainerStep: {
    id: `${scope}.title.step`,
    defaultMessage: 'Steps',
  },

  //container: trainee
  titleContainerListTrainee: {
    id: `${scope}.title.list`,
    defaultMessage: 'List trainee',
  },

  //other
  noData: {
    id: `${scope}.other.nodata`,
    defaultMessage: 'x No data',
  },
  loading: {
    id: `${scope}.other.loading`,
    defaultMessage: 'Loading data... , please wait',
  },
  edit: {
    id: `${scope}.button.edit`,
    defaultMessage: 'Edit',
  },
});
