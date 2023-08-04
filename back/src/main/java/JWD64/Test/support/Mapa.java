
//public class PrevoznikDto {   	DTO 
//		private Map<Long, String> linije = new HashMap<Long, String>();


//public interface LinijaService {		SERVICE
// 		List<Linija> findByIdIn (List<Long> ids);


//public class JpaLinijaService    JPA
//		@Override
//		public List<Linija> findByIdIn(List<Long> ids) {
//			return linijaRepository.findByIdIn(ids);
//}	

//public class PrevoznikDtoToPrevoznik     	SUPPORT
// 		List<Linija> linije = linijaService.findByIdIn(new ArrayList<>(dto.getLinije().keySet()));
//		prevoznik.setLinije(linije);

	
//public class PrevoznikToPrevoznikDto  		SUPPORT
//		List<Linija> linije = linijaService.findAll();
//		Map<Long, String> linijeMapa = new HashMap<Long, String>();
//		for (Linija linija : prevoznik.getLinije()) {
//			linijeMapa.put(linija.getId(), linija.getDestinacija());
//		}
//		dto.setLinije(linijeMapa);