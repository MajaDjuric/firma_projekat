import { useNavigate } from "react-router-dom";
import Axios from "../../apis/Axios";
import { Button, FormControl } from "react-bootstrap";
import React, { useCallback, useEffect, useState } from "react";


const RobaRow = (props) => {

    const robaId = props.roba.id;

    const [prodajnaCena, setProdajnaCena] = useState(props.roba.prodajnaCena)
    const [izmenaButton, setIzmenaButton] = useState(false)

    ///navigate
    var navigate = useNavigate()

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

    const izmenaProdajneCene = () => {

        const requestBody = {
            prodajnaCena: prodajnaCena
        }

        Axios.put('/roba/' + robaId, requestBody)
            .then(res => {
                console.log(res);
                alert('Uspesna izmena!');
                window.location.reload();
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske, pokusajte ponovo!');
            });
    }

    const prodajnaCenaOnChange = (e) => {
        setProdajnaCena(e.target.value)
        setIzmenaButton(true)
    }

    //krajnji ispis
    return (
        <tr key={props.roba.id}>
            <td>{props.roba.id}</td>
            <td>{props.roba.naziv}</td>
            <td>{props.roba.tipNaziv}</td>
            <td>{props.roba.proizvodjacNaziv}</td>
            <td>{props.roba.pakovanje}</td>
            <td>{props.roba.jedinicaMere}</td>
            {props.roba.vrstaNaziv == 'SEMENA' ? <td>{props.roba.tretman}</td> : null}
            <td>{props.roba.ulaz}</td>
            <td>{props.roba.izlaz}</td>
            <td>{props.roba.stanje}</td>
            {
                window.localStorage.getItem('role') == 'ROLE_FINANSIJE' ? <td><FormControl style={{ width: '120px' }} onChange={(e) => prodajnaCenaOnChange(e)} value={prodajnaCena.toFixed(2)}
                    name="prodajnaCena" type="number" /> </td> : <td>{props.roba.prodajnaCena.toFixed(2)}</td>
            }
            {window.localStorage.role == 'ROLE_FINANSIJE' ? <td><Button variant="warning" disabled={!izmenaButton} onClick={izmenaProdajneCene}>Izmeni</Button></td> : null}
        </tr>
    )
}

export default RobaRow;