import React from 'react';
import axios from 'axios';
import logo from '../../logo192.png';
import {
    Link,
    withRouter
} from 'react-router-dom';

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            toUsers: false
        }
    }

    login = event => {
        event.preventDefault();
        console.log("LOGGED IN");
        //this.props.history.push('/users');
        /*
        if (password == confPassword){
            axios.post(".../users/login", {
                username: this.state.username,
                password: this.state.password
            }).then((response) => {
                console.log(response);
            })
        } else {
            //TODO: notification manager error("")
        }
        */

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
                        <tr><td><label>Username: </label><td></td><input className="input-text" type="text" /></td></tr>
                        <tr><td><label>Password: </label><td></td><input className="input-text" type="password" /></td></tr>
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