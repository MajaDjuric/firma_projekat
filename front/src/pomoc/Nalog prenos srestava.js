// slanje vise parametara BACK

@PutMapping(value = "/nalog", consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity < Void > nalog(@RequestBody Map < String, String > requestBody){
    ----------------------
        
        if (requestBody == null) {
        return new ResponseEntity <> (HttpStatus.BAD_REQUEST);
    }
        String uplatilacRacun = requestBody.get("uplatilacBrojRacuna"); --------------------------
        // Racun uplatilac = racunService.findOneByBrojRacuna(uplatilacRacun);

        String primalcRacun = requestBody.get("primalacBrojRacuna"); --------------------------
            // Racun primalac = racunService.findOneByBrojRacuna(primalcRacun);

            int iznosInt = Integer.parseInt(requestBody.get("iznos")); --------------------------


//FRONT
const [uplatilacRacun, setUplatilacRacun] = useState('')
    const [primalacRacun, setPrimalacRacun] = useState('')
    const [iznoss, setIznoss] = useState(0)


    const nalog = () => {

        const requestBody = {                   ------------------------------------
            uplatilacBrojRacuna: uplatilacRacun,
                primalacBrojRacuna: primalacRacun,
                    iznos: iznoss
    }

    Axios.put('/racuni/nalog', requestBody)
        .then(res => {
            console.log(res);
            alert('Uspesno izvrsenje naloga')
            navigate('/racuni')
        })
        .catch(error => {
            console.log(error);
            alert('Doslo je do greske, pokusajte ponovo!');

        });
}