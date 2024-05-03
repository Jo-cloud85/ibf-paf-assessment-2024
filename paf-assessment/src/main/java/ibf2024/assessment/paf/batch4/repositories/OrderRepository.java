package ibf2024.assessment.paf.batch4.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2024.assessment.paf.batch4.models.BeerOrder;

@Repository
public class OrderRepository {

	// TODO: Task 5

	@Autowired
	private MongoTemplate mongoTemplate;

	public void insertOrder(BeerOrder beerOrder) {
		mongoTemplate.insert(beerOrder, "orders");
	}
}
