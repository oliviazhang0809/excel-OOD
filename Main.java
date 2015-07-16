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
		b6.setValue(new BigDecimal(2));

		Cell c6 = sheet.createCell("C6");
		c6.setValue(new BigDecimal(2));

		Cell d6 = sheet.createCell("D6");
		d6.setFormula("B6+C6");
		System.out.println(d6.name + "=" + sheet.getCellValue("D6"));

		Cell e6 = sheet.createCell("E6");
		e6.setFormula("D6*2");
		System.out.println(e6.name + "=" + sheet.getCellValue("E6"));

		// change b6
		b6.setValue(new BigDecimal(4));
		// System.out.println(d6.name + "=" + sheet.getCellValue("D6"));
		System.out.println(e6.name + "=" + sheet.getCellValue("E6"));

		d6.setFormula("4+C6");
		c6.setValue(new BigDecimal(3));
		System.out.println(e6.name + "=" + sheet.getCellValue("E6"));

	}

}
