package com.adorgolap.skiplist;

public class SkipItem {
	int key;
	SkipItem previous, next, up, down;

	public SkipItem() {

	}

	public SkipItem(int key) {
		this.key = key;
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
			return "<+INF>";
		} else if (key == Integer.MIN_VALUE) {
			return "<-INF>";
		} else {
			return "<" + key + ">";
		}
	}

	public int getNextKey() {
		return this.next.key;
	}
}
