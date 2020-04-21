package ua.knu.fit.mit31.dbis;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbisApplication implements CommandLineRunner {

    @Autowired
    NewTableRepository newTableRepository;

    public static void main(String[] args) {
        SpringApplication.run(DbisApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {

        System.out.println("Begin");
        newTableRepository.save(new NewTable("Roman", 17));
        newTableRepository.save(new NewTable("Ivan", 27));
        newTableRepository.save(new NewTable("Stepan", 23));
        newTableRepository.save(new NewTable("Olena", 19));
        newTableRepository.save(new NewTable("Marija", 22));

        System.out.println(newTableRepository.findAll());
        System.out.println(newTableRepository.findAllolder20());
        System.out.println(newTableRepository.findAllolder(22));

        System.out.println("End");

    }

}
