/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */

import React, {useEffect, useState} from "react";
import {Form, useParams} from "react-router-dom";

import {apiGet} from "../utils/api";
import Country from "./Country";
import { PurchasesTable} from "./PurchasesTable"
import { SalesTable } from "./SalesTable";

const PersonDetail = () => {
    const {id} = useParams();
    const [person, setPerson] = useState({});

    useEffect(() => {
        apiGet("/api/persons/" + id).then((data) => setPerson(data));

    }, []);
    const country = Country.CZECHIA === person.country ? "Česká republika" : "Slovensko";

    return (
        <>
            <div className="row">
                <div className="col-6">
                    <h1>Detail osoby</h1>
                    <hr/>
                    <h3>{person.name} ({person.identificationNumber})</h3>
                    <p>
                        <strong>DIČ:</strong>
                        <br/>
                        {person.taxNumber}
                    </p>
                    <p>
                        <strong>Bankovní účet:</strong>
                        <br/>
                        {person.accountNumber}/{person.bankCode} ({person.iban})
                    </p>
                    <p>
                        <strong>Tel.:</strong>
                        <br/>
                        {person.telephone}
                    </p>
                    <p>
                        <strong>Mail:</strong>
                        <br/>
                        {person.mail}
                    </p>
                    <p>
                        <strong>Sídlo:</strong>
                        <br/>
                        {person.street}, {person.city},
                        {person.zip}, {country}
                    </p>
                    <p>
                        <strong>Poznámka:</strong>
                        <br/>
                        {person.note}
                    </p>
                </div>
                
                <div className="col-6">
                    
                    <br/>
                    <PurchasesTable />
                    <br/>
                    <SalesTable />
                        
                </div>
            </div>
        </>
    );
};

export default PersonDetail;
