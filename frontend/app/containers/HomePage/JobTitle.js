import React,{Component} from 'react'
import messages from './messages';
import { FormattedMessage } from 'react-intl';

class JobTitle extends Component{
  render() {
    return (
      <div className="job row">
        <div className="col-lg-3 col-md-6 job__item" data-toggle="modal" data-target="#create"><p
          className="job__item__header"><FormattedMessage {...messages.addNewButton}/><FormattedMessage {...messages.job}/></p>
          <p className="job__item__content"><i className="fa fa-plus"></i></p></div>
        <div className="col-lg-3 col-md-6 job__item"><p className="job__item__header"><FormattedMessage {...messages.urgent}/><FormattedMessage {...messages.job}/></p>
          <p className="job__item__content">1</p></div>
        <div className="col-lg-3 col-md-6 job__item"><p className="job__item__header"><FormattedMessage {...messages.experied}/><FormattedMessage {...messages.job}/></p>
          <p className="job__item__content">1</p></div>
        <div className="col-lg-3 col-md-6 job__item"><p className="job__item__header"><FormattedMessage {...messages.all}/><FormattedMessage {...messages.job}/></p>
          <p className="job__item__content">1</p></div>
      </div>
    );
  }
}

export default JobTitle;
