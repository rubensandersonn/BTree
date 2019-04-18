/*
	Rubens Anderson, 362984 - Universidade Federal do Ceara
	Ciencia da cmputacao
	implmentacao arvore B+ (incompleto)

	No interno da arvore
*/

import java.util.*;

class IntNode extends Node{

	public IntNode(int N){
		super.N = N;
	}
	public IntNode(int N, IntNode pai){
		super.N = N;
		super.pai = pai;
	}
	
	public String twoString(String tabs) {
		return "IntNode " + super.twoString(tabs);
	}
}