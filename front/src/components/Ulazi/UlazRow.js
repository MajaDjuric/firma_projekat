import { useNavigate } from "react-router-dom";
import Axios from "../../apis/Axios";
import { Button } from "react-bootstrap";
import React, { useCallback, useEffect, useState } from "react";


const UlazRow = (props) => {

    const ulazId = props.ulaz.id;
    const robaId = props.robaId

    ///navigate
    var navigate = useNavigate()
    const [kolicina, setKolicina] = useState('')

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

    //dobavljanje kolicine kod pretrage po nazivu robe
    const getKolicina = () => {
        if (robaId != '') {
            Axios.get('/ulaziRobe/' + ulazId + '/' + robaId)
                .then(res => {
                    console.log(res);
                    setKolicina(res.data)
                })
                .catch(error => {
                    console.log(error);
                    alert('Doslo je do greske, pokusajte ponovo!');
                });
        }
    }

    useEffect(() => {
        getKolicina()
    }, [robaId])


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
            {robaId != '' ? <td>{kolicina}</td> : null}
        </tr>
    )
}

export default UlazRow;