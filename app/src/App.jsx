import React, {Component} from 'react';
import './App.css';
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import ItemList from './components/ItemList/ItemList';
import ItemEdit from "./components/ItemEdit/ItemEdit";
import ItemSearch from "./components/ItemSearch/ItemSearch";

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact component={() => <Redirect to="/items"/>}/>
                    <Route path='/items' exact component={ItemList}/>
                    <Route path='/items/:id' component={ItemEdit}/>
                    <Route path='/search/:name' component={ItemSearch}/>
                </Switch>
            </Router>
        )
    }
}

export default App;