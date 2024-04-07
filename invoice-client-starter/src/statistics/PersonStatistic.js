import React, { useEffect, useState } from "react";
import { apiGet } from "../utils/api";
import { Link } from "react-router-dom";

export function IndividualStatistic() {

    const [statisticsState, setStatistics] = useState([]);

    useEffect(() => {
        apiGet("/api/persons/statistics").then((data) => setStatistics(data));
    },[]);

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
                    {statisticsState.map((statistic, index) => (
                        <tr key={index + 1}>
                            <td>{statistic.personId}</td>
                            <td>
                                <Link to={"/persons/show/" + statistic.personId}>
                                    {statistic.personName}
                                </Link>
                            </td>
                            <td>{statistic.revenue}</td>
                        </tr> 
                    ))}
                    
                </tbody>
            </table>
        </>
    )
}