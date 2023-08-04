
import { Button, Col, Row, Form } from "react-bootstrap"
import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"

const Dopuna = (props) => {

    //navigate
    const navigate = useNavigate()

    // const params = useParams()

    let trebovanjeId = props.trebovanjeId

    const init = {
        trebovanjeId: '',
        robaId: '',
        robaPakovanje: '',
        robaJedinicaMere: '',
        kolicina: ''
    }


    //init

    const [dopuna, setDopuna] = useState(init)
    const [roba, setRoba] = useState([])
    const [svaTrebovanjaRobeIds, setSvaTrebovanjaRobeIds] = useState(props.svaTrebovanjaRobeIds)
    const [stanjeRobe, setStanjeRobe] = useState(0)


    //dodavanje 
    const dodaj = () => {

        const dto = {
            trebovanjeId: trebovanjeId,
            robaId: dopuna.robaId,
            robaPakovanje: dopuna.robaPakovanje,
            robaJedinicaMere: dopuna.robaJedinicaMere,
            kolicina: dopuna.kolicina,
        }

        Axios.post('/trebovanjaRobe', dto)
            .then(res => {
                console.log(res)
                alert('Uspesno dodavanje!')
                window.location.reload()
            })
            .catch(error => {
                console.log(error)
                if (svaTrebovanjaRobeIds.includes(dto.robaId)) {
                    alert('Navedena roba je vec trebovana')
                }
                else if (stanjeRobe < dopuna.kolicina) {
                    alert('Nema dovoljno robe na stanju')
                } else {
                    alert('Doslo je do greske, pokusajte ponovo!')
                }
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

    const getOdabraniProizvod = useCallback((id) => {
        Axios.get('/roba/' + id)
            .then(res => {
                console.log(res);
                setStanjeRobe(res.data.stanje)
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
        let dopunaCopy = dopuna
        dopunaCopy[name] = value
        setDopuna(dopunaCopy)
        // validiraj()
    }

    const proizvodInputValueChange = (e) => {
        setDopuna({ ...dopuna, robaId: e.target.value })
        getOdabraniProizvod(e.target.value)
        // validiraj()
    }

    return (
        <>
            <Form>
                <Row>
                    <Col>
                        <Form.Label htmlFor="robaId">Proizvod</Form.Label>
                        <Form.Select name="robaId" onChange={(e) => proizvodInputValueChange(e)}>
                            <option value={""}></option>
                            {robaSelect()}
                        </Form.Select>
                        <Form.Label htmlFor="kolicina">Kolicina</Form.Label>
                        <Form.Control name="kolicina" id="kolicina" type="number" onChange={(e) => inputValueChange(e)} />

                        <br /> <Button onClick={dodaj}> Dodaj </Button>

                    </Col>
                </Row>

            </Form>
            <br />
        </>
    )
}


export default Dopuna