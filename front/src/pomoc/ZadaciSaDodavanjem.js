import React, { useState, useEffect, useCallback } from 'react';
import { Button, Col, Form, Row, Table } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import Axios from '../../apis/Axios';
import ZadatakRow from './ZadaciRow';

const ZadaciIDodavanje = (props) => {

    //state

    const [searchParams, setSearchParams] = useState({
        ime: '',
        sprintId: ''
    })

    const [zadaci, setZadaci] = useState([])
    const [sprintovi, setSprintovi] = useState([])
    const [pageNo, setPageNo] = useState(0)
    const [totalPage, setTotalPage] = useState(0)
    const [hidden, setHidden] = useState(false)
    const [hiddenDodavanje, setHiddenDodavanje] = useState(false)
    
    //navigate
    var navigate = useNavigate()

    //dobavljanje getAll
    const getZadaci = useCallback((nextPage) => {

        const config = {
            params: {
                pageNo: nextPage,
                ime: searchParams.ime,
                sprintId: searchParams.sprintId
            }
        }

        Axios.get('/zadaci', config)
            .then(res => {
                console.log(res);
                setZadaci(res.data)
                setPageNo(nextPage)
                setTotalPage(res.headers["total-pages"])
                console.log(res.headers)
            })
            .catch(error => {
                console.log(error);
            });
    }, []);


    //dobavljanje svih sprintova
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
        getZadaci(0)
        getSprintovi()
    }, []);


    const goToDodavanje = () => {
        navigate('/zadaci/dodavanje');
    }

    const renderZadaci = () => {
        return zadaci.map((zadatak, index) => {
            return <ZadatakRow key={zadatak.id} zadatak={zadatak}></ZadatakRow>
        })
    }

    //izmena inputa 
    const onInputChange = (e) => {
        const name = e.target.name
        const value = e.target.value

        let searchCopy = searchParams
        searchCopy[name] = value
        console.log(searchCopy)

        setSearchParams(searchCopy)
        getZadaci(0) //ako nema dugmeta
    }
    const zadatakState = {
        ime: '',
        zaduzeni: '',
        bodovi: '',
        sprintId: '',
        stanjeId: '',
    }

    const [zadatak, setZadatak] = useState(zadatakState)
    const [stanja, setStanja] = useState([])

    //dodavanje linije
    const dodaj = () => {

        const zadatakDto = {
            ime: zadatak.ime,
            zaduzeni: zadatak.zaduzeni,
            bodovi: zadatak.bodovi,
            sprintId: zadatak.sprintId,
            stanjeId: zadatak.stanjeId
        }

        Axios.post('/zadaci', zadatakDto)
            .then(res => {
                console.log(res)
                alert('Uspesno dodavanje!')
                navigate('/zadaci')
            })
            .catch(error => {
                console.log(error)
                alert('Doslo je do greske, pokusajte ponovo!')
            })
    }


    const getStanja = useCallback(() => {
        Axios.get('/stanja')
            .then(res => {
                console.log(res)
                setStanja(res.data)
            })
            .catch(error => {
                console.log(error)
            })
    }, [])

    useEffect(() => {
        getSprintovi()
        getStanja();
    }, [])

    //stanja select 
    const stanjaSelect = () => {
        return stanja.map(stanje => {
            return (
                <option key={stanje.id} value={stanje.id}>{stanje.ime}</option>
            )
        })
    }

    const sprintoviSelect = () => {
        return sprintovi.map(sprint => {
            return (
                <option key={sprint.id} value={sprint.id}>{sprint.ime}</option>
            )
        })
    }

    //onChange
    const inputValueChange = (e) => {
        let input = e.target
        let name = input.name
        let value = input.value
        let zadatakSt = zadatak
        zadatakSt[name] = value
        setZadatak(zadatakSt)
    }

    //DODAVANJE SAKRIVENO CHECKBOX

//     const renderDodavanje = () => {
//     return (
//         <>
//         <input type='checkbox' id='checkbox' onChange={()=> setHiddenDodavanje(!hiddenDodavanje)}></input>
//         <label htmlFor='checkbox'> &nbsp; Prikazi formu za dodavanje</label>
//         <Row hidden={!hiddenDodavanje}>
//             <Col></Col>
//             <Col xs="12" sm="10" md="8">
//                 <h1>Dodavanje zadatka</h1>
//                 <Form>
//                     <Form.Label htmlFor="ime" >Ime</Form.Label>
//                     <Form.Control name="ime" id="ime" type="text" onChange={(e) => inputValueChange(e)} />
//                     <Form.Label htmlFor="zaduzeni">Zaduzeni</Form.Label>
//                     <Form.Control name="zaduzeni" id="zaduzeni" type="text" onChange={(e) => inputValueChange(e)} />
//                     <Form.Label htmlFor="bodovi">Bodovi</Form.Label>
//                     <Form.Control name="bodovi" id="bodovi" type="text" onChange={(e) => inputValueChange(e)} />
//                     <Form.Label htmlFor="stanjeId">Stanje</Form.Label>
//                     <Form.Select name="stanjeId" onChange={(e) => inputValueChange(e)}>
//                         <option></option>
//                         {stanjaSelect()}
//                     </Form.Select><br />
//                     <Form.Label htmlFor="sprintId">Sprint</Form.Label>
//                     <Form.Select name="sprintId" onChange={(e) => inputValueChange(e)}>
//                         <option></option>
//                         {sprintoviSelect()}
//                     </Form.Select><br />
//                     <Button onClick={dodaj}> Dodaj</Button>
//                 </Form>
//             </Col>
//             <Col></Col>
//         </Row>
//         </>
//     )
// }

//DODAVANJE SAKRIVNO DUGME
const renderDodavanje = () => {
        return (
            <>
           {hiddenDodavanje == false? <Button onClick={()=> setHiddenDodavanje(!hiddenDodavanje)}>Prikazi dodavanje</Button> : <Button onClick={()=> dodaj()}>Dodaj</Button> }
            <Row hidden={!hiddenDodavanje}>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                    <h1>Dodavanje zadatka</h1>
                    <Form>
                        <Form.Label htmlFor="ime" >Ime</Form.Label>
                        <Form.Control name="ime" id="ime" type="text" onChange={(e) => inputValueChange(e)} />
                        <Form.Label htmlFor="zaduzeni">Zaduzeni</Form.Label>
                        <Form.Control name="zaduzeni" id="zaduzeni" type="text" onChange={(e) => inputValueChange(e)} />
                        <Form.Label htmlFor="bodovi">Bodovi</Form.Label>
                        <Form.Control name="bodovi" id="bodovi" type="text" onChange={(e) => inputValueChange(e)} />
                        <Form.Label htmlFor="stanjeId">Stanje</Form.Label>
                        <Form.Select name="stanjeId" onChange={(e) => inputValueChange(e)}>
                            <option></option>
                            {stanjaSelect()}
                        </Form.Select><br />
                        <Form.Label htmlFor="sprintId">Sprint</Form.Label>
                        <Form.Select name="sprintId" onChange={(e) => inputValueChange(e)}>
                            <option></option>
                            {sprintoviSelect()}
                        </Form.Select><br />
                        {/* <Button onClick={dodaj}> Dodaj</Button> */}
                    </Form>
                </Col>
                <Col></Col>
            </Row>
            </>
        )
    }

    // forma za pretragu
    const renderSearchForm = () => {
        return (
            <> 
            <input type='checkbox' id='checkbox' onChange={()=> setHidden(!hidden)}></input>
            <label htmlFor='checkbox'> &nbsp; Prikazi pretragu</label>
                <Form  hidden={!hidden} >
                     {/* ako hocu obrnuto sklonim ! */}
                    <Row>
                        <Col>
                            <Form.Label>Ime zadatka</Form.Label>
                            <Form.Control name="ime" id="ime" type="text" onChange={(e) => onInputChange(e)} />
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                            <Form.Label>Sprint</Form.Label>
                            <Form.Select name="sprintId" onChange={(e) => onInputChange(e)}>
                                <option value={""}></option>
                                {sprintoviSelect()}
                            </Form.Select><br />
                        </Col>
                    </Row>
                    <Button onClick={() => getZadaci(0)}>Pretrazi</Button>
                </Form>
                
            </>
        )
    }

    //krajnji ispis
    return (
        <Col>
            <Row><h1>Zadaci</h1></Row>

            {renderDodavanje()} <br/>
            {renderSearchForm()}

            <br />

            {/* paginacija */}
            <br /> <Row>
                <Col>

                    <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
                        <Button disabled={pageNo == 0} onClick={() => getZadaci(pageNo - 1)}>Prethodna</Button>
                        <Button disabled={pageNo + 1 == totalPage} onClick={() => getZadaci(pageNo + 1)}>Sledeca</Button>
                    </div>
                </Col>
            </Row>

            <Row><Col>
                <Table id="movies-table">
                    <thead>
                        <tr>
                            <th>Ime</th>
                            <th>Zaduzeni</th>
                            <th>Bodovi</th>
                            <th>Stanje</th>
                            <th>Sprint</th>
                            <th>Actions</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderZadaci()}
                    </tbody>
                </Table>
                <Row>
                    <Col>
                        <div style={{ display: 'flex', justifyContent: 'flex-start' }}>
                            <Button className="button button-navy" onClick={() => goToDodavanje()}>Novi zadatak</Button>
                        </div>
                    </Col>
                </Row>
            </Col></Row>
        </Col>
    );
}


export default ZadaciIDodavanje;
// {window.localStorage.getItem('role') == 'ROLE_ADMIN' ?

