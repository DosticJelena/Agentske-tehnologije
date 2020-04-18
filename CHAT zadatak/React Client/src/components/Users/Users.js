import React from 'react';
import './Users.css';
import logo from '../../logo192.png';
import axios from 'axios';
//components
import User from './User/User';
import ChatBox from './ChatBox/ChatBox';

class Users extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            boxes: [],
            users: [
                { id: 1, name: "Prvi Prvic", active: false },
                { id: 2, name: "Drugi Drugic", active: false },
                { id: 3, name: "Treci Trecic", active: false }
            ],
            loggedInUsers: [],
            registeredUsers: [],
            msgContent: ''
        }
    }

    handleChange = event => {
        this.setState({ msgContent: event.target.value });
    }

    
    sendToAll = event => {
        event.preventDefault();
        console.log("SVI");
        console.log(this.state.msgContent)

        /*
        axios.post(".../messages/all",{
            subject: this.state.subject,
            content: this.state.content
        }).then((reponse) => {

        }).catch((error) => console.log(error)); //TODO: notification manager
        */
    }

    getLoggedUsers = () => {
        console.log("LOGGED USERS");

        /*
        axios.get(".../users/loggedIn")
        .then((response) => {
            console.log(response);
        })
        .catch((error) => {

        })
        */
    }

    getRegisteredUsers = () => {
        console.log("REGISTERED USERS");

        /*
        axios.get(".../users/registered")
        .then((response) => {
            console.log(response);
        })
        .catch((error) => {

        })
        */
    }

    changeActiveState = (userId) => {
        for (let i = 0; i < this.state.users.length; i++) {
            // eslint-disable-next-line
            if (this.state.users[i].id == userId) {
                // eslint-disable-next-line
                this.state.users[i].active = !this.state.users[i].active;
                this.forceUpdate()
            }
        }
    }

    closeBox = (boxId) => {
        this.setState({
            // eslint-disable-next-line
            boxes: this.state.boxes.filter(box => box.id != boxId)
        });
        this.changeActiveState(boxId);
    }

    showChatBox = (userName, userId) => {
        if (this.state.boxes.some(box => box.id === userId)) {
            this.closeBox(userId);
        } else {
            let newBox = {
                id: userId,
                name: userName
            }

            this.setState(
                { boxes: [...this.state.boxes, newBox] }
            )
            this.changeActiveState(userId);
        }
    }
    
    getAllMessages = user => {
        console.log("SPISAK SVIH PORUKA: " + user);

        /*
        axios.get(".../messages/{user}")
        .then((reponse) => {

        }).catch((error) => console.log(error)); //TODO: notification manager
        */
    }

    componentDidMount() {
        this.getLoggedUsers();
        this.getRegisteredUsers();
    }

    render() {
        return (
            <div className="Users">
                <div className="row">
                    <div className="col-4">
                        <div className="user-list">
                            <div className="row">
                                <div className="col-5">
                                    <div className="avatar"></div>
                                </div>
                                <div className="col-7 welcome">
                                    <h3>Hi, Jelena</h3>
                                </div>
                            </div>
                            <br />
                            <hr />
                            <br />
                            <h4>Say hi to someone!</h4>
                            <br />
                            {this.state.users.map((user) =>
                                <User onClickk={() => this.showChatBox(user.name, user.id)} name={user.name} active={user.active} key={user.id} id={user.id} />
                            )}
                            <br />
                            <hr />
                            <label>Send message to all logged users:</label>
                            <div>
                                <input type="text" className="typeField" value={this.state.msgContent} name="msgContent" onChange={this.handleChange}/>
                                <button onClick={this.sendToAll} className="btnSendAll">Send</button>
                            </div>
                        </div>
                    </div>
                    <div className="col-8">
                        <div className="home-page">
                            <div className="row">
                                <div className="col-9">
                                    <h1>Welcome!</h1>
                                </div>
                                <div className="col-3">
                                    <img alt="logo" src={logo} height="70" width="70" />
                                </div>
                            </div>
                            {this.state.boxes.reverse().map((box) =>
                                <ChatBox key={box.id} userName={box.name} userId={box.id} getMessages={() => this.getAllMessages(box.id)} closeBox={() => this.closeBox(box.id)} />
                            )}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Users;