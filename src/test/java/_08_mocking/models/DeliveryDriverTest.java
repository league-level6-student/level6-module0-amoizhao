package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import _07_intro_to_mocking.models.GasTank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;
    
    @Mock
    CellPhone cellPhone;
    
    @Mock
    Car car;
    
    private String name = "bob";
    private String number = "000000000";

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	deliveryDriver = new DeliveryDriver(name, car, cellPhone);
    }

    @Test
    void itShouldWasteTime() {
        //given
    	boolean expectedWaste = true;
        //when
    	when(cellPhone.browseCatMemes()).thenReturn(true);
        //then
    	boolean actualWaste = deliveryDriver.wasteTime();
    	assertEquals(expectedWaste, actualWaste);
    }

    @Test
    void itShouldRefuel() {
    	//given
    	boolean expectedRefuel = true;
    	int octane = 85;
        //when
    	when(car.fillTank(octane)).thenReturn(true);
    	boolean actualRefuel = deliveryDriver.refuel(octane);
        //then
    	assertEquals(expectedRefuel, actualRefuel);
    }

    @Test
    void itShouldContactCustomer() {
    	//given
    	boolean expectedContact = true;
        //when
    	when(cellPhone.call(number)).thenReturn(true);
    	boolean actualContact = deliveryDriver.contactCustomer(number);
        //then
    	assertEquals(expectedContact, actualContact);
    }

}