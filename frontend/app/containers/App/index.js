/**
 *
 * App
 *
 * This component is the skeleton around the actual pages, and should only
 * contain code that should be seen on all pages. (e.g. navigation bar)
 */

import React from 'react';
import { Helmet } from 'react-helmet';
import styled from 'styled-components';
import { Switch, Route } from 'react-router-dom';

import HomePage from 'containers/HomePage/Loadable';
import FeaturePage from 'containers/FeaturePage/Loadable';
import NotFoundPage from 'containers/NotFoundPage/Loadable';
import NavBar from 'components/NavBar';
import Footer from 'components/Footer';
import Header from 'components/Header';

import GlobalStyle from '../../global-styles';


//import bootstrap
import $ from 'jquery';
import 'popper.js/dist/popper.js';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import 'style/style.css';

const AppWrapper = styled.div`
`;
export default function App() {
  return (
    <div>
      <AppWrapper className="container-fluid">
        <Helmet
          titleTemplate="%s -interviews"
          defaultTitle="interviews"
        >
          <meta name="description" content="interview page"/>
        </Helmet>
        <div className="row">
          <NavBar/>
          <div className="col-lg-10 page_content text-center">
            <Header/>
            <Switch>
              <Route exact path="/" component={HomePage}/>
              <Route path="/features" component={FeaturePage}/>
              <Route path="" component={NotFoundPage}/>
            </Switch>
            <GlobalStyle/>
          </div>
        </div>
      </AppWrapper>
    </div>
  );
}
