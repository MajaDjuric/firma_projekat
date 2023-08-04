import Axios from '../../apis/Axios';
import { useCallback, useEffect, useState } from "react"
import { Button, Col, Form, Row, Table, FormCheck } from "react-bootstrap"
import { useNavigate, useParams } from 'react-router-dom';

const Trebovanja = () => {

    const params = useParams()

    const dispozicijaId = params.id

    //navigate
    const navigate = useNavigate()

    //state
    const [trebovanja, setTrebovanja] = useState([])
    const [komercijala, setKomercijala] = useState([])

    const [kupci, setKupci] = useState([])
    const [pageNo, setPageNo] = useState(0)
    const [totalPage, setTotalPage] = useState(0)
    const [hidden, setHidden] = useState(false)
    const [parametriPretrage, setParametriPretrage] = useState({
        teren: '',
        komercijalistaId: '',
        kupacId: ''
    })

    //dobavljanje svih
    const getTrebovanja = useCallback((nextPage) => {

        const config = {
            params: {
                teren: parametriPretrage.teren,
                komercijalistaId: parametriPretrage.komercijalistaId,
                kupacId: parametriPretrage.kupacId
            }
        }

        Axios.get('/trebovanja', config)
            .then(res => {
                console.log(res);
                setTrebovanja(res.data)
                setPageNo(nextPage)
                setTotalPage(res.headers["total-pages"])
                console.log(res.headers)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);


    const getKomercijala = useCallback(() => {
        Axios.get('/korisnici/komercijala')
            .then(res => {
                console.log(res);
                setKomercijala(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);



    const getKupci = useCallback(() => {
        Axios.get('/kupci')
            .then(res => {
                console.log(res);
                setKupci(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);


    useEffect(() => {
        getKupci()
        getKomercijala()
        getTrebovanja(0)
    }, [])


    const formatirajDatum = (datumParam) => {
        let datum = new Date(datumParam)
        let dan = datum.getDate()
        let mesec = datum.getMonth() + 1
        let godina = datum.getFullYear()
        return (dan <= 9 ? '0' + dan : dan) + '.' + (mesec <= 9 ? '0' + mesec : mesec) + '.' + godina + '.'
    }

    //ispis redova u tabeli
    const renderTrebovanja = () => {
        return trebovanja.map((trebovanje) => {
            return (
                <tr key={trebovanje.id}>
                    {window.localStorage.getItem('role') == 'ROLE_LOGISTIKA' ? <td onClick={() => navigate('/trebovanje/izmena/' + trebovanje.id + '/' + dispozicijaId)}>{trebovanje.id}</td> : <td onClick={() => navigate('/trebovanje/izmena/' + trebovanje.id)}>{trebovanje.id}</td>}
                    {window.localStorage.getItem('role') == 'ROLE_LOGISTIKA' ? <td onClick={() => navigate('/trebovanje/izmena/' + trebovanje.id + '/' + dispozicijaId)}>{trebovanje.kupacDto.teren}</td> : <td onClick={() => navigate('/trebovanje/izmena/' + trebovanje.id)}>{trebovanje.kupacDto.teren}</td>}
                    {window.localStorage.getItem('role') == 'ROLE_LOGISTIKA' ? <td onClick={() => navigate('/trebovanje/izmena/' + trebovanje.id + '/' + dispozicijaId)}>{trebovanje.komercijalistaIme}</td> : <td onClick={() => navigate('/trebovanje/izmena/' + trebovanje.id)}>{trebovanje.komercijalistaIme} {trebovanje.komercijlistaPrezime}</td>}
                    {window.localStorage.getItem('role') == 'ROLE_LOGISTIKA' ? <td onClick={() => navigate('/trebovanje/izmena/' + trebovanje.id + '/' + dispozicijaId)}>{trebovanje.kupacDto.naziv}</td> : <td onClick={() => navigate('/trebovanje/izmena/' + trebovanje.id)}>{trebovanje.kupacDto.naziv}</td>}
                    {window.localStorage.getItem('role') == 'ROLE_LOGISTIKA' ? <td onClick={() => navigate('/trebovanje/izmena/' + trebovanje.id + '/' + dispozicijaId)}>{formatirajDatum(trebovanje.datumTrebovanja)}</td> : <td onClick={() => navigate('/trebovanje/izmena/' + trebovanje.id)}>{formatirajDatum(trebovanje.datumTrebovanja)}</td>}
                    <td><FormCheck defaultChecked={trebovanje.disponirano} ></FormCheck></td>
                    <td><FormCheck defaultChecked={trebovanje.isporuceno} ></FormCheck></td>
                </tr>
            )
        })
    }

    //ispis za select
    const komercijalaSelect = () => {
        return komercijala.map(komercijalista => {
            return (
                <option key={komercijalista.id} value={komercijalista.id}>{komercijalista.ime}  {komercijalista.prezime}</option>
            )
        })
    }

    const kupciSelect = () => {
        return kupci.map(kupac => {
            return (
                <option key={kupac.id} value={kupac.id}>{kupac.naziv}</option>
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
        getTrebovanja(0)
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
                            <Form.Label>Teren</Form.Label>
                            <Form.Select name="teren" onChange={(e) => onInputChange(e)}>
                                <option value={''}></option>
                                <option value={'Severna Backa'}>Severna Backa</option>
                                <option value={'Severni Banat'}>Severni Banat</option>
                            </Form.Select>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Label>Komercijalista</Form.Label>
                            <Form.Select name="komercijalistaId" onChange={(e) => onInputChange(e)}>
                                <option value={''}></option>
                                {komercijalaSelect()}
                            </Form.Select>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Label>Kupac</Form.Label>
                            <Form.Select name="kupacId" onChange={(e) => onInputChange(e)}>
                                <option value={''}></option>
                                {kupciSelect()}
                            </Form.Select>
                        </Col>
                    </Row>
                </Form>

            </>
        )
    }

    //krajnji ispis
    return (
        <Col>
            <Row>
                {renderPretraga()}
            </Row>

            <br /><br />
            <Row><Col>

                <Row>
                    <Col>
                        {window.localStorage.getItem('role') == 'ROLE_KOMERCIJALA' ? < Button variant='success' onClick={() => navigate('/trebovanje/dodavanje')}>Dodaj trebovanje</Button> : null}
                    </Col>
                    <Col style={{ display: 'flex', justifyContent: 'flex-end' }}>
                        <Button disabled={pageNo == 0} onClick={() => getTrebovanja(pageNo - 1)}>Prethodna</Button>
                        <Button disabled={pageNo + 1 == totalPage || trebovanja.length == 0} onClick={() => getTrebovanja(pageNo + 1)}>Sledeca</Button>
                    </Col>
                </Row>
                <br />
                <Table style={{ marginTop: 5 }}>
                    <thead>
                        <tr>
                            <th>Redni broj</th>
                            <th>Teren</th>
                            <th>Komercijalista</th>
                            <th>Kupac</th>
                            <th>Datum trebovanja</th>
                            <th>Disponirano</th>
                            <th>Isporuceno</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderTrebovanja()}
                    </tbody>
                </Table>
            </Col>
            </Row >
        </Col >
    )
}

export default Trebovanja

// {window.localStorage.getItem('role') == 'ROLE_ADMIN' ?