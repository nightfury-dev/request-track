/**
 *
 * MenuHeader
 *
 */

import React from 'react';

import { FormattedMessage } from 'react-intl';
import messages from './messages';

/* eslint-disable react/prefer-stateless-function */
class MenuHeader extends React.Component {
  render() {
    return (
      <div>
        <div className="nav navbar nav-default row"><i className="fa fa-bars"></i><input className="input-form"
                                                                                         placeholder="Search"/>
          <div className="personal ml-auto navbar">
            <div className="personal__item"><i className="fa fa-signal"></i><sup className="badge badge-danger">0</sup>
            </div>
            <div className="personal__item"><i className="fa fa-bell"></i><sup className="badge badge-primary">1</sup>
            </div>
            <div className="personal__item nav-item">
              <div className="dropdown"><a className="text-dark nav-link dropdown-toggle bg-light"
                                           data-toggle="dropdown" id="navbarDropdown" href="#" role="button"
                                           aria-haspopup="true" aria-expanded="false"><i
                className="fa fa-user-circle"></i></a>
                <div className="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                  <div className="dropdown-item">info</div>
                  <div className="dropdown-item">info</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

MenuHeader.propTypes = {};

export default MenuHeader;
