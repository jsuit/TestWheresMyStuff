import static org.junit.Assert.assertSame;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.example.wheresmystuff.validation.ValidatePassword;


public class TestValidatePassword {


	ValidatePassword test;
	@Before
	public void setUp(){
		test =  new ValidatePassword();
	}
	
	@Test
	public void nullString(){
		assertSame("Should be Invalid", "Invalid Password. Need a Password\n", ValidatePassword.validate(null, null));
		
	}
	
	@Test
	public void emptyString(){
		assertSame("Should be Invalid", "Invalid Password. Need a Password\n", ValidatePassword.validate("", ""));
	}
	
	@Test
	public void differentStrings(){
		assertSame("Should be Invalid", "Invalid Password. Passwords don't match.\n", ValidatePassword.validate("abcdefadjfndj", "riidnidjfidjfdf"));
	}
	
	@Test
	public void differentStringsWithNumbers(){
		assertSame("Should be Invalid", "Invalid Password. Passwords don't match.\n", ValidatePassword.validate("abcdefadjfndj2", "riidnid3jfidjfdf"));
	}
	
	@Test
	public void tooShort(){
		assertSame("Should be Invalid", "Invalid Password. Need 7 characters with at least one number\n", ValidatePassword.validate("fdfd", "fdfd"));
	}
	@Test
	public void noNumbers(){
		assertSame("Should be Invalid", "Invalid Password. No Numbers.", ValidatePassword.validate("asdfghij", "asdfghij"));
	}
	
	@Test
	public void allNumbers(){
		assertSame("Should be Invalid", "Invalid Password. Can't be all numbers.\n", ValidatePassword.validate("123456789", "123456789"));
	}
	
	@Test
	public void tooLong(){
		Random r = new Random();
		StringBuffer b = new StringBuffer(200);
		for (int j = 0; j < 200; j++) {
			b.append(Integer.toString(r.nextInt()));
		}
		String i = b.toString();
		assertSame("Should be Invalid", "Invalid Password. Too Long.\n", ValidatePassword.validate(i, i));
	}
	@Test
	public void oneShort(){
		String i = "1asdf";
		assertSame("Should be Invalid", "Invalid Password. Need 7 characters with at least one number\n", ValidatePassword.validate(i, i));	
	}
	
	@Test
	public void oneTooLong(){
		Random r = new Random();
		StringBuffer b = new StringBuffer(101);
		for (int j = 0; j < 11; j++) {
			b.append(Integer.toString(r.nextInt()));
		}
		b.setLength(101);
		String i = b.toString();
		assertSame("Should be Invalid", "Invalid Password. Too Long.\n", ValidatePassword.validate(i, i));	
	}
	
	@Test
	public void justRight(){
		String i = "1asdfgh";
		assertSame("Should be Valid", null, ValidatePassword.validate(i, i));	
	}
}


