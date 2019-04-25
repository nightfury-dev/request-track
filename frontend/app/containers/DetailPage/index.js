/**
 *
 * DetailPage
 *
 */

import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { Helmet } from 'react-helmet';
import { FormattedMessage } from 'react-intl';
import { createStructuredSelector } from 'reselect';
import { compose } from 'redux';
import injectSaga from 'utils/injectSaga';
import injectReducer from 'utils/injectReducer';
import reducer from './reducer';
import saga from './saga';
import messages from './messages';
import DetailRequest from './DetailRequest';
import { fetchRequestInterview } from './actions';
import { selectDetailPage, selectErrorRequest, selectLoading, selectRequestInterview } from './selectors';
import List from './List';
import Step from './Step';

/* eslint-disable react/prefer-stateless-function */
export class DetailPage extends React.Component {
  constructor(props) {
    super(props);

  }

  componentDidMount() {
    const id = this.props.match.params.id;
    this.props.fetch(id);
  }

  render() {
    const { request_interviews,error,loading } = this.props;
    return (
      <div>
        <Helmet>
          <title>DetailPage</title>
          <meta name="description" content="Description of DetailPage"/>
        </Helmet>
        <div className="row pb-3">
          <div className="col-lg-4 col-md-6 col-sm-12">
            {error}
            {loading&& <FormattedMessage {...messages.loading}/>}
            {request_interviews.id  && <DetailRequest request={request_interviews}/>}
          </div>
          <div className="reviews col-lg-8 col-md-6 col-sm-12">
            <Step/>
            <List/>
          </div>
        </div>
      </div>
    );
  }
}

DetailPage.propTypes = {
  fetch: PropTypes.func,
};

const mapStateToProps = createStructuredSelector({
  request_interviews: selectRequestInterview(),
  error: selectErrorRequest(),
  loading: selectLoading()
});

function mapDispatchToProps(dispatch) {
  return {
    fetch: (idRequest) => dispatch(fetchRequestInterview(idRequest)),
  };
}

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

const withReducer = injectReducer({ key: 'detailPage', reducer });
const withSaga = injectSaga({ key: 'detailPage', saga });

export default compose(
  withReducer,
  withSaga,
  withConnect,
)(DetailPage);
