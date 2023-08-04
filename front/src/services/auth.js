import Axios from "../apis/Axios"
import jwt_decode from 'jwt-decode';

export const login = async (username, password) => {

    const body = {
        username: username,
        password: password
    }
    try {
        const resp = await Axios.post("/korisnici/auth", body)
        const decoded_jwt = jwt_decode(resp.data)
        window.localStorage.setItem("jwt", resp.data)
        window.localStorage.setItem("role", decoded_jwt.role.authority)
        window.location.replace("http://localhost:3000")
    } catch (e) {
        console.log(e)
        alert("Neispravno korisnicko ime ili lozinka!")
    }
}

export const logout = () => {
    window.localStorage.removeItem("jwt")
    window.location.replace("http://localhost:3000")
}