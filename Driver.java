//Author:	Nick Corrado
//Date:		09/11/16
//Desc:		Driver class for Level 1 of Project Legion
//Title:	Driver

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		int sizeX = 10;
//		int sizeY = 20;
//		ArrayList<Pair<Integer, Integer>> nodes = new ArrayList<Pair<Integer, Integer>>();
//		ArrayList<Pair<Integer, Integer>> dirs = new ArrayList<Pair<Integer, Integer>>();
//		System.out.println("Hello world");
//		
//		//this defines the topology of the board
//		//there's got to be a better way to do this
//		dirs.add(new Pair<Integer, Integer>(-1, -1));
//		dirs.add(new Pair<Integer, Integer>(-1, 0));
//		dirs.add(new Pair<Integer, Integer>(-1, 1));
//		dirs.add(new Pair<Integer, Integer>(0, -1));
//		dirs.add(new Pair<Integer, Integer>(0, 1));
//		dirs.add(new Pair<Integer, Integer>(1, -1));
//		dirs.add(new Pair<Integer, Integer>(1, 0));
//		dirs.add(new Pair<Integer, Integer>(1, 1));
//		
//		//this defines the nodes
//		for (int i = 0; i < sizeX; i++) {
//			for (int j = 0; j < sizeY; j++) {
//				nodes.add(new Pair<Integer, Integer>(i, j));
//			}
//		}
//		
//		//test; if the fourth guy has the fifth guy as one of his neighbors, print message
//		if (neighbors(nodes.get(4), dirs).contains(nodes.get(5))) {
//			System.out.println("He has a neighbor!");
//		}
//		
//		//now for the real test! this is the edge between the first and second nodes in the game, and second and third
//		//Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> edge1 = new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(nodes.get(0), nodes.get(1));
//		//Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> edge2 = new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(nodes.get(1), nodes.get(2));
//		ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> edges = new ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>();
//		//edges.add(edge1);
//		//edges.add(edge2);
//		
//		for (int i = 0; i < sizeX; i++) {
//			for (int j = 0; j < sizeY; j++) {
//				edges.add(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(nodes.get(i*j+i), nodes.get(j)));
//			}
//		}
//		
//		//fills edges list
//		for (Pair n : nodes) {
//			@SuppressWarnings("unchecked")
//			ArrayList<Pair<Integer, Integer>> neighbors = neighbors(n, dirs);
//			for (Pair ne : neighbors) {
//				edges.add(new Pair(n, ne));
//			}
//		}
//		
//		//prints edges
//		for (Pair e : edges) {
//			System.out.println(e);
//		}
		
		Board board = new Board(1200, 800);
		
	}
	
	public static ArrayList<Pair<Integer, Integer>> neighbors(Pair<Integer, Integer> node, ArrayList<Pair<Integer, Integer>> dirs) {
		ArrayList<Pair<Integer, Integer>> neighbors = new ArrayList<Pair<Integer, Integer>>();
		
		//doesn't work at all???
		for (Pair<Integer, Integer> d : dirs) {
			neighbors.add(new Pair<Integer, Integer>(node.getX() + d.getX(), node.getY()+d.getY()));
		}
		
		return neighbors;
	}

}
