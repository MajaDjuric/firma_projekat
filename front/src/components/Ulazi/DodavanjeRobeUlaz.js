
import { Button, Col, Row, Form } from "react-bootstrap"
import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import DodavanjeUlaza from "./DodavanjeUlaza"

const DodavanjeRobeUlaz = (props) => {

    //navigate
    const navigate = useNavigate()

    const init = {
        id: '',
        kolicina: '',
    }

    const initProizvod = {
        naziv: '',
        pakovanje: '',
        jedinicaMere: ''
    }

    let idUlaza = props.idUlaza;

    //init

    const [roba, setRoba] = useState([])
    const [novo, setNovo] = useState(init)
    const [noviUlazi, setNoviUlazi] = useState([])
    const [proizvod, setProizvod] = useState(initProizvod)
    const [validno, setValidno] = useState(false)

    //dodavanje 
    const dodaj = () => {

        Axios.put('/ulazi/dodavanje/' + idUlaza, noviUlazi)
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

    const getProizvod = useCallback((id) => {
        Axios.get('/roba/' + id)
            .then(res => {
                console.log(res);
                setProizvod(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    useEffect(() => {
        getRoba()
    }, [])


    const robaSelect = () => {
        return roba.map(proizvod => {
            return (
                <option key={proizvod.id} value={proizvod.id}>{proizvod.naziv}  {proizvod.pakovanje} {proizvod.jedinicaMere}</option>
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
        let novoCopy = novo
        novoCopy[name] = value
        setNovo(novoCopy)
        // validiraj()
    }

    const IdinputValueChange = (e) => {
        let id = e.target.value
        getProizvod(id)
    }

    const dodavanjeUListu = () => {
        setNoviUlazi([...noviUlazi, { id: proizvod.id, kolicina: novo.kolicina, naziv: proizvod.naziv, pakovanje: proizvod.pakovanje, jedinicaMere: proizvod.jedinicaMere }])
        console.log(noviUlazi)
        formaZaDodavanje()
    }

    const ispisListe = () => {
        return noviUlazi.map((ulaz, index) => {
            return (
                <tr>
                    <td>{index + 1}</td>
                    <td>{ulaz.naziv}</td>
                    <td>{ulaz.pakovanje}</td>
                    <td>{ulaz.jedinicaMere}</td>
                    <td>{ulaz.kolicina}</td>
                </tr>
            )
        })
    }

    const formaZaDodavanje = () => {
        return (
            <>
                <Form>
                    <Row>
                        <Col>
                            <Form.Label htmlFor="id">Roba</Form.Label>
                            <Form.Select name="id" onChange={(e) => IdinputValueChange(e)}>
                                <option value={""}></option>
                                {robaSelect()}
                            </Form.Select>
                        </Col>
                        <Col>
                            <Form.Label htmlFor="kolicina" >Kolicina</Form.Label>
                            <Form.Control name="kolicina" id="kolicina" type="number" onChange={(e) => inputValueChange(e)} />
                        </Col>
                        <Col>
                            <br /> <Button onClick={dodavanjeUListu}> Dodaj </Button>
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
                <h1>Dodavanje</h1>
                {/* <DodavanjeUlaza></DodavanjeUlaza> */}
                <br />
                {formaZaDodavanje()}
                <br />
                <tr>
                    <td>Redni broj</td>
                    <td>Naziv</td>
                    <td>Pakovanje</td>
                    <td>jedinica mere</td>
                    <td>Kolicina</td>
                </tr>
                {ispisListe()}
                <br /> <br />  <Button onClick={dodaj}> Kreiraj </Button>
            </Col>
            <Col></Col>
        </Row>
    )
}

export default DodavanjeRobeUlaz