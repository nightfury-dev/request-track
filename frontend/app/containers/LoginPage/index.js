/**
 * Login Page
 *
 */

import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { Helmet } from 'react-helmet';
import { FormattedMessage } from 'react-intl';
import { createStructuredSelector } from 'reselect';
import { compose } from 'redux';
import { Redirect } from 'react-router';

import injectSaga from 'utils/injectSaga';
import injectReducer from 'utils/injectReducer';
import { selectError, selectLoading, selectStatus } from './selectors';
import reducer from './reducer';
import saga from './saga';
import messages from './messages';
import { loginRequest } from './actions';

/* eslint-disable react/prefer-stateless-function */
export class LoginPage extends React.Component {
  login() {
    let username = this.user.value;
    let password = this.pass.value;
    this.props.loginRequest(username, password);
  }

  checkLogin() {
    let token = localStorage.getItem('token');
    if (token) {
      return <Redirect to="/"/>;
    }
  }

  render() {
    const { loading, error, status } = this.props;

    return (
      <div>
        {this.checkLogin()}
        <FormattedMessage {...messages.titlePage}>
          {title =>
            <Helmet>
              <title>{title}</title>
              <meta name="description" content=" LoginPage"/>
            </Helmet>
          }
        </FormattedMessage>

        <div className="container-fluid login-page">
          <div className="row justify-content-center align-items-center text-left  form-login">
            <div className="card-wrapper col col-sm-6 col-md-6 col-lg-8 col-xl-6">

              <div className="card fat">
                <div className="card-body">
                  <h4 className="card-title text-center"><FormattedMessage {...messages.title}/></h4>
                  <p className="text-danger">{error}</p>
                  {loading &&
                  <p className="font-italic font-weight-bold"><FormattedMessage {...messages.statusLoading}/></p>}
                  {!error && status}
                  <form onClick={e => e.preventDefault()}>

                    <div className="form-group">
                      <label htmlFor="name"><FormattedMessage {...messages.username}/></label>
                      <input id="name" type="text" className="form-control" name="name" required="" autoFocus=""
                             ref={value => this.user = value}/>
                    </div>

                    <div className="form-group">
                      <label htmlFor="email"><FormattedMessage {...messages.password}/></label>
                      <input id="email" type="password" className="form-control" name="pass" required=""
                             ref={value => this.pass = value}/>
                    </div>

                    <div className="form-group mb-8">
                      <button type="submit" className="btn btn-primary btn-block " onClick={() => this.login()}>
                        <FormattedMessage {...messages.buttonLogin}/>
                      </button>
                    </div>

                    <div className="form-group no-margin">
                      <button type="submit" className="btn btn-danger btn-block">
                        <FormattedMessage {...messages.buttonLoginOther}/>
                      </button>
                    </div>

                  </form>
                </div>
              </div>

            </div>
          </div>
        </div>
      </div>
    );
  }
}

LoginPage.propTypes = {
  loginRequest: PropTypes.func.isRequired,
};

const mapStateToProps = createStructuredSelector({
  error: selectError(),
  loading: selectLoading(),
  status: selectStatus(),

});

function mapDispatchToProps(dispatch) {
  return {
    loginRequest: (username, password) => dispatch(loginRequest(username, password)),
  };
}

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

const withReducer = injectReducer({ key: 'loginPage', reducer });
const withSaga = injectSaga({ key: 'loginPage', saga });

export default compose(
  withReducer,
  withSaga,
  withConnect,
)(LoginPage);
