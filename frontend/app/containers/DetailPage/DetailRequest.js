import React, { Component } from 'react';
import messages from './messages';
import { FormattedMessage } from 'react-intl';

export default class DetailRequest extends Component {
  render() {
    const { request } = this.props;
    return (
      <div>
        <div className="candidate col-12 bg-white border rounded-lg">
          <div className="candidate__header text-left py-3 border-bottom">
            <i className="fa fa-user mr-2"></i>
            <span><FormattedMessage {...messages.titleContainerRequest}/></span>
          </div>
          <div className="candidate__content border rounded-lg pt-2 mt-4 mb-3">
            <div className="container-fluid">
              <p className="candidate__content__item candidate__content__name">{request.language}</p>
              <div className="candidate__content__item candidate__content__code row bg-light">
                <p className="col-6"><FormattedMessage {...messages.requestName}/></p>
                <p className="col-6">{request.createdBy.name}</p></div>
              <div className="candidate__content__item candidate__content__quantity row">
                <p className="col-6"><FormattedMessage {...messages.requestQuantity}/></p>
                <p className="col-6">{request.quantity}</p></div>
              <div className="candidate__content__item candidate__content__deadline row bg-light"><p
                className="col-6"><FormattedMessage {...messages.requestDeadline}/></p>
                <p className="col-6">{request.deadline}</p>
                </div>
              <div className="candidate__content__item candidate__content__status row">
                <p className="col-6"><FormattedMessage {...messages.requestStatus}/></p>
                <p className="col-6">{request.status}</p>
              </div>
              <div className="candidate__content__item candidate__content__office row bg-light">
                <p className="col-6"><FormattedMessage {...messages.requestOffice}/></p>
                <p className="col-6">Đà Nẵng</p>
              </div>
              <div className="candidate__content__item candidate__content__division row bg-light">
                <p className="col-6"><FormattedMessage {...messages.requestDivision}/></p>
                <p className="col-6">{request.division}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
