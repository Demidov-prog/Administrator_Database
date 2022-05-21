import {USER_EDIT, USER_LOGIN, USER_LOGOUT, USER_SET} from "./types";

export function userLogin(response) {
    return {
        type: USER_LOGIN,
        value: response
    }
}

export function userLogout() {
    return {
        type: USER_LOGOUT,
        value: null
    }
}

export function userEdit(newUser) {
    return {
        type: USER_EDIT,
        value: newUser
    }
}
