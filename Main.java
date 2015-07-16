import java.math.BigDecimal;

/**
 * Excel
 * 
 * @author tzhang1
 *
 */
public class Main {
	final static Table sheet = new Table();

	public static void main(String[] args) {
		Cell b6 = sheet.createCell("B6");
		b6.setValue(new BigDecimal(1));

		Cell c6 = sheet.createCell("C6");
		c6.setValue(new BigDecimal(2));

		Cell d6 = sheet.createCell("D6");
		d6.setFormula("B6+C6");
		System.out.println(d6.name + "=" + d6.evaluate());

		Cell e6 = sheet.createCell("E6");
		e6.setFormula("D6*2");
		System.out.println(e6.name + "=" + e6.evaluate());

		b6.setValue(new BigDecimal(4));
		System.out.println(d6.name + "=" + d6.evaluate());
		System.out.println(e6.name + "=" + e6.evaluate());
	}

}
