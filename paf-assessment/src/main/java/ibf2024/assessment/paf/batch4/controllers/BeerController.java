package ibf2024.assessment.paf.batch4.controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.BeerOrder;
import ibf2024.assessment.paf.batch4.models.BeerOrderDetail;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.services.BeerService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class BeerController {

	@Autowired
	private BeerService beerSvc;

	//TODO Task 2 - view 0
	@GetMapping
	public ModelAndView getStyles(@ModelAttribute Style style) {
		try {
			ModelAndView mav = new ModelAndView("view0.html");
			List<Style> getBeerStyles = beerSvc.getStyles();
			mav.addObject("styles", getBeerStyles);
			mav.addObject("name", style.getName());
			mav.addObject("beerCount", style.getBeerCount());
			mav.setStatus(HttpStatus.OK);
			return mav;
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("error.html");
            mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return mav;
		}
	}

	//TODO Task 3 - view 1
	@GetMapping(path="/beer/style/{id}")
	public ModelAndView getBreweriesByBeer(
		@PathVariable("id") int styleId, 
		@RequestParam("styleName") String styleName,
		@ModelAttribute Beer beer) {
		try {
			ModelAndView mav = new ModelAndView("view1.html");
			List<Beer> getBreweriesByBeerList = beerSvc.getBreweriesByBeer(styleId);
			// System.out.println(getBreweriesByBeerList);
			mav.addObject("styleName", styleName);
			mav.addObject("beerList", getBreweriesByBeerList);
			mav.addObject("beerName", beer.getBeerName());
			mav.addObject("beerDescription", beer.getBeerDescription());
			mav.addObject("breweryName", beer.getBreweryName());
			mav.setStatus(HttpStatus.OK);
			return mav;
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("no-beers-found.html");
			mav.setStatus(HttpStatus.BAD_REQUEST);
			return mav;
		}
	}

	//TODO Task 4 - view 2
	@GetMapping(path="/beer/style/{id}/brewery/{breweryName}")
	public ModelAndView getBeersFromBrewery(
		@PathVariable("id") int styleId, 
		@PathVariable("breweryName") String breweryName, 
		@ModelAttribute Brewery brewery){

		try {
			// System.out.println(breweryName);
			ModelAndView mav = new ModelAndView("view2.html");
			Optional<Brewery> getBeersFromBrewery = beerSvc.getBeersFromBrewery(breweryName);
			// System.out.println(getBeersFromBrewery);
			List<Beer> beerList = brewery.getBeers();
			mav.addObject("brewery", getBeersFromBrewery.get());

			// BeerOrder beerOrder = new BeerOrder();
			BeerOrderDetail beerOrderDetail = new BeerOrderDetail();

			mav.addObject("orderdetail", beerOrderDetail);
			mav.addObject("quantity", beerOrderDetail.getQuantity());
			for (Beer beer : beerList) {
				mav.addObject("beerId", beer.getBeerId());
				mav.addObject("beerName", beer.getBeerName());
				mav.addObject("beerDescription", beer.getBeerDescription());
			}
			return mav;
		} catch (Exception e) {
			// TODO: handle exception
			ModelAndView mav = new ModelAndView("brewery-not-found.html");
			mav.setStatus(HttpStatus.BAD_REQUEST);
			return mav;
		}
	}

	
	//TODO Task 5 - view 2, place order
	@PostMapping(path="/brewery/{breweryId}/order")
	public ModelAndView postOrder(
		// @ModelAttribute Beer beer,
		// @ModelAttribute Brewery brewery,
		@PathVariable("breweryId") int breweryId,
		@RequestBody MultiValueMap<String,String> payload) {
		// System.out.println(beer);
		System.out.println(payload);
		System.out.println(breweryId);

		try {
			ModelAndView mav = new ModelAndView();

			String orderId = UUID.randomUUID().toString().substring(0, 8);
			Date orderDate = new Date();
			List<BeerOrderDetail> beerOrderDetails = new LinkedList<>();

			List<String> quantityList = payload.get("quantity");

			BeerOrder beerOrder = new BeerOrder();
			beerOrder.setOrderId(orderId);
			beerOrder.setDate(orderDate);
			beerOrder.setBreweryId(breweryId);
			beerOrder.setOrders(beerOrderDetails);

			beerSvc.placeOrder(beerOrder);

			mav.setViewName("view3");
			mav.addObject("orderId", orderId);
			mav.setStatus(HttpStatusCode.valueOf(200));
			return mav;
		} catch (Exception e) {
			// TODO: handle exception
			ModelAndView mav = new ModelAndView("error.html");
			mav.setStatus(HttpStatusCode.valueOf(404));
			return mav;
		}
	}
}
