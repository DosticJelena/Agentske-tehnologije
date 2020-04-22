import React from 'react';
import '../Users.css';

class User extends React.Component {
    render() {

        let activeDiv;
        if (this.props.isLoggedIn == "LOGGED_IN") {
            if (this.props.active) {
                activeDiv = <div className="activeCircle"></div>
            } else {
                activeDiv = <div className="inactiveCircle"></div>
            }
        } else {
            if (this.props.active) {
                activeDiv = <div className="redActiveCircle"></div>
            } else {
                activeDiv = <div className="redInactiveCircle"></div>
            }
        }


        return (
            <div className="row user">
                <div className="col-9">
                    <button onClick={this.props.onClickk}>{this.props.name}  {activeDiv}</button>
                </div>

            </div>
        );
    }
}

export default User;