import React from "react";
import InputField from "../components/InputField";
import InputSelect from "../components/InputSelect";

export const InvoiceFilter =(props) => {

    const handleChange = (e) => {
        props.handleChange(e);
    }

    const handleSubmit = (e) => {
        props.handleSubmit(e);
    }

    const filter = props.filter;

    return(
        <form onSubmit={handleSubmit}>
            <div className="row">
                <div className="col">
                    <InputSelect
                        name="buyerID"
                        items={props.buyerIDList}
                        required={true}
                        multiple={false}
                        handleChange={handleChange}
                        label="Odběratel"
                        prompt="nevybrán"
                        value={filter.buyerID}
                    />
                </div>
                <div className="col">
                    <InputSelect
                        name="sellerID"
                        items={props.sellerIDList}
                        handleChange={handleChange}
                        required={true}
                        multiple={false}
                        label="Dodavatel"
                        prompt="nevybrán"
                        value={filter.sellerID}
                    />
                </div>
                <div className="col">
                    <InputField
                        
                        type="text"
                        name="product"
                        min="3"
                        handleChange={handleChange}
                        label="Produkt"
                        prompt="Zadejte vyhledávaný produkt"
                        value={filter.product ? filter.product : ''}
                    />
                </div>
                <div className="col">
                    <InputField
                        
                        type="number"
                        name="minPrice"
                        min="0"
                        handleChange={handleChange}
                        label="Minimální cena"
                        prompt="Zadejte minimální cenu"
                        value={filter.minPrice ? filter.minPrice : ''}
                    />
                </div>
                <div className="col">
                    <InputField
                        
                        type="number"
                        name="maxPrice"
                        min="0"
                        handleChange={handleChange}
                        label="Maximální cena"
                        prompt="Zadejte maximální cenu"
                        value={filter.maxPrice ? filter.maxPrice : ''}
                    />
                </div>
                <div className="col">
                    <InputField
                        type="number"
                        min="1"
                        name="limit"
                        handleChange={handleChange}
                        label="Limit počtu faktur"
                        prompt="neuveden"
                        value={filter.limit ? filter.limit : ''}
                    />
                </div>
            </div>
            <div className="row">
                <div className="col">
                    <input  
                        type="submit"
                        className="btn btn-secondary float-right mt-2"
                        value={props.confirm}
                    />
                </div>
            </div>
        </form>
            

        
    )




} 