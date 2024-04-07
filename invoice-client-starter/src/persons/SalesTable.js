import React, {useState, useEffect} from "react";
import { apiGet } from "../utils/api";


export const SalesTable = () => {

    const [sales, setSales] = useState([]); 
    // const [invoices, setInvoices] = useState([]);
    // const [identificationNumber,setIdentificationNumber] = useState("");
    // const [person, setPerson] = useState("");
    
    
    useEffect(() => {
        // apiGet("/api/invoices").then((data) => setInvoices(data));
        // apiGet("/api/persons").then((data) => setPerson(data));
        // apiGet("/api/identification/" + person.identificationNumber +"/sales").then((data) => setIdentificationNumber(data));
    },[]);
    

    
    return (
        <div>
            <h5><em>Vystavené faktury</em></h5>

            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>Odběratel</th>
                        <th>IČO</th>
                        <th>Číslo faktury</th>
                        <th>Datum splatnosti</th>
                        <th>Produkt</th>
                        <th>Cena</th>
                        <th>DPH</th>
                    </tr>
                </thead>
                <tbody>
                    {sales.map((sale, index) =>(
                         <tr key={index + 1}>
                            <td>{sale.buyer.name}</td>
                            <td>{sale.buyer.identificationNumber}</td>
                            <td>{sale.invoiceNumber}</td>
                            <td>{sale.dueDate}</td>
                            <td>{sale.product}</td>
                            <td>{sale.price}</td>
                            <td>{sale.vat}</td>
                     </tr>       
                    ))}            
                </tbody>
            </table>
        </div>
    );
}
    
