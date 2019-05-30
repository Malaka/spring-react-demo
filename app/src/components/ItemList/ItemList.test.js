import * as React from "react";
import {shallow} from "enzyme";
import ItemList from "./ItemList";

describe('ItemList', () => {
    it('fetches data from server when server returns a successful response', async () => {
        const mockSuccessResponse = [{
            "id": 6,
            "name": "Fresh Milk",
            "description": "This is fresh",
            "category": {"id": 1, "name": "Milk"}
        }];
        const mockJsonPromise = Promise.resolve(mockSuccessResponse);
        const mockFetchPromise = Promise.resolve({
            json: () => mockJsonPromise,
        });
        jest.spyOn(global, 'fetch').mockImplementation(() => mockFetchPromise);

        const wrapper = shallow(<ItemList/>);

        expect(global.fetch).toHaveBeenCalledTimes(1);
        expect(global.fetch).toHaveBeenCalledWith('api/items');

        await flushPromises();
        expect(wrapper.state()).toEqual({
                "isLoading": false,
                "items": [{
                    "category": {"id": 1, "name": "Milk"},
                    "description": "This is fresh",
                    "id": 6,
                    "name": "Fresh Milk"
                }]
            }
        );
        expect(wrapper).toMatchSnapshot();
        global.fetch.mockClear();
    });
});

const flushPromises = () => {
    return new Promise(resolve => setImmediate(resolve));
};