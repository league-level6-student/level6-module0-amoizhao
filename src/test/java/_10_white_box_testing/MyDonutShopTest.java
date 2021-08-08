package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MyDonutShopTest {

    MyDonutShop myDonutShop;

    @Mock
    BakeryService bakeryService;
    
    @Mock
    PaymentService paymentService;
    
    @Mock
    DeliveryService deliveryService;
    
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
    	Order order = new Order("bob",
                "321",
                1,
                5.00,
                "123",
                true);

    	when(bakeryService.getDonutsRemaining()).thenReturn(2);
    	when(paymentService.charge(order)).thenReturn(true);
    	
        //when
    	myDonutShop.openForTheDay();
    	myDonutShop.takeOrder(order);
    	
        //then
    	verify(deliveryService, times(1)).scheduleDelivery(order);
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given

        //when

        //then
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given

        //when

        //then
    }

}