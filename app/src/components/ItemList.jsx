import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import {Link} from 'react-router-dom';

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

    remove = async (id) => {
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

        const itemList = items.map(item => {
            return (
                <tr key={item.id}>
                    <td style={{whiteSpace: 'nowrap'}}>{item.name}</td>
                    <td>{item.description}</td>
                    <td>{item.category && <Button size="sm" tag={Link}
                                                  to={`/search/${item.category.name}`}>{item.category.name}</Button>}</td>
                    <td>
                        <ButtonGroup>
                            <Button size="sm" color="primary" tag={Link} to={"/items/" + item.id}>Edit</Button>
                            <Button size="sm" color="danger" onClick={() => this.remove(item.id)}>Delete</Button>
                        </ButtonGroup>
                    </td>
                </tr>
            );
        });

        return (
            <div>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/items/new">Add Grocery Item</Button>
                    </div>
                    <h3>My Grocery List</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th>Description</th>
                            <th>Category</th>
                            <th width="10%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {itemList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default ItemList;