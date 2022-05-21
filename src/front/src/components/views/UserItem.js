import classes from '../../styles/userItem.module.css'
import {useState} from "react";

export default function UserItem(props) {
    let [username_, setUserName] = useState()
    let [password_, setPassword] = useState()
    const {id, username, password, roles} = props

    function update() {
        if (!username_)
            username_ = username
        if (!password_)
            password_ = password
        props.update_({
            id: id,
            username: username_,
            password: password_,
            role: roles
        })
    }

    function deleteUs() {
        props.delete_({
            id: id
        })
    }

    return (
        <li key={id} className={classes.flex}>
            <label className={classes.padding}>id: {id}</label>
            <div>
                <label>username: </label>
                <input type={'text'} className={classes.padding} defaultValue={username}
                       onChange={e => setUserName(e.target.value)}/>
            </div>
            <div>
                <label>password: </label>
                <input type={'text'} className={classes.padding} defaultValue={password}
                       onChange={e => setPassword(e.target.value)}/>
            </div>
            
            <button onClick={update} className={classes.padding}>Edit</button>
            <button onClick={deleteUs} className={classes.padding}>Delete</button>
        </li>
    )
}