package sample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;




public class Database {
    private final List<Case> allCases = new ArrayList<>();

    public Database() {
        loadData();
    }

    private void addCase(Case newCase) {
        allCases.add(newCase);
    }

    private void loadData() {
        Case case1 = new Case("Blixten har slagit ner i datorn", Category.ORDER, "2021-06-30");
        case1.addComment("Användaren säger att hon har haft fel på sin dator länge", "user X");
        case1.addComment("Användaren ringer in igen och undrar hur det går", "user X");
        case1.addComment("Tekniker har åtgärdat felet. Bekräftas av användaren", "user X");
        case1.closeCase();
        addCase(case1);


        addCase(new Case("Fel på min dator", Category.HARDWARE_ERROR, "2021-07-01"));
        addCase(new Case("Jag behöver hjälp med mitt system", Category.APPLICATION_SUPPORT, "2021-07-05"));
        addCase(new Case("Mitt system fungerar inte", Category.APPLICATION_ERROR, "2021-06-25"));
        addCase(new Case("Vill beställa ett nytt tangentbord", Category.ORDER, "2021-06-30"));


    }

    public Collection<Case> getCases() {
        return Collections.unmodifiableCollection(allCases);
    }

    public Collection<Case> getCases2() {
        return Collections.unmodifiableCollection(allCases);
    }
}
