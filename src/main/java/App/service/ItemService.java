package App.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import App.dao.ItemRepository;
import App.model.Item;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Cacheable(value = "itemCache",key = "#itemId", unless = "#result==null")
    public String getItemById(Integer itemId){
        System.out.println("Retrieving item by Id: " + itemId);
        final ArrayList<Item> list = new ArrayList<Item>();
        list.add(itemRepository.findById(itemId).get());
        return new Gson().toJson(list);
    }

    @CacheEvict(value = "itemCache", key = "#itemId")
    public void deleteItemById(Integer itemId){
    	
    	System.out.println("deleting item by Id: " + itemId);
        itemRepository.deleteById(itemId);
        
    }

    @CachePut(value = "itemCache", key = "#itemId")
    public void updateItemById(Integer itemId, int quantity){
    	

    	System.out.println("updating item by Id: " + itemId + " with quantity: "+quantity);
    	Item item = itemRepository.findById(itemId).get();
    	
        if(null != item){
            item.setQuantity(quantity);
            itemRepository.save(item);
            System.out.println("update succeded");
        }
        else
        {
        	System.out.println("Id pointing to non existing item");
        }
       
    }
    
    @CachePut(value = "itemCache", key = "#itemId")
    public void addItem(Item item) {
    	if(item != null)
    		{
    		itemRepository.save(item);
    		System.out.println("creating item succeded");
    		}
    	else
    		System.out.println("creating item failed");
    }
    
    @CachePut(value = "itemCache")
    public String findAll(){
    	final ArrayList<Item> list = new ArrayList<Item>();
	    Iterable<Item> iterator = itemRepository.findAll();
	    iterator.forEach(e -> {
			list.add(e);
		});
	    
	    return new Gson().toJson(list);

    }
}
