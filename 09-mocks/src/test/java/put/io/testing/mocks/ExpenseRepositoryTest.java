package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.mockito.*;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class ExpenseRepositoryTest {

    @Test
    public void testloadExpences()
    {
        ExpenseRepository ExRe = new ExpenseRepository(new MyDatabase());
        ExRe.loadExpenses();
        assertEquals(ExRe.getExpenses().size(), 0);
    }

    @Test
    public void testloadExpences2()
    {
        IFancyDatabase mockObj = mock(IFancyDatabase.class);
        when(mockObj.queryAll()).thenReturn(Collections.emptyList());
        ExpenseRepository ExRe = new ExpenseRepository(mockObj);
        ExRe.loadExpenses();
        InOrder io = inOrder(mockObj);

        verify(mockObj).connect();
        verify(mockObj).queryAll();
        verify(mockObj).close();
        io.verify(mockObj).connect();
        io.verify(mockObj).queryAll();
        io.verify(mockObj).close();

        assertEquals(ExRe.getExpenses().size(), 0);
    }

    @Test
    public void testsaveExpences() {
        IFancyDatabase mockObj = mock(IFancyDatabase.class);
        when(mockObj.queryAll()).thenReturn(Collections.emptyList());
        ExpenseRepository ExRe = new ExpenseRepository(mockObj);
        ExRe.loadExpenses();
        for(int i=0; i<5; ++i)
        {
            Expense Exp = new Expense();
            ExRe.addExpense(Exp);
        }
        ExRe.saveExpenses();
        verify(mockObj,times(5)).persist(any(Expense.class));

    }

}
