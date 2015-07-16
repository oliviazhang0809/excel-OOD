import java.math.BigDecimal;
import java.util.ArrayList;

public class Cell {
	String name;
	Table sheet;
	ArrayList<String> children;
	ArrayList<String> parents;

	BigDecimal value;
	Expression formula;
	boolean isFormula;

	public Cell(Table sheet, String name) {
		this.sheet = sheet;
		this.name = name;
		parents = new ArrayList<String>();
		children = new ArrayList<String>();
	}

	public void setValue(BigDecimal v) {
		clearDependencies();
		value = v;
		isFormula = false;
	}

	public void setFormula(String f) {
		clearDependencies();
		formula = new Expression(f);
		isFormula = true;

		// set as parents' child e.g. A1 + B1 = C1, A1 and B1 have C1 as child
		parents = getParents(f);
		for (String p : parents) {
			sheet.getCell(p).children.add(name);
		}
	}

	/**
	 * extract a list of variable while ignoring constants
	 */
	public ArrayList<String> getParents(String str) {
		ArrayList<String> res = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < str.length()) {
			char c = str.charAt(i);
			if (Character.isLetter(c) || Character.isDigit(c)) {
				sb.append(c);
			} else {
				if (Character.isLetter(sb.charAt(0))) {
					res.add(sb.toString());
				}
				sb = new StringBuilder();
			}
			i++;
		}
		if (Character.isLetter(sb.charAt(0))) {
			res.add(sb.toString());
		}
		return res;
	}

	/**
	 * Remove itself from parents' children and clear its own children
	 */
	public void clearDependencies() {
		if (isFormula) { // only clear dependencies if it's a formula instead of
							// constant
			for (String c : parents) {
				Cell parent = sheet.getCell(c);
				parent.children.remove(name); // clear parent
			}
			children = new ArrayList<String>(); // clear child
		}
	}

	/**
	 * recursively calculate
	 */
	public BigDecimal evaluate() {
		if (!isFormula) {
			return value;
		} else {
			for (int i = 0; i < parents.size(); i++) {
				Cell c = sheet.getCell(parents.get(i));
				formula.with(c.name, c.evaluate());
			}
			return formula.eval();
		}
	}
}
