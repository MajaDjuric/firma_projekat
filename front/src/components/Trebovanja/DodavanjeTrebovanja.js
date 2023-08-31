
import { Button, Col, Row, Form, Table } from "react-bootstrap"
import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"

const DodavanjeTrebovanja = () => {

    //navigate
    const navigate = useNavigate()

    const initTrebovanje = {
        kupacDto: { id: '' },
        komercijalistaId: window.localStorage.getItem('korisnikId')
    }

    const initTrebovanjeRobe = {
        robaId: '',
        trebovanjeId: '',
        kolicina: ''
    }

    const novoTrebovanjeInit = {
        komercijalistaIme: '',
        komercijalistaPrezime: '',
        kupacDto: {}
    }

    //init
    const [trebovanje, setTrebovanje] = useState(initTrebovanje)
    const [trebovanjeRobe, setTrebovanjeRobe] = useState(initTrebovanjeRobe)
    const [trebovanaRoba, setTrebovanaRoba] = useState([])
    const [roba, setRoba] = useState([])
    const [kupci, setKupci] = useState([])
    const [stanje, setStanje] = useState('')
    const [hidden, setHidden] = useState(false)
    const [hiddenFormaZaDodavanje, setHiddenFormaZaDodavanje] = useState(false)
    const [novoTrebovanje, setNovoTrebovanje] = useState(novoTrebovanjeInit)
    const [zavrsi, setZavrsi] = useState(false)

    const getTrebovanaRoba = useCallback((novoTrebovanjeId) => {
        Axios.get('/trebovanjaRobe/' + novoTrebovanjeId + '/trebovanja')
            .then(res => {
                console.log(res);
                setTrebovanaRoba(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    const getTrebovaniProizvod = useCallback((id) => {
        Axios.get('/roba/' + id)
            .then(res => {
                console.log(res);
                setStanje(res.data.stanje)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    const getNovoTrebovanje = useCallback((novoTrebovanjeId) => {
        Axios.get('/trebovanja/' + novoTrebovanjeId)
            .then(res => {
                console.log(res);
                setZavrsi(true)
                setNovoTrebovanje(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    //dodavanje  novog trebovanja
    const dodaj = () => {

        const dto = {
            kupacDto: { id: trebovanje.kupacId },
            komercijalistaId: trebovanje.komercijalistaId
        }

        Axios.post('/trebovanja', dto)
            .then(res => {
                console.log(res)
                setTrebovanjeRobe({ ...trebovanjeRobe, trebovanjeId: res.data.id })
                getNovoTrebovanje(res.data.id)
                setHidden(!hidden)
                setHiddenFormaZaDodavanje(!hiddenFormaZaDodavanje)
            })
            .catch(error => {
                console.log(error)
                alert('Doslo je do greske, pokusajte ponovo!')
            })
    }

    const dodajTrebovanejRobe = () => {

        const dto = {
            trebovanjeId: novoTrebovanje.id,
            robaId: trebovanjeRobe.robaId,
            kolicina: trebovanjeRobe.kolicina
        }

        Axios.post('/trebovanjaRobe', dto)
            .then(res => {
                console.log(res)
                getTrebovanaRoba(dto.trebovanjeId)
                setTrebovanjeRobe(initTrebovanjeRobe)
                setStanje('')
            })
            .catch(error => {
                console.log(error)
                alert('Doslo je do greske, pokusajte ponovo!')
            })
    }

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


    const getKupci = useCallback(() => {
        Axios.get('/kupci/' + window.localStorage.getItem('korisnikId') + '/komercijala')
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
        getRoba()
        getKupci()
    }, [])


    const robaSelect = () => {
        return roba.map(proizvod => {
            return (
                proizvod.stanje === 0 ? (
                    <option style={{ color: "red" }} key={proizvod.id} value={proizvod.id}>{proizvod.naziv}  {proizvod.pakovanje} {proizvod.jedinicaMere}</option>
                ) : (
                    <option key={proizvod.id} value={proizvod.id}>{proizvod.naziv}  {proizvod.pakovanje} {proizvod.jedinicaMere} {proizvod.tretman}</option>
                )
            );
        });
    };
    const kupciSelect = () => {
        return kupci.map(kupac => {
            return (
                <option key={kupac.id} value={kupac.id}>{kupac.naziv}</option>
            )
        })
    }

    const trebovanaRobaIspis = () => {
        return trebovanaRoba.map((roba, index) => {
            return (
                <tr key={roba.id}>
                    <td>{index + 1}</td>
                    <td>{roba.robaNaziv}</td>
                    <td>{roba.robaPakovanje}</td>
                    <td>{roba.robaJedinicaMere}</td>
                    <td>{roba.kolicina}</td>
                </tr>
            )
        })
    }

    //onChange
    const inputValueChange = (e) => {
        let input = e.target
        let name = input.name
        let value = input.value
        let trebovanjeCopy = trebovanje
        trebovanjeCopy[name] = value
        setTrebovanje(trebovanjeCopy)
        console.log(trebovanje)
    }

    const trebovanjeRobeInputValueChange = (e) => {
        let input = e.target
        let name = input.name
        let value = input.value
        let trebovanjeRobeCopy = trebovanjeRobe
        trebovanjeRobeCopy[name] = value
        setTrebovanjeRobe(trebovanjeRobeCopy)
        getTrebovaniProizvod(e.target.value)
    }

    const kolicinaTrebovaneRobeInputValueChange = (e) => {
        let input = e.target
        let name = input.name
        let value = input.value
        setTrebovanjeRobe(prevState => ({
            ...prevState,
            [name]: value
        }))
    }

    const formaZaDodavanje = () => {
        return (
            <>
                <div hidden={!hiddenFormaZaDodavanje}>
                    <h3>{novoTrebovanje.kupacDto.naziv}</h3>
                    <p>
                        {novoTrebovanje.kupacDto.teren} <br />
                        {novoTrebovanje.kupacDto.grad} <br />
                        {novoTrebovanje.kupacDto.adresa} <br />
                    </p>
                </div>
                <Form hidden={hiddenFormaZaDodavanje}>
                    <Button variant="warning" onClick={() => navigate('/kupci/dodavanje')}>Dodaj novog kupca</Button>
                    <br /><br />
                    <Row style={{ display: 'flex', alignItems: 'flex-end' }}>
                        <Col style={{ marginRight: '-60px' }}>
                            <Form.Label htmlFor="kupacId">Kupac</Form.Label>
                            <Form.Select style={{ width: '400px' }} name="kupacId" onChange={(e) => inputValueChange(e)}>
                                <option value={""}></option>
                                {kupciSelect()}
                            </Form.Select>
                        </Col>
                        <Col>
                            <br /> <Button onClick={dodaj} > Kreiraj trebovanje </Button>
                        </Col>
                    </Row>
                </Form>
                <br />


                <Form hidden={!hidden}>
                    <Row style={{ display: 'flex', alignItems: 'flex-end' }}>
                        <Col>
                            <Form.Label htmlFor="robaId">Proizvod</Form.Label>
                            <Form.Select style={{ width: '400px' }} value={trebovanjeRobe.robaId} name="robaId" onChange={(e) => trebovanjeRobeInputValueChange(e)}>
                                <option value={""}></option>
                                {robaSelect()}
                            </Form.Select>
                        </Col>
                        <Col style={{ marginRight: '-150px' }}>
                            <Form.Label htmlFor="kolicina">Kolicina</Form.Label>
                            <Form.Control style={{ width: '100px' }} value={trebovanjeRobe.kolicina} name="kolicina" id="kolicina" placeholder={stanje} type="number" onChange={(e) => kolicinaTrebovaneRobeInputValueChange(e)} />
                        </Col>
                        <Col>
                            <Button disabled={trebovanjeRobe.kolicina == 0} onClick={dodajTrebovanejRobe}>  Dodaj </Button>
                        </Col>
                    </Row>

                </Form>
                <br />
            </>
        )
    }

    return (
        <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">
                <h1>Novo trebovanje</h1>
                <br />
                {formaZaDodavanje()}
                <br />
                <Table hidden={!hidden} style={{ marginTop: 5 }}>
                    <thead>
                        <tr>
                            <th>Redni broj</th>
                            <th>Naziv</th>
                            <th>Pakovanje</th>
                            <th>jedinica mere</th>
                            <th>Kolicina</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {trebovanaRobaIspis()}
                    </tbody>
                </Table>
                <br />{zavrsi ? <Button variant="success" onClick={() => navigate('/trebovanja')} disabled={trebovanaRoba.length == 0}> Završi</Button> : null}

            </Col>
            <Col></Col>
        </Row >
    )
}

export default DodavanjeTrebovanja