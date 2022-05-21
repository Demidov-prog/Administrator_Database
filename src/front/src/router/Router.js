import {Route, Routes} from "react-router-dom";
import Home from "../components/views/Home";
import Login from "../components/views/Login";
import Profile from "../components/views/Profile";
import Users from "../components/views/Admin";
import Checkout from "../components/views/Checkout";
import ProductList from "../components/views/ProductList";
import Registration from "../components/views/Registration";

export default function Router() {
    return (
        <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/login" element={<Login/>}/>
            <Route path="/profile" element={<Profile/>}/>
            <Route path="/users" element={<Users/>}/>
            <Route path="/checkout" element={<Checkout/>}/>
            <Route path="/productlist" element={<ProductList/>}/>
            <Route path="/registration" element={<Registration/>}/>
        </Routes>
    )
}