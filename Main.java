import java.math.BigDecimal;

public class Main {
	final static Table sheet = new Table();

	public static void main(String[] args) {
		Cell b6 = sheet.createCell(6, 2);
		b6.setValue(new BigDecimal(1));

		Cell c6 = sheet.createCell(6, 3);
		c6.setValue(new BigDecimal(2));

		Cell d6 = sheet.createCell(6, 4);
		d6.setFormula("B6+C6");
		System.out.println(d6.name + "=" + d6.evaluate());

		Cell e6 = sheet.createCell(6, 5);
		e6.setFormula("D6*2");
		System.out.println(e6.name + "=" + e6.evaluate());

		b6.setValue(new BigDecimal(4));
		System.out.println(d6.name + "=" + d6.evaluate());
		System.out.println(e6.name + "=" + e6.evaluate());
	}

}
