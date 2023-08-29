
import { Button, Col, Row, Form } from "react-bootstrap"
import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"

const Dodavanje = () => {

    //navigate
    const navigate = useNavigate()

    //init
    const init = {
        naziv: '',
        vrstaId: '',
        tipId: '',
        proizvodjacId: '',
        ulaznaCena: '',
        prodajnaCena: '',
        pakovanje: '',
        jedinicaMere: '',
        tretman: ''
    }

    const [roba, setRoba] = useState(init)
    const [vrste, setVrste] = useState([])
    const [proizvodjaci, setProizvodjaci] = useState([])
    const [tipovi, setTipovi] = useState([])
    const [validno, setValidno] = useState(false)

    //dodavanje 
    const dodaj = () => {

        const dto = {
            naziv: roba.naziv,
            vrstaId: roba.vrstaId,
            tipId: roba.tipId,
            proizvodjacId: roba.proizvodjacId,
            ulaznaCena: roba.ulaznaCena,
            prodajnaCena: roba.prodajnaCena,
            pakovanje: roba.pakovanje,
            jedinicaMere: roba.jedinicaMere,
            tretman: roba.tretman
        }

        Axios.post('/roba', dto)
            .then(res => {
                console.log(res)
                alert('Uspesno dodavanje!')
                navigate('/roba')
            })
            .catch(error => {
                console.log(error)
                alert('Doslo je do greske, pokusajte ponovo!')
            })
    }

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

    const getTipovi = useCallback((vrstaId) => {
        Axios.get('/tipoviRobe/' + vrstaId)
            .then(res => {
                console.log(res);
                setTipovi(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    useEffect(() => {
        getVrste()
        getProizvodjaci()
    }, [])


    //ispis za select
    const vrsteSelect = () => {
        return vrste.map(vrsta => {
            return (
                <option key={vrsta.id} value={vrsta.id}>{vrsta.naziv}</option>
            )
        })
    }

    const tipoviSelect = () => {
        return tipovi.map(tip => {
            return (
                <option key={tip.id} value={tip.id}>{tip.naziv}</option>
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
        let robaSt = roba
        robaSt[name] = value
        setRoba(robaSt)
        // validiraj()
    }

    const vrstaOnChange = (e) => {
        let vrstaId = e.target.value
        setRoba({ ...roba, vrstaId: vrstaId })
        getTipovi(vrstaId)
        // validiraj()
    }

    //krajnji ispis
    return (
        <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">
                <Form>
                    <Row>
                        <Col>
                            <Form.Label htmlFor="naziv" >Naziv proizvoda</Form.Label>
                            <Form.Control style={{ width: "400px" }} name="naziv" id="naziv" type="text" onChange={(e) => inputValueChange(e)} />
                        </Col>
                    </Row>
                    <Row>
                        <Col style={{ marginRight: '-40px' }}>
                            <Form.Label htmlFor="vrstaId">Vrsta robe</Form.Label>
                            <Form.Select style={{ width: "400px" }} name="vrstaId" onChange={(e) => vrstaOnChange(e)}>
                                <option value={""}></option>
                                {vrsteSelect()}
                            </Form.Select>
                        </Col>
                        <Col>
                            <Form.Label htmlFor="tipId">Tip robe</Form.Label>
                            <Form.Select style={{ width: "400px" }} disabled={roba.vrstaId == ''} name="tipId" onChange={(e) => inputValueChange(e)}>
                                <option value={""}></option>
                                {tipoviSelect()}
                            </Form.Select>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Label hidden={roba.vrstaId != 2} htmlFor="tretman">Tretman</Form.Label>
                            <Form.Control style={{ width: "400px" }} hidden={roba.vrstaId != 2} name="tretman" onChange={(e) => inputValueChange(e)}></Form.Control>
                        </Col>
                    </Row>
                    <Row>
                        <Col style={{ marginRight: '-40px' }}>
                            <Form.Label htmlFor="pakovanje">Pakovanje</Form.Label>
                            <Form.Control style={{ width: "400px" }} name="pakovanje" id="pakovanje" type="text" onChange={(e) => inputValueChange(e)} />
                        </Col>
                        <Col>
                            <Form.Label htmlFor="jedinicaMere">Jedinica mere</Form.Label>
                            <Form.Select style={{ width: "80px" }} name="jedinicaMere" onChange={(e) => inputValueChange(e)}>
                                <option value={""}></option>
                                <option value={"KG"}>KG</option>
                                <option value={"L"}>L</option>
                                <option value={"G"}>G</option>
                                <option value={"ML"}>ML</option>
                            </Form.Select>
                        </Col>
                    </Row>
                    <Form.Label htmlFor="proizvodjacId">Proizvodjac</Form.Label>
                    <Form.Select style={{ width: "400px" }} name="proizvodjacId" onChange={(e) => inputValueChange(e)}>
                        <option value={""}></option>
                        {proizvodjaciSelect()}
                    </Form.Select>
                    <Form.Label htmlFor="prodajnaCena">Prodajna cena</Form.Label>
                    <Form.Control style={{ width: "400px" }} name="prodajnaCena" id="prodajnaCena" type="text" onChange={(e) => inputValueChange(e)} />
                    <br /> <br />  <Button onClick={dodaj}> Kreiraj </Button>
                </Form>
            </Col>
            <Col></Col>
        </Row >
    )
}

export default Dodavanje