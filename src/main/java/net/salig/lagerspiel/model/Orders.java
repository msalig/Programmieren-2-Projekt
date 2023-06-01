package net.salig.lagerspiel.model;

public class Orders {
	private final String[][] data = {
			{"1","Einlagerung", "assets/Papier","Weiss","A4","200"},
			{"2","Einlagerung", "assets/Papier","Blau","A5","300"},
			{"3","Einlagerung", "assets/Holz","Kiefer","Bretter","200"},
			{"4","Einlagerung", "assets/Holz","Buche","Balken","500"},
			{"5","Einlagerung", "assets/Holz","Eiche","Scheit","200"},
			{"6","Einlagerung", "assets/Papier","Blau","A5","200"},
			{"7","Einlagerung", "assets/Papier","Blau","A5","200"},
			{"8","Einlagerung", "assets/Stein","Marmor","Mittel","400"},
			{"9","Einlagerung", "assets/Stein","Granit","Schwer","500"},
			{"10","Einlagerung", "assets/Stein","Sandstein","Leicht","200"},
			{"11","Auslagerung", "assets/Papier","Blau","A5","1000"},
			{"12","Auslagerung", "assets/Holz","Eiche","Scheit","1200"},
			{"13","Auslagerung", "assets/Stein","Marmor","Mittel","1000"},
			{"14","Auslagerung", "assets/Papier","Weiss","A5","1500"},
			{"15","Einlagerung", "assets/Holz","Eiche","Balken","400"},
			{"16","Einlagerung", "assets/Holz","Eiche","Scheit","600"},
			{"17","Einlagerung", "assets/Holz","Buche","Scheit","200"},
			{"18","Einlagerung", "assets/Stein","Granit","Leicht","400"},
			{"19","Einlagerung", "assets/Papier","Blau","A3","200"},
			{"20","Einlagerung", "assets/Papier","Blau","A5","200"},
			{"21","Einlagerung", "assets/Holz","Eiche","Scheit","600"},
			{"22","Einlagerung", "assets/Holz","Buche","Balken","600"},
			{"23","Einlagerung", "assets/Stein","Sandstein","Schwer","200"},
			{"24","Einlagerung", "assets/Stein","Granit","Schwer","600"},
			{"25","Einlagerung", "assets/Holz","Buche","Bretter","400"},
			{"26","Einlagerung", "assets/Holz","Buche","Scheit","200"},
			{"27","Auslagerung", "assets/Holz","Buche","Scheit","1000"},
			{"28","Auslagerung", "assets/Papier","Blau","A5","1200"},
			{"29","Auslagerung", "assets/Stein","Granit","Schwer","1500"},
			{"30","Auslagerung", "assets/Holz","Buche","Balken","1000"},
			{"31","Auslagerung", "assets/Stein","Sandstein","Schwer","1300"},
			{"32","Einlagerung", "assets/Stein","Granit","Schwer","400"},
			{"33","Einlagerung", "assets/Stein","Marmor","Mittel","600"},
			{"34","Einlagerung", "assets/Stein","Granit","Leicht","400"},
			{"35","Einlagerung", "assets/Stein","Granit","Leicht","400"},
			{"36","Einlagerung", "assets/Papier","Weiss","A4","400"},
			{"37","Einlagerung", "assets/Stein","Granit","Leicht","400"},
			{"38","Einlagerung", "assets/Holz","Buche","Bretter","600"},
			{"39","Einlagerung", "assets/Holz","Kiefer","Bretter","600"},
			{"40","Einlagerung", "assets/Stein","Sandstein","Leicht","400"},
			{"41","Auslagerung", "assets/Papier","Weiss","A4","1000"},
			{"42","Auslagerung", "assets/Stein","Marmor","Mittel","1200"},
			{"43","Auslagerung", "assets/Holz","Buche","Bretter","1100"},
			{"44","Auslagerung", "assets/Papier","Weiss","A4","1500"},
			{"45","Auslagerung", "assets/Holz","Kiefer","Bretter","1000"},
			{"46","Auslagerung", "assets/Stein","Sandstein","Leicht","1200"},
			{"47","Auslagerung", "assets/Holz","Kiefer","Bretter","1100"},
			{"48","Einlagerung", "assets/Papier","Gruen","A4","400"}
		};
	private int lastOrder = -1;
	
	public String[] getNextOrder() {
		lastOrder++;
		return data[lastOrder];
	}
	
	public static void main(String[] args) {
		// just for testing purpose
		Orders orders = new Orders();
		String[] order = orders.getNextOrder();
		for(String cell: order) {
			System.out.print(cell);
			System.out.print(";");
		}
		System.out.println(); // new line
		order = orders.getNextOrder();
		for(String cell: order) {
			System.out.print(cell);
			System.out.print(";");
		}
	}
}
