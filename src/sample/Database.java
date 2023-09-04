package sample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;



// MOCK DATABASE CLASS
public class Database {
    private final List<Case> allCases = new ArrayList<>();

    public Database() {
        loadData();
    }

    private void addCase(Case newCase) {
        allCases.add(newCase);
    }

    private void loadData() {
        Case case1 = new Case("Lightning hit my PC", Category.ORDER, "2021-06-30");
        case1.addComment("User says her PC been broken for some time", "user X");
        case1.addComment("User is calling back requesting status", "user X");
        case1.addComment("The case has been resolved by technician", "user X");
        case1.closeCase();
        addCase(case1);


        addCase(new Case("My PC is broken!", Category.HARDWARE_ERROR, "2021-07-01"));
        addCase(new Case("I need help with the new IT system", Category.APPLICATION_SUPPORT, "2021-07-05"));
        addCase(new Case("My order system doesn't work!", Category.APPLICATION_ERROR, "2021-06-25"));
        addCase(new Case("want to order a new keyboard", Category.ORDER, "2021-06-30"));


    }

    public Collection<Case> getCases() {
        return Collections.unmodifiableCollection(allCases);
    }

    public Collection<Case> getCases2() {
        return Collections.unmodifiableCollection(allCases);
    }
}
