/*
 * HomePage Messages
 *
 * This contains all the text for the HomePage component.
 */
import { defineMessages } from 'react-intl';

export const scope = 'boilerplate.components.Header';

export default defineMessages({
  search: {
    id: `${scope}.search`,
    defaultMessage: 'Search',
  },
  info: {
    id: `${scope}.info`,
    defaultMessage: 'Info',
  },
  logout: {
    id: `${scope}.logout`,
    defaultMessage: 'Logout',
  },
});
