import React, { Component } from 'react';
import { FormattedMessage } from 'react-intl';
import messages from './messages';

class Filter extends Component {
  render() {

    return (
      <div className="filter">
        <form className="row">
          <div className="form-group col pr-1">
            <div className="input-group">
              <div className="input-group-prepend">
                <div className="input-group-text"><i className="fa fa-map-marker-alt"></i></div>
              </div>
              <select className="form-control">
                <FormattedMessage {...messages.searchBranch}>{text => <option>{text}</option>}
                </FormattedMessage>
                <option>Ha Noi</option>
                <option>Da Nang</option>
                <option>Ho Chi Minh</option>
              </select></div>
          </div>
          <div className="form-group col px-1">
            <div className="input-group">
              <div className="input-group-prepend">
                <div className="input-group-text"><i className="fa fa-calendar"></i></div>
              </div>
              <FormattedMessage {...messages.searchPosition}>{placeholder => <input className="form-control"
                                                                                    placeholder={placeholder}/>}
              </FormattedMessage>
            </div>
          </div>
          <div className="form-group col px-1">
            <div className="input-group">
              <div className="input-group-prepend">
                <div className="input-group-text"><i className="fa fa-bars"></i></div>
              </div>
              <FormattedMessage {...messages.searchCategory}>{placeholder => <input className="form-control"
                                                                                    placeholder={placeholder}/>}
              </FormattedMessage>
            </div>
          </div>
          <div className="form-group col px-1">
            <div className="input-group">
              <div className="input-group-prepend">
                <div className="input-group-text"><i className="fa fa-clock"></i></div>
              </div>
              <FormattedMessage {...messages.searchStatus}>{placeholder => <input className="form-control"
                                                                                  placeholder={placeholder}/>}
              </FormattedMessage></div>
          </div>
          <div className="form-group col px-1">
            <div className="input-group">
              <div className="input-group-prepend">
                <div className="input-group-text"><i className="fa fa-search"></i></div>
              </div>
              <FormattedMessage {...messages.searchJob}>{placeholder => <input className="form-control"
                                                                               placeholder={placeholder}/>}
              </FormattedMessage>
            </div>
          </div>
          <div className="form-group col pl-1">
            <button className="btn btn-primary"><i className="fa fa-search"></i><FormattedMessage {...messages.searchButton}/></button>
          </div>
        </form>
      </div>
    );
  }
}

export default Filter;
