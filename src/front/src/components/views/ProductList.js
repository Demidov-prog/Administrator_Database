import ProductItem from "./ProductItem"
import {useEffect, useState} from "react";
import {deleteProduct, editProduct} from "../../services/productService";
import {useSelector} from "react-redux";
import {Navigate} from "react-router-dom";
import React from "react";
import {getAllProducts} from "../../services/productService";

export default function ProductList() {

    const [items, setItems] = useState()
    const user = useSelector(state => state.user)

    useEffect(() => {
        if (!items) {
            getToken();
        }
    }, [])

    if (!user || (user.username!=='admin'))
        return <Navigate to={'/'}/>

    const getToken = async () => {
        let data = await getAllProducts()
        let temp = []
        for (let i=0; i < data.length; i+=1){
            
            temp.push(<ProductItem id={data[i].id} productname={data[i].productname} price={data[i].price} quantity={data[i].quantity} 
            updateProduct_={editProduct} deleteProduct_={deleteProduct}/>)
        }
    setItems(temp)
    }
        
    return (
        <ul>
            {items}
        </ul>
    )
}