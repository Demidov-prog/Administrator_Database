import {store} from "../components/store/store";
import {userEdit, userLogin, userLogout} from "../components/actions/userActions";

export function login(credentials) {
    return fetch('http://localhost:3001/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => {
            data.json().then(r => {
                store.dispatch(userLogin(r))
            })
        })
}

export function editUser(user) {
    return fetch('http://localhost:3001/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then(() => {
                if (user.id === store.getState().user.id)
                    store.dispatch(userEdit(user))
            }
        )
}

export function deleteUser(user) {
    return fetch('http://localhost:3001/deleteUser', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(() => {
        if (user.id === store.getState().user.id)
            store.dispatch(userLogout())
    })
}


export function registration(user) {
    return fetch('http://localhost:3001/registration', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then(data => {
            data.json().then(r => {
                store.dispatch(userLogin(user))
            })
        })
}


