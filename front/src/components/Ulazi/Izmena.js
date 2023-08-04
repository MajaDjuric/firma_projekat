import { Button, Col, Row, Form } from "react-bootstrap"
import Axios from "../../apis/Axios"
import { useCallback, useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"

const Izmena = () => {

    //navigate
    const navigate = useNavigate()

    //izvlacenje id-ja iz zahteva
    const params = useParams()

    const zadatakId = params.id

    //inicijalna linija
    const init = {
        id: '',
        ime: '',
        zaduzeni: '',
        bodovi: '',
        sprintId: '',
        sprintIme: '',
        stanjeId: '',
        stanjeIme: '',
    }

    const [zadatak, setZadatak] = useState(init)
    const [sprintovi, setSprintovi] = useState([])
    const [validno, setValidno] = useState(false)


    //izmena 
    const izmeni = () => {

        const dto = {
            id: zadatak.id,
            ime: zadatak.ime,
            zaduzeni: zadatak.zaduzeni,
            bodovi: zadatak.bodovi,
            sprintId: zadatak.sprintId,
            sprintIme: zadatak.sprintIme,
            stanjeId: zadatak.stanjeId,
            stanjeIme: zadatak.stanjeIme
        }

        Axios.put('/zadaci/' + zadatak.id, dto)
            .then(res => {
                console.log(res)
                alert('Uspesno izmena!')
                navigate('/zadaci')
            })
            .catch(error => {
                console.log(error)
                alert('Doslo je do greske, pokusajte ponovo!')
            })
    }

  //dobavljanje jednog
       const getZadatak = useCallback(() => {
        Axios.get('/zadaci/' + zadatakId)
            .then(res => {
                console.log(res)
                setZadatak(res.data)
            })
            .catch(error => {
                console.log(error)
            })
    }, [])

    //dobavljanje svih za select
    const getSprintovi = useCallback(() => {
        Axios.get('/sprintovi')
            .then(res => {
                console.log(res)
                setSprintovi(res.data)
            })
            .catch(error => {
                console.log(error)
            })
    }, [])


    useEffect(() => {
        getZadatak()
        getSprintovi()
    }, [])


    // select ispis
    const sprintoviSelect = () => {
        return sprintovi.map(sprint => {
            return (
                <option key={sprint.id} value={sprint.id}>{sprint.ime}</option>
            )
        })
    }

     //validacija
    const validiraj = () => {
        if (zadatak.ime == '' || zadatak.zaduzeni == '') {
            setValidno(false)
        } else {
             setValidno(true)
        }
     }


    //onChange
    const imeOnChange = (e) => {
        const ime = e.target.value
        setZadatak({ ...zadatak, ime: ime })
        validiraj()
    }
    const onChangeZaduzeni = (e) => {
        const zaduzeni = e.target.value
        setZadatak({ ...zadatak, zaduzeni: zaduzeni })
        validiraj()
    }
    const onChangeBodovi = (e) => {
        const bodovi = e.target.value
        setZadatak({ ...zadatak, bodovi: bodovi })
    }
    const onChangeStanje = (e) => {
        const stanje = e.target.value
        setZadatak({ ...zadatak, stanjeId: stanje })
    }
    const onChangeSprint = (e) => {
        const sprint = e.target.value
        setZadatak({ ...zadatak, sprintId: sprint })
    }

    //krajnji ispis
    return (
        <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">
                <h1>Izmena zadatka</h1>
                <Form>
                    <Form.Label htmlFor="ime" >Ime</Form.Label>
                       <Form.Control value={zadatak.ime} name="ime" id="ime" type="text" onChange={(e) => onChangeBodovi(e)} />
                    <Form.Label htmlFor="zaduzeni">Zaduzeni</Form.Label>
                       <Form.Control value={zadatak.zaduzeni} name="zaduzeni" id="zaduzeni" type="text" onChange={(e) => onChangeZaduzeni(e)} />
                    <Form.Label htmlFor="bodovi">Bodovi</Form.Label>
                       <Form.Control value={zadatak.bodovi} name="bodovi" id="bodovi" type="text" onChange={(e) => onChangeBodovi(e)} />
                    <Form.Label htmlFor="stanjeId">Stanje</Form.Label>
                       <Form.Select value={zadatak.stanjeId} name="stanjeId" onChange={(e) => onChangeStanje(e)}>
                        <option value={''}></option>
                        {sprintoviSelect()}
                       </Form.Select><br />
                    <Form.Label htmlFor="sprintId">Sprint</Form.Label>
                       <Form.Select value={zadatak.sprintId} name="sprintId" onChange={(e) => onChangeSprint(e)}>
                       <option value={''}></option>
                       {sprintoviSelect()}
                    </Form.Select><br />
                    <Button onClick={izmeni} disabled={!validno}> Izmeni</Button>
                </Form>
            </Col>
            <Col></Col>
        </Row>
    )
}

export default Izmena