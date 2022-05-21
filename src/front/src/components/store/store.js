import {createStore} from 'redux'
import {reducer} from "../reducers/reducer";
import {initialState} from "./initialState";

export const store = createStore(reducer, initialState)