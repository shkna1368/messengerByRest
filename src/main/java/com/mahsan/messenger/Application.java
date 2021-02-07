package com.mahsan.messenger;


import com.mahsan.messenger.mapper.MessageMapper;
import com.mahsan.messenger.utils.Seeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@SpringBootApplication
@EnableJpaAuditing
public class Application implements ApplicationRunner {


@Autowired
    Seeder seeder;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }


    @Override
    public void run(ApplicationArguments args)  {
        seeder.addUser();
      }



}
