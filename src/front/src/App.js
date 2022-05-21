import './styles/App.css';
import {BrowserRouter} from "react-router-dom";
import {Provider} from "react-redux";
import Header from "./components/views/Header";
import Router from "./router/Router";
import {store} from "./components/store/store";

function App() {

    return (
        <Provider store={store}>
            <BrowserRouter>
                <Header/>
                <Router/>
            </BrowserRouter>
        </Provider>
    );
}

export default App;