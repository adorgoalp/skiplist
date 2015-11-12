package com.adorgolap.skiplist;

import java.util.ArrayList;

public class SkipListSimulator {
	private static final int M_INF = Integer.MIN_VALUE, P_INF = Integer.MAX_VALUE;
	ArrayList<ArrayList<SkipItem>> skipList = new ArrayList<ArrayList<SkipItem>>();
	ArrayList<SkipItem> topList = new ArrayList<SkipItem>();
	SkipItem startItem;

	public SkipListSimulator() {
		createEmptySkipList();
		printList();
		skipInsert(2);
	}

	private void skipInsert(int key) {
		SkipItem item = new SkipItem(key);
		SkipItem tempItem = new SkipItem(startItem);
		while (tempItem.getNextKey() < key) {
			tempItem = tempItem.next;
		}
		item.next = tempItem.next;
		tempItem.next = item;
	}

	private void printList() {
		ArrayList<SkipItem> list = skipList.get(0);
		SkipItem temp = list.get(0);
		System.out.println(temp);
		while(temp.getNextKey()!=P_INF)
		{
			temp = temp.next;
			System.out.print(temp+" ");
		}
		
	}

	private void createEmptySkipList() {
		startItem = new SkipItem(M_INF);
		startItem.next = new SkipItem(P_INF);
		topList.add(startItem);
		topList.add(startItem.next);
		skipList.add(topList);
	}
}
