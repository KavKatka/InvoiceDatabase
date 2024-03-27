import React from "react";
import {Link} from "react-router-dom"

const PurchasesTable = ({label, items}) => {

    return (
        <div>
            <p>
                {label} {items.lenght}
            </p>
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>#</th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    
                </tbody>
            </table>

        </div>
    )
}