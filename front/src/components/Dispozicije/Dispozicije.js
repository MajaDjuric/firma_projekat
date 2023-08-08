import Axios from '../../apis/Axios';
import { useCallback, useEffect, useState, React } from "react"
import { Button, Col, Form, Row, Table, FormCheck } from "react-bootstrap"
import { useNavigate } from 'react-router-dom';

const Dispozicije = () => {

    //navigate
    const navigate = useNavigate()

    const dispozicijaInit = {
        datumIsporuke: '',
        vozacId: '',
        vozilo: { id: '' }
    }

    //state
    const [dispozicije, setDispozicije] = useState([])
    const [dispozicija, setDispozicija] = useState(dispozicijaInit)
    const [pageNo, setPageNo] = useState(0)
    const [totalPage, setTotalPage] = useState(0)
    const [hidden, setHidden] = useState(false)
    const [vozaci, setVozaci] = useState([])
    const [vozila, setVozila] = useState([])
    const [parametriPretrage, setParametriPretrage] = useState({
        datumIsporuke: null
    })

    //nova dispozicija 
    const dodajDispoziciju = () => {

        const dto = {
            datumIsporuke: dispozicija.datumIsporuke,
            vozacId: dispozicija.vozacId,
            vozacImeIPrezime: dispozicija.vozacImeIPrezime,
            vozilo: dispozicija.vozilo
        }

        Axios.post('/dispozicije', dto)
            .then(res => {
                console.log(res)
                alert('Uspesno dodavanje dispozicije!')
                window.location.reload()
            })
            .catch(error => {
                console.log(error)
                alert('Doslo je do greske, pokusajte ponovo!')
            })
    }

    //dobavljanje svih
    const getDispozicije = useCallback((nextPage) => {

        const config = {
            params: {
                pageNo: nextPage,
            }
        }
        if (parametriPretrage.datumIsporuke != "" && parametriPretrage.datumIsporuke != null) {
            config.params.datumIsporuke = parametriPretrage.datumIsporuke
        }

        Axios.get('/dispozicije', config)
            .then(res => {
                console.log(res);
                setDispozicije(res.data)
                setPageNo(nextPage)
                setTotalPage(res.headers["total-pages"])
                console.log(res.headers)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    const getVozaci = useCallback(() => {
        Axios.get('/korisnici/vozaci')
            .then(res => {
                console.log(res);
                setVozaci(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    const getVozila = useCallback(() => {
        Axios.get('/vozila')
            .then(res => {
                console.log(res);
                setVozila(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);


    useEffect(() => {
        getDispozicije(0)
        getVozaci()
        getVozila()
    }, [])

    const formatirajDatum = (datumParam) => {
        let datum = new Date(datumParam)
        let dan = datum.getDate()
        let mesec = datum.getMonth() + 1
        let godina = datum.getFullYear()
        return (dan <= 9 ? '0' + dan : dan) + '.' + (mesec <= 9 ? '0' + mesec : mesec) + '.' + godina + '.'
    }

    //SORTIRANJE PO DATUMU
    const sortedDispozicije = dispozicije.slice().sort((a, b) => new Date(b.datumIsporuke) - new Date(a.datumIsporuke));

    //ispis redova u tabeli
    const renderDispozicije = () => {
        return sortedDispozicije.map((dispozicija, index) => {

            return (
                <tr key={dispozicija.id}>
                    <td>{index + 1}</td>
                    <td>{formatirajDatum(dispozicija.datumIsporuke)}</td>
                    <td>{dispozicija.vozacImeIPrezime}</td>
                    <td>{dispozicija.vozilo.markaITip}</td>
                    <td>{dispozicija.vozilo.registracija}</td>
                    {window.localStorage.getItem('role') == 'ROLE_LOGISTIKA' ?
                        (<>
                            <td><FormCheck style={{ display: "flex", justifyContent: "center" }} defaultChecked={dispozicija.isporuceno}></FormCheck></td>
                            <td><Button variant='success' onClick={() => navigate('/dispozicija/' + dispozicija.id)}>Detaljnije</Button></td>
                            <td><Button variant='primary' disabled={dispozicija.isporuceno} onClick={() => navigate('/trebovanja/' + dispozicija.id)}>Dodaj trebovanja</Button></td>
                        </>) :
                        (<>
                            <td><FormCheck style={{ display: "flex", justifyContent: "center" }} defaultChecked={dispozicija.isporuceno}></FormCheck></td>
                            <td><Button variant='success' onClick={() => navigate('/dispozicija/' + dispozicija.id)}>Detaljnije</Button></td>
                        </>)
                    }
                </tr>
            )
        }
        )
    }

    //onchange
    const onInputChange = (e) => {
        let name = e.target.name
        let value = e.target.value

        let parametriCopy = parametriPretrage
        parametriCopy[name] = value
        setParametriPretrage(parametriCopy)
        console.log(e.target.value)
        getDispozicije(0)
    }

    const vozaciSelect = () => {
        return vozaci.map(vozac => {
            return (
                <option key={vozac.id} value={vozac.id}>{vozac.ime}  {vozac.prezime}</option>
            )
        })
    }

    const vozilaSelect = () => {
        return vozila.map(vozilo => {
            return (
                <option key={vozilo.id} value={vozilo.id}>{vozilo.markaITip}  {vozilo.registracija}</option>
            )
        })
    }


    const datumIsporukeInputChange = (e) => {
        setDispozicija({ ...dispozicija, datumIsporuke: e.target.value })
    }

    const voziloInputChange = (e) => {
        setDispozicija({ ...dispozicija, vozilo: { id: e.target.value } })
    }


    const vozacIdInputChange = (e) => {
        setDispozicija({ ...dispozicija, vozacId: e.target.value })
    }

    const dodavanjeDispozicije = () => {
        return (
            <>
                <Form>
                    <Row>
                        <Col xs="12" sm="10" md="8">
                            <Form.Label>Datum isporuke</Form.Label>
                            <Form.Control name="datumIsporuke" type='date' onChange={(e) => datumIsporukeInputChange(e)} />
                        </Col>
                    </Row>
                    <Row>
                        <Col xs="12" sm="10" md="8">
                            <Form.Label>Vozilo</Form.Label>
                            <Form.Select name="voziloId" onChange={(e) => voziloInputChange(e)}>
                                <option value={''}></option>
                                {vozilaSelect()}
                            </Form.Select>
                        </Col>
                    </Row>
                    <Row>
                        <Col xs="12" sm="10" md="8">
                            <Form.Label>Vozac</Form.Label>
                            <Form.Select name="vozacId" onChange={(e) => vozacIdInputChange(e)}>
                                <option value={''}></option>
                                {vozaciSelect()}
                            </Form.Select>
                        </Col>
                    </Row>
                    <br />
                    <Col xs="12" sm="10" md="8">
                        <Button onClick={dodajDispoziciju}>Kreiraj dispoziciju</Button>
                    </Col>
                </Form>
            </>
        )
    }

    //forma za pretragu
    const renderPretraga = () => {
        return (
            <>
                <Form>
                    <Row>
                        <Col>
                            <Form.Label>Datum isporuke</Form.Label>
                            <Form.Control name="datumIsporuke" type='date' onChange={(e) => onInputChange(e)} />
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
            {window.localStorage.getItem('role') == 'ROLE_LOGISTIKA' ? <Row> {dodavanjeDispozicije()} </Row> : null}
            <br /><br />
            <Row><Col>
                <Row>
                    <Col style={{ display: 'flex', justifyContent: 'flex-end' }}>
                        <Button disabled={pageNo == 0} onClick={() => getDispozicije(pageNo - 1)}>Prethodna</Button>
                        <Button disabled={pageNo + 1 == totalPage || dispozicije.length == 0} onClick={() => getDispozicije(pageNo + 1)}>Sledeca</Button>
                    </Col>
                </Row>

                <Table style={{ marginTop: 5 }}>
                    <thead>
                        <tr>
                            <th>Redni broj</th>
                            <th>Datum isporuke</th>
                            <th>Vozac</th>
                            <th>Vozilo</th>
                            <th>Registracija</th>
                            <th>Isporuceno</th>
                            {/* {window.localStorage.getItem('role') == 'ROLE_ADMIN' ? <th>Broj preostalih flasa</th> : null} */}
                        </tr>
                    </thead>
                    <tbody>
                        {renderDispozicije()}
                    </tbody>
                </Table>
            </Col>
            </Row>
        </Col>
    )
}

export default Dispozicije

// {window.localStorage.getItem('role') == 'ROLE_ADMIN' ?