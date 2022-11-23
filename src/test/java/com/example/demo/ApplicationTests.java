package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.Application;

@SpringBootTest
class ApplicationTests {

	@Test
	void main() {
        Application.main(new String[] {});
    }

}
