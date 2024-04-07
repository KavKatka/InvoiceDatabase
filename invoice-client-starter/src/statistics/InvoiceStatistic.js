import React, {useState, useEffect} from "react";
import { apiGet } from "../utils/api";


export function GeneralStatistic(){

    const [statisticsState, setStatistics] = useState([]);

    useEffect(() => {
        apiGet("/api/invoices/statistics").then((data) => setStatistics(data));
    },[]); 


    return(
        <>
            <h1>Obecné statistiky</h1>
            <hr />
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>Celkem všechny faktury</th>
                        <th>Počet faktur</th>
                        <th>Celkem za rok 2024</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{statisticsState.allTimeSum} </td>
                        <td>{statisticsState.invoicesCount}</td>
                        <td>{statisticsState.currentYearSum} </td>
                    </tr>
                    
                </tbody>
            </table>
        </>
    )
}