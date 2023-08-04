import Axios from '../../apis/Axios';
import { useCallback, useEffect, useState } from "react"
import { Button, Col, Form, Row, Table } from "react-bootstrap"
import { useNavigate } from 'react-router-dom';
import RobaRow from './RobaRow';

const Roba = () => {

    //navigate
    const navigate = useNavigate()

    //state
    const [robas, setRobas] = useState([])
    const [vrste, setVrste] = useState([])
    const [proizvodjaci, setProizvodjaci] = useState([])
    const [pageNo, setPageNo] = useState(0)
    const [totalPage, setTotalPage] = useState(0)
    const [hidden, setHidden] = useState(false)
    const [parametriPretrage, setParametriPretrage] = useState({
        naziv: '',
        tretman: '',
        vrstaId: '',
        proizvodjacId: '',
        pakovanje: ''
    })

    //dobavljanje svih
    const getRoba = useCallback((nextPage) => {

        const config = {
            params: {
                pageNo: nextPage,
                naziv: parametriPretrage.naziv,
                vrstaId: parametriPretrage.vrstaId,
                proizvodjacId: parametriPretrage.proizvodjacId,
                pakovanje: parametriPretrage.pakovanje,
            }
        }
        if (parametriPretrage.tretman != '') {
            config.params.tretman = parametriPretrage.tretman
        }

        Axios.get('/roba', config)
            .then(res => {
                console.log(res);
                setRobas(res.data)
                setPageNo(nextPage)
                setTotalPage(res.headers["total-pages"])
                console.log(res.headers)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    // dobavljanje za pretragu
    const getVrste = useCallback(() => {
        Axios.get('/vrsteRobe')
            .then(res => {
                console.log(res);
                setVrste(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    const getProizvodjaci = useCallback(() => {
        Axios.get('/proizvodjaci')
            .then(res => {
                console.log(res);
                setProizvodjaci(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    useEffect(() => {
        getVrste();
        getProizvodjaci()
        getRoba(0)
    }, [])


    //navigate na dodavanje
    const goToDodavanje = () => {
        navigate('/roba/dodavanje');
    }

    //ispis redova u tabeli
    const renderRoba = () => {
        return robas.map((roba) => {
            return <RobaRow key={roba.id} roba={roba}></RobaRow>
        })
    }

    //ispis za select
    const vrsteSelect = () => {
        return vrste.map(vrsta => {
            return (
                <option key={vrsta.id} value={vrsta.id}>{vrsta.naziv}</option>
            )
        })
    }

    const proizvodjaciSelect = () => {
        return proizvodjaci.map(proizvodjac => {
            return (
                <option key={proizvodjac.id} value={proizvodjac.id}>{proizvodjac.naziv}</option>
            )
        })
    }

    //onchange
    const onInputChange = (e) => {
        let name = e.target.name
        let value = e.target.value

        let parametriCopy = parametriPretrage
        parametriCopy[name] = value
        setParametriPretrage(parametriCopy)
        getRoba(0)
    }


    //forma za pretragu
    const renderPretraga = () => {
        return (
            <>

                <div style={{ display: 'flex' }}>
                    <Form.Check onChange={() => setHidden(!hidden)} ></Form.Check>
                    <Form.Label htmlFor='checkbox'> &nbsp; Prikazi pretragu</Form.Label>
                </div>

                {/* <Form  hidden={!hidden} > */}
                {/* ako hocu obrnuto sklonim ! */}

                <Form hidden={!hidden}>
                    <Row>
                        <Col>
                            <Form.Label>Naziv proizvoda</Form.Label>
                            <Form.Control type="text" name="naziv" onChange={(e) => onInputChange(e)}></Form.Control>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Label>Vrsta</Form.Label>
                            <Form.Select name="vrstaId" onChange={(e) => onInputChange(e)}>
                                <option value={''}></option>
                                {vrsteSelect()}
                            </Form.Select>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Label>Proizvodjac</Form.Label>
                            <Form.Select name="proizvodjacId" onChange={(e) => onInputChange(e)}>
                                <option value={''}></option>
                                {proizvodjaciSelect()}
                            </Form.Select>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Label>Pakovanje</Form.Label>
                            <Form.Control type="number" name="pakovanje" onChange={(e) => onInputChange(e)}></Form.Control>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Label>Tretman</Form.Label>
                            <Form.Control type="text" name="tretman" onChange={(e) => onInputChange(e)}></Form.Control>
                        </Col>
                    </Row>
                </Form>

            </>
        )
    }


    //krajnji ispis
    return (
        <Col>
            {/* <Row><h1>Vina</h1></Row> */}
            <Row>
                {renderPretraga()}
            </Row>

            <br /><br />
            <Row><Col>
                <Row>
                    <Col>
                        <Button variant='success' onClick={goToDodavanje}>Dodaj proizvod</Button>
                    </Col>
                    <Col style={{ display: 'flex', justifyContent: 'flex-end' }}>
                        <Button disabled={pageNo == 0} onClick={() => getRoba(pageNo - 1)}>Prethodna</Button>
                        <Button disabled={pageNo + 1 == totalPage || robas.length == 0} onClick={() => getRoba(pageNo + 1)}>Sledeca</Button>
                    </Col>
                </Row>

                <Table style={{ marginTop: 5 }}>
                    <thead>
                        <tr>
                            <th>Ident</th>
                            <th>Naziv</th>
                            <th>Vrsta</th>
                            <th>Tip</th>
                            <th>Proizvodjac</th>
                            <th>Pakovanje</th>
                            <th>Jedinica mere</th>
                            <th>Tretman</th>
                            <th>Ulazna cena</th>
                            <th>Prodajna cena</th>
                            <th>Ulaz</th>
                            <th>Izlaz</th>
                            <th>Stanje</th>
                            {/* {window.localStorage.getItem('role') == 'ROLE_ADMIN' ? <th>Broj preostalih flasa</th> : null} */}
                        </tr>
                    </thead>
                    <tbody>
                        {renderRoba()}
                    </tbody>
                </Table>
            </Col>
            </Row>
        </Col>
    )
}

export default Roba

// {window.localStorage.getItem('role') == 'ROLE_ADMIN' ?