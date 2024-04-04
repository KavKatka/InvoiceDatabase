import React from "react";


export const PurchasesAndSales = ({label, items}) => {

    return (
        <div>
            <p>
                {label}
            </p>
            
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Vydané faktury</th>
                        
                    </tr>
                </thead>
                <tbody>
                    <tr> 
                        <td></td>
                        <td></td>
                    </tr>
                  
                    
                </tbody>
            </table>
            
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Přijaté faktury</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {items.map((items, index) => (
                        <tr key={index + 1}>
                            <td>{index + 1}</td>
                            <td>{items.purchases}</td>
                        </tr>
                    ))}
                    
                </tbody>
            </table>
        </div>
    
    )
}