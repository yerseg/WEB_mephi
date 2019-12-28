import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route } from 'react-router-dom';
import { createBrowserHistory } from 'history';
import './styles/index.css';
import Auth from './Auth.js';
import Signup from './Signup.js';
import Home from './Home.js';
import * as serviceWorker from '../../lab_6/src/serviceWorker';

ReactDOM.render (    
    <Router history={createBrowserHistory()}>
        <Route component={Auth} path="/signin"/>
        <Route component={Signup} path="/signup"/>
        <Route component={Home} path="/home"/>
    </Router>,
document.getElementById('root'));
serviceWorker.unregister();
