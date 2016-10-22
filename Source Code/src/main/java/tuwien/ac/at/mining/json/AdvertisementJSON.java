package tuwien.ac.at.mining.json;

import java.io.Serializable;

import tuwien.ac.at.db.document.model.entity.Advertisement;

/**
 * @author alisanli
 *
 */

public class AdvertisementJSON implements Serializable {

	private static final long serialVersionUID = 4205175785839715412L;

	private String name;
	private String words;

	public AdvertisementJSON() {
	}

	public AdvertisementJSON(String name, String words) {
		this.name = name;
		this.setWords(words);
		
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Advertisement getMongoDBEntity(){
	
		Advertisement advertisement=new Advertisement();
		
		advertisement.setName(getName());
		advertisement.setTopic(words);
		
		//List<String> words=Arrays.asList(getWords().split(","));
		 
		
		
		return advertisement; 
	
	}
	
	
	public String toString() {
		return "AdvertisementJSON{name:"+name+" , words : "+words+"]";
	};
	

}
