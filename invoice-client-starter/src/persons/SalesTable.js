import React, {useState, useEffect} from "react";
import { apiGet } from "../utils/api";

export const SalesTable = () => {

    const [sales, setSales] = useState([]);

    useEffect(() => {
        apiGet("/api/identification/{identificationNumber}/sales").then((data) => setSales(data));
    },[]);

    return (
        <div>
            <p>
                <h5><em>Vystavené faktury</em></h5>
            </p>
            
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
    
