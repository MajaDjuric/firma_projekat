
import { Button, Col, Row, Form } from "react-bootstrap"
import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import DodavanjeRobeUlaz from "./DodavanjeRobeUlaz"

const DodavanjeUlaza = () => {

    //navigate
    const navigate = useNavigate()

    const init = {
        brojFakture: '',
        brojOtpremnice: '',
        datumUlaza: '',
        proizvodjacId: ''
    }

    //init

    const [ulaz, setUlaz] = useState([init])
    const [id, setId] = useState(0)
    const [proizvodjaci, setProizvodjaci] = useState([])
    const [validno, setValidno] = useState(false)

    //dodavanje 
    const dodaj = () => {

        const dto = {
            brojFakture: ulaz.brojFakture,
            brojOtpremnice: ulaz.brojOtpremnice,
            datumUlaza: ulaz.datumUlaza,
            proizvodjacId: ulaz.proizvodjacId
        }

        Axios.post('/ulazi', dto)
            .then(res => {
                console.log(res)
                setId(res.data.id)
                alert('Uspesno dodavanje!')
            })
            .catch(error => {
                console.log(error)
                alert('Doslo je do greske, pokusajte ponovo!')
            })
    }

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
    }, [])


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
        let ulazCopy = ulaz
        ulazCopy[name] = value
        setUlaz(ulazCopy)
        // validiraj()
    }


    // const ispisListe = () => {
    //     return noviUlazi.map((ulaz, index) => {
    //         return (
    //             <tr>
    //                 <td>{index + 1}</td>
    //                 <td>{ulaz.naziv}</td>
    //                 <td>{ulaz.pakovanje}</td>
    //                 <td>{ulaz.jedinicaMere}</td>
    //                 <td>{ulaz.kolicina}</td>
    //             </tr>
    //         )
    //     })
    // }

    return (
        <>
            <Form>
                <Row>
                    <Col>
                        <Form.Label htmlFor="datumUlaza">Datum ulaza</Form.Label>
                        <Form.Control name="datumUlaza" id="datumUlaza" type="date" onChange={(e) => inputValueChange(e)} />
                        <Form.Label htmlFor="brojOtpremnice">Broj otpremnice </Form.Label>
                        <Form.Control name="brojOtpremnice" id="brojOtpremnice" type="text" onChange={(e) => inputValueChange(e)} />
                        <Form.Label htmlFor="brojFakture">Broj fakture</Form.Label>
                        <Form.Control name="brojFakture" id="brojFakture" type="text" onChange={(e) => inputValueChange(e)} />
                        <Form.Label htmlFor="proizvodjacId">Dobavljac</Form.Label>
                        <Form.Select name="proizvodjacId" onChange={(e) => inputValueChange(e)}>
                            <option value={""}></option>
                            {proizvodjaciSelect()}
                        </Form.Select>
                        <br /> <Button onClick={dodaj}> Dodaj ulaz</Button>
                    </Col>
                </Row>

            </Form>
            <br />
            <DodavanjeRobeUlaz idUlaza={id}></DodavanjeRobeUlaz>
        </>
    )
}


export default DodavanjeUlaza