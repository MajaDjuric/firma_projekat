import { useNavigate } from "react-router-dom";
import Axios from "../../apis/Axios";
import { Button } from "react-bootstrap";
import React, { useCallback, useEffect, useState } from "react";


const RobaRow = (props) => {

    const robaId = props.roba.id;

    ///navigate
    var navigate = useNavigate()

    //putanja do izmene
    // const goToIzmena = () => {
    //     navigate('/vina/izmena/' + vinoId);
    // }


    //brisanje 
    const deleteRoba = () => {
        Axios.delete('/roba/' + robaId)
            .then(res => {
                console.log(res);
                alert('Uspesno brisanje!');
                window.location.reload();
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske, pokusajte ponovo!');
            });
    }


    //krajnji ispis

    // if( window.localStorage.getItem('role') == 'ROLE_KORISNIK' && props.vino.brojDostupnihFlasa <= 0){
    //     return null
    // }

    return (
        <tr key={props.roba.id}>
            <td>{props.roba.id}</td>
            <td>{props.roba.naziv}</td>
            <td>{props.roba.vrstaNaziv}</td>
            <td>{props.roba.tipNaziv}</td>
            <td>{props.roba.proizvodjacNaziv}</td>
            <td>{props.roba.pakovanje}</td>
            <td>{props.roba.jedinicaMere}</td>
            <td>{props.roba.tretman}</td>
            <td>{props.roba.ulaznaCena}</td>
            <td>{props.roba.prodajnaCena}</td>
            <td>{props.roba.ulaz}</td>
            <td>{props.roba.izlaz}</td>
            <td>{props.roba.stanje}</td>
            {/* <td><Button variant="warning" onClick={goToIzmena}>Edit</Button></td> */}

            {/* {window.localStorage.getItem('role') == 'ROLE_ADMIN' && props.vino.brojDostupnihFlasa < 10 ? (
             <React.Fragment key="adminElements">
            <td><input  onChange={(e) => onChangeBrojFlasa(e)} style={{ width: '40px' }} type="text"></input></td>
            <td><Button variant="primary" onClick={() => izmeni(props.vino.id)} >Naruci</Button></td>
            </React.Fragment>): null} */}
        </tr>
    )
}

export default RobaRow;