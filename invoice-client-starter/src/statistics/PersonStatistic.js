import React, { useEffect, useState } from "react";
import { apiGet } from "../utils/api";

export function IndividualStatistic({items}) {

    const [statisticsState, setStatistics] = useState([]);

    useEffect(() => {
        apiGet("/api/persons/statistics").then((data) => setStatistics(data));
    }) 

    
    
    return(
        <>
            <h1>Individuální statistiky dle osob</h1>
            <hr />
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>ID osoby</th>
                        <th>Jméno</th>
                        <th>Příjem</th>
                    </tr>
                </thead>
                <tbody>
                    {items.map((item) => (
                        <tr>
                            <td>{item.personId}</td>
                            <td>{item.personName}</td>
                            <td>{item.revenue}</td>
                    </tr>
                    ))}
                </tbody>
            </table>
        </>
    )
}