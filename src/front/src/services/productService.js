import {store} from "../components/store/store";
import {productEdit} from "../components/actions/productActions";
import {productDelete} from "../components/actions/productActions";

export function editProduct(product) {
    return fetch('http://localhost:3001/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
        .then(() => {
                if (product.id === store.getState().product.id)
                    store.dispatch(productEdit(product))
            }
        )
}

export function deleteProduct(product) {
    return fetch('http://localhost:3001/deleteProduct', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
        .then(() => {
            if (product.id === store.getState().product.id)
                store.dispatch(productDelete())
        }
    )
}

export async function getAllProducts() {
    const data = await fetch('http://localhost:3001/getAllProducts', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(data => { 
            return data 
        })  
 //   console.log(data)
    return data.json()

 //   console.log(finData)
    
}

