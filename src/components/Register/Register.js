import React from 'react';
import axios from 'axios';
import logo from '../../logo192.png';
import {
    Link,
    withRouter
} from 'react-router-dom';

class Register extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            confPassword: '',
            toUsers: false
        }
    }

    register = event => {
        event.preventDefault();
        console.log("REGISTERED");
        this.props.history.push('/users');
        /*
        if (password == confPassword){
            axios.post(".../users/register", {
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
                <img src={logo} className="App-logo" alt="logo" width="126" height="126" />
                <br />
                <form onSubmit={this.register}>
                    <table>
                        <tr>
                            <td className="first-col"><label>Username: </label></td>
                            <td><input className="input-text" type="text" /></td>
                        </tr>
                        <tr>
                            <td className="first-col"><label>Password: </label></td>
                            <td><input className="input-text" type="password" /></td>
                        </tr>
                        <tr>
                            <td className="first-col"><label>Confirm Password: </label></td>
                            <td><input className="input-text" type="password" /></td>
                        </tr>
                    </table>
                    <br />
                    <button type="submit" className="register-btn">Register</button>
                </form>
                <br />
                <small>Already have an account? <Link to="/">Log In!</Link></small>
            </header>
        );
    }
}

export default withRouter(Register);