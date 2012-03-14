package org.talend.mdm.modules;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.talend.mdm.Base;


public class User extends Base{
	
	public User(WebDriver driver) {
		super.setDriver(driver);
		this.driver = driver;
	}
	
	protected void gotoUserManagePage(){
		this.clickElementByXpath("//span[text()='Administration']");
		this.clickElementByXpath("//span[text()='Manage Users']");
		Assert.assertTrue("Go to User Manage page", this.isElementPresent(By.xpath(locator.getString("xpath.user.button.add")), this.WAIT_TIME_MAX));
	}
	
	protected void addUser(String identifier,String firstName,String lastName,String password,String confirmPassword,
			String email, String company, String defaultVersion, boolean active, String[] roles) {
		this.clickElementByXpath(locator.getString("xpath.user.button.add"));
		this.typeTextByName(locator.getString("name.user.add.name"), identifier);
		this.typeTextByName(locator.getString("name.user.add.password"), password);
		this.typeTextByXpath(locator.getString("xpath.user.add.password.confirm"), confirmPassword);
		this.typeTextByName(locator.getString("name.user.add.givenName"), firstName);
		this.typeTextByName(locator.getString("name.user.add.familyName"), lastName);
		this.typeTextByName(locator.getString("name.user.add.realEmail"), email);
		this.typeTextByName(locator.getString("name.user.add.company"), company);
		this.typeTextByName(locator.getString("name.user.add.universe"), defaultVersion);
		if(active) {
			this.getElementByName(locator.getString("name.user.add.enabled")).click();
		}
		for(String role: roles) {
			this.clickElementByXpath(locator.getString("xpath.user.add.role.img"));
			this.clickElementByXpath(this.getString(locator, "xpath.user.add.role.select", role));
			this.clickElementByXpath(locator.getString("xpath.user.add.role.add"));
		}
		this.clickElementByXpath("//table[contains(@id,'usermanager')]//button[text()='Save']");
		this.getElementByXpath("//button[text()='Ok']").click();
		Assert.assertNotNull("Haven't add the user " + identifier + "successfully!", getUserDeleteElement(identifier));
	}
	
	public void deleteUser(String userName) {
		this.clickElementByXpath(this.getString(locator, "xpath.user.delete", userName));
		this.clickElementByXpath(locator.getString("xpath.user.delete.yes"));
	}
	

	public void searchUser(String userName) {
		this.modifyTextByXpath(locator.getString("xpath.user.search.input"), userName);
		this.clickElementByXpath(locator.getString("xpath.user.search"));
	}
	
	public void clickAddNewUser(){
		this.clickElementByXpath(locator.getString("xpath.user.button.add"));
	}
	public  WebElement getUserDeleteElement(String userName) {
		return	this.getElementByXpath(this.getString(locator, "xpath.user.delete", userName));		 
	}
	
	public void typeUserName(String userName) {
		this.typeTextByName(locator.getString("name.user.add.name"), userName);
	}
	public void typePassword(String password) {
		this.typeTextByName(locator.getString("name.user.add.password"), password);
	}
	public void typeConfirmPassword(String password) {
		this.typeTextByXpath(locator.getString("xpath.user.add.password.confirm"), password);
	}
	public void typeFirstName(String firstName) {
		this.typeTextByName(locator.getString("name.user.add.givenName"), firstName);
	}
	public void typeLastName(String lastName) {
		this.typeTextByName(locator.getString("name.user.add.familyName"), lastName);
	}
	public void typeEmail(String email) {
		this.typeTextByName(locator.getString("name.user.add.realEmail"), email);
	}
	public void typeCompany(String company) {
		this.typeTextByName(locator.getString("name.user.add.company"), company);
	}
	public void typeUniVerse(String universe) {
		this.typeTextByName(locator.getString("name.user.add.universe"), universe);
	}
	
	public void clickActive() {
		this.getElementByName(locator.getString("name.user.add.enabled")).click();
	}
	public boolean isActive(){
		return this.getElementByXpath(locator.getString("xpath.user.add.enabled.true")) != null;
	}
	
	public void addRoles(String[] roles) {
		for(String role: roles) {
			this.clickElementByXpath(locator.getString("xpath.user.add.role.img"));
			this.clickElementByXpath(this.getString(locator, "xpath.user.add.role.select", role));
			this.clickElementByXpath(locator.getString("xpath.user.add.role.add"));
		}
	}
	public void deleteRoles(String[] roles) {
		for(String role: roles) {
			this.clickElementByXpath(this.getString(locator, "xpath.user.delete.role.select", role));
			this.clickElementByXpath(locator.getString("xpath.user.delete.role.delete"));
		}
	}
	
	// unfinished
	public List<String> getRoles(){
		List<String> roles = new ArrayList<String>();
		List<WebElement> elements = this.getElementsByXpath(locator.getString("xpath.user.added.roles"));
		
		for(WebElement element : elements) {
			roles.add(element.getText());
		}
		return roles;
	}

	
	public void clickSave(){
		this.clickElementByXpath(locator.getString("xpath.user.add.role.save"));
	}
	public void selectAUser(String userName) {
		this.clickElementByXpath(this.getString(locator, "xpath.user.select", userName));
	}
	
	public boolean isUserSheetExist(){
		return this.getElementsByXpath("//span[contains(@class, 'x-tab-strip-text') and text()='User Manager']") != null;
	}
}