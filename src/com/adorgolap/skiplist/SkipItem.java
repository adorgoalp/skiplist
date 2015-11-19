package com.adorgolap.skiplist;

public class SkipItem {
	int key, width;
	SkipItem previous, next, up, down;

	public SkipItem() {

	}

	public SkipItem(int key) {
		this.key = key;
	}

	public SkipItem(int key, int distance) {
		this.key = key;
		this.width = distance;
	}

	public SkipItem(SkipItem item) {
		this.key = item.key;
		this.next = item.next;
		this.previous = item.previous;
		this.up = item.up;
		this.down = item.down;
	}

	@Override
	public String toString() {
		if (key == Integer.MAX_VALUE) {
			return "-+INF>";
		} else if (key == Integer.MIN_VALUE) {
//			 return "<-INFw"+width+"-";
			return "<-INF-";
		} else {
//			return "-"+key+"w"+width+"-";
			int digit = (int) Math.ceil(Math.log(key));
			if (digit == 1 || digit == 0) {
				return "---" + key + "--";
			} else {
				return "--" + key + "--";
			}
		}
	}

	public int getNextKey() {
		// System.out.println("gnk" + this);
		return this.next.key;
	}

}
