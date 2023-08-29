import { Button, Col, Row, Form, Table, FormCheck } from "react-bootstrap"
import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import Dopuna from "./Dopuna"

const IzmenaTrebovanja = () => {

    //navigate
    const navigate = useNavigate()

    //izvlacenje id-ja iz zahteva
    const params = useParams()

    const trebovanjeId = params.trebovanjeId

    const dispozicijaId = params.dispozicijaId

    const trebovanjeInit = {
        komercijalistaId: '',
        kupacDto: {}
    }

    const [trebovanjaRobe, setTrebovanjaRobe] = useState([])
    const [trebovanje, setTrebovanje] = useState(trebovanjeInit)
    const [kolicina, setKolicina] = useState(0)
    const [trebovanjaRobeIds, setTrebovanjaRobeIds] = useState([])
    const [izmenaButton, setIzmenaButton] = useState(false)
    const [svaTrebovanjaRobeIds, setSvaTrebovanjaRobeIds] = useState([]) //props za dopunu
    const [validno, setValidno] = useState(false)


    //dodavanje trebovanja u dispoziciju
    const dodajTrebovanje = () => {

        const requestBody = {
            trebovanjaRobeIds: trebovanjaRobeIds
        }

        Axios.put('/dispozicije/' + dispozicijaId, requestBody)
            .then(res => {
                console.log(res);
                console.log(requestBody)
                console.log(dispozicijaId)
                alert('Uspesno dodavanje')
                navigate('/trebovanja/' + dispozicijaId)
            })
            .catch(error => {
                console.log(error);
                console.log(dispozicijaId)
                alert('Doslo je do greske, pokusajte ponovo!');
            });
    }


    //brisanje 
    const deleteTrebovanja = (trebovanjeId) => {
        Axios.delete('/trebovanja/' + trebovanjeId)
            .then(res => {
                console.log(res);
                alert('Uspesno brisanje!');
                navigate('/trebovanja')
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske, pokusajte ponovo!');
            });
    }


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


    //izmena 
    const izmeni = (id) => {

        const requestBody = {
            kolicina: kolicina
        }

        Axios.put('/trebovanjaRobe/' + id, requestBody)
            .then(res => {
                console.log(res)
                alert('Uspesno izmena!')
                window.location.reload()
            })
            .catch(error => {
                console.log(error)
                alert('Doslo je do greske, pokusajte ponovo!')
            })
    }

    //dobavljanje jednog
    const getTrebovanjeRobe = useCallback(() => {
        Axios.get('/trebovanjaRobe/' + trebovanjeId + '/trebovanja')
            .then(res => {
                console.log(res)
                const extractedIds = res.data.map(item => item.id);
                setSvaTrebovanjaRobeIds(extractedIds);
                setTrebovanjaRobe(res.data);
            })
            .catch(error => {
                console.log(error)
            })
    }, [])

    const getTrebovanje = useCallback(() => {
        Axios.get('/trebovanja/' + trebovanjeId)
            .then(res => {
                console.log(res)
                setTrebovanje(res.data)
            })
            .catch(error => {
                console.log(error)
            })
    }, [])

    useEffect(() => {
        getTrebovanjeRobe()
        getTrebovanje()
    }, [])

    //onChange
    const kolicinaOnChange = (e) => {
        setKolicina(e.target.value)
        setIzmenaButton(true)
    }


    const trebovanjaInputChange = (e) => {
        const newTrebovanjeId = e.target.value;
        const isChecked = e.target.checked
        if (isChecked) {
            setTrebovanjaRobeIds((prevIds) => [...prevIds, newTrebovanjeId]);
        } else {
            setTrebovanjaRobeIds((prevIds) =>
                prevIds.filter((id) => id !== newTrebovanjeId)
            )
        }
        console.log(trebovanjaRobeIds)
    }

    const renderTrebovanjaRobe = () => {
        return trebovanjaRobe.map((trebovanjeRobe, index) => {
            return (
                <tr onClick={() => trebovanjeRobe.disponirano ? window.open('/#/dispozicija/' + trebovanjeRobe.dispozicijaId) : null} key={trebovanjeRobe.id} >

                    <td>{index + 1}</td>
                    <td>{trebovanjeRobe.robaNaziv}</td>
                    <td>{trebovanjeRobe.robaPakovanje}</td>
                    <td >{trebovanjeRobe.robaJedinicaMere}</td>
                    {
                        window.localStorage.getItem('role') == 'ROLE_KOMERCIJALA' && !trebovanjeRobe.disponirano ? <td><Form.Control style={{ width: 120 }} placeholder={trebovanjeRobe.kolicina}
                            name="kolicina" type="number" onChange={(e) => kolicinaOnChange(e)} /></td> : <td> {trebovanjeRobe.kolicina}</td>
                    }
                    {trebovanjeRobe.disponirano ? <td><FormCheck checked /> </td> : <td></td>}
                    {trebovanjeRobe.isporuceno ? <td><FormCheck checked /> </td> : <td></td>}
                    {
                        window.localStorage.getItem('role') == 'ROLE_KOMERCIJALA' && !trebovanjeRobe.disponirano ?
                            (<> <td><Button style={{ marginRight: '10px' }} variant="warning" disabled={!izmenaButton} onClick={() => izmeni(trebovanjeRobe.id)}>Izmeni</Button>
                                <Button variant="danger" onClick={() => deleteTrebovanje(trebovanjeRobe.id)}>Obrisi</Button></td>
                            </>) : null
                    }
                    {
                        window.localStorage.getItem('role') == 'ROLE_LOGISTIKA' && !trebovanjeRobe.disponirano ? < td > <FormCheck value={trebovanjeRobe.id} onChange={(e) => trebovanjaInputChange(e)} ></FormCheck></td> : null
                    }
                    {/* {trebovanjeRobe.disponirano ? <td> <Button variant="success" onClick={() => navigate('/dispozicija/' + trebovanjeRobe.dispozicijaId)} >Pogledaj dispoziciju</Button></td> : null} */}
                </tr >
            )
        })
    }

    //  //validacija
    // const validiraj = () => {
    //     if (zadatak.ime == '' || zadatak.zaduzeni == '') {
    //         setValidno(false)
    //     } else {
    //          setValidno(true)
    //     }
    //  }


    //krajnji ispis
    return (
        <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">

                <h1>{trebovanje.kupacDto.naziv}</h1>
                <p>
                    {trebovanje.kupacDto.teren}<br />
                    {trebovanje.kupacDto.grad}<br />
                    {trebovanje.kupacDto.adresa}
                </p>

                <Table style={{ marginTop: 5 }}>
                    <thead>
                        <tr>
                            <th >Redni broj</th>
                            <th>Naziv proizvoda</th>
                            <th>Pakovanje</th>
                            <th>Jedinica mere</th>
                            <th>Kolicina</th>
                            <th >Disponirano</th>
                            <th >Isporuceno</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderTrebovanjaRobe()}
                    </tbody>
                </Table>
            </Col>
            <Col></Col>

            <Row>

                <Col></Col>

                <Col xs="12" sm="10" md="8">
                    <br />  <br />
                    {/* {window.localStorage.getItem('role') == 'ROLE_KOMERCIJALA' ? <h4>Dopuna trebovanja</h4> : null} */}
                    <br /> {window.localStorage.getItem('role') == 'ROLE_KOMERCIJALA' && !trebovanje.disponirano ? <Col ><Dopuna svaTrebovanjaRobeIds={svaTrebovanjaRobeIds} trebovanjeId={trebovanjeId}></Dopuna></Col> : null}
                </Col>
                <Col></Col>
            </Row>

            <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                    <br />
                    {window.localStorage.getItem('role') == 'ROLE_KOMERCIJALA' && !trebovanje.disponirano ? <Button variant="danger" onClick={() => deleteTrebovanja(trebovanjeId)} >Obrisi trebovanje</Button> : null}
                    {window.localStorage.getItem('role') == 'ROLE_LOGISTIKA' && !trebovanje.disponirano ? <Button variant="success" disabled={trebovanjaRobe.length == 0} onClick={() => dodajTrebovanje()}>Dodaj u dispoziciju</Button> : null}
                </Col>
                <Col></Col>
            </Row>

        </Row>
    )
}

export default IzmenaTrebovanja