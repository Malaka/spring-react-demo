import React, {Component} from 'react';
import {Button, Container, Input, InputGroup, Table} from 'reactstrap';
import {Link} from 'react-router-dom';

class ItemSearch extends Component {

    constructor(props) {
        super(props);
        this.state = {
            searchString: this.props.match.params.name,
            items: [],
            isLoading: true
        };
    }

    async componentDidMount() {
        await this.searchByCategory();
    };

    searchByCategory = async () => {
        this.setState({isLoading: true});
        await fetch(`/api/search/${this.state.searchString}`)
            .then(response => response.json())
            .then(data => this.setState({items: data, isLoading: false}));
    };

    handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        this.setState({searchString: value});
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
                    <td>{item.category.name}</td>
                </tr>
            );
        });

        return (
            <div>
                <Container fluid>
                    <div className="float-right">
                        <InputGroup>
                            <Input type="text" name="searchString" id="searchString" value={this.state.searchString}
                                   onChange={this.handleChange} autoComplete="searchString"/>
                            <Button color="success" onClick={this.searchByCategory}>Search</Button>
                        </InputGroup>
                    </div>
                    <h3>Search By Category</h3>

                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th>Description</th>
                            <th>Category</th>
                        </tr>
                        </thead>
                        <tbody>
                        {itemList}
                        </tbody>
                    </Table>
                    <Button color="secondary" tag={Link} to="/items">Back</Button>
                </Container>
            </div>
        );
    }
}

export default ItemSearch;