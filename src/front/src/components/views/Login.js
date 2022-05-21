import React, {useState} from 'react';
import {login} from "../../services/userService";
import {useSelector} from "react-redux";
import {Navigate} from "react-router-dom";

export default function Login() {
    const [username, setUserName] = useState()
    const [password, setPassword] = useState()
    const user = useSelector(state => state.user)

    const handleSubmit = async e => {
        e.preventDefault();
        login({
            username: username,
            password: password
        })
    }

    if (user)
        return <Navigate to={'/'}/>

    return (
        <div>
            <h1>Please, Log In</h1>
            <form onSubmit={handleSubmit}>
                <label>
                    <p>Username</p>
                    <input type="text" onChange={e => setUserName(e.target.value)}/>
                </label>
                <label>
                    <p>Password</p>
                    <input type="password" onChange={e => setPassword(e.target.value)}/>
                </label>
                <div>
                    <button type="submit">Submit</button>
                </div>
            </form>
        </div>
    )
}