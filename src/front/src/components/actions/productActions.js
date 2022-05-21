import {PRODUCT_EDIT} from "./types";
import {PRODUCT_DELETE} from "./types";

export function productEdit(newProduct) {
    return {
        type: PRODUCT_EDIT,
        value: newProduct
    }
}
export function productDelete() {
    return {
        type: PRODUCT_DELETE,
        value: null
    }
}