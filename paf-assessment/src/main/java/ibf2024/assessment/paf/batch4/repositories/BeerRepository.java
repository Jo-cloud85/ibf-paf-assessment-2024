package ibf2024.assessment.paf.batch4.repositories;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Style;

@Repository
public class BeerRepository implements Queries {

	@Autowired
    JdbcTemplate jdbcTemplate;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// TODO: Task 2
		List<Style> result = new LinkedList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_BEER_STYLES_AND_COUNT);
		while (rs.next()) {
			Style style = new Style();
			style.setStyleId(rs.getInt("id"));
			style.setName(rs.getString("style_name"));
			style.setBeerCount(rs.getInt("beer_count"));
			result.add(style);
		}
		return Collections.unmodifiableList(result);
	}
		
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(int styleId) {
		// TODO: Task 3
		List<Beer> result = new LinkedList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_BEER_STYLE_LIST, styleId);
		while(rs.next()) {
			Beer beer = new Beer();
			beer.setBeerId(rs.getInt("id"));
			beer.setBeerName(rs.getString("beer_name"));
			beer.setBeerDescription(rs.getString("description"));
			beer.setBreweryId(rs.getInt("brewery_id"));
			beer.setBreweryName(rs.getString("brewery_name"));
			result.add(beer);
		}
		return Collections.unmodifiableList(result);
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	@SuppressWarnings("null")
	public Optional<Brewery> getBeersFromBrewery(String breweryName) {
		// TODO: Task 4
		SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_BEERS_FROM_BREWERY, breweryName);

		if(rs.next()) {
			Brewery brewery = new Brewery();
			brewery.setBreweryId(rs.getInt("brewery_id"));
			brewery.setName(rs.getString("name"));
			brewery.setAddress1(rs.getString("address1"));
			brewery.setAddress2(rs.getString("address2"));
			brewery.setCity(rs.getString("city"));
			brewery.setPhone(rs.getString("phone"));
			brewery.setWebsite(rs.getString("website"));
			brewery.setDescription(rs.getString("description"));
			brewery.setBeers(getBeerList(breweryName));
			return Optional.of(brewery);
		}
		return Optional.empty();
	}


	public List<Beer> getBeerList(String breweryName) {
		SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_BEERS_FROM_BREWERY, breweryName);
		List<Beer> beerList = new LinkedList<>();
		while (rs.next()) {
			Beer beer = new Beer();
			beer.setBeerName(rs.getString("beer_name"));
			beer.setBeerDescription(rs.getString("beer_description"));
			beerList.add(beer);
		}
		return beerList;
	}

	
}
