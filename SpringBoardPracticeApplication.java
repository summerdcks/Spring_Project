package kr.ac.kopo.ctc.kopo36.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class SpringBoardPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoardPracticeApplication.class, args);
	}

}
