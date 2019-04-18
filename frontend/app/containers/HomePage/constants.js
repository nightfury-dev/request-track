/*
 * HomeConstants
 * Each action has a corresponding type, which the reducer knows and picks up on.
 * To avoid weird typos between the reducer and the actions, we save them as
 * constants here. We prefix them with 'yourproject/YourComponent' so we avoid
 * reducers accidentally picking up actions they shouldn't.
 *
 * Follow this format:
 * export const YOUR_ACTION_CONSTANT = 'yourproject/YourContainer/YOUR_ACTION_CONSTANT';
 */

export const FETCH_REQUEST_INTERVIEW = 'boilerplate/Home/FETCH_REQUEST_INTERVIEW';
export const FETCH_REQUEST_INTERVIEW_SUCCESS = 'boilerplate/Home/FETCH_REQUEST_INTERVIEW_SUCCESS';
export const FETCH_REQUEST_INTERVIEW_ERROR = 'boilerplate/Home/FETCH_REQUEST_INTERVIEW_FAIL';
