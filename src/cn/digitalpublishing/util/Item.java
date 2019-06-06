package cn.digitalpublishing.util;

public enum Item {
	
	KEY("key"),VAL("val");
	
	private final String item;
	
	private Item(String _itme){
		this.item=_itme;
	}
	
	public String getName(){
		return this.item;
	}

}
