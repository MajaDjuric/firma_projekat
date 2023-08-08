package Projekat.Firma.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import Projekat.Firma.model.Dispozicija;

public interface DispozicijaService {

	Dispozicija findOne (Long id);
	Page<Dispozicija> search (LocalDate datumIsporuke, int pageNo);
	Dispozicija save (Dispozicija dispozicija);
	Dispozicija update (Dispozicija dispozicija);
	void delete (Long id);
	Page<Dispozicija> findAll (int pageNo);
}
