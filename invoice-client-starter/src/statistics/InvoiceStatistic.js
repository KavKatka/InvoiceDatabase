import React, {useState, useEffect} from "react";


export function GeneralStatistic(items){

    const [statisticsState, setStatistics] = useState("");

    useEffect(() => {
        apiGet("/api/invoices/statistics").then((data) => setStatistics(data));
    }) 


    return(
        <>
            <h1>Generální statistiky dle faktur</h1>
            <hr />
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>Celkem</th>
                        <th>Počet faktur</th>
                        <th>Celkem za rok</th>
                    </tr>
                </thead>
                <tbody>
                    {items.map((item) => (
                        <tr>
                            <td>{item.allTimeSum}</td>
                            <td>{item.invoiceCount}</td>
                            <td>{item.currentYearSum}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    )
}