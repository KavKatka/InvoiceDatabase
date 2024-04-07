import React, {useEffect, useState} from "react";

import {apiDelete, apiGet} from "../utils/api";

import InvoiceTable from "./InvoiceTable";
import { InvoiceFilter } from "./InvoiceFilter";

const InvoiceIndex = () => {
    const [invoices, setInvoice] = useState([]);
    const [buyerIDList, setBuyerIDList] = useState([]);
    const [sellerIDList, setSellerIDList] = useState([]);
    const [filterState, setFilter] = useState({
        buyerID: undefined,
        sellerID: undefined,
        product: undefined,
        minPrice: undefined,
        maxPrice: undefined,
        limit: undefined,
    });

    const deleteInvoice = async (id) => {
        try {
            await apiDelete("/api/invoices/" + id);
        } catch (error) {
            console.log(error.message);
            alert(error.message)
        }
        setInvoice(invoices.filter((item) => item._id !== id));
    };

    useEffect(() => {
        apiGet("/api/invoices").then((data) => setInvoice(data));
        apiGet("/api/persons").then((data) => {setBuyerIDList(data);setSellerIDList(data);});
        
    }, []);

    const handleChange = (e) => {
        if(e.target.value === "false" || e.target.value === "true" || e.target.value === ''){
            setFilter(prevState => {
                return {...prevState, [e.target.name]: undefined}
            });
        } else {
            setFilter(prevState => {
                return { ...prevState, [e.target.name]: e.target.value}
            });
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const params = filterState;

        const data = await apiGet("/api/invoices", params);
        setInvoice(data);
    };

    return (
        <div>
            <h1>Seznam Faktur</h1>
            <hr/>
           
            <InvoiceFilter
                handleChange={handleChange}
                handleSubmit={handleSubmit}
                buyerIDList={buyerIDList}
                sellerIDList={sellerIDList}
                filter={filterState}
                confirm="Filtrovat"
            />
            <hr/>
            <InvoiceTable
                deleteInvoice={deleteInvoice}
                items={invoices}
                label="Počet faktur:"
            />
        </div>
    );
};
export default InvoiceIndex;