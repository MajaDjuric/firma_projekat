parsiranje
    // postignutiGolovi: parseInt(igrac.postignutiGolovi) + 1


disable
    /* <Button  onClick={dodaj} disabled={!validno}> Dodaj</Button> */

disable jednog selecta ako drugi select nije odabran
    // <Form.Select disabled={racun.bankaId == ''} name="tipRacunaId" id="tipRacunaId" onChange={(e) => inputValueChange(e)}>

hidden forma
                //1 linije
                //2 sprintovi
                 //1 linije- LinijeSaDodavanjem - sakrivanje citave forme za dodavanje linije

    // const [hidden, setHidden] = useState(false)

    // const renderSearchForm = () => {
    //     return (
    //         <> 

    ------     /*
          <div style={{ display: 'flex' }}>
          <Form.Check onChange={()=> setHidden(!hidden)} ></Form.Check> 
          <Form.Label htmlFor='checkbox'> &nbsp; Prikazi pretragu</Form.Label>
         </div> 
         /* <Form  hidden={!hidden} > */
                                 /* ako hocu obrnuto sklonim ! */
            //  <Row>
            //  <Col>
     -----       //  <Form.Label>Destinacija</Form.Label>


KORISNIK/ADIM
    // {window.localStorage.getItem('role') == 'ROLE_ADMIN' ? 
    // {window.localStorage.getItem('role') == 'ROLE_KORISNIK' ? 


Izvlacenje iz headera
    // setTotalPage(res.headers["total-pages"])


Boje dugmeta
    // variant="primary" 
    // variant="warning"
    // variant="danger"
    // variant="success"

slanje jednog parametra  //4 vina
  
    // const kupiVino = (idVina) => {

    //     const config = {
    //         params: {
    //             brojFlasa: brojFlasa
    //         }
    //     }

    //     Axios.get('/vina/' + idVina + '/naruci', config)
    //         .then(res => {
    //             console.log(res);
    //             alert('Uspesna kupovina')
    //             window.location.reload();
    //         })

    ako nije LIKE nego ==

        // const getRacuni = useCallback((nextPage) => {

        //     const config = {
        //         params: {
        //             pageNo: nextPage,
        //         }
        //     }
------  //     if (searchParams.jmbg != '') {
------  //         config.params.jmbg = searchParams.jmbg
        //     }

        //     Axios.get('/racuni', config)


dodavanje  init parametri
        // const init = {
             //     id: ''                 --------ne treba
        //     imeIPrezime: '',
        //     jmbg: '',
        //     brojRacuna: '',
        //     stanjeRacuna: '',
        //     tipRacunaId: '',
             //     tipRacunaNaziv: '',    --------ne treba
        //     bankaId: '',
             //     bankaNaziv: '',        --------ne treba
        // }

najbolji iz liste 
    // const najboljiIgrac = () => {
    //     let najIgrac = igraci[0];
    //     igraci.forEach(igrac => {
    //         if (parseInt(igrac.postignutiGolovi) > parseInt(najIgrac.postignutiGolovi)) {
    //             najIgrac = igrac;
    //         }
    //     });
    //     alert(najIgrac.ime + ' ' + najIgrac.prezime)
    // };

slanje kroz body/header

    body - objekat (dto)
    // const params = {
    //     ime:ime,
    //     prezime:prezime
    // }

    header - parametri
    // const config = {
    //     params : {
    //         ime:ime,
    //         prezime: prezime
    //     }
    // }

React.Fragment za grupisanje ako kod uslova imam vise elemenata za ispis  //4 VinoRow
    // {window.localStorage.getItem('role') == 'ROLE_ADMIN' && props.vino.brojDostupnihFlasa < 10 ? (
 --   //     <React.Fragment key="adminElements">
    //    <td><input  onChange={(e) => onChangeBrojFlasa(e)} style={{ width: '40px' }} type="text"></input></td>
    //    <td><Button variant="primary" onClick={() => izmeni(props.vino.id)} >Naruci</Button></td>
--    //    </React.Fragment>): null}

izvlacenje iz mape 
//     const getGenresStringFromList = (list) => {
//     return list.map(element => element.naziv).join(',');
// }