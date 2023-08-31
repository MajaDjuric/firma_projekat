import { useState } from "react"
import { Row, Col, Form, Button } from "react-bootstrap"
import { login } from "../../services/auth"

const Login = () => {
    // Primer kako mozete da uradite "onInputChage" bez pomocne funkcije,
    // gde bi iz Form.Contole-a direktno pozivali setUsername i setPassword funkcije
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const handleKeyPress = (event) => {
        if (event.key === "Enter") {
            event.preventDefault()
            login(username, password)
        }
    }

    return (
        <Row className="justify-content-center">
            <Col md={6}>
                <Form>
                    <Form.Group>
                        <Form.Label>Korisnicko ime</Form.Label>
                        <Form.Control style={{ width: '400px' }} type="text" onChange={(e) => setUsername(e.target.value)} onKeyPress={handleKeyPress}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Lozinka</Form.Label>
                        <Form.Control style={{ width: '400px' }} type="password" onChange={(e) => setPassword(e.target.value)} onKeyPress={handleKeyPress}></Form.Control>

                    </Form.Group>
                </Form>
                <br />   <Button onClick={() => login(username, password)}>Login</Button>
            </Col>
        </Row>
    )
}

export default Login