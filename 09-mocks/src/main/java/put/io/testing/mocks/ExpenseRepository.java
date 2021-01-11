package put.io.testing.mocks;

import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.database.FancyDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseRepository implements IExpenseRepository {

	List<Expense> expenses;
	IFancyDatabase fancyDatabase;

	public ExpenseRepository(IFancyDatabase obd) {
		this.fancyDatabase = obd;
		expenses = new ArrayList<Expense>();
	}

	@Override
	public List<Expense> getExpenses() {
		return Collections.unmodifiableList(expenses);
	}

	@Override
	public List<Expense> getExpensesByCategory(String category) {
		List<Expense> filteredList = new ArrayList<Expense>();

		for (Expense expense : expenses) {
			if (expense.getCategory().equals(category)) {
				filteredList.add(expense);
			}
		}

		return filteredList;
	}

	@Override
	public void addExpense(Expense expense) {
		expenses.add(expense);
	}

	@Override
	public void deleteExpense(Expense expense) {
		expenses.remove(expense);
	}

	@Override
	public void loadExpenses() {
		fancyDatabase.connect();

		expenses = new ArrayList<Expense>(fancyDatabase.<Expense>queryAll());

		fancyDatabase.close();

	}

	@Override
	public void saveExpenses() {
		fancyDatabase.connect();

		int i = 1;
		for (Expense expense : expenses) {
			fancyDatabase.persist(expense);
		}

		fancyDatabase.close();
	}
}
