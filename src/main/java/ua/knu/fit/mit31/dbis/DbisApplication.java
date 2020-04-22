package ua.knu.fit.mit31.dbis;

import java.util.HashSet;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbisApplication implements CommandLineRunner {

    @Autowired
    NewTableRepository newTableRepository;

    @Autowired
    ChildTableRepository childTableRepository;

    public static void main(String[] args) {
        SpringApplication.run(DbisApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {

        System.out.println("======= Begin =======");

        System.out.println("======= NewTable populating =======");

        NewTable roman = new NewTable("Roman", 37);
        NewTable stepan = new NewTable("Stepan", 23);

        newTableRepository.save(new NewTable("Ivan", 27));
        newTableRepository.save(new NewTable("Olena", 19));
        newTableRepository.save(new NewTable("Marija", 22));
        newTableRepository.save(roman);
        newTableRepository.save(stepan);

        System.out.println("======= ChildTable populating =======");
        ChildTable vasylko = new ChildTable("Vasylko", 4, stepan);
        ChildTable olenka = new ChildTable("Olenka", 3, roman);
        ChildTable ivasyk = new ChildTable("Ivasyk", 5, roman);

        childTableRepository.save(vasylko);
        childTableRepository.save(olenka);
        childTableRepository.save(ivasyk);

        System.out.println("======= Tables Relations Creating =======");

        roman.setChildTable(new HashSet<ChildTable>() {
            {
                add(ivasyk);
                add(olenka);
            }
        });
        stepan.setChildTable(new HashSet<ChildTable>() {
            {
                add(vasylko);
            }
        });

        System.out.println("======= Showing Result =======");

        System.out.println(newTableRepository.findAll());

        System.out.println(newTableRepository.findAllOlder20());

        System.out.println(newTableRepository.findAllOlder(22));

        System.out.println(childTableRepository.findAll());

        System.out.println(newTableRepository.findParents());
        System.out.println(newTableRepository.findByChildrenAmount(1));

        System.out.println("======= End =======");

    }

}
