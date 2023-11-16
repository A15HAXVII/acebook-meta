package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;
import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.openqa.selenium.WebElement;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PostTest {
	WebDriver driver;
	Faker faker;
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		driver = new ChromeDriver();
		faker = new Faker();
	}
	@After
	public void tearDown() {
		driver.close();
	}

	private Post post = new Post("hello");

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}
	//	Test post form creates new post displayed on posts index page
	@Test
	public void createPostFormUpdatesPostsIndexPage() {

	}

	//	Test user picture and name displays on their post
//	@Test
//	public void userPictureAndNameDisplaysWithPost() {
//		User exampleUser = new User("charlieDude12", "passwordlol");
//		createuser, createpost
//		Post firstPost = new Post("Yo it's your neighbourhood Charlie now on Acebook!");
//		driver.get("http://localhost:8080/posts"); // render post index page
//		// Find the element with class post
//		WebElement postElement = driver.findElement(By.className("post"));
//		// Find the post child element with class username
//		WebElement usernameElement = postElement.findElement(By.className("username"));
//		// Find the post child element with class profile picture
//		WebElement profilePictureElement = postElement.findElement(By.className("profile-picture"));
//		// Assert that the username element has the value "charlie"
//		assertEquals("charlieDude12", usernameElement.getText());
//		// Assert that the profile picture element has the value "gravitar.com/charlie.png"
//		assertEquals("https://gravitar.com/charlie.png", profilePictureElement.getAttribute("src"));
//	}
}



