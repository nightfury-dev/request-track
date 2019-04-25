import { fromJS } from 'immutable';
import detailPageReducer from '../reducer';

describe('detailPageReducer', () => {
  it('returns the initial state', () => {
    expect(detailPageReducer(undefined, {})).toEqual(fromJS({}));
  });
});
