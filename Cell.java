import java.math.BigDecimal;
import java.util.ArrayList;

public class Cell {
	String name;
	BigDecimal value;
	Expression formula;
	Table sheet;
	boolean isFormula;

	ArrayList<String> children;
	ArrayList<String> parents;

	public Cell(Table sheet) {
		this.sheet = sheet;
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

		// set parent's children
		ArrayList<String> ls = getParents(f);

		children = ls;
		for (String p : ls) {
			sheet.getCell(p).parents.add(name);
		}
	}

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

	public void clearDependencies() {
		if (isFormula) {
			for (String c : children) {
				Cell child = sheet.getCell(c);
				child.parents.remove(name); // clear parent
			}
			children = new ArrayList<String>(); // clear child
		}
	}

	public BigDecimal evaluate() {
		if (!isFormula) {
			return value;
		} else {
			for (int i = 0; i < children.size(); i++) {
				Cell c = sheet.getCell(children.get(i));
				formula.with(c.name, c.evaluate());
			}
			return formula.eval();
		}
	}
}
