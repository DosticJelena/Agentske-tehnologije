import React from 'react';
import axios from 'axios';
import logo from '../../assets/ikonica.png';
import { NotificationManager } from 'react-notifications';
import {
    Link,
    withRouter
} from 'react-router-dom';

import Users from '../Users/Users';

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            toUsers: false,
            loggedInUser: {
                username: '',
                password: '',
                id: 0
            },
            loggedStatus: false
        }
    }

    login = event => {
        event.preventDefault();
        if (this.state.username === '' || this.state.password === '') {
            NotificationManager.warning("Username and password cannot be empty!", "", 3000);
        } else {
            console.log("LOGGED IN");
            axios.post("http://localhost:8080/WAR_-_Chat/rest/users/login", {
                username: this.state.username,
                password: this.state.password
            }).then((response) => {
                this.setState({ loggedInUser: response.data, loggedStatus: true});
                NotificationManager.success("Welcome back!", "", 3000);
                this.props.history.push({
                    pathname: '/users',
                    state: {
                        loggedUser: {
                            username: response.data.username,
                            id: response.data.id,
                            status: this.state.loggedStatus
                        }
                    }
                })
            }).catch((error) => NotificationManager.error("There is no registered users with given username and password.", "", 3000));
        }
    }

    handleChange = event => {
        this.setState({ [event.target.name]: event.target.value });
    }

    render() {
        return (
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" width="256" height="256" />
                <br />
                <p>
                    Welcome to Chatout app!
              </p>
                <form autoComplete="off" onSubmit={this.login}>
                    <table>
                        <tr><td><label>Username: </label><td></td><input className="input-text" onChange={this.handleChange} value={this.state.username} name="username" type="text" /></td></tr>
                        <tr><td><label>Password: </label><td></td><input className="input-text" onChange={this.handleChange} value={this.state.password} name="password" type="password" /></td></tr>
                    </table>
                    <br />
                    <button type="submit" className="register-btn">Login</button>
                </form>
                <br />
                <small>Doesn't have an account? <Link to="/register">Register!</Link></small>
            </header>);
    }
}

export default withRouter(Login);