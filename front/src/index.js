import React from "react";
import { createRoot } from 'react-dom/client';
import { Route, Link, HashRouter as Router, Routes } from "react-router-dom";
import { Navbar, Nav, Container, Button } from "react-bootstrap";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import 'bootstrap/dist/css/bootstrap.min.css';
import { logout } from "./services/auth";
import { Navigate } from "react-router-dom/dist";
import Login from "./components/authorization/Login";
import Dodavanje from "./components/Roba/Dodavanje";
import Roba from "./components/Roba/Roba";
import Ulazi from "./components/Ulazi/Ulazi";
import DodavanjeRobeUlaz from "./components/Ulazi/DodavanjeRobeUlaz";
import DodavanjeUlaza from "./components/Ulazi/DodavanjeUlaza";
import Ulaz from "./components/Ulazi/Ulaz";
import Trebovanja from "./components/Trebovanja/Trebovanja";
import KupacDodavanje from "./components/Kupci/KupacDodavanje";
import IzmenaTrebovanja from "./components/Trebovanja/TrebovanjeIzmena";
import DodavanjeTrebovanja from "./components/Trebovanja/DodavanjeTrebovanja";
import Dispozicije from "./components/Dispozicije/Dispozicije";
import Dispozicija from "./components/Dispozicije/Dispozicija";



const App = () => {
  if (window.localStorage["jwt"]) {
    return (
      <>
        <Router>
          <Navbar expand bg="dark" variant="dark">
            {/* <Navbar.Brand as={Link} to="/">
                JWD64
            </Navbar.Brand> */}
            <Nav>
              <Nav.Link as={Link} to="/roba">Roba</Nav.Link>
              <Nav.Link as={Link} to="/ulazi">Ulazi</Nav.Link>
              <Nav.Link as={Link} to="/trebovanja">Trebovanja</Nav.Link>
              <Nav.Link as={Link} to="/dispozicije">Dispozicije</Nav.Link>
              <Button onClick={logout}>Logout</Button>
            </Nav>
          </Navbar>
          <Container style={{ paddingTop: "25px" }}>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/roba" element={<Roba />} />
              <Route path="/ulazi" element={<Ulazi />} />
              <Route path="/trebovanja" element={<Trebovanja />} />
              <Route path="/trebovanja/:id" element={<Trebovanja />} />
              <Route path="/trebovanje/dodavanje" element={<DodavanjeTrebovanja />} />
              <Route path="/trebovanje/izmena/:trebovanjeId" element={<IzmenaTrebovanja />} />
              <Route path="/trebovanje/izmena/:trebovanjeId/:dispozicijaId" element={<IzmenaTrebovanja />} />
              <Route path="/ulaz/:id" element={<Ulaz />} />
              <Route path="/roba/dodavanje" element={<Dodavanje />} />
              <Route path="/dispozicija/:id" element={<Dispozicija />} />
              <Route path="/dispozicije" element={<Dispozicije />} />
              <Route path="/kupci/dodavanje" element={<KupacDodavanje />} />
              <Route path="/ulaz/dodavanjeRobe" element={<DodavanjeUlaza />} />
              {/* <Route path="/vina/izmena/:id" element={<Izmena />} />             */}
              <Route path="*" element={<NotFound />} />
            </Routes>
          </Container>
        </Router>
      </>
    );
  } else {
    return (
      <>
        <Router>
          <Container style={{ paddingTop: "25px" }}>
            <Routes>
              <Route path="/" element={<Login />} />
              <Route path="*" element={<Navigate replace to="/login" />} />
            </Routes>
          </Container>
        </Router>
      </>
    );
  }
};

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
  <App />,
);
