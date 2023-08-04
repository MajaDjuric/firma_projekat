//FRONT 
--------------------------------------------------- slanje jednog param kroz requestBody(na backu mapa)

const kupiVino = (idVina) => {

    const requestBody = {
        brojFlasa: brojFlasa
    }

    Axios.put('/vina/' + idVina + '/kupi', requestBody)
        .then(res => {
            console.log(res);
            console.log(requestBody)
            alert('Uspesna kupovina')
            window.location.reload();
        })
        .catch(error => {
            console.log(error);
            if (vino.brojDostupnihFlasa < brojFlasa) {
                alert('Nema dovoljno flasa na stanju!')
            } else {
                alert('Doslo je do greske, pokusajte ponovo!');
            }
        });
}

//BACK
@PutMapping(value = "/{id}/kupi")
public ResponseEntity < Void > naruciVino(@PathVariable Long id, @RequestBody Map < String, Integer > requestBody){
		int brojFlasa = requestBody.get("brojFlasa");
		Vino vino = vinoService.findOne(id);
    if (vino.getBrojDostupnihFlasa() < brojFlasa) {
        return new ResponseEntity <> (HttpStatus.BAD_REQUEST);
    } else {
        vino.setBrojDostupnihFlasa(vino.getBrojDostupnihFlasa() - brojFlasa);
		Vino updated = vinoService.update(vino);
        return new ResponseEntity <> (HttpStatus.OK);
    }
}
}

/////////////////////////////////////////////////////////////////////////////////////////////////

// BACK
------------------------------------------------------slanje celog dto objekta sa izmenjenim parametrom
@PreAuthorize("hasRole('ADMIN')")
@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity < VinoDto > update(@PathVariable Long id, @Validated @RequestBody VinoDto vinoDto){
    if (!id.equals(vinoDto.getId())) {
        return new ResponseEntity <> (HttpStatus.BAD_REQUEST);
    }
    Vino vino = toVino.convert(vinoDto);
    Vino izmenjenoVino = vinoService.update(vino);
    return new ResponseEntity <> (toVinoDto.convert(izmenjenoVino), HttpStatus.OK);
}


//FRONT 
--------------------------------- vino u state
                                    vrednosti iz propsa / izmenjen
                                    param kroz dto

const izmeni = (idVina) => {

    const vinoDto = {
        id: vino.id,
        ime: vino.ime,
        opis: vino.opis,
        godinaProizvodnje: vino.godinaProizvodnje,
        brojDostupnihFlasa: parseInt(vino.brojDostupnihFlasa) + parseInt(brojFlasa),
        cenaFlase: vino.cenaFlase,
        tipVinaIme: vino.tipVinaIme,
        tipVinaId: vino.tipVinaId,
        vinarijaIme: vino.vinarijaIme,
        vinarijaId: vino.vinarijaId
    }

    Axios.put('/vina/' + idVina, vinoDto)
        .then(res => {
            console.log(res)
            console.log(vino.brojDostupnihFlasa)
            alert('Uspesno narucivanje!')
            window.location.reload()
        })
        .catch(error => {
            console.log(error)
            alert('Doslo je do greske, pokusajte ponovo!')
        })
}

////////////////////////////////////////////////////////////////
//BACK
--------------------------------------------------------- slanje parametraz kroz requestParam(GETmapping)

@PreAuthorize("hasRole('ROLE_KORISNIK')")
@GetMapping(value = "/{id}/naruci")
public ResponseEntity < Void > naruciVino(@PathVariable Long id, @RequestParam int brojFlasa){
		Vino vino = vinoService.findOne(id);
    if (vino.getBrojDostupnihFlasa() < brojFlasa) {
        return new ResponseEntity <> (HttpStatus.BAD_REQUEST);
    } else {
        vino.setBrojDostupnihFlasa(vino.getBrojDostupnihFlasa() - brojFlasa);
		Vino updated = vinoService.update(vino);
        return new ResponseEntity <> (HttpStatus.OK);
    }
}
}


//FRONT
const kupiVino = (idVina) => {

    const config = {
        params: {
            brojFlasa: brojFlasa
        }
    }

    Axios.get('/vina/' + idVina + '/naruci', config)
        .then(res => {
            console.log(res);
            alert('Uspesna kupovina')
            window.location.reload();
        })
        .catch(error => {
            console.log(error);
            if (vino.brojDostupnihFlasa < brojFlasa) {
                alert('Nema dovoljno flasa na stanju!')
            } else {
                alert('Doslo je do greske, pokusajte ponovo!');
            }
        });
}