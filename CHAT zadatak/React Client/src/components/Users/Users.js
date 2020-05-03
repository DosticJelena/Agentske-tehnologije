import React from 'react';
import './Users.css';
import logo from '../../assets/ikonica.png';
import axios from 'axios';
import { withRouter, Redirect } from 'react-router-dom';
import { NotificationManager } from 'react-notifications';
//components
import User from './User/User';
import ChatBox from './ChatBox/ChatBox';

class Users extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            BASE_URL: "http://localhost:8080/WAR_-_Chat/rest",
            WS_HOST: "ws://localhost:8080/WAR_-_Chat/ws",
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

    socketReg = () => {
        var urlReg = this.state.WS_HOST + "/registered";
        var socketReg = new WebSocket(urlReg);
        socketReg.onmessage = (msg) => {
            console.log(socketReg.readyState + " ---- (message) ---- ");
            this.setState({users: JSON.parse(msg.data)});
        }
        socketReg.onclose = () => socketReg = null;
    }

    socketLog = () => {
        var urlLog = this.state.WS_HOST + "/loggedIn";
        var socketLog = new WebSocket(urlLog);
        socketLog.onmessage = (msg) => {
            console.log(socketLog.readyState + " ---- (message log) ---- ");
            this.setState({loggedInUsers: JSON.parse(msg.data)});
        }
        socketLog.onclose = () => socketLog = null;
    }
       
    handleChange = event => {
        this.setState({ msgContent: event.target.value });
    }

    _handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            this.sendToAll(e);
        }
    }

    sendToAll = event => {
        event.preventDefault();
        console.log("SVI");
        console.log(this.state.msgContent);

        if (this.state.loggedInUsers.length - 1  == 0) {
            NotificationManager.warning("There is no logged in users.", "", 3000);
        } else {
            axios.post(this.state.BASE_URL + "/messages/all", {
                subject: "Subject (All)",
                content: this.state.msgContent,
                senderId: this.props.location.state.loggedUser.id,
                receiverId: 1
            }).then((response) => {
                console.log(response);
                this.setState({ msgContent: "" })
                this.getAllMessages();
                this.closeAllBoxes();
                NotificationManager.success("Successfully sent!", "", 3000);
            }).catch(() => NotificationManager.error("Something went wrong. Please try again later.", "", 3000));
        }

    }

    closeAllBoxes = () => {
        for (let i = 0; i < this.state.users.length; i++) {
            // eslint-disable-next-line
            this.state.users[i].active = false;
            this.forceUpdate()
            this.setState({ boxes: [] })
        }
    }

    getAllMessages = () => {
        axios.get(this.state.BASE_URL + "/messages/" + this.state.loggedUserId)
            .then((response) => {
                console.log(response);
                this.setState(
                    { messages: response.data }
                )
            })
            .catch((error) => {

            })
    }


    getLoggedUsers = () => {
        console.log("LOGGED USERS");

        axios.get(this.state.BASE_URL + "/users/loggedIn")
            .then((response) => {
                console.log(response);
                this.setState({ loggedInUsers: response.data });
            })
            .catch((error) => {

            })
    }

    getRegisteredUsers = () => {
        console.log("REGISTERED USERS");

        axios.get(this.state.BASE_URL + "/users/registered")
            .then((response) => {
                console.log(response);
                this.setState({ users: response.data });
            })
            .catch((error) => {

            })
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
                username: userName,
                messages: []
            }

            axios.get(this.state.BASE_URL + "/messages/" + userId)
                .then((response) => {
                    console.log(response);
                    newBox.messages = response.data;
                    this.setState(
                        { boxes: [...this.state.boxes, newBox] }
                    )
                    this.changeActiveState(userId);
                })
                .catch((error) => {

                })

        }
    }

    logOut = event => {
        event.preventDefault();
        axios.delete(this.state.BASE_URL + "/users/" + this.props.location.state.loggedUser.id)
            .then((response) => {
                console.log(response);

            })
            .catch((error) => {

            })
        this.props.history.push("/"); //privremeno
    }

    componentDidMount() {
        if (this.props.location.state != undefined) {
            this.getLoggedUsers();
            this.getRegisteredUsers();
            this.setState({ loggedInUserId: this.props.location.state.id })

            this.socketReg();
            this.socketLog();
        }
    }

    render() {

        if (this.props.location.state != undefined) {
            var loggedId = this.props.location.state.loggedUser.id;
            var loggedUsername = this.props.location.state.loggedUser.username;
            var filteredUsers = this.state.users.filter(function (user) {
                return user.id !== loggedId;
            });

        }

        return this.props.location.state != undefined ? (
            <div className="Users">
                <div className="row">
                    <div className="col-12 col-md-6 col-lg-4 .col-xl-4">
                        <div className="user-list">
                            <div className="row">
                                <div className="col-4 col-sm-4 col-md-12 .col-xl-5 col-lg-5">
                                    <div className="avatar"></div>
                                </div>
                                <div className="col-8 col-sm-8 .col-xl-7 col-lg-7 col-md-12 welcome">
                                    <h3>Hi, {loggedUsername}</h3>
                                </div>
                            </div>
                            <br />
                            <hr />
                            <br />
                            <h4>Say hi to someone!</h4>
                            <br />
                            <div className="userss">
                                {filteredUsers.map((user) =>
                                    <User isLoggedIn={user.loggedIn} onClickk={() => this.showChatBox(user.username, user.id)} name={user.username} active={user.active} key={user.id} id={user.id} />
                                )}
                            </div>
                            <br />
                            <hr />
                            <label>Send message to all logged users:</label>
                            <div>
                                <input type="text" className="typeField" onKeyDown={this._handleKeyDown} value={this.state.msgContent} name="msgContent" onChange={this.handleChange} />
                                <button onClick={this.sendToAll} className="btnSendAll">Send</button>
                            </div>
                            <hr />
                            <button onClick={this.logOut} className="btnSendAll">Log out</button>
                        </div>
                    </div>
                    <div className="col-12 col-md-6 col-lg-8 .col-xl-8">
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
                                <ChatBox loggedUser={loggedUsername} loggedUserId={loggedId} key={box.id} userName={box.username} userId={box.id} messages={box.messages} closeBox={() => this.closeBox(box.id)} />
                            )}
                        </div>
                    </div>
                </div>
            </div>
        ) : (<Redirect to="/" />);
    }
}

export default withRouter(Users);