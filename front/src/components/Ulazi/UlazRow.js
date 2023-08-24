import { useNavigate } from "react-router-dom";
import Axios from "../../apis/Axios";
import { Button } from "react-bootstrap";
import React, { useCallback, useEffect, useState } from "react";


const UlazRow = (props) => {

    const ulazId = props.ulaz.id;

    ///navigate
    var navigate = useNavigate()

    //putanja do izmene
    // const goToIzmena = () => {
    //     navigate('/vina/izmena/' + vinoId);
    // }


    //brisanje 
    const deleteUlaz = () => {
        Axios.delete('/ulazi/' + ulazId)
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

    const formatDatuma = (datum) => {
        datum = new Date(props.ulaz.datumUlaza)
        let dan = datum.getDate()
        let mesec = datum.getMonth() + 1
        let godina = datum.getFullYear()
        return (dan < 10 ? '0' + dan : dan) + '.' + (mesec < 10 ? '0' + mesec : mesec) + '.' + godina + '.'
    }

    return (
        <tr key={props.ulaz.id} onClick={() => navigate("/ulaziRobe/" + props.ulaz.id)}>
            <td>{props.ulaz.id}</td>
            <td>{formatDatuma(props.ulaz.datumUlaza)}</td>
            <td>{props.ulaz.brojFakture}</td>
            <td>{props.ulaz.brojOtpremnice}</td>
            <td>{props.ulaz.proizvodjacNaziv}</td>

            {/* <td><Button variant="warning" onClick={goToIzmena}>Edit</Button></td> */}

            {/* {window.localStorage.getItem('role') == 'ROLE_ADMIN' && props.vino.brojDostupnihFlasa < 10 ? (
             <React.Fragment key="adminElements">
            <td><input  onChange={(e) => onChangeBrojFlasa(e)} style={{ width: '40px' }} type="text"></input></td>
            <td><Button variant="primary" onClick={() => izmeni(props.vino.id)} >Naruci</Button></td>
            </React.Fragment>): null} */}
        </tr>
    )
}

export default UlazRow;