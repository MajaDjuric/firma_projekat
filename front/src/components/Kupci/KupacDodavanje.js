
import { Button, Col, Row, Form } from "react-bootstrap"
import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"


const KupacDodavanje = () => {

    //navigate
    const navigate = useNavigate()

    const init = {
        naziv: '',
        pib: '',
        mb: '',
        adresa: '',
        grad: '',
        teren: ''
    }

    //init

    const [kupac, setKupac] = useState([init])
    const [validno, setValidno] = useState(false)

    //dodavanje 
    const dodaj = () => {

        const dto = {
            naziv: kupac.naziv,
            pib: kupac.pib,
            mb: kupac.mb,
            adresa: kupac.adresa,
            grad: kupac.grad,
            teren: kupac.teren,
            komercijalistaId: window.localStorage.getItem('korisnikId')
        }

        Axios.post('/kupci', dto)
            .then(res => {
                console.log(res)
                alert('Uspesno dodavanje!')
                navigate('/trebovanje/dodavanje')
            })
            .catch(error => {
                console.log(error)
                alert('Doslo je do greske, pokusajte ponovo!')
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
        let kupacCopy = kupac
        kupacCopy[name] = value
        setKupac(kupacCopy)
        // validiraj()
    }

    return (
        <>
            <Form>
                <Row>
                    <Col>
                        <Form.Label htmlFor="naziv">Naziv kupca</Form.Label>
                        <Form.Control name="naziv" id="naziv" type="text" onChange={(e) => inputValueChange(e)} />
                        <Form.Label htmlFor="pib">PIB </Form.Label>
                        <Form.Control name="pib" id="pib" type="text" onChange={(e) => inputValueChange(e)} />
                        <Form.Label htmlFor="mb">MB</Form.Label>
                        <Form.Control name="mb" id="mb" type="text" onChange={(e) => inputValueChange(e)} />
                        <Form.Label htmlFor="adresa">Adresa</Form.Label>
                        <Form.Control name="adresa" id="adresa" type="text" onChange={(e) => inputValueChange(e)} />
                        <Form.Label htmlFor="grad">Grad</Form.Label>
                        <Form.Control name="grad" id="grad" type="text" onChange={(e) => inputValueChange(e)} />
                        <Form.Label htmlFor="teren">Teren</Form.Label>
                        <Form.Select name="teren" onChange={(e) => inputValueChange(e)}>
                            <option value={""}></option>
                            <option value={"Severna Bačka"}>Severna Bačka</option>
                            <option value={"Juzna Backa"}>Juzna Backa</option>
                            <option value={"Severni Banat"}>Severni Banat</option>
                            <option value={"Juzni Banat"}>Juzni Banat</option>
                            <option value={"Srem"}>Srem</option>
                            <option value={"Centralna Srbija"}>Centralna Srbija</option>
                        </Form.Select>
                        <br /> <Button onClick={dodaj}> Dodaj kupca</Button>
                    </Col>
                </Row>
            </Form>
            <br />
        </>
    )
}

export default KupacDodavanje