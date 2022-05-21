import React from "react";
import {useState} from "react";
import {useSelector} from "react-redux";
import {deleteUser, editUser} from "../../services/userService";
import {Navigate} from "react-router-dom";

export default function Profile() {
    const [username, setUserName] = useState()
    const [password, setPassword] = useState()
    const [isAdmin, setIsAdmin] = useState()
    const user = useSelector(state => state.user)
    function handleEdit(){
        editUser({
            username: username,
            password: password,
            role: isAdmin ? 'ROLE_ADMIN' : 'ROLE_USER'
        })
    }
    function deleteMe(){
        deleteUser({
            username: user.username
        })
    }

    if (!user)
        return <Navigate to={'/'}/>

    return (
        <div>
            <h1>Profile</h1>
            <form onSubmit={handleEdit}>
                <label>
                    <p>Username</p>
                    <input type="text" defaultValue={user.username} onChange={e => setUserName(e.target.value)}/>
                </label>
                <label>
                    <p>Password</p>
                    <input type="password" defaultValue={user.password} onChange={e => setPassword(e.target.value)}/>
                </label>
                
                <div>
                    <button type="submit">Submit</button>
                </div>
            </form>
            <button onClick={deleteMe}>Delete</button>
        </div>
    )
}