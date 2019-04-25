import React,{Component} from 'react';
import messages from './messages';
import { FormattedMessage } from 'react-intl';

export default class List extends Component{
  render() {
    return (
      <div className="list col-12 bg-white mb-3 border rounded-lg mt-3">
        <div className="list__header text-left py-3 border-bottom">
          <div className="row align-items-center">
            <div className="text-left col">
              <i className="fa fa-bars mr-2"></i>
              <b><FormattedMessage {...messages.titleContainerListTrainee}/></b><sup
              className="badge badge-primary">0</sup></div>
            <div className="text-right col">
              <button className="btn rounded-pill mr-1">
                <i className="fa fa-pencil-alt pr-2"></i>
                <span><FormattedMessage {...messages.edit}/></span>
              </button>
            </div>
          </div>
        </div>
        <div className="mail__content border rounded-lg pt-2 mt-4 mb-3">
          <div className="container-fluid">
            <div className="row">
              <div className="col">
                <div className="text-center text-danger"><FormattedMessage {...messages.noData}/></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
