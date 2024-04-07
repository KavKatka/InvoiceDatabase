import React from "react";
import InputField from "../components/InputField";
import InputSelect from "../components/InputSelect";

export const InvoiceFilter =(props) => {

    const handleChange = (e) => {
        e.handleChange(e);
    }

    const handleSubmit = (e) => {
        e.handleSubmit(e);
    }

    const filter = props.filter;

    // const [buyerList, setBuyerList] = useState([]);
    // const [sellerList, setSellerList] = useState([]);



    return(
        <form onSubmit={handleSubmit}>
            <div className="row">
                <div className="col">
                    <InputSelect
                        name="buyerID"
                        items={props.buyerIDList}
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
                        label="Dodavatel"
                        prompt="nevybrán"
                        value={filter.sellerID}
                    />
                </div>
                <div className="col">
                    <InputField
                        required={true}
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
                        required={true}
                        type="number"
                        name="minPrice"
                        min="1"
                        handleChange={handleChange}
                        label="Minimální cena"
                        prompt="Zadejte minimální cenu"
                        value={filter.minPrice ? minPrice : ''}
                    />
                </div>
                <div className="col">
                    <InputField
                        required={true}
                        type="number"
                        name="maxPrice"
                        min="1"
                        handleChange={handleChange}
                        label="Maximální cena"
                        prompt="Zadejte maximální cenu"
                        value={filter.maxPrice ? filter.maxPrice : ''}
                    />
                </div>

            </div>
        </form>
            

        
    )




} 