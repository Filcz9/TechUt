package ug.fczyz.techut.zad03.jdbcdemo.util;

import ug.fczyz.techut.zad03.jdbcdemo.service.ComputerGameServiceJDBC;

public class Start {
	public static void main(String[] args) {
		ComputerGameServiceJDBC ps = new ComputerGameServiceJDBC();
			ps.deleteAllComputerGames();
		System.out.println("koniec");
	}
}
