package put.io.testing.mocks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.service.FancyService;
import java.net.ConnectException;
import java.util.*;


public class ExpenseManagerTest {

    private static ExpenseRepository ExRe;
    private static FancyService fs;

    @Test
    public void testcalculateTotal()
    {
        ExRe = mock(ExpenseRepository.class);
        fs = mock(FancyService.class);
        List<Expense> xplist = new LinkedList<Expense>();
        for (int i = 0; i < 3; i++)
        {
            Expense xp = new Expense();
            xp.setAmount(2);
            xplist.add(xp);
        }
        when(ExRe.getExpenses()).thenReturn(Collections.unmodifiableList(xplist));
        ExpenseManager ExMan = new ExpenseManager(ExRe, fs);
        long sum = ExMan.calculateTotal();
        assertEquals(sum, 6);
    }

    @Test
    public void calculateTotalForCategoryTest()
    {
        ExRe = mock(ExpenseRepository.class);
        fs = mock(FancyService.class);
        List<Expense> xplist = new LinkedList<Expense>();

        Expense xp = new Expense();
        xp.setAmount(5);
        xp.setCategory("Home");
        xplist.add(xp);

        Expense xp2 = new Expense();
        xp2.setAmount(1);
        xp2.setCategory("Car");
        xplist.add(xp2);

        Expense xp3 = new Expense();
        xp3.setAmount(2);
        xp3.setCategory("Home");
        xplist.add(xp3);

        Expense xp4 = new Expense();
        xp4.setAmount(0);
        xp4.setCategory("Car");
        xplist.add(xp4);

        Expense xp5 = new Expense();
        xp5.setAmount(8);
        xp5.setCategory("Car");
        xplist.add(xp5);

        when(ExRe.getExpenses()).thenReturn(Collections.unmodifiableList(xplist));
        when(ExRe.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(ExRe.getExpensesByCategory("Home")).thenReturn(Arrays.asList(xp,xp3));
        when(ExRe.getExpensesByCategory("Car")).thenReturn(Arrays.asList(xp2,xp4,xp5));

        ExpenseManager ExMan = new ExpenseManager(ExRe, fs);
        assertEquals(ExMan.calculateTotalForCategory("Home"),7);
        assertEquals(ExMan.calculateTotalForCategory("Car"),9);
    }
    @Test
    public void testcalculateTotalInDollars() throws ConnectException
    {
        ExpenseRepository ExRe = mock(ExpenseRepository.class);
        FancyService fs = mock(FancyService.class);
        Expense xp = new Expense();
        xp.setAmount(28);
        List<Expense> Expenses = new ArrayList<Expense>();
        Expenses.add(xp);
        when(ExRe.getExpenses()).thenReturn(Expenses);
        when(fs.convert(anyDouble(), eq("PLN"),eq("USD"))).thenAnswer((Answer) invocation ->
        {
            var args = invocation.getArguments();
            return (double) args[0]/4;
        });
        ExpenseManager ExpMan = new ExpenseManager(ExRe, fs);
        double convert = ExpMan.calculateTotalInDollars();
        verify(fs).convert(anyDouble(), eq("PLN"), eq("USD"));
        assertEquals(7.0, convert);
    }



}
