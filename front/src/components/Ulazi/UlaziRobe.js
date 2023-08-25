import Axios from '../../apis/Axios';
import { useCallback, useEffect, useState } from "react"
import { Button, Col, Form, Row, Table } from "react-bootstrap"
import { useNavigate, useParams } from 'react-router-dom';

const UlaziRobe = (props) => {

    //navigate
    const navigate = useNavigate()

    const params = useParams()

    const ulazId = params.id

    const init = {
        datumUlaza: '',
        brojFakture: '',
        brojOtpremnice: '',
        proizvodjacNaziv: ''
    }

    //state
    const [ulaziRobe, setUlaziRobe] = useState([])
    const [roba, setRoba] = useState([])
    const [ulaz, setUlaz] = useState(init)


    //dobavljanje svih
    const getUlaziRobe = useCallback(() => {

        Axios.get('/ulaziRobe/' + ulazId + '/ulazi')
            .then(res => {
                console.log(res);
                setUlaziRobe(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    const getUlaz = useCallback(() => {

        Axios.get('/ulazi/' + ulazId)
            .then(res => {
                console.log(res);
                setUlaz(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    const getRoba = useCallback(() => {
        Axios.get('/ulazi/' + ulazId + '/roba')
            .then(res => {
                console.log(res);
                setRoba(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);


    useEffect(() => {
        getUlaziRobe()
        getUlaz()
        getRoba()
    }, [])


    const ulaziRobeRender = () => {
        return ulaziRobe.map((ulazRobe, index) => {
            return (
                <tr key={ulazRobe.id}>
                    <td>{index + 1}</td>
                    <td>{ulazRobe.robaNaziv}</td>
                    <td>{ulazRobe.robaPakovanje}</td>
                    <td>{ulazRobe.robaJedinicaMere}</td>
                    <td>{ulazRobe.kolicina}</td>
                    <td>{ulazRobe.cenaPoJediniciMere}</td>
                    <td>{ulazRobe.pdv}</td>
                    <td>{ulazRobe.rabat}</td>
                    <td>{ulazRobe.krajnjaCenaPoJediniciMere}</td>
                    <td>{ulazRobe.krajnjaCena.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}</td>
                    {window.localStorage.role == 'ROLE_FINANSIJE' ?
                        <td><Button variant='warning'>Izmeni</Button></td> : null}
                </tr>

            )
        })
    }

    const formatirajDatum = (datumParam) => {
        let datum = new Date(datumParam)
        let dan = datum.getDate()
        let mesec = datum.getMonth() + 1
        let godina = datum.getFullYear()
        return (dan <= 9 ? '0' + dan : dan) + '.' + (mesec <= 9 ? '0' + mesec : mesec) + '.' + godina + '.'
    }

    //krajnji ispis
    return (
        <Col>

            <br /><br />
            <Row><Col>

                <div>
                    <p> Datum ulaza: {formatirajDatum(ulaz.datumUlaza)}</p>
                    <p> Broj fakture: {ulaz.brojFakture}</p>
                    <p> Broj otpremnice: {ulaz.brojOtpremnice}</p>
                    <p> Dobavljac: {ulaz.proizvodjacNaziv}</p>
                </div>
                <br />

                <Table style={{ marginTop: 5 }}>
                    <thead>
                        <tr>
                            <th>Redni broj</th>
                            <th>Naziv</th>
                            <th>Pakovanje</th>
                            <th>Jedinica mere</th>
                            <th>Kolicina</th>
                            <th>Osnovna cena</th>
                            <th>PDV</th>
                            <th>Rabat</th>
                            <th>Krajnja cena/j.m.</th>
                            <th>Krajnja cena</th>
                            {window.localStorage.role == 'ROLE_FINANSIJE' ? <th></th> : null}
                        </tr>
                    </thead>
                    <tbody>
                        {ulaziRobeRender()}
                    </tbody>
                </Table>
            </Col>
            </Row>
        </Col>
    )
}

export default UlaziRobe
