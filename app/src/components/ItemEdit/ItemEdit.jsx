import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';

class ItemEdit extends Component {

    emptyItem = {
        name: '',
        description: '',
        category: {}
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem,
        };
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            await fetch(`/api/item/${this.props.match.params.id}`)
                .then(response => response.json())
                .then(item => this.setState({item: item}));
        }
    };

    handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    };

    handleChangeCategory = (event) => {
        const target = event.target;
        const value = target.value;
        let item = {...this.state.item};
        item.category = {
            ...item.category,
            name: value
        };
        this.setState({item});
    };

    handleSubmit = async (event) => {
        event.preventDefault();
        const {item} = this.state;
        const url = (item.id) ? `/api/item/${item.id}` : "/api/item";

        await fetch(url, {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/items');
    };

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Grocery Item' : 'Add Grocery Item'}</h2>;

        return (
            <div>
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <Label for="name">Name</Label>
                            <Input type="text" name="name" id="name" value={item.name || ''}
                                   onChange={this.handleChange} autoComplete="name"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="description">Description</Label>
                            <Input type="text" name="description" id="description" value={item.description || ''}
                                   onChange={this.handleChange} autoComplete="description"/>
                        </FormGroup>

                        <FormGroup>
                            <Label for="description">Category</Label>
                            <Input type="text" name="category" id="description"
                                   value={item.category ? item.category.name : ''}
                                   onChange={this.handleChangeCategory}/>
                        </FormGroup>
                        <FormGroup>
                            <Button color="primary" type="submit">Save</Button>{' '}
                            <Button color="secondary" tag={Link} to="/items">Cancel</Button>
                        </FormGroup>
                    </Form>
                </Container>
            </div>
        )
    }
}

export default withRouter(ItemEdit);