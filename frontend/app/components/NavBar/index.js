/**
 *
 * NavBar
 *
 */

import React from 'react';
// import PropTypes from 'prop-types';
// import styled from 'styled-components';

import { FormattedMessage } from 'react-intl';
import messages from './messages';
import LogoImg from './Logo.png';

/* eslint-disable react/prefer-stateless-function */
class NavBar extends React.Component {
  render() {
    return (
      <div className="col-lg-2 sidebar">
        <div className="sidebar-logo">
          <img alt="" src={LogoImg} />
          <span className="push-top-bottom"><strong>RMS</strong></span>
        </div>
        <ul className="sidebar-nav">
          <li className="sidebar-nav__item">
            <ul>
              <li><a><i className="fas fa-home"></i><span><FormattedMessage {...messages.home}/></span></a></li>
              <li><a><i className="fas fa-tachometer-alt"></i><span><FormattedMessage {...messages.dashBoard}/></span></a></li>
            </ul>
          </li>
          <li className="sidebar-nav__item"><h6 className="sidebar-nav__item__header"><FormattedMessage {...messages.titleNavListRe}/></h6>
            <ul>
              <li><a><i className="fa fa-suitcase"></i><span><FormattedMessage {...messages.itemNavJob}/></span></a></li>
              <li><a><i className="fa fa-users"></i><span><FormattedMessage {...messages.itemNavUser}/></span></a></li>
              <li><a><i className="fa fa-file"></i><span><FormattedMessage {...messages.itemNavApply}/></span></a></li>
              <li><a><i className="fa fa-calendar-alt"></i><span><FormattedMessage {...messages.itemNavCalendar}/></span></a></li>
              <li><a><i className="fa fa-envelope"></i><span><FormattedMessage {...messages.itemNavEmail}/></span></a></li>
            </ul>
          </li>
          <li className="sidebar-nav__item"><h6 className="sidebar-nav__item__header">MANAGE RECRUMENT</h6>
            <ul>
              <li><a><i className="fa fa-suitcase"></i><span>Job</span></a></li>
              <li><a><i className="fa fa-users"></i><span>User</span></a></li>
              <li><a><i className="fa fa-file"></i><span>Apply</span></a></li>
              <li><a><i className="fa fa-calendar-alt"></i><span>Calendar</span></a></li>
              <li><a><i className="fa fa-envelope"></i><span>Email</span></a></li>
            </ul>
          </li>
        </ul>
    </div>
    );
  }
}

NavBar.propTypes = {};

export default NavBar;
