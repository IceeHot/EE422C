/**
 * Phase A <dkm989><bma862>
 * Phase B <bma862><dkm989>
 */

package pMap.phaseB;

import java.util.Map;

/**
 * Map.Entry. Assume that key is an integer and value is a string.
 */

public class Entry implements Map.Entry<Integer,String> {
	
	private Integer key;
	private String val;
	
	public Entry(Integer key, String value) {
		this.key = key;
		this.val = value;
	}
	
	public Integer getKey() {
		return this.key;
	}
	
	public String getValue() {
		return this.val;
	}
	
	public String setValue(String value) {
		this.val = value;
		return value;
	}


}
