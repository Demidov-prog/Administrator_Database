import {useSelector} from "react-redux";
import ProductItemForBuy from "./ProductItemForBuy"
import {useEffect, useState} from "react";
import {deleteProduct, editProduct} from "../../services/productService";
import React from "react";
import {getAllProducts} from "../../services/productService";


export default function Home() {

    const [items, setItems] = useState()
    const user = useSelector(state => state.user)

    useEffect(() => {
        if (!items) {
            getToken();
        }
    }, [])

    const getToken = async () => {
        const data = await getAllProducts()
        const temp = []
        for (let i=0; i < data.length; i+=1){
            
            temp.push(<ProductItemForBuy id={data[i].id} productname={data[i].productname} price={data[i].price} quantity={data[i].quantity} 
            />)
        }
    setItems(temp)
    }

    if (user?.username==null){
    return (
        
        <div className="App">
            <header className="App-header">
                <p>
                    Welcome to my antique shop. Please, look at the products or log in to make purchases. 
                    <ul>
                        {items}
                    </ul>
                </p>
            </header>
             
        </div>
    )
    }
    else
    {
    return(
        <div className="App">
            <header className="App-header">
                <p>
                    Welcome to my antique shop, {user?.username}!
                    <ul>
                        {items}
                    </ul>
                </p>
            </header>
        </div>
        )
    }
}
