package warehouse;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * User: Tomas
 * Date: 12.03.14
 * Time: 1:33
 */
public class WarehouseTest {
    //for simplification tests on Integer values but here can be any comparable type like Data or Calendar
    private Warehouse<Integer> warehouse = new Warehouse<Integer>();

    @Test
    public void testFindMaxCars0() throws Exception {
        Warehouse.Result result = warehouse.findMaxCars();
        assertEquals(0, result.getNumber());
    }

    @Test
    public void testFindMaxCars1() throws Exception {
        warehouse.addCar(1, 2);
        Warehouse<Integer>.Result result = warehouse.findMaxCars();
        assertEquals(1, result.getNumber());
        assertThat(result.getIntervals(), contains(warehouse.new Interval(1, 2)));
    }

    @Test
    public void testFindMaxCars1_2() throws Exception {
        warehouse.addCar(1, 2);
        warehouse.addCar(3, 4);
        Warehouse<Integer>.Result result = warehouse.findMaxCars();
        assertEquals(1, result.getNumber());
        assertThat(result.getIntervals(), contains(warehouse.new Interval(1, 2), warehouse.new Interval(3, 4)));
    }

    @Test
    public void testFindMaxCars1_3() throws Exception {
        warehouse.addCar(1, 2);
        warehouse.addCar(2, 3);
        Warehouse<Integer>.Result result = warehouse.findMaxCars();
        assertEquals(1, result.getNumber());
        assertThat(result.getIntervals(), contains(warehouse.new Interval(1, 3)));
    }

    @Test
    public void testFindMaxCars2() throws Exception {
        warehouse.addCar(1, 4);
        warehouse.addCar(2, 3);
        Warehouse<Integer>.Result result = warehouse.findMaxCars();
        assertEquals(2, result.getNumber());
        assertThat(result.getIntervals(), contains(warehouse.new Interval(2, 3)));
    }

    @Test
    public void testFindMaxCars2_1() throws Exception {
        warehouse.addCar(1, 5);
        warehouse.addCar(2, 3);
        warehouse.addCar(4, 6);
        Warehouse<Integer>.Result result = warehouse.findMaxCars();
        assertEquals(2, result.getNumber());
        assertThat(result.getIntervals(), contains(warehouse.new Interval(2, 3), warehouse.new Interval(4, 5)));
    }

    @Test
    public void testFindMaxCars2_2() throws Exception {
        warehouse.addCar(1, 5);
        warehouse.addCar(1, 3);
        Warehouse<Integer>.Result result = warehouse.findMaxCars();
        assertEquals(2, result.getNumber());
        assertThat(result.getIntervals(), contains(warehouse.new Interval(1, 3)));
    }

    @Test
    public void testFindMaxCars2_3() throws Exception {
        warehouse.addCar(1, 5);
        warehouse.addCar(1, 5);
        Warehouse<Integer>.Result result = warehouse.findMaxCars();
        assertEquals(2, result.getNumber());
        assertThat(result.getIntervals(), contains(warehouse.new Interval(1, 5)));
    }

    @Test
    public void testFindMaxCars2_4() throws Exception {
        warehouse.addCar(2, 5);
        warehouse.addCar(1, 5);
        Warehouse<Integer>.Result result = warehouse.findMaxCars();
        assertEquals(2, result.getNumber());
        assertThat(result.getIntervals(), contains(warehouse.new Interval(2, 5)));
    }

    @Test
    public void testFindMaxCars3() throws Exception {
        warehouse.addCar(1, 6);
        warehouse.addCar(2, 3);
        warehouse.addCar(4, 8);
        warehouse.addCar(5, 7);
        Warehouse<Integer>.Result result = warehouse.findMaxCars();
        assertEquals(3, result.getNumber());
        assertThat(result.getIntervals(), contains(warehouse.new Interval(5, 6)));
    }

}
