import * as React from "react";
import {shallow} from "enzyme";
import {ItemsDisplay} from "./ItemsDisplay";

describe("ItemsDisplay", () => {

    const items = [
        {
            id: 1,
            name: 'name1',
            description: 'description1',
            category: {
                name: 'food'
            }
        },
        {
            id: 2,
            name: 'name2',
            description: 'description2',
            category: {
                name: 'food'
            }
        }
    ];
    it("should render when passed handler", () => {
        const component = shallow(<ItemsDisplay items={items} removeHandler={() => {
        }}/>);
        expect(component).toMatchSnapshot();
    });

    it("should render when not pass handler", () => {
        const component = shallow(<ItemsDisplay items={items}/>);
        expect(component).toMatchSnapshot();
    });
});