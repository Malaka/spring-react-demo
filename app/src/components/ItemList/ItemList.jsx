import React, {Component} from 'react';
import {Button} from 'reactstrap';
import {Link} from 'react-router-dom';
import {ItemsDisplay} from '../ItemsDisplay/ItemsDisplay'

class ItemList extends Component {

    constructor(props) {
        super(props);
        this.state = {items: [], isLoading: true};
    }

    async componentDidMount() {
        this.setState({isLoading: true});
        await fetch('api/items')
            .then(response => response.json())
            .then(data => this.setState({items: data, isLoading: false}));
    };

    removeHandler = async (id) => {
        await fetch(`/api/item/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedItems = [...this.state.items].filter(i => i.id !== id);
            this.setState({items: updatedItems});
        });
    };

    render() {
        const {items, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <ItemsDisplay items={items} removeHandler={this.removeHandler}>
                <div className="float-right">
                    <Button color="success" tag={Link} to="/items/new">Add Grocery Item</Button>
                </div>
                <h3>My Grocery List</h3>
            </ItemsDisplay>
        );
    }
}

export default ItemList;