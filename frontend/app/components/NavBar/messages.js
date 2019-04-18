/*
 * NavBar Messages
 *
 * This contains all the text for the NavBar component.
 */

import { defineMessages } from 'react-intl';

export const scope = 'app.components.NavBar';

export default defineMessages({
  logo: {
    id: `${scope}.logo`,
    defaultMessage: 'RMS',
  },
  home: {
    id: `${scope}.home`,
    defaultMessage: 'Home',
  },
  dashBoard: {
    id: `${scope}.dashBoard`,
    defaultMessage: 'DashBoard',
  },
  titleNavListRe: {
    id: `${scope}.titleNavListRe`,
    defaultMessage: 'MANAGE RECRUMENT',
  },
  itemNavJob: {
    id: `${scope}.itemNavJob`,
    defaultMessage: 'Job',
  },
  itemNavUser: {
    id: `${scope}.itemNavUser`,
    defaultMessage: 'User',
  },
  itemNavApply: {
    id: `${scope}.itemNavAppy`,
    defaultMessage: 'Apply',
  },
  itemNavCalendar: {
    id: `${scope}.itemNavCalendar`,
    defaultMessage: 'Calendar',
  },
  itemNavEmail: {
    id: `${scope}.itemNavEmail`,
    defaultMessage: 'Email',
  },
});
