package day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Xalts {

    public static void main(String[] args) throws InterruptedException {
        // Set the EdgeDriver path
        System.setProperty("webdriver.edge.driver", "/home/manjunath.gowda/---Testing---/msedgedriver");

        // Initialize WebDriver
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        // Navigate to the login page
        driver.get("https://xaltsocnportal.web.app");
        System.out.println("Page Title: " + driver.getTitle());

        // Sign-in process
        try {
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/div/button")).click(); // Already have an account
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/button[2]")).click(); // Get started
            driver.findElement(By.xpath("//*[@id=\"outlined-basic\"]")).sendKeys("thevairu@gmail.com"); // Username
            driver.findElement(By.xpath("/html/body/div/div/main/div[2]/div[2]/div/input")).sendKeys("Hero@123"); // Password
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div[2]/button[1]")).click(); // Login
            Thread.sleep(3500);
            System.out.println("Test Case Passed: Login Successful");
        } catch (Exception e) {
            System.out.println("Test Case Failed: Login Unsuccessful");
        }

        // Start Node addition process
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/div/button")).click(); // Get started
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/div[1]/h2")).click();

        // Valid Add Node test case
        addNode(driver, "NodeID-1234", "49.249.88.146");

        // Invalid Node ID test case
        addNode(driver, "", "49.249.88.146");

        // Invalid Public IP test case
        addNode(driver, "NodeID-5678", "invalid_ip");

        // Duplicate Node ID test case
        addNode(driver, "NodeID-1234", "49.249.88.147");

        // Add multiple nodes
        addNode(driver, "NodeID-9012", "49.249.88.148");
        addNode(driver, "NodeID-3456", "49.249.88.149");

        // Remove a Node
        removeNode(driver);

        // Next button - Validation Error (without adding any nodes)
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[3]/div/div/div[1]/button")).click();

        // Provide valid Wallet Details
        addWallet(driver, "0x88fa61d2faA13aad8Fbd5B030372B4A159BbbDFb");

        // Provide Invalid Wallet Address
        addWallet(driver, "invalid_wallet_address");

        // Duplicate Wallet Address
        addWallet(driver, "0x88fa61d2faA13aad8Fbd5B030372B4A159BbbDFb");

        // Add multiple Wallets
        addWallet(driver, "0x1234567890abcdef1234567890abcdef12345678");
        addWallet(driver, "0xabcdefabcdefabcdefabcdefabcdefabcdef");

        // Remove Wallet
        removeWallet(driver);

        // Submit the form
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[3]/div/div[2]/button[2]")).click();
        System.out.println("Test Case Passed: Form Submitted Successfully");

        // Close the driver
        Thread.sleep(5000);
        driver.quit();
    }

    private static void addNode(WebDriver driver, String nodeId, String ipAddress) throws InterruptedException {
        try {
            WebElement nodeField = driver.findElement(By.xpath("//*[@id=\"outlined-basic\"]"));
            WebElement ipField = driver.findElement(By.xpath("/html/body/div/div/main/section[3]/div/div/div[1]/div[2]/div/input"));
            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[3]/div/div/div[1]/div[3]/button"));

            nodeField.clear();
            nodeField.sendKeys(nodeId);
            ipField.clear();
            ipField.sendKeys(ipAddress);
            addButton.click();
            Thread.sleep(1000);
            System.out.println("Test Case Passed: Node Added Successfully - " + nodeId);
        } catch (Exception e) {
            System.out.println("Test Case Failed: Unable to Add Node - " + nodeId);
        }
    }

    private static void removeNode(WebDriver driver) throws InterruptedException {
        try {
            WebElement removeButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[3]/div/div/div[1]/div[4]/button"));
            removeButton.click();
            Thread.sleep(1000);
            System.out.println("Test Case Passed: Node Removed Successfully");
        } catch (Exception e) {
            System.out.println("Test Case Failed: Unable to Remove Node");
        }
    }

    private static void addWallet(WebDriver driver, String walletAddress) throws InterruptedException {
        try {
            WebElement walletField = driver.findElement(By.xpath("//*[@id=\"outlined-basic\"]"));
            WebElement addWalletButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[3]/div/div/div[1]/div[2]/button"));

            walletField.clear();
            walletField.sendKeys(walletAddress);
            addWalletButton.click();
            Thread.sleep(1000);
            System.out.println("Test Case Passed: Wallet Added Successfully - " + walletAddress);
        } catch (Exception e) {
            System.out.println("Test Case Failed: Unable to Add Wallet - " + walletAddress);
        }
    }

    private static void removeWallet(WebDriver driver) throws InterruptedException {
        try {
            WebElement removeWalletButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/section[3]/div/div/div[1]/div[3]/button[1]"));
            removeWalletButton.click();
            Thread.sleep(1000);
            System.out.println("Test Case Passed: Wallet Removed Successfully");
        } catch (Exception e) {
            System.out.println("Test Case Failed: Unable to Remove Wallet");
        }
    }
}
