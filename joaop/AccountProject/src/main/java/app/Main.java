package app;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
@NamedQueries({
        @NamedQuery(name = "AccountProject_SelectAllTest", query = "Select acc from Account acc")
})
public class Main {
    public static void main(String[] args) {

    }
}