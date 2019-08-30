package com.bitgroupware.approval;

public class Test {
	public static void main(String[] args) {
		String[] rank = new String[] {"사원","팀장"};
		String[] sign = new String[] {"O",null};
		String[] name = new String[] {"한솔","이슬"};
		
		for(int i=0; i<rank.length; i++) {
			System.out.print(rank[i]+" ");
			System.out.print(sign[i]);
			System.out.print(name[i]);
			System.out.println();
		}
	}
}
