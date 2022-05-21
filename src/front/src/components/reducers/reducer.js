import {USER_EDIT, USER_LOGIN, USER_LOGOUT} from "../actions/types";
import {initialState} from "../store/initialState";

export function reducer(state, action) {
    switch (action.type) {
        case USER_LOGIN:
            return {user: action?.value}
        case USER_LOGOUT:
            return initialState
        case USER_EDIT:
            return {user: action?.value}
        default:
            return state
    }
}