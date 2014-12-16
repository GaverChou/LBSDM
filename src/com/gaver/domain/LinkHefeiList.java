package com.gaver.domain;

import java.util.ArrayList;

import com.gaver.util.TextUtil;

public class LinkHefeiList {

	private ArrayList<LinkHefei> linkHefeis = new ArrayList<LinkHefei>();
	
	public ArrayList<LinkHefei> getLinkHefeis() {
		return linkHefeis;
	}

	public static LinkHefeiList parseLinkHefeisByTxt(String data){
		if(!TextUtil.textValid(data)){
			return null;
		}
		LinkHefeiList list = new LinkHefeiList();
		String[] loads = data.split("\n");
		for (int i = 0; i < loads.length; i++) {
			LinkHefei linkHefei = LinkHefei.parseLinkHefeiByTxt(loads[i]);
			list.linkHefeis.add(linkHefei);
		}
		return list;
	}
}
