import React from 'react';
import axios from 'axios';
import logo from '../../logo192.png';
import {
    Link,
    withRouter,
    Redirect
} from 'react-router-dom';

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
            }
        }
    }

    login = event => {
        event.preventDefault();
        console.log("LOGGED IN");
        axios.post("http://localhost:8080/WAR_-_Chat/rest/users/login", {
            username: this.state.username,
            password: this.state.password
        }).then((response) => {
            this.setState({ loggedInUser: response.data });
            this.props.history.push({
                pathname: '/users',
                state: {
                    loggedUser: {
                        username: response.data.username,
                        id: response.data.id
                    }
                }  
            })
        })
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
                <form onSubmit={this.login}>
                    <table>
                        <tr><td><label>Username: </label><td></td><input className="input-text" onChange={this.handleChange} value={this.state.username} name="username" type="text" /></td></tr>
                        <tr><td><label>Password: </label><td></td><input className="input-text" onChange={this.handleChange} value={this.state.password} name="password" type="password" /></td></tr>
                    </table>
                    <br />
                    <button type="submit" className="register-btn">Login</button>
                </form>
                <br />
                <small>Doesn't have an account? <Link to="/register">Register!</Link></small>
            </header>
        );
    }
}

export default withRouter(Login);