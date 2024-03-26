import React, {useEffect, useState} from "react";

import {apiDelete, apiGet} from "../utils/api";

import InvoiceTable from "./InvoiceTable";

const InvoiceIndex = () => {
    const [invoices, setInvoice] = useState([]);

    const deleteInvoice = async (id) => {
        try {
            await apiDelete("/api/invoice/" + id);
        } catch (error) {
            console.log(error.message);
            alert(error.message)
        }
        setInvoice(invoices.filter((item) => item._id !== id));
    };

    useEffect(() => {
        apiGet("/api/invoices").then((data) => setInvoice(data));
    }, []);

    return (
        <div>
            <h1>Seznam Faktur</h1>
            <InvoiceTable
                deleteInvoice={deleteInvoice}
                items={invoices}
                label="Počet faktur:"
            />
        </div>
    );
};
export default InvoiceIndex;