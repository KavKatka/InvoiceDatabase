import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {apiGet} from "../utils/api";
import { Link } from "react-router-dom";

const InvoiceDetail = () => {
    const {id} = useParams();
    const [invoice, setInvoice] = useState({});

    useEffect(() => {
        apiGet("/api/invoices/" + id).then((data) => setInvoice(data));
    }, []);

   
    return (
        <>
            <div>
                <h1>Detail faktury</h1>
                <hr/>
                <h3> Číslo faktury: ({invoice.invoiceNumber})</h3>
                 <p>
                    <strong>Dodavatel</strong>
                    <br/>
                    <Link to={"/persons/show/" + invoice.seller?._id}>
                        {invoice.seller?.name}
                    </Link>
                    <br/>
                    IČO:  {invoice.seller?.identificationNumber}
                  
                </p>
                <p>
                    <strong>Odběratel</strong>
                    <br/>
                    <Link to={"/persons/show/" + invoice.buyer?._id}>
                        {invoice.buyer?.name}
                    </Link>
                    <br/>
                    IČO: {invoice.buyer?.identificationNumber}
                </p> 
                <p>
                    <strong>Datum vystavení</strong>
                    <br/>
                    {invoice.issued}
                </p>
                <p>
                    <strong>Datum splatnosti</strong>
                    <br/>
                    {invoice.dueDate}
                </p>
                <p>
                    <strong>Produkt</strong>
                    <br/>
                    {invoice.product}
                </p>
                <p>
                    <strong>Cena</strong>
                    <br/>
                    {invoice.price} Kč
                </p>
                <p>
                    <strong>DPH</strong>
                    <br/>
                    {invoice.vat} %
                </p>
                <p>
                    <strong>Poznámka</strong>
                    <br/>
                    {invoice.note}
                </p>
            </div>
        </>
    );
};

export default InvoiceDetail;
