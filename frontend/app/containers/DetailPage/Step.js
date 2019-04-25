import React, { Component } from 'react';
import messages from './messages';
import { FormattedMessage } from 'react-intl';

export default class Step extends Component {
  render() {
    return (
      <div className="step col-12 bg-white border rounded-lg">
        <div className="step__header text-left py-3 border-bottom"><i
          className="fa fa-bars mr-2"></i><span><FormattedMessage {...messages.titleContainerStep}/></span>
        </div>
        <div className="step__content border rounded-lg pt-2 mt-4 mb-3">
          <FormattedMessage {...messages.titleContainerRequest}/>
        </div>
      </div>
    );
  }
}
