//basic
import React from 'react';
import './App.css';
//components
import Register from './components/Register/Register';
import Login from './components/Login/Login';
import Users from './components/Users/Users';
//libs
import 'react-notifications/lib/notifications.css';
import { NotificationContainer } from 'react-notifications';
import {
  HashRouter as Router,
  Switch,
  Route
} from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Router basename="/">
        <Switch>
          <Route exact path="/">
            <Login />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/users">
            <Users />
          </Route>
        </Switch>

        <NotificationContainer />
      </Router>
      
    </div>
  );
}

export default App;
