import { Button, Col, Row, Form, Table } from "react-bootstrap"
import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import DodavanjeUlaza from "./DodavanjeUlaza"

const DodavanjeRobeUlaz = (props) => {

    //navigate
    const navigate = useNavigate()

    const init = {
        ulazId: '',
        robaId: '',
        kolicina: '',
        cenaPoJediniciMere: '',
        rabat: '',
        pdv: '',
    }


    let idUlaza = props.idUlaza;

    const [roba, setRoba] = useState([])
    const [noviUlazRobe, setNoviUlazRobe] = useState(init)
    const [listaUlazaRobe, setListaUlazaRobe] = useState([])
    const [proizvod, setProizvod] = useState('')

    //dodavanje 
    const dodaj = () => {

        const dto = {
            ulazId: idUlaza,
            robaId: noviUlazRobe.robaId,
            kolicina: noviUlazRobe.kolicina,
            cenaPoJediniciMere: noviUlazRobe.cenaPoJediniciMere,
            rabat: noviUlazRobe.rabat,
            pdv: noviUlazRobe.pdv,
            krajnjaCena: noviUlazRobe.krajnjaCena,
        }

        Axios.post('/ulaziRobe/' + idUlaza, dto)
            .then(res => {
                console.log(res)
                getUlaziRobe()
                console.log('Before reset:', noviUlazRobe);
                setNoviUlazRobe(init);
                console.log('After reset:', noviUlazRobe);
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

    //svi ulaziRobe odredjenog ulaza
    const getUlaziRobe = useCallback(() => {
        Axios.get('/ulaziRobe/' + idUlaza + '/ulazi')
            .then(res => {
                console.log(res);
                setListaUlazaRobe(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    //dobavljanje izabrane robe pri dodavanju ulazaRobe da bi se setovao pdv
    const getProizvod = useCallback((id) => {
        Axios.get('/roba/' + id)
            .then(res => {
                console.log(res);
                setProizvod(res.data.vrstaNaziv)
                if (res.data.vrstaNaziv == 'PESTICIDI') {
                    setNoviUlazRobe(prevNoviUlazRobe => ({ ...prevNoviUlazRobe, pdv: 15 }))
                } else {
                    setNoviUlazRobe(prevNoviUlazRobe => ({ ...prevNoviUlazRobe, pdv: 10 }))
                }
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    useEffect(() => {
        getRoba()
    }, [])


    //onChange
    const inputValueChange = (e) => {
        let input = e.target
        let name = input.name
        let value = input.value
        setNoviUlazRobe(prevState => ({
            ...prevState,
            [name]: value
        }))
        if (name == 'robaId') {
            getProizvod(value)
        }
    }

    const robaSelect = () => {
        return roba.map(proizvod => {
            return (
                <option key={proizvod.id} value={proizvod.id}>{proizvod.naziv}  {proizvod.pakovanje} {proizvod.jedinicaMere} {proizvod.tretman}</option>
            )
        })
    }

    const ispisListe = () => {
        return listaUlazaRobe.map((ulazRobe, index) => {
            return (
                <tr>
                    <td>{index + 1}</td>
                    <td>{ulazRobe.robaNaziv}</td>
                    <td>{ulazRobe.robaPakovanje}</td>
                    <td>{ulazRobe.robaJedinicaMere}</td>
                    <td>{ulazRobe.kolicina}</td>
                    <td>{ulazRobe.cenaPoJediniciMere}</td>
                    <td>{ulazRobe.pdv}</td>
                    <td>{ulazRobe.rabat}</td>
                    <td>{ulazRobe.krajnjaCenaPoJediniciMere}</td>
                    <td>{ulazRobe.krajnjaCena.toFixed(2)}</td>
                </tr>
            )
        })
    }

    //forma za dodavanje tacne kolicine i cene robe 
    const formaZaDodavanje = () => {
        return (
            <>
                <Form>
                    <Row style={{ display: 'flex', alignItems: 'flex-end' }}>
                        <Col>
                            <Form.Label htmlFor="robaId">Roba</Form.Label>
                            <Form.Select value={noviUlazRobe.robaId} style={{ width: "250px" }} name="robaId" onChange={(e) => inputValueChange(e)}>
                                <option value={""}></option>
                                {robaSelect()}
                            </Form.Select>
                        </Col>
                        <Col>
                            <Form.Label htmlFor="kolicina" >Kolicina</Form.Label>
                            <Form.Control value={noviUlazRobe.kolicina} name="kolicina" id="kolicina" type="number" onChange={(e) => inputValueChange(e)} />
                        </Col>
                        <Col>
                            <Form.Label htmlFor="cenaPoJediniciMere" >Cena/j.m</Form.Label>
                            <Form.Control value={noviUlazRobe.cenaPoJediniciMere} name="cenaPoJediniciMere" id="cenaPoJediniciMere" type="number" onChange={(e) => inputValueChange(e)} />
                        </Col>
                        <Col>
                            <Form.Label htmlFor="rabat" >Rabat</Form.Label>
                            <Form.Control value={noviUlazRobe.rabat} name="rabat" id="rabat" type="number" onChange={(e) => inputValueChange(e)} />
                        </Col>
                        <Col>
                            <Form.Label htmlFor="pdv" >PDV</Form.Label>
                            <Form.Control value={noviUlazRobe.pdv} name="pdv" id="pdv" type="number" onChange={(e) => inputValueChange(e)} />
                        </Col>
                        <Col>
                            <br /> <Button onClick={dodaj}> Dodaj </Button>
                        </Col>
                    </Row>
                </Form>
            </>
        )
    }

    //krajnji ispis
    return (
        <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">
                <br />
                {formaZaDodavanje()}
                <br />
                <br />
                <Table>
                    <thead>
                        <tr>
                            <th>Redni broj</th>
                            <th>Naziv</th>
                            <th>Pakovanje</th>
                            <th>Jedinica mere</th>
                            <th>Kolicina</th>
                            <th>Cena/j.m.</th>
                            <th>PDV</th>
                            <th>Rabat</th>
                            <th>Ukupna cena/j.m.</th>
                            <th>Ukupna cena</th>
                        </tr>
                    </thead>
                    <tbody>
                        {ispisListe()}
                    </tbody>
                </Table>
                <br /> <br />  <Button onClick={() => navigate('/ulazi')}> Završi </Button>
            </Col>
            <Col></Col>
        </Row>
    )
}

export default DodavanjeRobeUlaz