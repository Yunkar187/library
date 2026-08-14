package yunkar.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "yunkar")
@EntityScan(basePackages = "yunkar")
@EnableJpaRepositories(basePackages = "yunkar")
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }
    @Bean
    public CommandLineRunner checkBean(ApplicationContext ctx) {
        return args -> {
            String targetBean = "authorService"; // Thay thế bằng tên Bean cần tìm
            boolean isLoaded = ctx.containsBean(targetBean);

            System.out.println("========== KIỂM TRA BEAN ==========");
            System.out.println("Bean [" + targetBean + "] khởi tạo chưa? -> " + (isLoaded ? "RỒI (THÀNH CÔNG)" : "CHƯA"));
            System.out.println("===================================");
        };
    }
}
