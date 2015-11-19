package com.adorgolap.skiplist;

import java.util.Random;

public class SkipListController {
	Random random = new Random();
	private static final int M_INF = Integer.MIN_VALUE, P_INF = Integer.MAX_VALUE;
	SkipItem startItem, head, tail;
	int totalInserted = 0;
	public SkipListController() {
		createEmptySkipList();

	}

	public void skipInsert(int key) {
		SkipItem item = new SkipItem(key, 1);
		SkipItem entryPoint = skipSearch(key);
		item.next = entryPoint.next;
		item.previous = entryPoint;
		entryPoint.next = item;
		int level = 1;
		SkipItem tempItem = item;
		while (random.nextBoolean()) {

			SkipItem newSkipItem = new SkipItem(key);
			int height = getHeight();
			SkipItem newEntryPoint = getNewEntryPoint(tempItem);
			newSkipItem.next = newEntryPoint.next;
			newSkipItem.previous = newEntryPoint;
			newSkipItem.down = tempItem;
			tempItem.up = newSkipItem;
			tempItem = newSkipItem;
			if (level + 1 == height) {
				createNewLevelAboveOfLevel(level);
			}
			level++;
		}
		head.width = totalInserted+1;
	}
	public void skipInsertT(int key) {
		totalInserted++;
		
		SkipItem item = new SkipItem(key, 1);
		SkipItem entryPoint = skipSearchUpdate(key);
		item.next = entryPoint.next;
		item.previous = entryPoint;
		entryPoint.next = item;
		int level = 1;
		SkipItem tempItem = item;
		while (random.nextBoolean()) {
			SkipItem newSkipItem = new SkipItem(key);
			int height = getHeight();
			SkipItem newEntryPoint = getNewEntryPointAndtravelDistance(tempItem);
			System.out.println("inserting " + key +" at level "+level+" distance to entry " + distance);
			newSkipItem.next = newEntryPoint.next;
			newSkipItem.previous = newEntryPoint;
			newSkipItem.down = tempItem;
			//update distance
			newSkipItem.width = newEntryPoint.width-distance;
			newEntryPoint.width = distance;
			if (level + 1 == height) {
				createNewLevelAboveOfLevel(level);
			}
			tempItem.up = newSkipItem;
			tempItem = newSkipItem;
			level++;
		}
		head.width = totalInserted +1;
	}

	private int getHeight() {
		int h = 1;
		SkipItem temp = head;
		while (temp.down != null) {
			h++;
			temp = temp.down;
		}
		return h;
	}

	private SkipItem getNewEntryPoint(SkipItem item) {
		while (item.previous.up == null) {
			item = item.previous;
		}
		return item.previous.up;
	}

	int distance;

	private SkipItem getNewEntryPointAndtravelDistance(SkipItem item) {
		item = item.previous;
		distance = item.previous.width;
		while (item.up == null) {
			distance += item.previous.width;
			System.out.println("dist " + distance);
			item = item.previous;
		}
		return item.previous.up;
	}

	private void createNewLevelAboveOfLevel(int level) {
		SkipItem s = new SkipItem(M_INF);
		SkipItem e = new SkipItem(P_INF);
		s.next = e;
		e.previous = s;
		SkipItem downListStart = head;
		SkipItem downListEnd = tail;
		downListStart.up = s;
		downListEnd.up = e;
		s.down = downListStart;
		e.down = downListEnd;
		s.width = downListStart.width;
		head = s;
		tail = e;
		head.width = totalInserted+1;
	}

	public void printList() {
		String[] data = new String[500];
		for (int i = 0; i < data.length; i++) {
			data[i] = "";
		}
		SkipItem tempHorizontal = startItem;
		SkipItem tempVertical = startItem;
		int level = 0;
		while (true) {
			level = 0;
			data[level++] += tempHorizontal;
			tempVertical = tempHorizontal.up;
			while (tempVertical != null) {
				data[level++] += tempVertical;
				tempVertical = tempVertical.up;
			}
			for (; level < 50; level++) {
				data[level] += "------";
			}
			tempHorizontal = tempHorizontal.next;
			if (tempHorizontal == null) {
				break;
			}
		}
		for (int i = data.length - 1; i >= 0; i--) {
			if (data[i].equals("") || data[i].charAt(0) == '-') {
			} else {
				System.out.println(i + data[i]);
			}
		}
		System.out.println();
	}

	private SkipItem skipSearch(int key) {
		SkipItem p = head;
		while (true) {
			if (p.getNextKey() <= key) {
				p = p.next;
			} else {
				if (p.down != null) {
					p = p.down;
				} else {
					return p;
				}
			}
		}
	}
	private SkipItem skipSearchUpdate(int key) {
		SkipItem p = head;
		while (true) {
			if (p.getNextKey() <= key) {
				p = p.next;
			} else {
				if (p.down != null) {
					p.down.width +=1;
					p = p.down;
				} else {
					startItem.width = 1;
					return p;
				}
			}
		}
	}

	private void createEmptySkipList() {
		startItem = new SkipItem(M_INF, 1);
		SkipItem endItem = new SkipItem(P_INF, -1);
		startItem.next = endItem;
		endItem.previous = startItem;
		head = startItem;
		tail = endItem;
		createNewLevelAboveOfLevel(0);
	}

	public int indexOf(int key) {
		SkipItem temp = startItem;
		int index = -1;
		while (true) {
			if (temp.key == key) {
				return index;
			} else {
				index++;
				temp = temp.next;
			}
			if (temp.key == P_INF) {
				break;
			}
		}
		return -1;
	}
}
