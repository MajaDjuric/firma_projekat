import Axios from '../../apis/Axios';
import { useCallback, useEffect, useState } from "react"
import { Button, Col, Form, Row, Table } from "react-bootstrap"
import { useNavigate } from 'react-router-dom';
import RobaRow from './UlazRow';
import UlazRow from './UlazRow';

const Ulazi = () => {

    //navigate
    const navigate = useNavigate()

    //state
    const [ulazi, setUlazi] = useState([])
    const [roba, setRoba] = useState([])
    const [proizvodjaci, setProizvodjaci] = useState([])
    const [pageNo, setPageNo] = useState(0)
    const [totalPage, setTotalPage] = useState(0)
    const [hidden, setHidden] = useState(false)
    const [parametriPretrage, setParametriPretrage] = useState({
        brojFakture: '',
        brojOtpremnice: '',
        minDatumUlaza: null,
        maxDatumUlaza: null,
        proizvodjacId: '',
        robaId: ''
    })

    //dobavljanje svih
    const getUlazi = useCallback((nextPage) => {

        const config = {
            params: {
                pageNo: nextPage,
                brojFakture: parametriPretrage.brojFakture,
                brojOtpremnice: parametriPretrage.brojOtpremnice,
                minDatumUlaza: parametriPretrage.minDatumUlaza,
                maxDatumUlaza: parametriPretrage.maxDatumUlaza,
                proizvodjacId: parametriPretrage.proizvodjacId,
                robaId: parametriPretrage.robaId
            }
        }

        Axios.get('/ulazi', config)
            .then(res => {
                console.log(res);
                setUlazi(res.data)
                setPageNo(nextPage)
                setTotalPage(res.headers["total-pages"])
                console.log(res.headers)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    const getRoba = useCallback(() => {
        Axios.get('/roba')
            .then(res => {
                console.log(res);
                setRoba(res.data)
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
        getProizvodjaci()
        getRoba()
        getUlazi(0)
    }, [])


    //navigate na dodavanje
    const goToDodavanje = () => {
        navigate('/ulaz/dodavanjeRobe');
    }

    //ispis redova u tabeli
    const renderUlazi = () => {
        return ulazi.map((ulaz) => {
            return <UlazRow key={ulaz.id} ulaz={ulaz} robaId={parametriPretrage.robaId}></UlazRow>
        })
    }

    //ispis za select
    const robaSelect = () => {
        return roba.map(proizvod => {
            return (
                <option key={proizvod.id} value={proizvod.id}>{proizvod.naziv}  {proizvod.pakovanje} {proizvod.jedinicaMere}</option>
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
        getUlazi(0)
        console.log(value)
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
                        <Col style={{ marginRight: '-670px' }}>
                            <Form.Label>Roba</Form.Label>
                            <Form.Select style={{ width: '280px' }} name="robaId" onChange={(e) => onInputChange(e)}>
                                <option value={''}></option>
                                {robaSelect()}
                            </Form.Select>
                        </Col>
                        <Col>
                            <Form.Label>Proizvodjac</Form.Label>
                            <Form.Select style={{ width: '280px' }} name="proizvodjacId" onChange={(e) => onInputChange(e)}>
                                <option value={''}></option>
                                {proizvodjaciSelect()}
                            </Form.Select>
                        </Col>
                    </Row>
                    <Row>
                        <Col style={{ marginRight: '-670px' }}>
                            <Form.Label>Broj fakture</Form.Label>
                            <Form.Control style={{ width: '280px' }} type="text" name="brojFakture" onChange={(e) => onInputChange(e)}></Form.Control>
                        </Col>
                        <Col>
                            <Form.Label>Broj otpremnice</Form.Label>
                            <Form.Control style={{ width: '280px' }} type="text" name="brojOtpremnice" onChange={(e) => onInputChange(e)}></Form.Control>
                        </Col>
                    </Row>
                    <Row>
                        <Col style={{ marginRight: '-670px' }}>
                            <Form.Label>Min datum ulaza</Form.Label>
                            <Form.Control style={{ width: '280px' }} type="date" name="minDatumUlaza" onChange={(e) => onInputChange(e)}></Form.Control>
                        </Col>
                        <Col>
                            <Form.Label>Max datum ulaza</Form.Label>
                            <Form.Control style={{ width: '280px' }} type="date" name="maxDatumUlaza" onChange={(e) => onInputChange(e)}></Form.Control>
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
                        {window.localStorage.role == 'ROLE_FINANSIJE' ? <Button variant='success' onClick={goToDodavanje}>Dodaj ulaz</Button> : null}
                    </Col>
                    <Col style={{ display: 'flex', justifyContent: 'flex-end' }}>
                        <Button disabled={pageNo == 0} onClick={() => getUlazi(pageNo - 1)}>Prethodna</Button>
                        <Button disabled={pageNo + 1 == totalPage || ulazi.length == 0} onClick={() => getUlazi(pageNo + 1)}>Sledeca</Button>
                    </Col>
                </Row>

                <Table style={{ marginTop: 5 }}>
                    <thead>
                        <tr>
                            <th>Ident</th>
                            <th>Datum ulaza</th>
                            <th>Broj fakture</th>
                            <th>Broj otpremnice</th>
                            <th>Dobavljac</th>
                            {parametriPretrage.robaId != '' ? <th>Količina</th> : null}
                        </tr>
                    </thead>
                    <tbody>
                        {renderUlazi()}
                    </tbody>
                </Table>
            </Col>
            </Row>
        </Col>
    )
}

export default Ulazi

// {window.localStorage.getItem('role') == 'ROLE_ADMIN' ?