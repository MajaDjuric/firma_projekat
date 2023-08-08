
import { Button, Col, Row, Form, Table } from "react-bootstrap"
import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import KupacDodavanje from "../Kupci/KupacDodavanje"

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
    const [listaRobe, setListaRobe] = useState([])
    const [roba, setRoba] = useState([])
    const [kupci, setKupci] = useState([])
    const [stanje, setStanje] = useState(0)
    const [komercijalisti, setKomercijalisti] = useState([])
    const [hidden, setHidden] = useState(false)
    const [hiddenTabela, setHiddenTabela] = useState(false)
    const [hiddenFormaZaDodavanje, setHiddenFormaZaDodavanje] = useState(false)
    const [novoTrebovanje, setNovoTrebovanje] = useState(novoTrebovanjeInit)
    const [zavrsi, setZavrsi] = useState(false)

    //brisanje 
    const deleteTrebovanje = (id) => {
        Axios.delete('/trebovanjaRobe/' + id)
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
                // alert('Uspesno dodavanje!')
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
            trebovanjeId: trebovanjeRobe.trebovanjeId,
            robaId: trebovanjeRobe.robaId,
            kolicina: trebovanjeRobe.kolicina
        }

        Axios.post('/trebovanjaRobe', dto)
            .then(res => {
                console.log(res)
                getTrebovanaRoba(dto.trebovanjeId)
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


    const getKupci = useCallback((komercijalistaId) => {
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

    const getKomercijala = useCallback(() => {
        Axios.get('/korisnici/komercijala')
            .then(res => {
                console.log(res);
                setKomercijalisti(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    useEffect(() => {
        getRoba()
        getKupci()
        getKomercijala()
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
                    <td><Button variant="danger" onClick={() => deleteTrebovanje(roba.id)}>Obrisi</Button></td>
                </tr>
            )
        })
    }


    //validacija
    // const validiraj = () => {
    //     if (vino.ime == '' || vino.opis == '') {
    //         setValidno(false)
    //     } else {
    //         setValidno(true)
    //     }
    // }

    //onChange
    const inputValueChange = (e) => {
        let input = e.target
        let name = input.name
        let value = input.value
        let trebovanjeCopy = trebovanje
        trebovanjeCopy[name] = value
        setTrebovanje(trebovanjeCopy)
        console.log(trebovanje)
        // validiraj()
    }

    const trebovanjeRobeInputValueChange = (e) => {
        let input = e.target
        let name = input.name
        let value = input.value
        let trebovanjeRobeCopy = trebovanjeRobe
        trebovanjeRobeCopy[name] = value
        setTrebovanjeRobe(trebovanjeRobeCopy)
        getTrebovaniProizvod(e.target.value)
        // validiraj()
    }

    const kolicinaTrebovaneRobeInputValueChange = (e) => {
        let input = e.target
        let name = input.name
        let value = input.value
        let trebovanjeRobeCopy = trebovanjeRobe
        trebovanjeRobeCopy[name] = value
        setTrebovanjeRobe(trebovanjeRobeCopy)
        // validiraj()
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
                    <Button variant="warning" onClick={() => navigate('/kupci/dodavanje')}>Dodaj kupca</Button>
                    <br /><br />
                    <Row>
                        <Col>
                            <Form.Label htmlFor="kupacId">Kupac</Form.Label>
                            <Form.Select name="kupacId" onChange={(e) => inputValueChange(e)}>
                                <option value={""}></option>
                                {kupciSelect()}
                            </Form.Select>
                            <br /> <Button onClick={dodaj} > Kreiraj trebovanje </Button>
                        </Col>
                    </Row>
                </Form>
                <br />
                <Form hidden={!hidden}>
                    <Row>
                        <Col>
                            <Form.Label htmlFor="robaId">Proizvod</Form.Label>
                            <Form.Select name="robaId" onChange={(e) => trebovanjeRobeInputValueChange(e)}>
                                <option value={""}></option>
                                {robaSelect()}
                            </Form.Select>
                            <Form.Label htmlFor="kolicina">Kolicina</Form.Label>
                            <Form.Control name="kolicina" id="kolicina" placeholder={stanje} type="number" onChange={(e) => kolicinaTrebovaneRobeInputValueChange(e)} />

                            <br /> <Button onClick={dodajTrebovanejRobe}>  Dodaj </Button>
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
                <br />{zavrsi ? <Button variant="success" onClick={() => navigate('/trebovanja')} disabled={trebovanaRoba.length == 0}> Zavrsi</Button> : null}

            </Col>
            <Col></Col>
        </Row >
    )
}

export default DodavanjeTrebovanja