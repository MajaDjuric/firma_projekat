import { isValidElement } from "react";
import ProjekcijaIzmena from "./components/Projekcije/ProjekcijaIzmena";

//TRENUTNI DATUM
    let datum = new Date();
    let months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec']

    let d = datum.getDate()

    //verzije za mesec (+1 ili iz liste)
    let m = datum.getMonth()+1;
    let mm = months[datum.getMonth()];

    let y = datum.getFullYear()
 
    //return d + '.' + m + '.' + y + '.'                           //25.6.2023.
    // return d + '.' + (m <= 9 ? '0'+ m : m) + '.' + y + '.'      //25.06.2023.
    // return d + '.' + mm + ' ' + y + '.'                         //25. June 2023.


//RADNOM DATUM
    const randomDatum = new Date(2022, 8, 2, 9, 35);
    
    let rd = randomDatum.getDate();
    let rm = randomDatum.getMonth();
    let ry = randomDatum.getFullYear();
    let rh = randomDatum.getHours();
    let rM = randomDatum.getMinutes();

    return (rd <= 9? '0' + rd: rd) + '.' + (rm <= 9 ? '0'+ rm : rm) + '.' + ry + '.  ' + (rh <= 9? '0' + rh : rh) + ':' + (rM<= 9? '0' + rM: rM)

    // ----------------------------------------------------------------------------------

on change DATUM

    dodavanje
    // const onDatumChange = (e) => {
    //     const name = e.target.name
    //     const value = e.target.value
    //     let searchCopy = searchParams
----//     searchCopy[name] = value.replace('T', ' ')  
    //     console.log(searchCopy)
    //     setSearchParams(searchCopy)
    // }

    izmena 
    // const datumOnChange = (e) => {
    //     const datumivreme = e.target.value
 ----//     setProjekcija({ ...projekcija, datumIVreme: datumivreme.replace('T', ' ')})
    //     validiraj()
    // }


formatiranje
    // const formatirajDatum =(datum)=>{
    //   return datum.replace ('T', ' ')
    // }

    ili 

    // const formatirajDatum =(datumm)=>{
    //     let datum = new Date(datumm)
    //     let dan = datum.getDate();
    //     let mesec = datum.getMonth() + 1;
    //     let godina = datum.getFullYear();
    //     let sati = datum.getHours();
    //     let minuti = datum.getMinutes();

    //     // return  dan + '.' + mesec + '.' + godina + '.'
    //     return (dan <= 9? '0' + dan : dan) + '.' + (mesec <= 9? '0' + mesec : mesec) + '.' + godina + '. ' + (sati <= 9? '0' + sati : sati) + ':' + (minuti <= 9? '0' + minuti : minuti)
    //   }

    pozivanje funkcije za formatiranje
    -------  //<td>{formatirajDatum(props.projekcija.datumIVreme)}</td>