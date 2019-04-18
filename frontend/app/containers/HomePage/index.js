/*
 * HomePage
 *
 * This is the first thing users see of our App, at the '/' route
 */

import React from 'react';
import PropTypes from 'prop-types';
import { Helmet } from 'react-helmet';
import { FormattedMessage } from 'react-intl';
import { connect } from 'react-redux';
import { compose } from 'redux';
import { createStructuredSelector } from 'reselect';

import injectReducer from 'utils/injectReducer';
import injectSaga from 'utils/injectSaga';
import {
  makeSelectRepos,
  makeSelectLoading,
  makeSelectError,
} from 'containers/App/selectors';
import H2 from 'components/H2';
import ReposList from 'components/ReposList';
import AtPrefix from './AtPrefix';
import CenteredSection from './CenteredSection';
import Form from './Form';
import Input from './Input';
import Section from './Section';
import messages from './messages';
import { loadRepos } from '../App/actions';
import { changeUsername, fetchRequestInterview } from './actions';
import reducer from './reducer';
import saga from './saga';
import NavBar from '../../components/NavBar';
import JobTitle from './JobTitle';
import Table from './Table';
import Filter from './Filter';
import { selectRequestInterview, selectHome, selectErrorRequest, selectLoading } from './selectors';

/* eslint-disable react/prefer-stateless-function */
export class HomePage extends React.PureComponent {
  /**
   * when initial state username is not null, submit the form to load repos
   */
  componentDidMount() {
      this.props.fetch();
  }

  render() {
    const { request_interview, error, loading } = this.props;
    return (
      <div>
        <JobTitle/>
        <div className="row information-job">
          <p className="information-job__header"><i className="fa fa fa-suitcase"></i>Job</p>
          <Filter/>

          {error != false ? error :
            <Table request_interview={request_interview}/>
          }
          {
            loading == true &&
            <p><FormattedMessage {...messages.loading}/></p>
          }

        </div>
      </div>
    );
  }
}

HomePage.propTypes = {
  fetchRequestInterview: PropTypes.func,
};

export function mapDispatchToProps(dispatch) {
  return {
    fetch: () => dispatch(fetchRequestInterview()),
  };
}

const mapStateToProps = createStructuredSelector({
  request_interview: selectRequestInterview(),
  error: selectErrorRequest(),
  loading: selectLoading(),
});

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

const withReducer = injectReducer({ key: 'home', reducer });
const withSaga = injectSaga({ key: 'home', saga });

export default compose(
  withReducer,
  withSaga,
  withConnect,
)(HomePage);
