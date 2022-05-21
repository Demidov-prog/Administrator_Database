import classes from '../../styles/userItem.module.css'
import {useState} from "react";
import {Link} from "react-router-dom";

export default function ProductItemForBuy(props) {
    const {id, productname, price, quantity} = props

    return (
        <li key={id} className={classes.flex}>
            <label className={classes.padding}>name: {productname}</label>
            <label className={classes.padding}>price: {price}</label>
            <label className={classes.padding}>quantity: {quantity}</label>
            
            <Link to="/checkout">
                <button >
                    Buy!
                </button>
            </Link>
        </li>
    )
}