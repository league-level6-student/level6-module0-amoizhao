package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
    	double hourlyWage = 15.5;
    	int numHours = 3;
    	double expected = 46.5;
        //when
    	double actual = payroll.calculatePaycheck(hourlyWage, numHours);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
    	int milesTraveled = 14;
    	double expected = 8.05;
        //when
    	double actual = payroll.calculateMileageReimbursement(milesTraveled);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
    	String employeeName = "Joeshmo";
    	double hourlyWage = 15.5;
    	String expected = "Hello " + employeeName + ", We are pleased to offer you an hourly wage of " + hourlyWage;
        //when
    	String actual = payroll.createOfferLetter(employeeName, hourlyWage);
        //then
    	assertEquals(expected, actual);
    }

}