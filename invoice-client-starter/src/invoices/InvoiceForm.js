import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";

import {apiGet, apiPost, apiPut} from "../utils/api";

import InputField from "../components/InputField";
import FlashMessage from "../components/FlashMessage";
import InputSelect from "../components/InputSelect";


const InvoiceForm = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const [invoice, setInvoice] = useState({
        
        invoiceNumber: "",
        seller: {
            _id: ""
        },
        buyer: {
            _id: ""
        },
        issued: Date,
        dueDate: Date,
        product: "",
        price: "",
        vat: "",
        note: ""
    });
    const [sentState, setSent] = useState(false);
    const [successState, setSuccess] = useState(false);
    const [errorState, setError] = useState(null);

    const [buyerList, setBuyerList] = useState([]);
    const [sellerList, setSellerList] = useState([]);

    useEffect(() => {
        if (id) {
            apiGet("/api/invoices/" + id).then((data) => setInvoice(data));
        }
        apiGet("/api/persons").then((data) => setBuyerList(data));
        apiGet("/api/persons").then((data) => setSellerList(data));
    }, [id]);



    const handleSubmit = (e) => {
        e.preventDefault();

        (id ? apiPut("/api/invoices/" + id, invoice) : apiPost("/api/invoices", invoice))
            .then((data) => {
                setSent(true);
                setSuccess(true);
                navigate("/invoices");
            })
            .catch((error) => {
                console.log(error.message);
                setError(error.message);
                setSent(true);
                setSuccess(false);
            });
    };

    const sent = sentState;
    const success = successState;

    return (
        <div>
            <h1>{id ? "Upravit" : "Vytvořit"} fakturu</h1>
            <hr/>
            {errorState ? (
                <div className="alert alert-danger">{errorState}</div>
            ) : null}
            {sent && (
                <FlashMessage
                    theme={success ? "success" : ""}
                    text={success ? "Uložení faktury proběhlo úspěšně." : ""}
                />
            )}
            <form onSubmit={handleSubmit}>
                <InputField
                    required={true}
                    type="text"
                    name="invoiceNumber"
                    min="3"
                    label="Číslo faktury"
                    prompt="Zadejte číslo faktury"
                    value={invoice.invoiceNumber}
                    handleChange={(e) => {
                        setInvoice({...invoice, invoiceNumber: e.target.value});
                    }}
                />

                <InputSelect 
                    items={sellerList}
                    required={true}
                    multiple={false}
                    type="text"
                    name="seller"
                    label="Odběratel"
                    prompt="Vyberte dodavatele"
                    value={invoice.seller._id}
                    handleChange={(e) => {
                        setInvoice({...invoice, seller:{_id: e.target.value}})
                    }}
                />
                
                <InputSelect 
                    items={buyerList}
                    required={true}
                    multiple={false}
                    type="text"
                    name="buyer"
                    label="Dodavatel"
                    prompt="Vyberte odběratele"
                    value={invoice.buyer._id}
                    handleChange={(e) => {
                        setInvoice({...invoice, buyer:{_id: e.target.value}})
                    }}
                />
                
                {/* 
                Původní inputField - změna na Select

                <InputField
                    required={true}
                    type="text"
                    name="seller"
                    min="1"
                    label="Dodavatel"
                    prompt="Zadejte ID Dodavatele"
                    value={invoice.seller._id}
                    handleChange={(e) => {
                        setInvoice({...invoice, seller:{_id: e.target.value}});
                    }}
                />
               
                <InputField
                    required={true}
                    type="text"
                    name="buyer"
                    min="1"
                    label="Odběratel"
                    prompt="Zadejte ID Odběratele"
                    value={invoice.buyer._id}
                    handleChange={(e) => {
                        setInvoice({...invoice, buyer:{_id: e.target.value}});
                    }}
                /> */}
               
                <InputField
                    required={true}
                    type="date"
                    name="issued"
                    min="3"
                    label="Datum vystavení"
                    prompt="Zadejte datum vystavení"
                    value={invoice.issued}
                    handleChange={(e) => {
                        setInvoice({...invoice, issued: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="date"
                    name="dueDate"
                    min="3"
                    label="Datum splatnosti"
                    prompt="Zadejte datum splatnosti"
                    value={invoice.dueDate}
                    handleChange={(e) => {
                        setInvoice({...invoice, dueDate: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="text"
                    name="product"
                    min="3"
                    label="Produkt"
                    prompt="Zadejte název produktu"
                    value={invoice.product}
                    handleChange={(e) => {
                        setInvoice({...invoice, product: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="text"
                    name="price"
                    min="1"
                    label="Cena"
                    prompt="Zadejte cenu"
                    value={invoice.price}
                    handleChange={(e) => {
                        setInvoice({...invoice, price: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="text"
                    name="vat"
                    min="1"
                    label="DPH"
                    prompt="Zadejte výši DPH"
                    value={invoice.vat}
                    handleChange={(e) => {
                        setInvoice({...invoice, vat: e.target.value});
                    }}
                />

                <InputField
                    required={true}
                    type="text"
                    name="note"
                    label="Poznámka"
                    value={invoice.note}
                    handleChange={(e) => {
                        setInvoice({...invoice, note: e.target.value});
                    }}
                />
                <input type="submit" className="btn btn-primary" value="Uložit"/>
            </form>
        </div>
    );
};

export default InvoiceForm;
