/*
 * HomePage Messages
 *
 * This contains all the text for the HomePage component.
 */
import { defineMessages } from 'react-intl';

export const scope = 'boilerplate.containers.HomePage';

export default defineMessages({
  loading: {
    id: `${scope}.loading`,
    defaultMessage: 'Loading to fetch , waiting for it',
  },
  job: {
    id: `${scope}.job`,
    defaultMessage: 'Job',
  },
  addNewButton: {
    id: `${scope}.title.new`,
    defaultMessage: 'Add New',
  },
  urgent: {
    id: `${scope}.title.urgent`,
    defaultMessage: 'Urgent',
  },
  experied: {
    id: `${scope}.title.experied`,
    defaultMessage: 'Experied',
  },
  all: {
    id: `${scope}.title.`,
    defaultMessage: 'All',
  },

  no: {
    id: `${scope}.table.no`,
    defaultMessage: 'No.',
  },
  nameRequest: {
    id: `${scope}.table.name`,
    defaultMessage: 'Name Request',
  },
  language: {
    id: `${scope}.table.language`,
    defaultMessage: 'Language',
  },
  quantity: {
    id: `${scope}.table.quantity`,
    defaultMessage: 'Quantity',
  },
  interview: {
    id: `${scope}.table.interview`,
    defaultMessage: 'Interview',
  },
  trainee: {
    id: `${scope}.table.trainee`,
    defaultMessage: 'Picked Trainee',
  },
  division: {
    id: `${scope}.table.division`,
    defaultMessage: 'Division',
  },
  office: {
    id: `${scope}.table.office`,
    defaultMessage: 'Office',
  },
  status: {
    id: `${scope}.table.status`,
    defaultMessage: 'Status',
  },
  deadline: {
    id: `${scope}.table.deadline`,
    defaultMessage: 'Deadline',
  },
  action: {
    id: `${scope}.table.action`,
    defaultMessage: 'Action',
  },
  searchBranch: {
    id: `${scope}.filter.branch`,
    defaultMessage: 'All Branch',
  },
  searchPosition: {
    id: `${scope}.filter.position`,
    defaultMessage: 'All Position Type',
  },
  searchCategory: {
    id: `${scope}.filter.category`,
    defaultMessage: 'Search Category',
  },
  searchStatus: {
    id: `${scope}.filter.status`,
    defaultMessage: 'Status',
  },
  searchJob: {
    id: `${scope}.filter.job`,
    defaultMessage: 'Enter Your Job',
  },
  searchButton: {
    id: `${scope}.filter.button`,
    defaultMessage: 'Search',
  },
  modalButtonCancel: {
    id: `${scope}.modal.buttonCancel`,
    defaultMessage: 'Cancel',
  },
  name: {
    id: `${scope}.modal.trainee.name`,
    defaultMessage: 'Name',
  },
});
