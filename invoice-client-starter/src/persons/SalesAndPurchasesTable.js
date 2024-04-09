import React from "react";
import { Link } from "react-router-dom";

export const SalesAndPurchasesTable = ({items}) => {

    return (
        <>
        <div>
            
            <h5><em>Přijaté faktury</em></h5> 

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
                    {items.map((purchase, index) => (
                        <tr key={index + 1}>    
                            <td>
                                <Link to={"/persons/show/" + purchase.seller._id}>
                                    {purchase.seller.name}
                                </Link>
                            </td>
                            <td></td>
                            <td>{purchase.invoiceNumber}</td>
                            <td>{purchase.dueDate}</td>
                            <td>{purchase.product}</td>
                            <td>{purchase.price}</td>
                            <td>{purchase.vat}</td>
                        </tr>    
                    ))}
                </tbody>
            </table>

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
                    {items.map((sale, index) =>(
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
        </div>
        </>
    )
}