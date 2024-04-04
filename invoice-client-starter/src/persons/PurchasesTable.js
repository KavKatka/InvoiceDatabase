import React, {useState, useEffect} from "react";
import { apiGet } from "../utils/api";


export const PurchasesTable = () => {

    const [invoices, setInvoices] = useState([]);
    const [purchases, setPurchases] = useState([]);
    const [identificationNumber, setIdentificationNumber] = useState("");

    useEffect(() => {
        apiGet("/api/identification/{identificationNumber}/purchases").then((data) => setPurchases(data));
    },[]);

    return (
        <div>
            <p>
               <h5><em>Přijaté faktury</em></h5> 
            </p>
            
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>Dodavatel</th>
                        <th>IČO</th>
                        <th>Číslo faktury</th>
                        <th>Datum splatnosti</th>
                        <th>Produkt</th>
                        <th>Cena</th>
                        <th>DPH</th>
                    </tr>
                </thead>
                <tbody>
                    {purchases.map((purchase, index) => (
                        <tr key={index + 1}>    
                            <td>{purchase.seller.name}</td>
                            <td>{purchase.seller.identificationNumber}</td>
                            <td>{purchase.invoiceNumber}</td>
                            <td>{purchase.dueDate}</td>
                            <td>{purchase.product}</td>
                            <td>{purchase.price}</td>
                            <td>{purchase.vat}</td>
                        </tr>    
                    ))}
                </tbody>
            </table>
        </div>
    )
}