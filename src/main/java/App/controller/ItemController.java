package App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import App.model.Item;
import App.service.ItemService;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/homepage/id/{itemId}")
    public @ResponseBody String getItemById(@PathVariable Integer itemId){
    	
    	String result = itemService.getItemById(itemId);
        return result;
    }

    @RequestMapping(value="/homepage/addItem",method = RequestMethod.POST) 
    public void addItem( @RequestParam(value="name") String name , @RequestParam(value="price") Integer price
    					,@RequestParam(value="quantity") Integer quantity ) {
    	System.out.println(name);
    	System.out.println(quantity);
    	System.out.println(price);
    	itemService.addItem(new Item(name,quantity,price));
	}
    
    @DeleteMapping("/homepage/delete/{itemId}")
    public void deleteItemById(@PathVariable Integer itemId){
    	
    	itemService.deleteItemById(itemId);
    	
    }

    @PutMapping("/homepage/update/{itemId}/quantity/{itemQuantity}")
    public void updateItemById(@PathVariable Integer itemId,@PathVariable Integer itemQuantity){
        itemService.updateItemById(itemId,itemQuantity);
    }
    
    @RequestMapping("/homepage/allitems") // Map ONLY GET Requests
	public @ResponseBody String getAllItems () {

    	String result = itemService.findAll();
        return result;	    
	}
    @RequestMapping("/homepage")
	public String getHomePage() {	
    	return "homepage/homepage.html";
	}
}
