import Axios from '../../apis/Axios';
import { useCallback, useEffect, useState } from "react"
import { Button, Col, Form, Row, Table } from "react-bootstrap"
import { useNavigate, useParams } from 'react-router-dom';
import RobaRow from './UlazRow';
import UlazRow from './UlazRow';

const UlaziRobe = (props) => {

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
    const [ulaziRobe, setUlaziRobe] = useState([])
    const [roba, setRoba] = useState([])
    const [proizvodjaci, setProizvodjaci] = useState([])
    const [hidden, setHidden] = useState(false)


    //dobavljanje svih
    const getUlaziRobe = useCallback(() => {

        Axios.get('/ulaziRobe/' + ulazId + '/ulazi')
            .then(res => {
                console.log(res);
                setUlaziRobe(res.data)
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
        getUlaziRobe()
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

    const ulaziRobeRender = () => {
        return ulaziRobe.map((ulazRobe, index) => {
            return (
                <tr key={ulazRobe.id}>
                    <td>{index + 1}</td>
                    <td>{ulazRobe.robaNaziv}</td>
                    <td>{ulazRobe.robaPakovanje}</td>
                    <td>{ulazRobe.robaJedinicaMere}</td>
                    <td>{ulazRobe.kolicina}</td>
                    <td>{ulazRobe.cenaPoJediniciMere}</td>
                    <td>{ulazRobe.pdv}</td>
                    <td>{ulazRobe.rabat}</td>
                    <td>{ulazRobe.krajnjaCena}</td>


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

                {/* <h2>Broj fakture: {ulaz.brojFakture}</h2> */}
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
                            <th>PDV</th>
                            <th>Rabat</th>
                            <th>Krajnja cena</th>
                            {/* {window.localStorage.getItem('role') == 'ROLE_ADMIN' ? <th>Broj preostalih flasa</th> : null} */}
                        </tr>
                    </thead>
                    <tbody>
                        {ulaziRobeRender()}
                    </tbody>
                </Table>
            </Col>
            </Row>
        </Col>
    )
}

export default UlaziRobe

// {window.localStorage.getItem('role') == 'ROLE_ADMIN' ?