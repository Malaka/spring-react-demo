import React from 'react';
import {Button, ButtonGroup, Container, Table} from "reactstrap";
import {Link} from "react-router-dom";

export const ItemsDisplay = (props) => {

    const {items, removeHandler} = props;

    const itemList = items.map(item => {
        return (
            <tr key={item.id}>
                <td style={{whiteSpace: 'nowrap'}}>{item.name}</td>
                <td>{item.description}</td>
                <td>{item.category && <Button size="sm" tag={Link}
                                              to={`/search/${item.category.name}`}>{item.category.name}</Button>}</td>
                {removeHandler && <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={`/items/${item.id}`}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => removeHandler(item.id)}>Delete</Button>
                    </ButtonGroup>
                </td>}
            </tr>
        );
    });

    return (
        <div>
            <Container fluid>
                {props.children}
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="20%">Name</th>
                        <th>Description</th>
                        <th>Category</th>
                        {removeHandler && <th width="10%">Actions</th>}
                    </tr>
                    </thead>
                    <tbody>
                    {itemList}
                    </tbody>
                </Table>
            </Container>
        </div>
    );
};
