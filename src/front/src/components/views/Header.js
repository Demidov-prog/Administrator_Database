import classes from '../../styles/header.module.css'
import {Link} from "react-router-dom";
import {store} from "../store/store";
import {userLogout} from "../actions/userActions";
import {useSelector} from "react-redux";

export default function Header() {
    const user = useSelector(state => state.user)
    function logout(){
        store.dispatch(userLogout())
    }
    return (
        <header className={classes.header}>
            <Link to="/" className={classes.left}>Home</Link>
            <div className={classes.right}>
                <Link
                    to={'login'}
                >Login</Link>
                <Link
                    to={'/profile'}
                >Profile</Link>
                <Link
                    to={'/users'}
                >Users</Link>
                <Link
                    to={'/productlist'}
                >ProductList</Link>
                <Link
                    to={'/registration'}
                >Registration</Link>
                <button onClick={logout} disabled={user == null}>Logout</button>
            </div>
        </header>
    );
}