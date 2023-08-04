import Axios from '../../apis/Axios';
import { useCallback, useEffect, useState } from "react"
import { Button, Col, Form, Row, Table } from "react-bootstrap"
import { useNavigate, useParams } from 'react-router-dom';
import RobaRow from './UlazRow';
import UlazRow from './UlazRow';

const Ulaz = (props) => {

    //navigate
    const navigate = useNavigate()

    const params = useParams()

    const ulazId = params.id

    const init = {
        datumUlaza: '',
        brojFakture: '',
        brojOtpremnice: '',
        proizvodjacId: ''
    }

    //state
    const [ulaz, setUlaz] = useState([])
    const [roba, setRoba] = useState([])
    const [proizvodjaci, setProizvodjaci] = useState([])
    const [hidden, setHidden] = useState(false)


    //dobavljanje svih
    const getUlaz = useCallback(() => {

        Axios.get('/ulazi/' + ulazId)
            .then(res => {
                console.log(res);
                setUlaz(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    const getRoba = useCallback(() => {
        Axios.get('/ulazi/' + ulazId + '/roba')
            .then(res => {
                console.log(res);
                setRoba(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    // const getProizvodjaci = useCallback(() => {
    //     Axios.get('/proizvodjaci')
    //         .then(res => {
    //             console.log(res);
    //             setProizvodjaci(res.data)
    //         })
    //         .catch(error => {
    //             console.log(error);
    //             alert('Doslo je do greske!')
    //         });
    // }, []);


    useEffect(() => {
        getUlaz()
        getRoba()
    }, [])


    //ispis za select
    const robaSelect = () => {
        return roba.map(proizvod => {
            return (
                <option key={proizvod.id} value={proizvod.id}>{proizvod.naziv}  {proizvod.pakovanje} {proizvod.jedinicaMere}</option>
            )
        })
    }

    const robaRender = () => {
        return roba.map((roba1, index) => {
            return (
                <tr key={roba1.id}>
                    <td>{index + 1}</td>
                    <td>{roba1.naziv}</td>
                    <td>{roba1.pakovanje}</td>
                    <td>{roba1.jedinicaMere}</td>
                    <td>{ }</td>

                </tr>

            )
        })
    }


    //onchange
    // const onInputChange = (e) => {
    //     let name = e.target.name
    //     let value = e.target.value

    //     let parametriCopy = parametriPretrage
    //     parametriCopy[name] = value
    //     setParametriPretrage(parametriCopy)
    // }


    //krajnji ispis
    return (
        <Col>
            {/* <Row><h1>Vina</h1></Row> */}

            <br /><br />
            <Row><Col>

                {/* <h1>Broj fakture: {ulaz.brojFakture}</h1> */}
                <h2>Broj fakture: {ulaz.brojFakture}</h2>
                <br />

                <Table style={{ marginTop: 5 }}>
                    <thead>
                        <tr>
                            <th>Redni broj</th>
                            <th>Naziv</th>
                            <th>Pakovanje</th>
                            <th>Jedinica mere</th>
                            <th>Kolicina</th>
                            <th>Osnovna cena</th>
                            <th>Rabat</th>
                            <th>PDV</th>
                            <th>Krajnja cena</th>
                            {/* {window.localStorage.getItem('role') == 'ROLE_ADMIN' ? <th>Broj preostalih flasa</th> : null} */}
                        </tr>
                    </thead>
                    <tbody>
                        {robaRender()}
                    </tbody>
                </Table>
            </Col>
            </Row>
        </Col>
    )
}

export default Ulaz

// {window.localStorage.getItem('role') == 'ROLE_ADMIN' ?