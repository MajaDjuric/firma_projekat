import Axios from '../../apis/Axios';
import React, { useCallback, useEffect, useState } from "react"
import { Button, Col, Row, Table, FormCheck } from "react-bootstrap"
import { useNavigate, useParams } from 'react-router-dom';


const Dispozicija = () => {

    //navigate
    const navigate = useNavigate()

    const params = useParams()

    const dispozicijaId = params.id

    const init = {
        datumIsporuke: '',
        vozacId: '',
        vozilo: {}
    }

    //state
    const [dispozicija, setDispozicija] = useState(init)
    const [trebovanjaRobe, setTrebovanjaRobe] = useState([])
    const [trebovanjaRobeIds, setTrebovanjaRobeIds] = useState([])


    //dobavljanje svih
    const getDispozicija = useCallback(() => {

        Axios.get('/dispozicije/' + dispozicijaId)
            .then(res => {
                console.log(res);
                setDispozicija(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    const getTrebovanjaRobe = useCallback(() => {

        Axios.get('/trebovanjaRobe/dispozicija/' + dispozicijaId)
            .then(res => {
                console.log(res);
                setTrebovanjaRobe(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    useEffect(() => {
        getTrebovanjaRobe()
        getDispozicija()
    }, [])


    const setIsporuceno = () => {

        const requestBody = {
            trebovanjaRobeIds: trebovanjaRobeIds
        }

        Axios.put('/dispozicije/' + dispozicijaId + '/isporuceno', requestBody)
            .then(res => {
                console.log(res);
                console.log(requestBody)
                navigate('/dispozicije')
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske, pokusajte ponovo!');
            });
    }

    const formatirajDatum = (datumParam) => {
        let datum = new Date(datumParam)
        let dan = datum.getDate()
        let mesec = datum.getMonth() + 1
        let godina = datum.getFullYear()
        return (dan <= 9 ? '0' + dan : dan) + '.' + (mesec <= 9 ? '0' + mesec : mesec) + '.' + godina + '.'
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

    const deleteTrebovanjeRobe = useCallback((trebovanjeRobeId) => {

        Axios.delete('/dispozicije/brisanjeTrebovanjaRobe/' + trebovanjeRobeId)
            .then(res => {
                console.log(res);
                window.location.reload()
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    const ipsisCheckBoxIliBrisanje = (trebovanjeRobe) => {
        if (!trebovanjeRobe.isporuceno && window.localStorage.getItem('role') == 'ROLE_MAGACIN') {
            return <td><FormCheck value={trebovanjeRobe.id} onChange={(e) => trebovanjaInputChange(e)} ></FormCheck></td>
        } else if (trebovanjeRobe.isporuceno && (window.localStorage.getItem('role') == 'ROLE_MAGACIN' || window.localStorage.getItem('role') == 'ROLE_LOGISTIKA')) {
            return <td><FormCheck checked></FormCheck></td>
        } else if (!dispozicija.isporuceno && window.localStorage.getItem('role') == 'ROLE_LOGISTIKA') {
            return <td><Button variant='danger' onClick={() => deleteTrebovanjeRobe(trebovanjeRobe.id)} >Obrisi</Button></td>
        }
    }

    const trebovanjaRobeRender = () => {
        const uniqueKupci = new Set();

        return trebovanjaRobe.map((trebovanjeRobe, index) => {
            const isUniqueKupac = !uniqueKupci.has(trebovanjeRobe.kupac.naziv);
            if (isUniqueKupac) {
                uniqueKupci.add(trebovanjeRobe.kupac.naziv);
            }

            return (
                <React.Fragment key={trebovanjeRobe.id}>
                    {isUniqueKupac && (

                        <thead>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td colSpan="5" style={{ color: "blue" }}>
                                    {trebovanjeRobe.kupac.naziv} &nbsp;&nbsp;&nbsp;&nbsp; {trebovanjeRobe.kupac.grad} &nbsp;&nbsp;&nbsp;&nbsp; {trebovanjeRobe.kupac.adresa}
                                </td>
                            </tr>
                            <tr>
                                <th>Redni broj</th>
                                <th>Proizvod</th>
                                <th>Pakovanje</th>
                                <th>Jedinica mere</th>
                                <th>Količina</th>
                                {window.localStorage.getItem('role') == 'ROLE_MAGACIN' ? <th>Isporučeno</th> : null}
                            </tr>
                        </thead>

                    )}
                    <tbody>
                        <tr>
                            <td style={!trebovanjeRobe.isporuceno ? { color: "red" } : {}}>{index + 1}</td>
                            <td style={!trebovanjeRobe.isporuceno ? { color: "red" } : {}}>{trebovanjeRobe.robaNaziv}</td>
                            <td style={!trebovanjeRobe.isporuceno ? { color: "red" } : {}}>{trebovanjeRobe.robaPakovanje}</td>
                            <td style={!trebovanjeRobe.isporuceno ? { color: "red" } : {}}>{trebovanjeRobe.robaJedinicaMere}</td>
                            <td style={!trebovanjeRobe.isporuceno ? { color: "red" } : {}}>{trebovanjeRobe.kolicina}</td>
                            {ipsisCheckBoxIliBrisanje(trebovanjeRobe)}
                        </tr>
                    </tbody>
                </React.Fragment>
            );
        });
    };

    //krajnji ispis
    return (
        <Col>

            <br /><br />
            <Row><Col>

                <h5>{formatirajDatum(dispozicija.datumIsporuke)}</h5>

                <p>
                    Vozac: {dispozicija.vozacImeIPrezime}<br />
                    Vozilo: {dispozicija.vozilo.markaITip}<br />
                    Registracija: {dispozicija.vozilo.registracija}
                </p>

                <Table style={{ marginTop: 5 }}>
                    {trebovanjaRobeRender()}
                </Table>
                {window.localStorage.getItem('role') == 'ROLE_MAGACIN' ? <td><Button onClick={() => setIsporuceno()} >Završi </Button></td> : null}
            </Col>
            </Row>
        </Col>
    )
}

export default Dispozicija
