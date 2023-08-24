
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
        // robaPakovanje: '',
        // robaNaziv: '',
        // robaJedinicaMere: '',
        kolicina: '',
        cenaPoJediniciMere: '',
        rabat: '',
        pdv: '',
        krajnjaCena: ''
    }

    let idUlaza = props.idUlaza;

    const [roba, setRoba] = useState([])
    const [noviUlazRobe, setNoviUlazRobe] = useState(init)
    const [noviUlazi, setNoviUlazi] = useState([])

    //dodavanje 
    const dodaj = () => {

        const dto = {
            ulazId: idUlaza,
            robaId: noviUlazRobe.robaId,
            // robaPakovanje: noviUlazRobe.robaPakovanje,
            // robaNaziv: noviUlazRobe.robaNaziv,
            // robaJedinicaMere: noviUlazRobe.robaJedinicaMere,
            kolicina: noviUlazRobe.kolicina,
            cenaPoJediniciMere: noviUlazRobe.cenaPoJediniciMere,
            rabat: noviUlazRobe.rabat,
            pdv: noviUlazRobe.pdv,
            krajnjaCena: noviUlazRobe.krajnjaCena,
        }

        Axios.post('/ulaziRobe/' + idUlaza, dto)
            .then(res => {
                console.log(res)
                alert('Uspesno dodavanje!')
                navigate('/ulazi')
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

    // const getProizvod = useCallback((id) => {
    //     Axios.get('/roba/' + id)
    //         .then(res => {
    //             console.log(res);
    //             setProizvod(res.data)
    //         })
    //         .catch(error => {
    //             console.log(error);
    //             alert('Doslo je do greske!')
    //         });
    // }, []);

    useEffect(() => {
        getRoba()
    }, [])


    //onChange
    const inputValueChange = (e) => {
        let input = e.target
        let name = input.name
        let value = input.value
        let novoCopy = noviUlazRobe
        novoCopy[name] = value
        setNoviUlazRobe(novoCopy)
        // validiraj()
    }

    const IdinputValueChange = (e) => {
        let id = e.target.value
        // getProizvod(id)
    }

    const robaSelect = () => {
        return roba.map(proizvod => {
            return (
                <option key={proizvod.id} value={proizvod.id}>{proizvod.naziv}  {proizvod.pakovanje} {proizvod.jedinicaMere} {proizvod.tretman}</option>
            )
        })
    }

    // const dodavanjeUListu = () => {
    //     setNoviUlazi([...noviUlazi, { id: proizvod.id, naziv: proizvod.naziv, pakovanje: proizvod.pakovanje, jedinicaMere: proizvod.jedinicaMere }])
    //     console.log(noviUlazi)
    //     formaZaDodavanje()
    // }

    const ispisListe = () => {
        return noviUlazi.map((ulaz, index) => {
            return (
                <tr>
                    <td>{index + 1}</td>
                    <td>{ulaz.naziv}</td>
                    <td>{ulaz.pakovanje}</td>
                    <td>{ulaz.jedinicaMere}</td>
                    <td>{ulaz.kolicina}</td>
                    <td>{ulaz.cenaPoJediniciMere}</td>
                    <td>{ulaz.pdv}</td>
                    <td>{ulaz.rabat}</td>
                    <td>{ulaz.ukupnaCena}</td>
                </tr>
            )
        })
    }

    //forma za dodavanje tacne kolicine i cene robe 
    const formaZaDodavanje = () => {
        return (
            <>
                <Form>
                    <Row>
                        <Col>
                            <Form.Label htmlFor="robaId">Roba</Form.Label>
                            <Form.Select style={{ width: "100px" }} name="robaId" onChange={(e) => inputValueChange(e)}>
                                <option value={""}></option>
                                {robaSelect()}
                            </Form.Select>
                        </Col>
                        <Col>
                            <Form.Label htmlFor="kolicina" >Kolicina</Form.Label>
                            <Form.Control name="kolicina" id="kolicina" type="number" onChange={(e) => inputValueChange(e)} />
                        </Col>
                        <Col>
                            <Form.Label htmlFor="cenaPoJediniciMere" >Cena/j.m</Form.Label>
                            <Form.Control name="cenaPoJediniciMere" id="cenaPoJediniciMere" type="number" onChange={(e) => inputValueChange(e)} />
                        </Col>
                        <Col>
                            <Form.Label htmlFor="rabat" >Rabat</Form.Label>
                            <Form.Control name="rabat" id="rabat" type="number" onChange={(e) => inputValueChange(e)} />
                        </Col>
                        <Col>
                            <Form.Label htmlFor="pdv" >PDV</Form.Label>
                            <Form.Control name="pdv" id="pdv" type="number" onChange={(e) => inputValueChange(e)} />
                        </Col>
                        <Col>
                            <Form.Label htmlFor="krajnjaCena" >Ukupno</Form.Label>
                            <Form.Control name="krajnjaCena" id="ukupnaCena" type="number" onChange={(e) => inputValueChange(e)} />
                        </Col>
                        <Col>
                            {/* <br /> <Button onClick={dodavanjeUListu}> Dodaj </Button> */}
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
                <h4>Dodavanje</h4>
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
                            <th>Cena po jedinici mere</th>
                            <th>PDV</th>
                            <th>Rabat</th>
                            <th>Ukupna cena</th>
                        </tr>
                    </thead>

                    {ispisListe()}
                </Table>
                <br /> <br />  <Button onClick={dodaj}> Kreiraj </Button>
            </Col>
            <Col></Col>
        </Row>
    )
}

export default DodavanjeRobeUlaz