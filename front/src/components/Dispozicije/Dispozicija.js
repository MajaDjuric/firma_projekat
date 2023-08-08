import Axios from '../../apis/Axios';
import React, { useCallback, useEffect, useState } from "react"
import { Button, Col, Form, Row, Table, FormCheck } from "react-bootstrap"
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
    const [hidden, setHidden] = useState(false)


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
                alert('Uspesno!')
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
                                <th>Kolicina</th>
                            </tr>
                        </thead>

                    )}
                    <tbody>
                        <tr>
                            <td style={trebovanjeRobe.isporuceno ? { color: "red" } : {}}>{index + 1}</td>
                            <td style={trebovanjeRobe.isporuceno ? { color: "red" } : {}}>{trebovanjeRobe.robaNaziv}</td>
                            <td style={trebovanjeRobe.isporuceno ? { color: "red" } : {}}>{trebovanjeRobe.robaPakovanje}</td>
                            <td style={trebovanjeRobe.isporuceno ? { color: "red" } : {}}>{trebovanjeRobe.robaJedinicaMere}</td>
                            <td style={trebovanjeRobe.isporuceno ? { color: "red" } : {}}>{trebovanjeRobe.kolicina}</td>
                            {window.localStorage.getItem('role') == 'ROLE_MAGACIN' && !trebovanjeRobe.isporuceno ? <td><FormCheck value={trebovanjeRobe.id} onChange={(e) => trebovanjaInputChange(e)} ></FormCheck></td> : null}
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
                <br />

                <Table style={{ marginTop: 5 }}>
                    {/* <tbody> */}
                    {trebovanjaRobeRender()}
                    {/* </tbody> */}
                </Table>
                {window.localStorage.getItem('role') == 'ROLE_MAGACIN' ? <Button onClick={() => setIsporuceno()} >Zavrsi </Button> : null}
            </Col>
            </Row>
        </Col>
    )
}

export default Dispozicija

// {window.localStorage.getItem('role') == 'ROLE_ADMIN' ?