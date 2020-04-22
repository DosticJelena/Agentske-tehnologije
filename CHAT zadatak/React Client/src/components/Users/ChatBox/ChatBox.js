import React from 'react';
import axios from 'axios';
import '../Users.css';

class ChatBox extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            BASE_URL: "http://localhost:8080/WAR_-_Chat/rest",
            subject: 'From: ',
            content: '',
            messages: []
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

        axios.post(this.state.BASE_URL + "/messages/" + this.props.userId, {
            subject: this.state.subject,
            content: this.state.content,
            senderId: 1,
            receiverId: this.props.userId
        }).then((response) => {
            console.log(response);
            this.setState({ content: "" });
            this.getAllMessages();
        }).catch((error) => console.log(error)); //TODO: notification manager

    }

    getAllMessages = () => {
        axios.get(this.state.BASE_URL + "/messages/" + this.props.userId)
            .then((response) => {
                console.log(response);
                this.setState(
                    { messages: response.data }
                )
            })
            .catch((error) => {

            })
    }

    componentDidMount() {
        this.getAllMessages();
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
                <div className="messages">
                    {this.state.messages.reverse().map((msg) =>
                        <div key={msg.id} className="row message-right">
                            <small>00:00</small>
                            <p>{msg.content}</p>
                        </div>)}
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