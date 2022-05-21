import UserItem from "./UserItem";
import ProductItem from "./ProductItem"
import {useEffect, useState} from "react";
import {deleteUser, editUser} from "../../services/userService";
import {deleteProduct, editProduct} from "../../services/productService";
import {useSelector} from "react-redux";
import {Navigate} from "react-router-dom";
import React from "react";

export default function Checkout() {

    const [items, setItems] = useState()
    const user = useSelector(state => state.user)
    useEffect(() => {
      //  getAllProducts()
    }, [])
    if (user?.username==null){
    return <Navigate to={'/login'}/>
    }
    return <div className="App">
    <header className="App-header">
        <p>
            WRITE UR PHONE HERE. 
        </p>
    </header>
    </div>
}