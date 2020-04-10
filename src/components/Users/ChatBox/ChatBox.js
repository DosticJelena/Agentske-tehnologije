import React from 'react';
import axios from 'axios';
import '../Users.css';

class ChatBox extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            subject: 'From: ',
            content: '',
        }
    }

    handleChange = event => {
        this.setState({ content: event.target.value });
    }

    sendMessage = event => {
        event.preventDefault();
        console.log("POSLATO: " + this.props.userName);
        console.log(this.state.subject)
        console.log(this.state.content)

        /*
        axios.post(".../messages/{user}",{
            subject: this.state.subject,
            content: this.state.content
        }).then((reponse) => {

        }).catch((error) => console.log(error)); //TODO: notification manager
        */
    }

    render() {

        return (
            <div className="chatBox">
                <div className="row ">
                    <div className="col-5 name-header">
                        <h4>{this.props.userName}</h4>
                    </div>
                    <div className="col-6">

                    </div>
                    <div className="col-1">
                        <button onClick={this.props.closeBox} className="close">X</button>
                    </div>
                </div>
                <hr />
                <div className="row messages">

                </div>
                <hr />
                <div className="row inputDiv">
                    <input type="text" name="msgContent" value={this.state.content} onChange={this.handleChange} className="typeField" />
                    <button onClick={this.sendMessage} className="btnSend">Send</button>
                </div>
            </div>
        );
    }
}

export default ChatBox;