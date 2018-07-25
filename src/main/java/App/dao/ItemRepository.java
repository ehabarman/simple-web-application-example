package App.dao;

import org.springframework.data.repository.CrudRepository;
import App.model.Item;

public interface ItemRepository extends CrudRepository<Item,Integer> {
}
