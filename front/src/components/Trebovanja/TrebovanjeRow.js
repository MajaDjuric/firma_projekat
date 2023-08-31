import { useNavigate } from "react-router-dom";
import Axios from "../../apis/Axios";
import { Button, FormCheck } from "react-bootstrap";

const TrebovanjeRow = (props) => {

    var navigate = useNavigate()

    var trebovanjeId = props.trebovanje.id

    const deleteTrebovanje = () => {
        Axios.delete('/trebovanja/' + trebovanjeId)
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


    return (
        <tr>
            <td onClick={() => navigate('/trebovanje/izmena/' + trebovanjeId)}>{trebovanjeId}</td>
            <td onClick={() => navigate('/trebovanje/izmena/' + trebovanjeId)}>{props.trebovanje.kupacDto.teren}</td>
            <td onClick={() => navigate('/trebovanje/izmena/' + trebovanjeId)}>{props.trebovanje.komercijalistaIme} {props.trebovanje.komercijlistaPrezime}</td>
            <td onClick={() => navigate('/trebovanje/izmena/' + trebovanjeId)}>{props.trebovanje.kupacDto.naziv}</td>
            {window.localStorage.getItem('role') == 'ROLE_LOGISTIKA' ? <td><FormCheck></FormCheck></td> : null}
            {window.localStorage.getItem('role') == 'ROLE_KOMERCIJALA' ? <td><Button variant="danger" onClick={deleteTrebovanje}>Obrisi</Button></td> : null}
        </tr>
    )
}

export default TrebovanjeRow;