package ibf2024.assessment.paf.batch4.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.BeerOrder;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.repositories.BeerRepository;
import ibf2024.assessment.paf.batch4.repositories.OrderRepository;

@Service
public class BeerService {

	@Autowired
	private BeerRepository beerRepo;

	@Autowired
	private OrderRepository orderRepo;

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(BeerOrder order) {
		// TODO: Task 5 
		orderRepo.insertOrder(order);
		return "";
	}

	public List<Style> getStyles() {
		return beerRepo.getStyles();
	}

	public List<Beer> getBreweriesByBeer(int styleId){
		return beerRepo.getBreweriesByBeer(styleId);
	}

	public Optional<Brewery> getBeersFromBrewery(String breweryName) {
		return beerRepo.getBeersFromBrewery(breweryName);
	}
}
