package com.wang.luck.draw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LuckDrawApplicationTests {

	@Test
	public void contextLoads() {
		Random ra =new Random();
		for (int i=0;i<30;i++){
			System.out.println( ra.nextInt(10) );
		}

		/*System.out.println( (int)(Math.random() * 5) );
		System.out.println( (int)(Math.random() * 5) );
		System.out.println( (int)(Math.random() * 5) );
		System.out.println( (int)(Math.random() * 5) );
		System.out.println( (int)(Math.random() * 5) );
		System.out.println( (int)(Math.random() * 5) );
		System.out.println( (int)(Math.random() * 5) );
		System.out.println( (int)(Math.random() * 5) );
		System.out.println( (int)(Math.random() * 5) );
		System.out.println( (int)(Math.random() * 5) );*/

	}

}
